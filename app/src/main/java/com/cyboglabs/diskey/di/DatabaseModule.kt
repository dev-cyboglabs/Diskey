package com.cyboglabs.diskey.di

import android.content.Context
import androidx.room.Room
import com.cyboglabs.diskey.data.db.AppDatabase
import com.cyboglabs.diskey.data.db.dao.AudioFileDao
import com.cyboglabs.diskey.data.db.dao.DeviceDao
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "diskey.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideDeviceDao(db: AppDatabase): DeviceDao = db.deviceDao()
    @Provides fun provideAudioFileDao(db: AppDatabase): AudioFileDao = db.audioFileDao()
    @Provides fun provideDownloadHistoryDao(db: AppDatabase): DownloadHistoryDao = db.downloadHistoryDao()
    @Provides fun providePlaybackHistoryDao(db: AppDatabase): PlaybackHistoryDao = db.playbackHistoryDao()
}
