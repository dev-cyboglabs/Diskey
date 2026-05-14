package com.cyboglabs.diskey.ble.protocol

import com.cyboglabs.diskey.ble.BleCommand
import com.cyboglabs.diskey.domain.model.BlePacket
import com.cyboglabs.diskey.domain.model.HandshakePacket
import com.cyboglabs.diskey.domain.model.AudioDataPacket
import com.cyboglabs.diskey.domain.model.FileCompletePacket
import com.cyboglabs.diskey.domain.model.FileListPacket
import com.cyboglabs.diskey.domain.model.BatteryPacket
import com.cyboglabs.diskey.domain.model.OtaProgressPacket
import com.cyboglabs.diskey.domain.model.UnknownPacket
import com.cyboglabs.diskey.utils.CrcUtils
import timber.log.Timber
import java.nio.charset.Charset

/**
 * Parses raw BLE notification bytes into typed BlePacket objects.
 *
 * Standard packet header: [type:1][cmd:1][sub:1]
 * Audio packet: [type:1][cmd_hi:1][cmd_lo:1][index_hi:1][index_lo:1][audio:N]
 * File-complete: [type:1][cmd:1][sub:1][crc_lo:1][crc_hi:1]
 */
object PacketParser {

    fun parse(raw: ByteArray): BlePacket {
        if (raw.size < 3) {
            Timber.w("PacketParser: packet too short (${raw.size} bytes)")
            return UnknownPacket(raw)
        }

        val type = raw[0]
        val cmd = raw[1]
        val sub = raw[2]

        return when {
            // Handshake packets:
            //   Hello:   0x01 0x01 0x00 0x00 + JSON
            //   Status:  0x01 0x01 0x00 0x02 status + JSON
            type == 0x01.toByte() && cmd == 0x01.toByte() && sub == 0x00.toByte() && raw.size >= 4 -> {
                val hsType = raw[3]
                when (hsType) {
                    0x00.toByte() -> {
                        val jsonStr = if (raw.size > 4) raw.copyOfRange(4, raw.size)
                            .toString(Charset.forName("UTF-8")) else ""
                        HandshakePacket(jsonStr, success = false)
                    }

                    0x02.toByte() -> {
                        val statusByte = if (raw.size > 4) raw[4] else 0x01
                        val jsonStr = if (raw.size > 5) raw.copyOfRange(5, raw.size)
                            .toString(Charset.forName("UTF-8")) else ""
                        HandshakePacket(jsonStr, success = statusByte == 0x00.toByte())
                    }

                    else -> UnknownPacket(raw)
                }
            }

            // File list response: 0x01 0x1B 0x00 + JSON
            type == BleCommand.TYPE_CONTROL && cmd == BleCommand.CMD_GET_FILE_LIST && sub == 0x00.toByte() -> {
                val jsonStr = if (raw.size > 3) raw.copyOfRange(3, raw.size).toString(Charset.forName("UTF-8")) else ""
                Timber.d("PacketParser: FileList JSON: $jsonStr")
                FileListPacket(jsonStr)
            }

            // Audio data packet: type, cmd, sub, index_lo, index_hi, data
            raw.size > 5 && isAudioCmd(type, cmd, sub) -> {
                val index = (raw[3].toInt() and 0xFF) or ((raw[4].toInt() and 0xFF) shl 8)
                val audioData = raw.copyOfRange(5, raw.size)
                AudioDataPacket(index, audioData)
            }

            // File download complete: 0x01 0x1D 0x00 crc_lo crc_hi
            type == BleCommand.TYPE_CONTROL && cmd == BleCommand.CMD_FILE_COMPLETE && sub == 0x00.toByte() && raw.size >= 5 -> {
                val crcLo = raw[3].toInt() and 0xFF
                val crcHi = raw[4].toInt() and 0xFF
                val crc = crcLo or (crcHi shl 8)
                Timber.d("PacketParser: File complete, CRC: 0x%04X", crc)
                FileCompletePacket(crc)
            }

            // Battery level response: 0x01 0x09 0x00 + level
            type == BleCommand.TYPE_CONTROL && cmd == BleCommand.CMD_BATTERY && sub == 0x00.toByte() && raw.size >= 4 -> {
                val level = raw[3].toInt() and 0xFF
                Timber.d("PacketParser: Battery level: $level%")
                BatteryPacket(level)
            }

            // OTA progress: 0x01 0xA4 0x00 + 4 bytes
            type == BleCommand.TYPE_CONTROL && cmd == BleCommand.CMD_OTA_PROGRESS && sub == 0x00.toByte() && raw.size >= 7 -> {
                val received = ((raw[3].toInt() and 0xFF) shl 24) or
                    ((raw[4].toInt() and 0xFF) shl 16) or
                    ((raw[5].toInt() and 0xFF) shl 8) or
                    (raw[6].toInt() and 0xFF)
                OtaProgressPacket(received)
            }

            else -> {
                Timber.d("PacketParser: unknown packet type=0x%02X cmd=0x%02X sub=0x%02X", type, cmd, sub)
                UnknownPacket(raw)
            }
        }
    }

    private fun isAudioCmd(type: Byte, cmd: Byte, sub: Byte): Boolean {
        val isAudioType = type == BleCommand.TYPE_CONTROL || type == 0x02.toByte()
        return isAudioType && cmd == BleCommand.CMD_DOWNLOAD_FILE && sub == 0x00.toByte()
    }
}
