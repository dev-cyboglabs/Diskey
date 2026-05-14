package com.cyboglabs.diskey.audio

import android.content.Context
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import com.cyboglabs.diskey.domain.model.AudioDataPacket
import com.cyboglabs.diskey.domain.model.DownloadProgress
import com.cyboglabs.diskey.domain.model.DownloadState
import com.cyboglabs.diskey.domain.model.FileCompletePacket
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages BLE audio file downloads from the T240 device.
 *
 * Flow:
 *   1. Send CMD_DOWNLOAD_FILE + filename
 *   2. Receive AudioDataPackets (index, data)
 *   3. Buffer packets via OpusFileManager
 *   4. On FileCompletePacket: validate CRC, write to disk, update DB
 */
@Singleton
class AudioDownloadManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bleConnectionManager: BleConnectionManager,
    private val opusFileManager: OpusFileManager,
    private val audioFileRepository: AudioFileRepository
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _progress = MutableStateFlow<DownloadProgress?>(null)
    val progress: StateFlow<DownloadProgress?> = _progress.asStateFlow()

    private val downloadQueue = ArrayDeque<String>()
    private var isDownloading = false

    fun enqueue(filename: String) {
        if (downloadQueue.contains(filename)) return
        downloadQueue.addLast(filename)
        Timber.d("AudioDownloadManager: queued '$filename' (queue size=${downloadQueue.size})")
        if (!isDownloading) processNext()
    }

    private fun processNext() {
        val filename = downloadQueue.removeFirstOrNull() ?: return
        isDownloading = true
        scope.launch {
            downloadFile(filename)
            isDownloading = false
            processNext()
        }
    }

    private suspend fun downloadFile(filename: String) {
        if (bleConnectionManager.connectionState.value != ConnectionState.CONNECTED) {
            Timber.e("AudioDownloadManager: cannot download - BLE not connected")
            _progress.value = DownloadProgress(filename, 0, -1, DownloadState.FAILED, "Device not connected")
            return
        }

        Timber.i("AudioDownloadManager: starting download '$filename'")
        opusFileManager.beginFile(filename)

        _progress.value = DownloadProgress(filename, 0, -1, DownloadState.DOWNLOADING)

        // Send download command
        bleConnectionManager.sendCommand(PacketBuilder.downloadFile(filename))

        // Collect audio packets with a timeout
        val downloadTimeout = 5 * 60 * 1000L // 5 minutes max

        val completePacket = withTimeoutOrNull(downloadTimeout) {
            coroutineScope {
                val audioJob = launch {
                    bleConnectionManager.packets
                        .filterIsInstance<AudioDataPacket>()
                        .collect { pkt ->
                            opusFileManager.addPacket(pkt.index, pkt.data)
                            _progress.value = _progress.value?.copy(
                                receivedPackets = opusFileManager.bufferedPacketCount()
                            )
                        }
                }

                val complete = bleConnectionManager.packets
                    .filterIsInstance<FileCompletePacket>()
                    .first()

                audioJob.cancelAndJoin()
                complete
            }
        }

        if (completePacket == null) {
            Timber.e("AudioDownloadManager: timeout waiting for file complete '$filename'")
            opusFileManager.cancel()
            _progress.value = _progress.value?.copy(state = DownloadState.FAILED, errorMessage = "Timeout")
            return
        }

        val savedFile = opusFileManager.finalizeFile(completePacket.crc)

        if (savedFile != null && savedFile.length() > 0L) {
            Timber.i("AudioDownloadManager: '$filename' saved to ${savedFile.path}")
            _progress.value = DownloadProgress(
                filename, opusFileManager.bufferedPacketCount(),
                opusFileManager.bufferedPacketCount(), DownloadState.COMPLETED
            )
            audioFileRepository.markAsDownloaded(filename, savedFile.absolutePath)
        } else {
            Timber.e("AudioDownloadManager: '$filename' download failed")
            _progress.value = _progress.value?.copy(state = DownloadState.FAILED)
        }
    }

    fun cancelAll() {
        downloadQueue.clear()
        opusFileManager.cancel()
        isDownloading = false
        _progress.value = null
    }
}
