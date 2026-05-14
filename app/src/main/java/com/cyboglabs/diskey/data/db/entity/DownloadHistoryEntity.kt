package com.cyboglabs.diskey.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_history")
data class DownloadHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val filename: String,
    val deviceAddress: String,
    val startedAtMs: Long,
    val completedAtMs: Long,
    val sizeBytes: Long,
    val success: Boolean,
    val errorMessage: String?
)
