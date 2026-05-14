package com.cyboglabs.diskey.wifi.protocol

import com.cyboglabs.diskey.utils.CrcUtils
import com.cyboglabs.diskey.wifi.WifiConstants
import timber.log.Timber
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset

/**
 * Serializes/deserializes MeChoWifi TCP packets.
 *
 * Wire format (all multi-byte fields are little-endian):
 *   [header:14][command:2][sequence:4][length:4][crc:2][data:N][tail:12]
 */
object WifiPacketSerializer {

    private val HEADER_BYTES = WifiConstants.PACKET_HEADER.toByteArray(Charsets.US_ASCII)
    private val TAIL_BYTES = WifiConstants.PACKET_TAIL.toByteArray(Charsets.US_ASCII)
    private const val FIXED_OVERHEAD = 14 + 2 + 4 + 4 + 2 + 12  // header + cmd + seq + len + crc + tail = 38

    fun serialize(packet: WifiPacket): ByteArray {
        val crc = CrcUtils.compute(packet.data)
        val totalSize = FIXED_OVERHEAD + packet.data.size

        val buf = ByteBuffer.allocate(totalSize).order(ByteOrder.LITTLE_ENDIAN)
        buf.put(HEADER_BYTES)                          // 14 bytes
        buf.putShort(packet.command.toShort())          // 2 bytes
        buf.putInt(packet.sequence)                     // 4 bytes
        buf.putInt(packet.data.size)                    // 4 bytes
        buf.putShort(crc.toShort())                     // 2 bytes
        buf.put(packet.data)                            // N bytes
        buf.put(TAIL_BYTES)                             // 12 bytes

        return buf.array()
    }

    /**
     * Deserializes bytes from the TCP stream into a WifiPacket.
     * Returns null on header/tail/CRC mismatch.
     */
    fun deserialize(bytes: ByteArray): WifiPacket? {
        if (bytes.size < FIXED_OVERHEAD) {
            Timber.w("WifiPacketSerializer: packet too small (${bytes.size})")
            return null
        }

        val header = bytes.copyOfRange(0, 14).toString(Charsets.US_ASCII)
        if (header != WifiConstants.PACKET_HEADER) {
            Timber.w("WifiPacketSerializer: bad header '$header'")
            return null
        }

        val buf = ByteBuffer.wrap(bytes, 14, bytes.size - 14).order(ByteOrder.LITTLE_ENDIAN)
        val command = buf.short.toInt() and 0xFFFF
        val sequence = buf.int
        val dataLen = buf.int
        val storedCrc = buf.short.toInt() and 0xFFFF

        val dataStart = 14 + 2 + 4 + 4 + 2
        val dataEnd = dataStart + dataLen
        if (dataEnd + 12 > bytes.size) {
            Timber.w("WifiPacketSerializer: insufficient bytes for data")
            return null
        }

        val data = bytes.copyOfRange(dataStart, dataEnd)
        val tail = bytes.copyOfRange(dataEnd, dataEnd + 12).toString(Charsets.US_ASCII)

        if (tail != WifiConstants.PACKET_TAIL) {
            Timber.w("WifiPacketSerializer: bad tail '$tail'")
            return null
        }

        val computedCrc = CrcUtils.compute(data)
        if (computedCrc != storedCrc) {
            Timber.e("WifiPacketSerializer: CRC mismatch (expected=0x%04X got=0x%04X)", storedCrc, computedCrc)
            return null
        }

        return WifiPacket(command, sequence, data)
    }

    fun heartbeat(sequence: Int): ByteArray =
        serialize(WifiPacket(WifiPacketCommands.HEARTBEAT, sequence, WifiConstants.HEARTBEAT_BYTES))
}
