package com.cyboglabs.diskey.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioFileDao {
    @Query("SELECT * FROM audio_files WHERE deviceAddress = :deviceAddress ORDER BY createdAtEpoch DESC")
    fun getFilesForDevice(deviceAddress: String): Flow<List<AudioFileEntity>>

    @Query("SELECT * FROM audio_files WHERE deviceAddress = :deviceAddress ORDER BY createdAtEpoch DESC")
    suspend fun getFilesForDeviceList(deviceAddress: String): List<AudioFileEntity>

    @Query("SELECT * FROM audio_files WHERE filename = :filename LIMIT 1")
    suspend fun getFile(filename: String): AudioFileEntity?

    @Query("SELECT * FROM audio_files WHERE isDownloaded = 1 ORDER BY downloadedAtMs DESC")
    fun getDownloadedFiles(): Flow<List<AudioFileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(file: AudioFileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAll(files: List<AudioFileEntity>)

    @Query("UPDATE audio_files SET isDownloaded = 1, localPath = :localPath, downloadedAtMs = :timeMs WHERE filename = :filename")
    suspend fun markDownloaded(filename: String, localPath: String, timeMs: Long)

    @Query("DELETE FROM audio_files WHERE filename = :filename")
    suspend fun deleteFile(filename: String)

    @Query("DELETE FROM audio_files WHERE deviceAddress = :deviceAddress")
    suspend fun deleteAllForDevice(deviceAddress: String)
}
