package com.cyboglabs.diskey.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaybackHistoryDao {
    @Query("SELECT * FROM playback_history ORDER BY playedAtMs DESC LIMIT 50")
    fun getRecentPlayback(): Flow<List<PlaybackHistoryEntity>>

    @Query("SELECT positionMs FROM playback_history WHERE filename = :filename ORDER BY playedAtMs DESC LIMIT 1")
    suspend fun getResumePosition(filename: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: PlaybackHistoryEntity): Long
}
