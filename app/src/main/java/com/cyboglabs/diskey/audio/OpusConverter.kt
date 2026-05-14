package com.cyboglabs.diskey.audio

import android.content.Context
// TODO: Re-enable when FFmpegKit is added
// import com.arthenica.ffmpegkit.FFmpegKit
// import com.arthenica.ffmpegkit.ReturnCode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

/**
 * Converts OPUS files to WAV using FFmpegKit when direct OPUS playback
 * is not supported by ExoPlayer on the target device.
 */
@Singleton
class OpusConverter @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /**
     * Converts an OPUS file to WAV using FFmpegKit.
     * Returns the WAV File on success, null on failure.
     */
    suspend fun opusToWav(opusFile: File, wavFile: File): File? = withContext(Dispatchers.IO) {
        // TODO: Re-enable when FFmpegKit is added
        Timber.w("OpusConverter: FFmpegKit not available - conversion disabled")
        return@withContext null
        
        /* Original FFmpegKit implementation - commented out
        if (!opusFile.exists()) {
            Timber.e("OpusConverter: source file not found: ${opusFile.path}")
            return@withContext null
        }

        if (wavFile.exists()) wavFile.delete()

        val cmd = "-y -i \"${opusFile.absolutePath}\" " +
            "-acodec pcm_s16le -ar 48000 -ac 2 " +
            "\"${wavFile.absolutePath}\""

        Timber.d("OpusConverter: running FFmpeg: $cmd")

        suspendCancellableCoroutine { cont ->
            val session = FFmpegKit.executeAsync(cmd) { session ->
                if (ReturnCode.isSuccess(session.returnCode)) {
                    Timber.i("OpusConverter: converted ${opusFile.name} → ${wavFile.name}")
                    cont.resume(wavFile)
                } else {
                    Timber.e("OpusConverter: FFmpeg failed — ${session.failStackTrace}")
                    cont.resume(null)
                }
            }
            cont.invokeOnCancellation {
                FFmpegKit.cancel(session.sessionId)
            }
        }
        */
    }

    /** Returns WAV file if it already exists, otherwise converts. */
    suspend fun ensureWav(opusFile: File, wavFile: File): File? {
        return if (wavFile.exists() && wavFile.length() > 0) wavFile
        else opusToWav(opusFile, wavFile)
    }

    /**
     * Gets audio duration in milliseconds using FFprobe.
     * Approximate — uses file size and expected bitrate if probe fails.
     */
    suspend fun getDurationMs(opusFile: File): Long = withContext(Dispatchers.IO) {
        // TODO: Re-enable when FFmpegKit is added
        // Estimate duration based on file size (rough approximation)
        val estimatedDurationMs = (opusFile.length() / 16000.0 * 1000).toLong()
        Timber.w("OpusConverter: FFmpegKit not available - using estimated duration")
        return@withContext estimatedDurationMs
        
        /* Original FFprobe implementation - commented out
        var durationMs = 0L
        val cmd = "-v quiet -print_format json -show_streams \"${opusFile.absolutePath}\""
        val session = com.arthenica.ffmpegkit.FFprobeKit.execute(cmd)
        try {
            val output = session.output ?: ""
            val regex = """"duration"\s*:\s*"([\d.]+)"""".toRegex()
            val match = regex.find(output)
            if (match != null) {
                durationMs = (match.groupValues[1].toDouble() * 1000).toLong()
            }
        } catch (e: Exception) {
            Timber.w(e, "OpusConverter: could not parse duration")
        }
        durationMs
        */
    }
}
