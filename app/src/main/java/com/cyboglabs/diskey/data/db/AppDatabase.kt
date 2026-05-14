package com.cyboglabs.diskey.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyboglabs.diskey.data.db.dao.AudioFileDao
import com.cyboglabs.diskey.data.db.dao.DeviceDao
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity
import com.cyboglabs.diskey.data.db.entity.DeviceEntity
import com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity
import com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity

@Database(
    entities = [
        DeviceEntity::class,
        AudioFileEntity::class,
        DownloadHistoryEntity::class,
        PlaybackHistoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
    abstract fun audioFileDao(): AudioFileDao
    abstract fun downloadHistoryDao(): DownloadHistoryDao
    abstract fun playbackHistoryDao(): PlaybackHistoryDao
}
