package com.cyboglabs.diskey.audio

import android.content.Context
import com.cyboglabs.diskey.utils.CrcUtils
import com.cyboglabs.diskey.utils.FileUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages OPUS file reconstruction from ordered BLE audio packets.
 *
 * Packets arrive as [index, data] pairs. Missing packets are detected
 * via sequence gaps. Once the file-complete signal arrives with a CRC,
 * the assembled bytes are CRC-validated and written to disk.
 */
@Singleton
class OpusFileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var currentFilename: String? = null
    private var expectedPacketCount: Int = -1
    private var outputStream: FileOutputStream? = null
    private var outputFile: File? = null
    private var lastSeenIndex: Int = -1
    private var receivedPackets: Int = 0
    private var runningCrc: Int = CrcUtils.initialValue()

    fun beginFile(filename: String) {
        cancel()

        currentFilename = filename
        expectedPacketCount = -1
        lastSeenIndex = -1
        receivedPackets = 0
        runningCrc = CrcUtils.initialValue()

        outputFile = FileUtils.getOpusFile(context, filename)
        outputStream = FileOutputStream(outputFile!!, false)
        Timber.d("OpusFileManager: started collecting '$filename'")
    }

    fun addPacket(index: Int, data: ByteArray) {
        val out = outputStream ?: return

        if (lastSeenIndex >= 0 && index < lastSeenIndex) {
            Timber.w("OpusFileManager: out-of-order index $index (last=$lastSeenIndex)")
        }
        lastSeenIndex = index

        receivedPackets++
        writePacket(out, index, data)
    }

    fun setExpectedPacketCount(count: Int) {
        expectedPacketCount = count
    }

    /** Returns the assembled file or null if validation fails. */
    fun finalizeFile(expectedCrc: Int): File? {
        val out = outputStream ?: return null
        val file = outputFile ?: return null

        runCatching { out.flush() }
        runCatching { out.close() }
        outputStream = null

        val fileSize = file.length()
        if (fileSize <= 0L) {
            Timber.e("OpusFileManager: saved file is empty — ${file.name}")
            FileUtils.deleteFile(file)
            resetStateAfterFinalize()
            return null
        }

        if (expectedCrc != 0 && runningCrc != expectedCrc) {
            Timber.e("OpusFileManager: CRC mismatch — expected=0x%04X computed=0x%04X", expectedCrc, runningCrc)
            FileUtils.deleteFile(file)
            resetStateAfterFinalize()
            return null
        }

        Timber.i("OpusFileManager: saved '${file.absolutePath}' ($fileSize B)")

        resetStateAfterFinalize()

        return file
    }

    private fun resetStateAfterFinalize() {
        currentFilename = null
        outputFile = null
        lastSeenIndex = -1
        expectedPacketCount = -1
    }

    fun cancel() {
        currentFilename = null
        lastSeenIndex = -1
        expectedPacketCount = -1

        outputStream?.let { stream ->
            runCatching { stream.flush() }
            runCatching { stream.close() }
        }
        outputStream = null

        outputFile?.let { FileUtils.deleteFile(it) }
        outputFile = null

        Timber.d("OpusFileManager: cancelled")
    }

    fun isCollecting(): Boolean = currentFilename != null

    fun bufferedPacketCount(): Int = receivedPackets

    private fun writePacket(out: FileOutputStream, index: Int, data: ByteArray) {
        out.write(data)
        runningCrc = CrcUtils.computeIncremental(runningCrc, data)
        Timber.v("OpusFileManager: wrote packet $index (${data.size}B)")
    }
}
