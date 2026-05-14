package com.cyboglabs.diskey.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_files")
data class AudioFileEntity(
    @PrimaryKey val filename: String,
    val sizeBytes: Long,
    val createdAtEpoch: Long,
    val durationMs: Long,
    val index: Int,
    val localPath: String?,
    val isDownloaded: Boolean,
    val deviceAddress: String,
    val downloadedAtMs: Long = 0L
)
