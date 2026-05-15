package com.cyboglabs.diskey.presentation.files

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.audio.AudioDownloadService
import com.cyboglabs.diskey.audio.SyncManager
import com.cyboglabs.diskey.audio.SyncPhase
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.model.AudioFile
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import com.cyboglabs.diskey.utils.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

enum class SortOrder { DATE_DESC, DATE_ASC, SIZE_DESC, NAME }

data class FileBrowserUiState(
    val files: List<AudioFile> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val sortOrder: SortOrder = SortOrder.DATE_DESC,
    val selectedFiles: Set<String> = emptySet(),
    val error: String? = null,
    val downloadingFiles: Set<String> = emptySet()  // files currently downloading
) {
    val filteredFiles: List<AudioFile> get() {
        val filtered = if (searchQuery.isBlank()) files
        else files.filter { it.filename.contains(searchQuery, ignoreCase = true) }
        return when (sortOrder) {
            SortOrder.DATE_DESC -> filtered.sortedByDescending { it.createdAtEpoch }
            SortOrder.DATE_ASC  -> filtered.sortedBy { it.createdAtEpoch }
            SortOrder.SIZE_DESC -> filtered.sortedByDescending { it.sizeBytes }
            SortOrder.NAME      -> filtered.sortedBy { it.filename }
        }
    }

    // Separate lists for clean UI rendering
    val playableFiles: List<AudioFile> get() = filteredFiles.filter { it.isDownloaded }
    val pendingFiles: List<AudioFile> get() = filteredFiles.filter { !it.isDownloaded }
    val hasAnyFiles: Boolean get() = files.isNotEmpty()
}

