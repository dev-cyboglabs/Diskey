package com.cyboglabs.diskey.utils

import android.content.Context
import android.os.Environment
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {

    fun getAudioDirectory(context: Context): File {
        val dir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),
            "diskey_audio"
        )
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun getOpusFile(context: Context, filename: String): File =
        File(getAudioDirectory(context), filename)

    fun getWavFile(context: Context, opusFilename: String): File {
        val wavName = opusFilename.removeSuffix(".opus") + ".wav"
        return File(getAudioDirectory(context), wavName)
    }

    fun getTempDirectory(context: Context): File {
        val dir = File(context.cacheDir, "diskey_temp")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun writeBytesToFile(file: File, data: ByteArray): Boolean {
        return try {
            FileOutputStream(file, false).use { it.write(data) }
            true
        } catch (e: IOException) {
            Timber.e(e, "FileUtils: failed to write ${file.name}")
            false
        }
    }

    fun appendBytesToFile(file: File, data: ByteArray): Boolean {
        return try {
            FileOutputStream(file, true).use { it.write(data) }
            true
        } catch (e: IOException) {
            Timber.e(e, "FileUtils: failed to append to ${file.name}")
            false
        }
    }

    fun deleteFile(file: File): Boolean {
        return if (file.exists()) file.delete() else true
    }

    fun formatSize(bytes: Long): String {
        val kb = bytes / 1024.0
        return if (kb < 1024) "%.1f KB".format(kb) else "%.2f MB".format(kb / 1024.0)
    }

    fun isOggContainer(file: File): Boolean {
        if (!file.exists() || file.length() < 4L) return false
        return try {
            val header = ByteArray(4)
            FileInputStream(file).use { it.read(header) }
            header.contentEquals(byteArrayOf('O'.code.toByte(), 'g'.code.toByte(), 'g'.code.toByte(), 'S'.code.toByte()))
        } catch (e: Exception) {
            Timber.e(e, "FileUtils: failed to read header for ${file.name}")
            false
        }
    }
}
