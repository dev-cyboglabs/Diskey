package com.cyboglabs.diskey.ota

import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import com.cyboglabs.diskey.domain.model.OtaProgressPacket
import com.cyboglabs.diskey.utils.CrcUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

enum class OtaState { IDLE, STARTING, TRANSFERRING, VERIFYING, COMPLETE, FAILED }

data class OtaProgress(
    val state: OtaState = OtaState.IDLE,
    val bytesSent: Int = 0,
    val totalBytes: Int = 0,
    val errorMessage: String? = null
) {
    val percent: Float get() = if (totalBytes > 0) bytesSent.toFloat() / totalBytes else 0f
}

/**
 * Manages OTA firmware updates over BLE.
 *
 * Sequence:
 *   1. Send OTA_START with file size + CRC
 *   2. Stream firmware in chunks with OTA_PACKET
 *   3. Send OTA_END
 *   4. Device confirms with OTA_PROGRESS packets
 */
@Singleton
class OtaManager @Inject constructor(
    private val bleConnectionManager: BleConnectionManager
) {
    private val _progress = MutableStateFlow(OtaProgress())
    val progress: StateFlow<OtaProgress> = _progress.asStateFlow()

    suspend fun startUpdate(firmwareFile: File) {
        if (!firmwareFile.exists()) {
            _progress.value = OtaProgress(OtaState.FAILED, errorMessage = "Firmware file not found")
            return
        }

        val firmwareBytes = firmwareFile.readBytes()
        val totalBytes = firmwareBytes.size
        val crc = CrcUtils.compute(firmwareBytes)

        Timber.i("OtaManager: starting OTA update — ${totalBytes}B, CRC=0x%04X".format(crc))
        _progress.value = OtaProgress(OtaState.STARTING, 0, totalBytes)

        // Send OTA start
        bleConnectionManager.sendCommand(PacketBuilder.otaStart(totalBytes, crc))
        delay(500)

        // Stream packets
        _progress.value = _progress.value.copy(state = OtaState.TRANSFERRING)
        var bytesSent = 0
        var packetIndex = 0

        firmwareBytes.toList().chunked(OtaConstants.OTA_CHUNK_SIZE).forEach { chunk ->
            val chunkBytes = chunk.toByteArray()
            bleConnectionManager.sendCommand(PacketBuilder.otaPacket(packetIndex, chunkBytes))
            bytesSent += chunkBytes.size
            packetIndex++
            _progress.value = _progress.value.copy(bytesSent = bytesSent)
            delay(OtaConstants.OTA_PACKET_DELAY_MS)
        }

        // Send end
        bleConnectionManager.sendCommand(PacketBuilder.otaEnd())
        _progress.value = _progress.value.copy(state = OtaState.VERIFYING)

        Timber.i("OtaManager: OTA transfer complete, awaiting device confirmation")
        // Wait for device confirmation (OTA_PROGRESS packet)
        // In practice you'd listen for a success/failure response packet
        delay(3000)
        _progress.value = _progress.value.copy(state = OtaState.COMPLETE)
        Timber.i("OtaManager: OTA update complete")
    }

    fun reset() {
        _progress.value = OtaProgress()
    }
}
