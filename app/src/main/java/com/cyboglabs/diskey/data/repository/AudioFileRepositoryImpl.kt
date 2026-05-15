package com.cyboglabs.diskey.data.repository

import com.cyboglabs.diskey.data.db.dao.AudioFileDao
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity
import com.cyboglabs.diskey.domain.model.AudioFile
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AudioFileRepositoryImpl @Inject constructor(
    private val audioFileDao: AudioFileDao,
    private val playbackHistoryDao: PlaybackHistoryDao
) : AudioFileRepository {

    override fun getFilesForDevice(deviceAddress: String): Flow<List<AudioFile>> =
        audioFileDao.getFilesForDevice(deviceAddress).map { it.map { e -> e.toDomain() } }

    override suspend fun getFilesForDeviceList(deviceAddress: String): List<AudioFile> =
        audioFileDao.getFilesForDeviceList(deviceAddress).map { it.toDomain() }

    override fun getDownloadedFiles(): Flow<List<AudioFile>> =
        audioFileDao.getDownloadedFiles().map { it.map { e -> e.toDomain() } }

    override suspend fun getFile(filename: String): AudioFile? =
        audioFileDao.getFile(filename)?.toDomain()

    override suspend fun saveFiles(files: List<AudioFile>, deviceAddress: String) {
        audioFileDao.insertOrUpdateAll(files.map { it.toEntity(deviceAddress) })
    }

    override suspend fun markAsDownloaded(filename: String, localPath: String) {
        audioFileDao.markDownloaded(filename, localPath, System.currentTimeMillis())
    }

    override suspend fun deleteFile(filename: String) {
        audioFileDao.deleteFile(filename)
    }

    override suspend fun deleteAllForDevice(deviceAddress: String) {
        audioFileDao.deleteAllForDevice(deviceAddress)
    }

    override suspend fun getResumePosition(filename: String): Long =
        playbackHistoryDao.getResumePosition(filename) ?: 0L

    private fun AudioFileEntity.toDomain() = AudioFile(
        filename = filename,
        sizeBytes = sizeBytes,
        createdAtEpoch = createdAtEpoch,
        durationMs = durationMs,
        index = index,
        localPath = localPath,
        isDownloaded = isDownloaded
    )

    private fun AudioFile.toEntity(deviceAddress: String) = AudioFileEntity(
        filename = filename,
        sizeBytes = sizeBytes,
        createdAtEpoch = createdAtEpoch,
        durationMs = durationMs,
        index = index,
        localPath = localPath,
        isDownloaded = isDownloaded,
        deviceAddress = deviceAddress
    )
}
