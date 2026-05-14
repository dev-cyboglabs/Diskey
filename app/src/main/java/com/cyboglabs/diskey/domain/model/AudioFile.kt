package com.cyboglabs.diskey.domain.model

data class AudioFile(
    val filename: String,
    val sizeBytes: Long,
    val createdAtEpoch: Long,
    val durationMs: Long,
    val index: Int,
    val localPath: String? = null,
    val isDownloaded: Boolean = false,
    val downloadProgress: Float = 0f
) {
    val displayName: String get() = filename.removeSuffix(".opus")
    val sizeDisplay: String get() {
        val kb = sizeBytes / 1024.0
        return if (kb < 1024) "%.1f KB".format(kb) else "%.2f MB".format(kb / 1024.0)
    }
    val durationDisplay: String get() {
        val totalSec = durationMs / 1000
        val min = totalSec / 60
        val sec = totalSec % 60
        return "%d:%02d".format(min, sec)
    }
}

enum class DownloadState { IDLE, QUEUED, DOWNLOADING, COMPLETED, FAILED }

data class DownloadProgress(
    val filename: String,
    val receivedPackets: Int,
    val totalPackets: Int,
    val state: DownloadState,
    val errorMessage: String? = null
) {
    val percent: Float get() = if (totalPackets > 0) receivedPackets.toFloat() / totalPackets else 0f
}
