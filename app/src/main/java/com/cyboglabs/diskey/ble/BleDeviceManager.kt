package com.cyboglabs.diskey.ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import com.cyboglabs.diskey.ble.protocol.PacketParser
import com.cyboglabs.diskey.domain.model.BlePacket
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import no.nordicsemi.android.ble.BleManager
import no.nordicsemi.android.ble.data.Data
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Nordic BLE Manager for T240 device communication.
 * Handles service discovery, notifications, and write operations.
 */
@Singleton
class BleDeviceManager @Inject constructor(
    @ApplicationContext context: Context
) : BleManager(context) {

    private var writeCharacteristic: BluetoothGattCharacteristic? = null
    private var notifyCharacteristic: BluetoothGattCharacteristic? = null

    // Inbound packet channel — callers collect from packetFlow
    val packetChannel = Channel<BlePacket>(capacity = Channel.UNLIMITED)

    private val _mtu = MutableStateFlow(BleConstants.DEFAULT_MTU)
    val mtu: StateFlow<Int> = _mtu.asStateFlow()

    private val _connectionReady = MutableStateFlow(false)
    val connectionReady: StateFlow<Boolean> = _connectionReady.asStateFlow()

    // Callback for device disconnection (services invalidated)
    var onDeviceDisconnected: (() -> Unit)? = null

    // ─── BleManager overrides ───────────────────────────────────────────────

    private var _gattCallback: BleManagerGattCallback? = null
    
    override fun getGattCallback(): BleManagerGattCallback {
        if (_gattCallback == null) {
            _gattCallback = object : BleManagerGattCallback() {

        override fun isRequiredServiceSupported(gatt: BluetoothGatt): Boolean {
            Timber.d("BleDeviceManager: isRequiredServiceSupported called")
            val service = gatt.getService(BleConstants.SERVICE_UUID)
            if (service == null) {
                Timber.e("BleDeviceManager: T240 service not found! UUID=${BleConstants.SERVICE_UUID}")
                Timber.d("BleDeviceManager: Available services: ${gatt.services.map { it.uuid }}")
                return false
            }
            Timber.d("BleDeviceManager: T240 service found")
            writeCharacteristic = service.getCharacteristic(BleConstants.WRITE_CHARACTERISTIC_UUID)
            notifyCharacteristic = service.getCharacteristic(BleConstants.NOTIFY_CHARACTERISTIC_UUID)

            val hasWrite = writeCharacteristic != null
            val hasNotify = notifyCharacteristic != null

            Timber.d("BleDeviceManager: write=$hasWrite (${BleConstants.WRITE_CHARACTERISTIC_UUID}) notify=$hasNotify (${BleConstants.NOTIFY_CHARACTERISTIC_UUID})")
            if (!hasWrite) Timber.e("BleDeviceManager: Write characteristic NOT FOUND!")
            if (!hasNotify) Timber.e("BleDeviceManager: Notify characteristic NOT FOUND!")
            return hasWrite && hasNotify
        }

        override fun initialize() {
            requestMtu(BleConstants.MTU_SIZE).enqueue()

            setNotificationCallback(notifyCharacteristic)
                .with { _: BluetoothDevice, data: Data ->
                    val bytes = data.value ?: return@with
                    val hexStr = bytes.joinToString(" ") { "%02X".format(it) }
                    Timber.d("BleDeviceManager: RX ${bytes.size}B: $hexStr")
                    val packet = PacketParser.parse(bytes)
                    Timber.d("BleDeviceManager: Parsed packet: ${packet.javaClass.simpleName}")
                    packetChannel.trySend(packet)
                }

            enableNotifications(notifyCharacteristic)
                .done { 
                    _connectionReady.value = true
                    Timber.i("BleDeviceManager: ✓ Notifications ENABLED successfully")
                }
                .fail { _, status -> 
                    Timber.e("BleDeviceManager: ✗ Enable notifications FAILED: status=$status") 
                }
                .enqueue()
        }

        override fun onMtuChanged(gatt: BluetoothGatt, mtu: Int) {
            super.onMtuChanged(gatt, mtu)
            _mtu.value = mtu
            Timber.d("BleDeviceManager: MTU negotiated to $mtu")
        }

        override fun onServicesInvalidated() {
            writeCharacteristic = null
            notifyCharacteristic = null
            _connectionReady.value = false
            Timber.e("BleDeviceManager: ✗✗✗ SERVICES INVALIDATED - Device disconnected! ✗✗✗")
            onDeviceDisconnected?.invoke()
        }
    }
        }
        return _gattCallback!!
    }

    // ─── Public write API ───────────────────────────────────────────────────

    fun sendCommand(bytes: ByteArray) {
        val char = writeCharacteristic ?: run {
            Timber.w("BleDeviceManager: write characteristic not available")
            return
        }
        val hexStr = bytes.joinToString(" ") { "%02X".format(it) }
        Timber.d("BleDeviceManager: TX ${bytes.size}B: $hexStr")
        
        val chunkSize = (_mtu.value - 3).coerceAtLeast(20)
        if (bytes.size <= chunkSize) {
            writeCharacteristic(char, bytes, BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE)
                .fail { _, status -> Timber.e("Write failed: $status") }
                .enqueue()
        } else {
            // Chunked write for large payloads (e.g., OTA)
            bytes.toList().chunked(chunkSize).forEach { chunk ->
                writeCharacteristic(char, chunk.toByteArray(), BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE)
                    .fail { _, status -> Timber.e("Chunked write failed: $status") }
                    .enqueue()
            }
        }
    }
}
