package com.cyboglabs.diskey.wifi

import android.content.Context
import com.cyboglabs.diskey.wifi.protocol.WifiPacket
import com.cyboglabs.diskey.wifi.protocol.WifiPacketCommands
import com.cyboglabs.diskey.wifi.protocol.WifiPacketSerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

enum class WifiState { DISCONNECTED, CONNECTING, CONNECTED, ERROR }

@Singleton
class WifiTransferManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val seqCounter = AtomicInteger(0)

    private val _state = MutableStateFlow(WifiState.DISCONNECTED)
    val state: StateFlow<WifiState> = _state.asStateFlow()

    private val _inboundPackets = MutableSharedFlow<WifiPacket>(extraBufferCapacity = 128)
    val inboundPackets: SharedFlow<WifiPacket> = _inboundPackets.asSharedFlow()

    private var socket: Socket? = null
    private var outputStream: DataOutputStream? = null
    private var heartbeatJob: Job? = null
    private var receiveJob: Job? = null

    suspend fun connect(host: String = WifiConstants.TCP_HOST, port: Int = WifiConstants.TCP_PORT) {
        _state.value = WifiState.CONNECTING
        Timber.d("WifiTransferManager: connecting to $host:$port")
        try {
            val sock = Socket()
            sock.connect(InetSocketAddress(host, port), WifiConstants.CONNECT_TIMEOUT_MS)
            sock.soTimeout = WifiConstants.READ_TIMEOUT_MS
            socket = sock
            outputStream = DataOutputStream(sock.getOutputStream())

            _state.value = WifiState.CONNECTED
            Timber.i("WifiTransferManager: connected")

            startHeartbeat()
            startReceiving(sock)
        } catch (e: Exception) {
            Timber.e(e, "WifiTransferManager: connection failed")
            _state.value = WifiState.ERROR
        }
    }

    fun disconnect() {
        heartbeatJob?.cancel()
        receiveJob?.cancel()
        runCatching { socket?.close() }
        socket = null
        outputStream = null
        seqCounter.set(0)
        _state.value = WifiState.DISCONNECTED
        Timber.d("WifiTransferManager: disconnected")
    }

    fun sendPacket(packet: WifiPacket) {
        val bytes = WifiPacketSerializer.serialize(packet)
        scope.launch {
            try {
                outputStream?.apply {
                    write(bytes)
                    flush()
                } ?: Timber.w("WifiTransferManager: no output stream")
            } catch (e: Exception) {
                Timber.e(e, "WifiTransferManager: send error")
                _state.value = WifiState.ERROR
            }
        }
    }

    fun nextSequence(): Int = seqCounter.getAndIncrement()

    fun requestFileList() {
        sendPacket(WifiPacket(WifiPacketCommands.FILE_LIST_REQUEST, nextSequence()))
    }

    fun requestFileDownload(filename: String) {
        sendPacket(WifiPacket(
            WifiPacketCommands.FILE_DOWNLOAD_REQUEST,
            nextSequence(),
            filename.toByteArray(Charsets.UTF_8)
        ))
    }

    private fun startHeartbeat() {
        heartbeatJob = scope.launch {
            while (isActive) {
                delay(WifiConstants.HEARTBEAT_INTERVAL_MS)
                try {
                    val hb = WifiPacketSerializer.heartbeat(nextSequence())
                    outputStream?.apply { write(hb); flush() }
                } catch (e: Exception) {
                    Timber.e(e, "WifiTransferManager: heartbeat error")
                    _state.value = WifiState.ERROR
                    break
                }
            }
        }
    }

    private fun startReceiving(sock: Socket) {
        receiveJob = scope.launch {
            val dis = DataInputStream(BufferedInputStream(sock.getInputStream()))
            val headerBytes = WifiConstants.PACKET_HEADER.toByteArray(Charsets.US_ASCII)
            val buffer = ByteArray(65536)

            while (isActive && !sock.isClosed) {
                try {
                    val bytesRead = dis.read(buffer, 0, buffer.size)
                    if (bytesRead <= 0) break

                    val raw = buffer.copyOfRange(0, bytesRead)
                    val packet = WifiPacketSerializer.deserialize(raw)
                    if (packet != null) {
                        _inboundPackets.emit(packet)
                    }
                } catch (e: java.net.SocketTimeoutException) {
                    // Read timeout — check heartbeat then continue
                    Timber.v("WifiTransferManager: read timeout (heartbeat ok)")
                } catch (e: Exception) {
                    if (isActive) {
                        Timber.e(e, "WifiTransferManager: receive error")
                        _state.value = WifiState.ERROR
                    }
                    break
                }
            }
            Timber.d("WifiTransferManager: receive loop ended")
        }
    }
}
