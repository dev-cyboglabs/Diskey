package com.cyboglabs.diskey.domain.repository

import com.cyboglabs.diskey.domain.model.AudioFile
import kotlinx.coroutines.flow.Flow

interface AudioFileRepository {
    fun getFilesForDevice(deviceAddress: String): Flow<List<AudioFile>>
    fun getDownloadedFiles(): Flow<List<AudioFile>>
    suspend fun getFile(filename: String): AudioFile?
    suspend fun saveFiles(files: List<AudioFile>, deviceAddress: String)
    suspend fun markAsDownloaded(filename: String, localPath: String)
    suspend fun deleteFile(filename: String)
    suspend fun deleteAllForDevice(deviceAddress: String)
    suspend fun getResumePosition(filename: String): Long
}
