package com.cyboglabs.diskey.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playback_history")
data class PlaybackHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val filename: String,
    val playedAtMs: Long,
    val durationPlayedMs: Long,
    val positionMs: Long  // resume position
)
