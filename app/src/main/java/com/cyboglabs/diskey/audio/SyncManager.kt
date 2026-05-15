package com.cyboglabs.diskey.audio

import android.content.Context
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import com.cyboglabs.diskey.domain.model.AudioDataPacket
import com.cyboglabs.diskey.domain.model.AudioFile
import com.cyboglabs.diskey.domain.model.FileCompletePacket
import com.cyboglabs.diskey.domain.model.FileListPacket
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import com.cyboglabs.diskey.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

enum class SyncPhase { IDLE, FETCHING_LIST, DOWNLOADING, COMPLETE, ERROR }

data class SyncState(
    val phase: SyncPhase = SyncPhase.IDLE,
    val totalFiles: Int = 0,
    val newFilesCount: Int = 0,
    val downloadedFiles: Int = 0,
    val currentFile: String = "",
    val currentFileProgress: Float = 0f,
    val errorMessage: String? = null,
    val filesOnDevice: List<AudioFile> = emptyList()
) {
    val overallProgress: Float
        get() = if (newFilesCount > 0) downloadedFiles.toFloat() / newFilesCount else 0f

    val statusText: String
        get() = when (phase) {
            SyncPhase.IDLE -> "Tap Sync to transfer recordings from device"
            SyncPhase.FETCHING_LIST -> "Reading file list from device SD card…"
            SyncPhase.DOWNLOADING ->
                "Transferring ${currentFile.removeSuffix(".opus")} " +
                    "(${downloadedFiles + 1} of $newFilesCount)"
            SyncPhase.COMPLETE -> when {
                totalFiles == 0 -> "No recordings found on device SD card"
                newFilesCount == 0 ->
                    "All $totalFiles recording${if (totalFiles != 1) "s" else ""} already on phone"
                else ->
                    "Transferred $downloadedFiles of $newFilesCount new recording${if (newFilesCount != 1) "s" else ""}"
            }
            SyncPhase.ERROR -> "Transfer failed: $errorMessage"
        }
}

/**
 * Orchestrates the complete SD card → phone transfer:
 *
 *   1. Subscribe to BLE packet flow FIRST (prevents race-condition where fast
 *      device responses arrive before the listener is ready)
 *   2. Send GET_FILE_LIST — device replies with one JSON packet per SD card file
 *   3. Collect until 3 s of silence (end-of-list signal)
 *   4. For each file not already on this phone:
 *        – Send DOWNLOAD_FILE
 *        – Collect AudioDataPackets into OpusFileManager buffer
 *        – FileCompletePacket triggers CRC validation + disk write
 */
