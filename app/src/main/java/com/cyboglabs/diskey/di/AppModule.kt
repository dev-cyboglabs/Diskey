package com.cyboglabs.diskey.di

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import com.cyboglabs.diskey.data.repository.AudioFileRepositoryImpl
import com.cyboglabs.diskey.data.repository.DeviceRepositoryImpl
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import com.cyboglabs.diskey.domain.repository.DeviceRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindDeviceRepository(impl: DeviceRepositoryImpl): DeviceRepository

    @Binds
    @Singleton
    abstract fun bindAudioFileRepository(impl: AudioFileRepositoryImpl): AudioFileRepository

    companion object {

        @Provides
        @Singleton
        fun provideBluetoothAdapter(@ApplicationContext context: Context): BluetoothAdapter {
            val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            return manager.adapter
        }

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().setLenient().create()
    }
}
