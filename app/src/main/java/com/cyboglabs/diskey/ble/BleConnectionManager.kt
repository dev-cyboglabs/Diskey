package com.cyboglabs.diskey.ble

import android.bluetooth.BluetoothAdapter
import android.content.Context
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.model.BlePacket
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.domain.model.HandshakePacket
import com.cyboglabs.diskey.utils.getStrOrNull
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Orchestrates the full BLE connection lifecycle:
 * scan → connect → discover → notify → handshake → connected
 *
 * Provides exponential-backoff reconnect on unexpected disconnection.
 */
@Singleton
class BleConnectionManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter,
    private val bleDeviceManager: BleDeviceManager,
    private val appPreferences: AppPreferences,
    private val gson: Gson
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private enum class HandshakeStage {
        WAITING_HELLO,
        WAITING_STATUS
    }

    @Volatile private var handshakeStage: HandshakeStage? = null
    @Volatile private var handshakeDeferred: CompletableDeferred<Boolean>? = null

    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _packets = MutableSharedFlow<BlePacket>(
        extraBufferCapacity = 2048,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val packets: SharedFlow<BlePacket> = _packets.asSharedFlow()

    private val _batteryLevel = MutableStateFlow(-1)
    val batteryLevel: StateFlow<Int> = _batteryLevel.asStateFlow()

    private var targetAddress: String? = null
    private var reconnectAttempts = 0

    init {
        // Set up disconnection callback for physical disconnections
        bleDeviceManager.onDeviceDisconnected = {
            scope.launch {
                Timber.w("BleConnectionManager: Physical disconnection detected")
                _connectionState.value = ConnectionState.DISCONNECTED
                targetAddress = null
                clearHandshake()
            }
        }
    }

    init {
        // Forward packets from the BLE manager channel into the shared flow
        scope.launch {
            bleDeviceManager.packetChannel.consumeEach { packet ->
                handleHandshakePacket(packet)
                handleInboundPacket(packet)
                _packets.tryEmit(packet)
            }
        }
    }

    suspend fun connect(address: String) {
        targetAddress = address
        reconnectAttempts = 0
        doConnect(address)
    }

    private suspend fun doConnect(address: String) {
        _connectionState.value = ConnectionState.CONNECTING
        Timber.i("BleConnectionManager: ========== CONNECTING TO $address ==========")

        val device = runCatching { bluetoothAdapter.getRemoteDevice(address) }.getOrNull() ?: run {
            Timber.e("BleConnectionManager: invalid address $address")
            _connectionState.value = ConnectionState.ERROR
            return
        }

        _connectionState.value = ConnectionState.CONNECTING

        bleDeviceManager.connect(device)
            .timeout(BleConstants.CONNECT_TIMEOUT_MS)
            .retry(BleConstants.MAX_RETRY_COUNT, BleConstants.RECONNECT_DELAY_MS.toInt())
            .useAutoConnect(false)
            .done {
                Timber.i("BleConnectionManager: ✓ GATT connected, discovering services")
                _connectionState.value = ConnectionState.DISCOVERING_SERVICES
            }
            .fail { _, status ->
                Timber.e("BleConnectionManager: ✗ Connect failed, status=$status")
                _connectionState.value = ConnectionState.ERROR
                scheduleReconnect()
            }
            .await()

        // Prepare handshake listening before notifications complete; device can send hello immediately.
        prepareHandshake()

        // Wait for characteristics to be ready and notifications enabled
        Timber.d("BleConnectionManager: Waiting for notifications to be enabled...")
        _connectionState.value = ConnectionState.ENABLING_NOTIFICATIONS

        // Wait for the connection-ready signal (notifications enabled)
        var waited = 0L
        while (!bleDeviceManager.connectionReady.value && waited < BleConstants.CONNECT_TIMEOUT_MS) {
            delay(100)
            waited += 100
        }
        if (!bleDeviceManager.connectionReady.value) {
            Timber.e("BleConnectionManager: ✗ Notifications not enabled in time (waited ${waited}ms)")
            _connectionState.value = ConnectionState.ERROR
            clearHandshake()
            return
        }
        Timber.i("BleConnectionManager: ✓ Notifications enabled")

        // Perform handshake
        Timber.d("BleConnectionManager: Starting handshake...")
        _connectionState.value = ConnectionState.HANDSHAKING
        performHandshake()
    }

    private suspend fun performHandshake() {
        Timber.d("BleConnectionManager: waiting for device handshake hello")
        // The device sends the hello packet spontaneously; we listen for it
        val deferred = handshakeDeferred
        if (deferred == null) {
            Timber.e("BleConnectionManager: handshake not prepared; aborting")
            _connectionState.value = ConnectionState.ERROR
            return
        }

        val ok = withTimeoutOrNull(BleConstants.HANDSHAKE_TIMEOUT_MS) { deferred.await() } ?: false

        clearHandshake()

        if (ok) {
            _connectionState.value = ConnectionState.CONNECTED
            reconnectAttempts = 0
            Timber.i("BleConnectionManager: handshake complete, fully connected")
        } else {
            Timber.e("BleConnectionManager: handshake failed or timed out")
            _connectionState.value = ConnectionState.ERROR
            scheduleReconnect()
        }
    }

    private suspend fun handleDeviceHello(packet: HandshakePacket) {
        try {
            val json = gson.fromJson(packet.json, JsonObject::class.java)
            val deviceUuid = json.get("uuid")?.asString ?: ""
            Timber.d("BleConnectionManager: device UUID = $deviceUuid")
            appPreferences.saveDeviceUuid(deviceUuid)

            // Send app response
            val timestamp = System.currentTimeMillis() / 1000L
            val response = PacketBuilder.handshakeResponse(timestamp, BleConstants.ANDROID_APP_UUID)
            bleDeviceManager.sendCommand(response)

            // Next expected packet from device is handshake status (0x02)
            handshakeStage = HandshakeStage.WAITING_STATUS
        } catch (e: Exception) {
            Timber.e(e, "BleConnectionManager: handshake parse error")
            handshakeDeferred?.complete(false)
        }
    }

    private fun prepareHandshake() {
        handshakeDeferred?.cancel()
        handshakeDeferred = CompletableDeferred()
        handshakeStage = HandshakeStage.WAITING_HELLO
    }

    private fun clearHandshake() {
        handshakeDeferred = null
        handshakeStage = null
    }

    private suspend fun handleHandshakePacket(packet: BlePacket) {
        val stage = handshakeStage ?: return
        val deferred = handshakeDeferred ?: return
        if (!deferred.isActive) return
        if (_connectionState.value != ConnectionState.HANDSHAKING && stage != HandshakeStage.WAITING_HELLO) return

        if (packet is HandshakePacket) {
            when (stage) {
                HandshakeStage.WAITING_HELLO -> {
                    // Hello carries the device uuid JSON and success=false.
                    if (!packet.success && packet.json.contains("\"uuid\"")) {
                        handleDeviceHello(packet)
                    }
                }
                HandshakeStage.WAITING_STATUS -> {
                    if (packet.success) {
                        deferred.complete(true)
                    } else if (!packet.json.contains("\"uuid\"")) {
                        deferred.complete(false)
                    }
                }
            }
        }
    }

    private fun handleInboundPacket(packet: BlePacket) {
        // Battery level is globally interesting; update it here
        if (packet is com.cyboglabs.diskey.domain.model.BatteryPacket) {
            _batteryLevel.value = packet.levelPercent
        }
    }

    private fun scheduleReconnect() {
        val target = targetAddress ?: return
        val delay = (BleConstants.RECONNECT_DELAY_MS * (1 shl reconnectAttempts.coerceAtMost(4)))
            .coerceAtMost(BleConstants.MAX_RECONNECT_DELAY_MS)
        reconnectAttempts++
        Timber.d("BleConnectionManager: reconnect in ${delay}ms (attempt $reconnectAttempts)")
        _connectionState.value = ConnectionState.RECONNECTING
        scope.launch {
            delay(delay)
            doConnect(target)
        }
    }

    suspend fun disconnect() {
        targetAddress = null
        clearHandshake()
        bleDeviceManager.disconnect().await()
        _connectionState.value = ConnectionState.DISCONNECTED
        Timber.d("BleConnectionManager: disconnected")
    }

    fun sendCommand(bytes: ByteArray) = bleDeviceManager.sendCommand(bytes)

    fun deleteFile(filename: String) {
        scope.launch {
            try {
                val packet = PacketBuilder.buildDeleteFilePacket(filename)
                bleDeviceManager.sendCommand(packet)
                Timber.d("BleConnectionManager: sent delete command for '$filename'")
            } catch (e: Exception) {
                Timber.e(e, "BleConnectionManager: failed to send delete command for '$filename'")
            }
        }
    }
}
