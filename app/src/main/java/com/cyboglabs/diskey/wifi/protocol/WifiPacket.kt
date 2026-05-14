package com.cyboglabs.diskey.wifi.protocol

import com.cyboglabs.diskey.utils.CrcUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset

/**
 * WiFi TCP packet for the T240 MeChoWifi protocol.
 *
 * Wire format:
 *   [header: "MeChoWifiStart"][command:2][sequence:4][length:4][crc:2][data:N][tail: "MeChoWifiEnd"]
 */
data class WifiPacket(
    val command: Int,
    val sequence: Int,
    val data: ByteArray = ByteArray(0)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WifiPacket) return false
        return command == other.command && sequence == other.sequence && data.contentEquals(other.data)
    }
    override fun hashCode(): Int = 31 * command + sequence + data.contentHashCode()
}

object WifiPacketCommands {
    const val HEARTBEAT = 0xFF00
    const val FILE_LIST_REQUEST = 0x0100
    const val FILE_LIST_RESPONSE = 0x0101
    const val FILE_DOWNLOAD_REQUEST = 0x0200
    const val FILE_DOWNLOAD_DATA = 0x0201
    const val FILE_DOWNLOAD_COMPLETE = 0x0202
    const val FILE_DOWNLOAD_ERROR = 0x0203
    const val OTA_START = 0x0300
    const val OTA_DATA = 0x0301
    const val OTA_COMPLETE = 0x0302
}
