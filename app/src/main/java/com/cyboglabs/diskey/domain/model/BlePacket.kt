package com.cyboglabs.diskey.domain.model

/** Sealed hierarchy of parsed BLE notification packets. */
sealed class BlePacket

data class HandshakePacket(
    val json: String,
    val success: Boolean = false
) : BlePacket()

data class AudioDataPacket(
    val index: Int,
    val data: ByteArray
) : BlePacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AudioDataPacket) return false
        return index == other.index && data.contentEquals(other.data)
    }
    override fun hashCode(): Int = 31 * index + data.contentHashCode()
}

data class FileCompletePacket(val crc: Int) : BlePacket()

data class FileListPacket(val json: String) : BlePacket()

data class BatteryPacket(val levelPercent: Int) : BlePacket()

data class OtaProgressPacket(val bytesReceived: Int) : BlePacket()

data class UnknownPacket(val raw: ByteArray) : BlePacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnknownPacket) return false
        return raw.contentEquals(other.raw)
    }
    override fun hashCode(): Int = raw.contentHashCode()
}