@Singleton
class SyncManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bleConnectionManager: BleConnectionManager,
    private val audioFileRepository: AudioFileRepository,
    private val opusFileManager: OpusFileManager,
    private val gson: Gson
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _syncState = kotlinx.coroutines.flow.MutableStateFlow(SyncState())
    val syncState: kotlinx.coroutines.flow.StateFlow<SyncState> = _syncState.asStateFlow()

    private var syncJob: Job? = null

    // ─── Public API ────────────────────────────────────────────────────────────

    fun startSync(deviceAddress: String) {
        if (_syncState.value.phase == SyncPhase.FETCHING_LIST ||
            _syncState.value.phase == SyncPhase.DOWNLOADING) {
            Timber.w("SyncManager: sync already in progress — ignored")
            return
        }
        // Reset before starting a fresh sync
        _syncState.value = SyncState(phase = SyncPhase.FETCHING_LIST)
        syncJob?.cancel()
        syncJob = scope.launch { runSync(deviceAddress) }
    }

    fun cancel() {
        syncJob?.cancel()
        opusFileManager.cancel()
        _syncState.value = SyncState()
        Timber.d("SyncManager: cancelled")
    }

    // ─── Sync pipeline ─────────────────────────────────────────────────────────

    private suspend fun runSync(deviceAddress: String) {
        val deviceFiles = fetchFileList(deviceAddress)

        if (deviceFiles.isEmpty()) {
            _syncState.value = SyncState(phase = SyncPhase.COMPLETE, totalFiles = 0, newFilesCount = 0)
            Timber.i("SyncManager: device SD card appears empty")
            return
        }

        // Identify files not yet on this phone
        val missing = deviceFiles.filter { file ->
            val f = FileUtils.getOpusFile(context, file.filename)
            !f.exists() || f.length() == 0L
        }

        Timber.i("SyncManager: ${deviceFiles.size} on device, ${missing.size} to transfer")

        if (missing.isEmpty()) {
            _syncState.value = SyncState(
                phase = SyncPhase.COMPLETE,
                filesOnDevice = deviceFiles,
                totalFiles = deviceFiles.size,
                newFilesCount = 0,
                downloadedFiles = 0
            )
            return
        }

        _syncState.value = SyncState(
            phase = SyncPhase.DOWNLOADING,
            filesOnDevice = deviceFiles,
            totalFiles = deviceFiles.size,
            newFilesCount = missing.size,
            downloadedFiles = 0
        )

        missing.forEachIndexed { idx, audioFile ->
            _syncState.value = _syncState.value.copy(
                currentFile = audioFile.filename,
                currentFileProgress = 0f,
                downloadedFiles = idx
            )

            val success = downloadFile(audioFile)

            if (success) {
                audioFileRepository.markAsDownloaded(
                    audioFile.filename,
                    FileUtils.getOpusFile(context, audioFile.filename).absolutePath
                )
                _syncState.value = _syncState.value.copy(downloadedFiles = idx + 1)
                Timber.i("SyncManager: ✓ ${audioFile.filename}")
            } else {
                Timber.e("SyncManager: ✗ ${audioFile.filename} (skipping)")
            }
        }

        _syncState.value = _syncState.value.copy(
            phase = SyncPhase.COMPLETE,
            currentFile = "",
            currentFileProgress = 1f
        )
        Timber.i("SyncManager: sync complete — ${_syncState.value.downloadedFiles}/${missing.size} transferred")
    }

    // ─── Phase 1: file list ────────────────────────────────────────────────────

    /**
     * CRITICAL: We subscribe to the packet flow BEFORE sending the BLE command.
     * If the command is sent first, fast device responses can arrive before
     * [collect] starts — those emissions are lost (SharedFlow has no replay cache).
     *
     * Fix: launch the listener coroutine → [delay] 80 ms (enough for the
     * subscription to be active inside the Kotlin coroutine dispatcher) →
     * then send the BLE command.
     */
    private suspend fun fetchFileList(deviceAddress: String): List<AudioFile> {
        _syncState.value = SyncState(phase = SyncPhase.FETCHING_LIST)

        val files = mutableListOf<AudioFile>()
        // Unlimited channel bridges the listener coroutine to this coroutine
        val fileChannel = Channel<AudioFile>(Channel.UNLIMITED)

        // Step 1 — subscribe FIRST
        val listenerJob = scope.launch {
            bleConnectionManager.packets
                .filterIsInstance<FileListPacket>()
                .collect { packet ->
                    parseFileEntry(packet.json)?.let { fileChannel.trySend(it) }
                }
        }

        // Step 2 — give the subscription time to activate
        delay(80)

        // Step 3 — now send the BLE command
        bleConnectionManager.sendCommand(PacketBuilder.getFileList())
        Timber.d("SyncManager: GET_FILE_LIST sent — waiting for device SD card entries")

        // Step 4 — drain channel; treat 3 s of silence as end-of-list
        val IDLE_TIMEOUT_MS = 3_000L
        val TOTAL_TIMEOUT_MS = 30_000L

        withTimeoutOrNull(TOTAL_TIMEOUT_MS) {
            while (isActive) {
                val file = withTimeoutOrNull(IDLE_TIMEOUT_MS) { fileChannel.receive() }
                    ?: break   // 3 s of silence — device is done sending the list
                files.add(file)
                _syncState.value = _syncState.value.copy(
                    filesOnDevice = files.toList(),
                    totalFiles = files.size
                )
                Timber.d("SyncManager: SD card entry [${files.size}] ${file.filename}")
            }
        }

        listenerJob.cancel()
        fileChannel.close()

        // Persist discovered file list so the file browser can show it immediately
        if (files.isNotEmpty()) {
            Timber.d("SyncManager: saving ${files.size} files to database with address: '$deviceAddress'")
            audioFileRepository.saveFiles(files, deviceAddress)
            Timber.d("SyncManager: files saved successfully")
        }

        Timber.i("SyncManager: file list complete — ${files.size} entries")
        return files
    }

    // ─── Phase 2: file download ────────────────────────────────────────────────

    /**
     * Same subscribe-before-send discipline applied to audio data collection.
     *
     * Waits for [FileCompletePacket] using [first] (suspends until packet arrives
     * or the per-file timeout elapses — no busy-poll loop).
     */
    private suspend fun downloadFile(audioFile: AudioFile): Boolean {
        opusFileManager.beginFile(audioFile.filename)

        val approxPackets = ((audioFile.sizeBytes / 512L).coerceAtLeast(1L)).toInt()

        // Subscribe to audio-data stream BEFORE sending the download command
        val audioJob = scope.launch {
            bleConnectionManager.packets
                .filterIsInstance<AudioDataPacket>()
                .collect { pkt ->
                    opusFileManager.addPacket(pkt.index, pkt.data)
                    _syncState.value = _syncState.value.copy(
                        currentFileProgress = (opusFileManager.bufferedPacketCount()
                            .toFloat() / approxPackets).coerceIn(0f, 0.99f)
                    )
                }
        }

        // 80 ms head-start for subscription, then send command
        delay(80)
        bleConnectionManager.sendCommand(PacketBuilder.downloadFile(audioFile.filename))
        Timber.d("SyncManager: DOWNLOAD_FILE sent → ${audioFile.filename}")

        // Wait for the single file-complete packet (clean, no polling)
        val PER_FILE_TIMEOUT_MS = 5 * 60_000L
        val completePacket = withTimeoutOrNull(PER_FILE_TIMEOUT_MS) {
            bleConnectionManager.packets
                .filterIsInstance<FileCompletePacket>()
                .first()
        }

        audioJob.cancel()

        if (completePacket == null) {
            Timber.e("SyncManager: timed out waiting for FileCompletePacket — ${audioFile.filename}")
            opusFileManager.cancel()
            return false
        }

        _syncState.value = _syncState.value.copy(currentFileProgress = 1f)

        val savedFile = opusFileManager.finalizeFile(completePacket.crc)
        return savedFile != null
    }

    // ─── Helpers ────────────────────────────────────────────────────────────────

    private fun parseFileEntry(json: String): AudioFile? = try {
        val obj = gson.fromJson(json, JsonObject::class.java)
        val filename = obj.get("file")?.asString ?: return null
        AudioFile(
            filename = filename,
            sizeBytes = obj.get("size")?.asLong ?: 0L,
            createdAtEpoch = obj.get("creat_time")?.asLong ?: 0L,
            durationMs = obj.get("duration_ms")?.asLong ?: 0L,
            index = obj.get("index")?.asInt ?: 0
        )
    } catch (e: Exception) {
        Timber.e(e, "SyncManager: bad JSON → $json")
        null
    }
}

// Needed because the class uses both MutableStateFlow and StateFlow without import alias
private fun <T> kotlinx.coroutines.flow.MutableStateFlow<T>.asStateFlow() =
    this as kotlinx.coroutines.flow.StateFlow<T>
