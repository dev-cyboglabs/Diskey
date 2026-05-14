package com.cyboglabs.diskey

import com.cyboglabs.diskey.utils.CrcUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CrcUtilsTest {

    @Test
    fun `compute returns consistent CRC for same input`() {
        val data = byteArrayOf(0x01, 0x14, 0x00, 0x05, 0xAB.toByte())
        val crc1 = CrcUtils.compute(data)
        val crc2 = CrcUtils.compute(data)
        assertEquals(crc1, crc2)
    }

    @Test
    fun `validate returns true for correct CRC`() {
        val data = byteArrayOf(0x01, 0x14, 0x00)
        val crc = CrcUtils.compute(data)
        assertTrue(CrcUtils.validate(data, crc))
    }

    @Test
    fun `appendCrc and extractAndVerify are inverse operations`() {
        val original = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05)
        val withCrc = CrcUtils.appendCrc(original)
        assertEquals(original.size + 2, withCrc.size)
        val extracted = CrcUtils.extractAndVerify(withCrc)
        assertNotNull(extracted)
        assertTrue(original.contentEquals(extracted!!))
    }

    @Test
    fun `extractAndVerify returns null for corrupted data`() {
        val data = byteArrayOf(0x01, 0x02, 0x03)
        val withCrc = CrcUtils.appendCrc(data)
        // Corrupt one byte
        withCrc[0] = (withCrc[0].toInt() xor 0xFF).toByte()
        val result = CrcUtils.extractAndVerify(withCrc)
        assertNull(result)
    }

    @Test
    fun `CRC is in range 0 to 65535`() {
        val data = "Hello, T240!".toByteArray()
        val crc = CrcUtils.compute(data)
        assertTrue(crc in 0..0xFFFF)
    }
}
