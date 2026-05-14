package com.cyboglabs.diskey.audio

import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Wraps raw OPUS data in an OGG container so ExoPlayer can decode it.
 * 
 * This is needed when the T240 device sends raw Opus frames instead of
 * pre-packaged Ogg Opus files.
 */
object OggOpusWrapper {

    private const val OPUS_SAMPLE_RATE = 48_000
    private const val DEFAULT_FRAME_DURATION_MS = 20
    private const val MAX_OGG_SEGMENTS = 255
    private const val MAX_SEGMENT_SIZE = 255
    private const val SERIAL_NUMBER = 0x4449534B // "DISK"

    /**
     * Wraps raw OPUS data in minimal OGG Opus container.
     * Returns the wrapped file, or null on failure.
     */
    fun wrapRawOpus(
        rawOpusFile: File,
        outputOggFile: File,
        frameSizeBytes: Int,
        frameDurationMs: Int = DEFAULT_FRAME_DURATION_MS
    ): File? {
        if (!rawOpusFile.exists() || rawOpusFile.length() == 0L) {
            Timber.e("OggOpusWrapper: input file missing or empty")
            return null
        }

        if (frameSizeBytes <= 0) {
            Timber.e("OggOpusWrapper: invalid frameSizeBytes=$frameSizeBytes")
            return null
        }

        Timber.d("OggOpusWrapper: wrapping ${rawOpusFile.name} (${rawOpusFile.length()}B)")

        return try {
            // Delete existing output file if present
            if (outputOggFile.exists()) {
                outputOggFile.delete()
                Timber.d("OggOpusWrapper: deleted existing output file")
            }

            FileOutputStream(outputOggFile).use { out ->
                // Write OGG Opus header pages
                writeOpusHeaders(out)
                
                // Write raw opus data as OGG pages
                wrapOpusDataInOggPages(rawOpusFile, out, frameSizeBytes, frameDurationMs)
            }
            
            if (!outputOggFile.exists() || outputOggFile.length() == 0L) {
                Timber.e("OggOpusWrapper: output file is empty or missing")
                return null
            }
            
            Timber.i("OggOpusWrapper: ✓ wrapped ${rawOpusFile.length()}B → ${outputOggFile.length()}B")
            outputOggFile
        } catch (e: Exception) {
            Timber.e(e, "OggOpusWrapper: failed to wrap opus data")
            outputOggFile.delete()
            null
        }
    }

    private fun writeOpusHeaders(out: FileOutputStream) {
        // OGG page 0: OpusHead (identification header)
        val opusHead = buildOpusHeadPacket()
        writeOggPageSinglePacket(out, opusHead, 0, 0x02, 0) // BOS flag
        
        // OGG page 1: OpusTags (comment header)
        val opusTags = buildOpusTagsPacket()
        writeOggPageSinglePacket(out, opusTags, 1, 0x00, 0)
    }

    private fun buildOpusHeadPacket(): ByteArray {
        // OpusHead packet structure (19 bytes)
        return ByteBuffer.allocate(19).apply {
            order(ByteOrder.LITTLE_ENDIAN)
            put("OpusHead".toByteArray()) // 8 bytes
            put(1) // version
            put(1) // channel count (mono)
            putShort(312) // pre-skip
            putInt(48000) // sample rate (48kHz - Opus native)
            putShort(0) // output gain
            put(0) // channel mapping family
        }.array()
    }

    private fun buildOpusTagsPacket(): ByteArray {
        val vendor = "Diskey"
        return ByteBuffer.allocate(8 + 4 + vendor.length + 4).apply {
            order(ByteOrder.LITTLE_ENDIAN)
            put("OpusTags".toByteArray()) // 8 bytes
            putInt(vendor.length)
            put(vendor.toByteArray())
            putInt(0) // user comment list length
        }.array()
    }

    private fun wrapOpusDataInOggPages(
        rawOpusFile: File,
        out: FileOutputStream,
        frameSizeBytes: Int,
        frameDurationMs: Int
    ) {
        val samplesPerFrame = (OPUS_SAMPLE_RATE.toLong() * frameDurationMs) / 1000L
        if (samplesPerFrame <= 0L) {
            Timber.e("OggOpusWrapper: invalid samplesPerFrame=$samplesPerFrame")
            return
        }

        if (rawOpusFile.length() % frameSizeBytes.toLong() != 0L) {
            Timber.w(
                "OggOpusWrapper: file size not multiple of frameSizeBytes. size=${rawOpusFile.length()} frameSize=$frameSizeBytes"
            )
        }

        var pageSequence = 2
        var granulePosition = 0L
        var totalFrames = 0L

        FileInputStream(rawOpusFile).use { input ->
            val frameBuf = ByteArray(frameSizeBytes)
            val packets = ArrayList<ByteArray>(MAX_OGG_SEGMENTS)
            var lacingSegmentsInPage = 0

            fun flushPage(isEos: Boolean) {
                if (packets.isEmpty()) return
                val headerType = if (isEos) 0x04 else 0x00
                writeOggPagePackets(
                    out = out,
                    packets = packets,
                    sequenceNumber = pageSequence++,
                    headerType = headerType,
                    granulePosition = granulePosition
                )
                packets.clear()
                lacingSegmentsInPage = 0
            }

            while (true) {
                var read = 0
                while (read < frameBuf.size) {
                    val r = input.read(frameBuf, read, frameBuf.size - read)
                    if (r < 0) break
                    read += r
                }
                if (read <= 0) break
                if (read != frameBuf.size) {
                    Timber.w("OggOpusWrapper: short frame read: $read/${frameBuf.size}")
                    break
                }

                val framePacket = frameBuf.clone()
                val frameLacingSegments = buildLacingForPacket(framePacket).size
                if (lacingSegmentsInPage + frameLacingSegments > MAX_OGG_SEGMENTS) {
                    flushPage(isEos = false)
                }

                packets.add(framePacket)
                lacingSegmentsInPage += frameLacingSegments
                totalFrames++
                granulePosition += samplesPerFrame
            }

            // Final page (EOS)
            flushPage(isEos = true)
        }

        Timber.d(
            "OggOpusWrapper: wrote $totalFrames frames, frameSize=$frameSizeBytes bytes, frameDuration=${frameDurationMs}ms"
        )
    }