@HiltViewModel
class FileBrowserViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val audioFileRepository: AudioFileRepository,
    private val syncManager: SyncManager,
    private val appPreferences: AppPreferences,
    private val bleConnectionManager: BleConnectionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(FileBrowserUiState())
    val uiState: StateFlow<FileBrowserUiState> = _uiState.asStateFlow()

    init {
        loadFilesFromDatabase()
        observeSyncCompletion()
    }

    /**
     * Primary load: read all known files from Room DB.
     * Files that exist on disk are marked isDownloaded = true.
     * This shows the full picture: previously synced files are immediately visible.
     */
    private fun loadFilesFromDatabase() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            // Use same logic as DashboardViewModel: if no paired address, use "current_device"
            val address = appPreferences.pairedAddress.first().orEmpty().ifBlank { "current_device" }

            audioFileRepository.getFilesForDevice(address).collect { dbFiles ->
                // Cross-check each file against the actual filesystem
                val enriched = dbFiles.map { file ->
                    val localFile = FileUtils.getOpusFile(context, file.filename)
                    file.copy(
                        isDownloaded = localFile.exists() && localFile.length() > 0L,
                        localPath = if (localFile.exists()) localFile.absolutePath else null
                    )
                }
                _uiState.update { it.copy(files = enriched, isLoading = false) }
                Timber.d("FileBrowserViewModel: loaded ${enriched.size} files from DB")
            }
        }
    }

    /**
     * Force reload using one-shot query to get files directly from database
     * This ensures files are loaded even if the Flow doesn't emit.
     */
    suspend fun forceReloadSync() {
        _uiState.update { it.copy(isLoading = true) }
        val address = appPreferences.pairedAddress.first().orEmpty().ifBlank { "current_device" }
        Timber.d("FileBrowserViewModel: force reloading files for address: '$address'")
        val dbFiles = audioFileRepository.getFilesForDeviceList(address)
        Timber.d("FileBrowserViewModel: got ${dbFiles.size} files from database for address '$address'")
        
        // Cross-check each file against the actual filesystem
        val enriched = dbFiles.map { file ->
            val localFile = FileUtils.getOpusFile(context, file.filename)
            file.copy(
                isDownloaded = localFile.exists() && localFile.length() > 0L,
                localPath = if (localFile.exists()) localFile.absolutePath else null
            )
        }
        Timber.d("FileBrowserViewModel: after filesystem check - ${enriched.count { it.isDownloaded }} downloaded, ${enriched.count { !it.isDownloaded }} not downloaded")
        _uiState.update { it.copy(files = enriched, isLoading = false) }
    }

    /**
     * When the SyncManager finishes a sync cycle, reload the file list from database
     * so newly synced files show up immediately.
     */
    private fun observeSyncCompletion() {
        viewModelScope.launch {
            syncManager.syncState.collect { syncState ->
                if (syncState.phase == SyncPhase.COMPLETE) {
                    // Reload files from database to get newly synced files
                    loadFilesFromDatabase()
                }

                // Track which files are currently downloading so we can show a spinner
                if (syncState.phase == SyncPhase.DOWNLOADING) {
                    _uiState.update { state ->
                        state.copy(downloadingFiles = state.downloadingFiles + syncState.currentFile)
                    }
                } else {
                    _uiState.update { it.copy(downloadingFiles = emptySet()) }
                }
            }
        }
    }

    /**
     * Re-scan the filesystem to flip isDownloaded for files that just arrived.
     * Called after a sync completes.
     */
    private fun refreshLocalFileState() {
        val current = _uiState.value.files
        val refreshed = current.map { file ->
            val localFile = FileUtils.getOpusFile(context, file.filename)
            file.copy(
                isDownloaded = localFile.exists() && localFile.length() > 0L,
                localPath = if (localFile.exists()) localFile.absolutePath else null
            )
        }
        _uiState.update { it.copy(files = refreshed) }
        Timber.d("FileBrowserViewModel: refreshed local state — ${refreshed.count { it.isDownloaded }} playable")
    }

    // ─── Individual file download (manual, per-file) ─────────────────────────

    fun downloadFile(filename: String) {
        _uiState.update { it.copy(downloadingFiles = it.downloadingFiles + filename) }
        val intent = Intent(context, AudioDownloadService::class.java).apply {
            putExtra(AudioDownloadService.EXTRA_FILENAME, filename)
        }
        context.startForegroundService(intent)
        Timber.d("FileBrowserViewModel: manual download for '$filename'")
    }

    fun downloadSelected() {
        _uiState.value.selectedFiles.forEach { downloadFile(it) }
        _uiState.update { it.copy(selectedFiles = emptySet()) }
    }

    // ─── Selection ────────────────────────────────────────────────────────────

    fun toggleSelect(filename: String) {
        _uiState.update { state ->
            val sel = state.selectedFiles.toMutableSet()
            if (filename in sel) sel.remove(filename) else sel.add(filename)
            state.copy(selectedFiles = sel)
        }
    }

    fun clearSelection() = _uiState.update { it.copy(selectedFiles = emptySet()) }

    // ─── Delete files ───────────────────────────────────────────────────────────

    fun deleteFile(filename: String) {
        viewModelScope.launch {
            try {
                // Delete from SD card via BLE
                bleConnectionManager.deleteFile(filename)
                Timber.d("FileBrowserViewModel: sent delete command to device for '$filename'")

                // Delete from local filesystem
                val localFile = FileUtils.getOpusFile(context, filename)
                if (localFile.exists()) {
                    localFile.delete()
                    Timber.d("FileBrowserViewModel: deleted local file '$filename'")
                }

                // Delete from database
                audioFileRepository.deleteFile(filename)
                Timber.d("FileBrowserViewModel: deleted file from database '$filename'")

                // Refresh file list
                refreshLocalFileState()
            } catch (e: Exception) {
                Timber.e(e, "FileBrowserViewModel: failed to delete file '$filename'")
            }
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            _uiState.value.selectedFiles.forEach { filename ->
                try {
                    // Delete from SD card via BLE
                    bleConnectionManager.deleteFile(filename)
                    Timber.d("FileBrowserViewModel: sent delete command to device for '$filename'")

                    // Delete from local filesystem
                    val localFile = FileUtils.getOpusFile(context, filename)
                    if (localFile.exists()) {
                        localFile.delete()
                        Timber.d("FileBrowserViewModel: deleted local file '$filename'")
                    }

                    // Delete from database
                    audioFileRepository.deleteFile(filename)
                    Timber.d("FileBrowserViewModel: deleted file from database '$filename'")
                } catch (e: Exception) {
                    Timber.e(e, "FileBrowserViewModel: failed to delete file '$filename'")
                }
            }

            // Clear selection and refresh
            _uiState.update { it.copy(selectedFiles = emptySet()) }
            refreshLocalFileState()
        }
    }

    // ─── Filtering / sorting ──────────────────────────────────────────────────

    fun setSearchQuery(query: String) = _uiState.update { it.copy(searchQuery = query) }
    fun setSortOrder(order: SortOrder) = _uiState.update { it.copy(sortOrder = order) }

    // ─── Force reload ─────────────────────────────────────────────────────────

    fun forceReload() {
        viewModelScope.launch {
            forceReloadSync()
        }
    }
}
