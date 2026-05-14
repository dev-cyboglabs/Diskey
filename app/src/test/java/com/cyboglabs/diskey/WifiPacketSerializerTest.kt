package com.cyboglabs.diskey

import com.cyboglabs.diskey.wifi.protocol.WifiPacket
import com.cyboglabs.diskey.wifi.protocol.WifiPacketCommands
import com.cyboglabs.diskey.wifi.protocol.WifiPacketSerializer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class WifiPacketSerializerTest {

    @Test
    fun `serialize and deserialize are inverse operations`() {
        val original = WifiPacket(
            command = WifiPacketCommands.FILE_LIST_REQUEST,
            sequence = 42,
            data = "hello T240".toByteArray()
        )
        val bytes = WifiPacketSerializer.serialize(original)
        val recovered = WifiPacketSerializer.deserialize(bytes)

        assertNotNull(recovered)
        assertEquals(original.command, recovered!!.command)
        assertEquals(original.sequence, recovered.sequence)
        assertArrayEquals(original.data, recovered.data)
    }

    @Test
    fun `deserialize returns null for empty packet`() {
        val result = WifiPacketSerializer.deserialize(byteArrayOf())
        assertNull(result)
    }

    @Test
    fun `deserialize returns null for corrupted header`() {
        val packet = WifiPacket(WifiPacketCommands.HEARTBEAT, 1)
        val bytes = WifiPacketSerializer.serialize(packet)
        bytes[0] = 'X'.code.toByte()  // corrupt header
        val result = WifiPacketSerializer.deserialize(bytes)
        assertNull(result)
    }

    @Test
    fun `heartbeat packet has correct command`() {
        val bytes = WifiPacketSerializer.heartbeat(1)
        val packet = WifiPacketSerializer.deserialize(bytes)
        assertNotNull(packet)
        assertEquals(WifiPacketCommands.HEARTBEAT, packet!!.command)
    }
}
