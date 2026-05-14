package com.cyboglabs.diskey.ble.protocol

import com.cyboglabs.diskey.ble.BleCommand
import com.cyboglabs.diskey.utils.CrcUtils
import java.nio.ByteBuffer
import java.nio.charset.Charset

/**
 * Builds binary command packets for the T240 BLE protocol.
 *
 * Standard command packet layout:
 *   [type:1][cmd:1][sub:1][...optional payload]
 *   where type=0x01, cmd=command byte, sub=0x00
 *
 * Audio packet layout:
 *   [type:1][cmd:1][sub:1][index_hi:1][index_lo:1][audio:N]
 */
object PacketBuilder {

    fun startRecording(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_START_RECORDING, 0x00)

    fun stopRecording(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_STOP_RECORDING, 0x00)

    fun pauseRecording(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_PAUSE_RECORDING, 0x00)

    fun resumeRecording(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_RESUME_RECORDING, 0x00)

    fun getBattery(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_BATTERY, 0x00)

    fun enableWifi(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_WIFI_ENABLE, 0x00)

    fun getFileList(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_GET_FILE_LIST, 0x00)

    fun downloadFile(filename: String): ByteArray {
        val nameBytes = filename.toByteArray(Charset.forName("UTF-8"))
        return byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_DOWNLOAD_FILE, 0x00) + nameBytes
    }

    fun handshakeResponse(timestamp: Long, appUuid: String): ByteArray {
        val json = """{"time":$timestamp,"uuid":"$appUuid"}"""
        val jsonBytes = json.toByteArray(Charset.forName("UTF-8"))
        // Header: 0x01 0x01 0x00 0x01 + json
        return byteArrayOf(0x01, 0x01, 0x00, 0x01) + jsonBytes
    }

    fun otaStart(fileSize: Int, crc: Int): ByteArray {
        val buf = ByteBuffer.allocate(9)
        buf.put(BleCommand.TYPE_CONTROL)
        buf.put(BleCommand.CMD_OTA_START)
        buf.put(0x00)
        buf.putInt(fileSize)
        buf.putInt(crc)
        return buf.array()
    }

    fun otaPacket(index: Int, data: ByteArray): ByteArray {
        val buf = ByteBuffer.allocate(5 + data.size)
        buf.put(BleCommand.TYPE_CONTROL)
        buf.put(BleCommand.CMD_OTA_PACKET)
        buf.put(0x00)
        buf.putShort(index.toShort())
        buf.put(data)
        return buf.array()
    }

    fun otaEnd(): ByteArray =
        byteArrayOf(BleCommand.TYPE_CONTROL, BleCommand.CMD_OTA_END, 0x00)
}