    private fun writeOggPageSinglePacket(
        out: FileOutputStream,
        payload: ByteArray,
        sequenceNumber: Int,
        headerType: Int,
        granulePosition: Long
    ) {
        val segmentTable = buildLacingForPacket(payload)
        writeOggPage(out, segmentTable, payload, sequenceNumber, headerType, granulePosition)
    }

    private fun writeOggPagePackets(
        out: FileOutputStream,
        packets: List<ByteArray>,
        sequenceNumber: Int,
        headerType: Int,
        granulePosition: Long
    ) {
        val lacing = ArrayList<Byte>(MAX_OGG_SEGMENTS)
        var payloadSize = 0
        packets.forEach { pkt ->
            val pktLacing = buildLacingForPacket(pkt)
            if (lacing.size + pktLacing.size > MAX_OGG_SEGMENTS) {
                throw IllegalStateException("Too many lacing segments for one page")
            }
            pktLacing.forEach { b -> lacing.add(b) }
            payloadSize += pkt.size
        }

        val payload = ByteArray(payloadSize)
        var offset = 0
        packets.forEach { pkt ->
            System.arraycopy(pkt, 0, payload, offset, pkt.size)
            offset += pkt.size
        }

        val segmentTable = ByteArray(lacing.size) { i -> lacing[i] }
        writeOggPage(out, segmentTable, payload, sequenceNumber, headerType, granulePosition)
    }

    private fun buildLacingForPacket(packet: ByteArray): ByteArray {
        if (packet.isEmpty()) return byteArrayOf(0)
        val segments = (packet.size + (MAX_SEGMENT_SIZE - 1)) / MAX_SEGMENT_SIZE
        val segmentTable = ByteArray(segments)
        for (i in 0 until segments) {
            val remaining = packet.size - (i * MAX_SEGMENT_SIZE)
            segmentTable[i] = if (remaining >= MAX_SEGMENT_SIZE) {
                MAX_SEGMENT_SIZE.toByte()
            } else {
                remaining.toByte()
            }
        }
        return segmentTable
    }

    private fun writeOggPage(
        out: FileOutputStream,
        segmentTable: ByteArray,
        payload: ByteArray,
        sequenceNumber: Int,
        headerType: Int,
        granulePosition: Long
    ) {
        require(segmentTable.size in 1..MAX_OGG_SEGMENTS) {
            "segmentTable size must be 1..$MAX_OGG_SEGMENTS"
        }

        val header = ByteBuffer.allocate(27 + segmentTable.size).apply {
            order(ByteOrder.LITTLE_ENDIAN)
            put("OggS".toByteArray()) // capture pattern
            put(0) // version
            put(headerType.toByte()) // header type flags
            putLong(granulePosition)
            putInt(SERIAL_NUMBER) // serial number
            putInt(sequenceNumber)
            putInt(0) // checksum (will calculate)
            put(segmentTable.size.toByte()) // segment count
            put(segmentTable)
        }.array()
        
        // Calculate CRC32 for OGG page
        val crc = calculateOggCrc(header, payload)
        ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN).putInt(22, crc)
        
        out.write(header)
        out.write(payload)
    }

    private fun calculateOggCrc(header: ByteArray, payload: ByteArray): Int {
        val crcTable = IntArray(256) { i ->
            var r = i shl 24
            repeat(8) {
                r = if (r and 0x80000000.toInt() != 0) {
                    (r shl 1) xor 0x04c11db7
                } else {
                    r shl 1
                }
            }
            r
        }
        
        var crc = 0
        header.forEach { byte ->
            crc = (crc shl 8) xor crcTable[((crc ushr 24) and 0xff) xor (byte.toInt() and 0xff)]
        }
        payload.forEach { byte ->
            crc = (crc shl 8) xor crcTable[((crc ushr 24) and 0xff) xor (byte.toInt() and 0xff)]
        }
        return crc
    }
}
