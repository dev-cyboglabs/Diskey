package com.cyboglabs.diskey.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadHistoryDao {
    @Query("SELECT * FROM download_history ORDER BY startedAtMs DESC LIMIT 100")
    fun getRecentHistory(): Flow<List<DownloadHistoryEntity>>

    @Insert
    suspend fun insert(entry: DownloadHistoryEntity): Long

    @Query("DELETE FROM download_history WHERE id IN (SELECT id FROM download_history ORDER BY startedAtMs ASC LIMIT :count)")
    suspend fun trimOldest(count: Int)
}
