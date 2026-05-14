package com.cyboglabs.diskey.utils

/**
 * CRC-16/CCITT-FALSE used by the T240 protocol for packet integrity checks.
 * Initial value: 0xFFFF, polynomial: 0x1021, no reflection, XOR out: 0x0000.
 */
object CrcUtils {

    private const val POLY = 0x1021
    private const val INIT = 0xFFFF

    private val crcTable: IntArray = IntArray(256) { i ->
        var crc = i shl 8
        repeat(8) {
            crc = if ((crc and 0x8000) != 0) ((crc shl 1) xor POLY) else (crc shl 1)
        }
        crc and 0xFFFF
    }

    fun compute(data: ByteArray, offset: Int = 0, length: Int = data.size - offset): Int {
        return computeIncremental(INIT, data, offset, length)
    }

    fun initialValue(): Int = INIT

    fun computeIncremental(initialCrc: Int, data: ByteArray, offset: Int = 0, length: Int = data.size - offset): Int {
        var crc = initialCrc and 0xFFFF
        for (i in offset until offset + length) {
            val byte = data[i].toInt() and 0xFF
            crc = ((crc shl 8) xor crcTable[((crc ushr 8) xor byte) and 0xFF]) and 0xFFFF
        }
        return crc
    }

    fun validate(data: ByteArray, expectedCrc: Int): Boolean = compute(data) == expectedCrc

    /** Append 2-byte CRC (little-endian) to data. */
    fun appendCrc(data: ByteArray): ByteArray {
        val crc = compute(data)
        return data + byteArrayOf((crc and 0xFF).toByte(), ((crc ushr 8) and 0xFF).toByte())
    }

    /** Extract and verify trailing 2-byte CRC (little-endian). Returns null if invalid. */
    fun extractAndVerify(dataWithCrc: ByteArray): ByteArray? {
        if (dataWithCrc.size < 3) return null
        val payload = dataWithCrc.copyOfRange(0, dataWithCrc.size - 2)
        val storedCrc = (dataWithCrc[dataWithCrc.size - 2].toInt() and 0xFF) or
            ((dataWithCrc[dataWithCrc.size - 1].toInt() and 0xFF) shl 8)
        return if (compute(payload) == storedCrc) payload else null
    }
}
