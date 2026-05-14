package com.cyboglabs.diskey

import com.cyboglabs.diskey.ble.BleCommand
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PacketBuilderTest {

    @Test
    fun `startRecording builds correct packet`() {
        val packet = PacketBuilder.startRecording()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x14), packet)
    }

    @Test
    fun `stopRecording builds correct packet`() {
        val packet = PacketBuilder.stopRecording()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x17), packet)
    }

    @Test
    fun `pauseRecording builds correct packet`() {
        val packet = PacketBuilder.pauseRecording()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x15), packet)
    }

    @Test
    fun `resumeRecording builds correct packet`() {
        val packet = PacketBuilder.resumeRecording()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x16), packet)
    }

    @Test
    fun `getBattery builds correct packet`() {
        val packet = PacketBuilder.getBattery()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x09), packet)
    }

    @Test
    fun `enableWifi builds correct packet`() {
        val packet = PacketBuilder.enableWifi()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x0A), packet)
    }

    @Test
    fun `getFileList builds correct packet`() {
        val packet = PacketBuilder.getFileList()
        assertArrayEquals(byteArrayOf(0x01, 0x00, 0x1B), packet)
    }

    @Test
    fun `downloadFile includes filename bytes`() {
        val filename = "R20200101-000013.opus"
        val packet = PacketBuilder.downloadFile(filename)
        assertEquals(3, packet.size - filename.length)
        val extractedName = packet.copyOfRange(3, packet.size).toString(Charsets.UTF_8)
        assertEquals(filename, extractedName)
    }

    @Test
    fun `handshakeResponse contains valid header`() {
        val ts = 1730254726L
        val uuid = "test-uuid"
        val packet = PacketBuilder.handshakeResponse(ts, uuid)
        assertEquals(0x01, packet[0])
        assertEquals(0x01, packet[1])
        assertEquals(0x00, packet[2])
        assertEquals(0x01, packet[3])
        val json = packet.copyOfRange(4, packet.size).toString(Charsets.UTF_8)
        assertTrue(json.contains(ts.toString()))
        assertTrue(json.contains(uuid))
    }
}
