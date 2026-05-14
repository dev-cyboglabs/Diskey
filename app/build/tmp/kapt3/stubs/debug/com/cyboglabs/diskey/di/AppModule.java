package com.cyboglabs.diskey.di;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.cyboglabs.diskey.data.repository.AudioFileRepositoryImpl;
import com.cyboglabs.diskey.data.repository.DeviceRepositoryImpl;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/cyboglabs/diskey/di/AppModule;", "", "()V", "bindAudioFileRepository", "Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;", "impl", "Lcom/cyboglabs/diskey/data/repository/AudioFileRepositoryImpl;", "bindDeviceRepository", "Lcom/cyboglabs/diskey/domain/repository/DeviceRepository;", "Lcom/cyboglabs/diskey/data/repository/DeviceRepositoryImpl;", "Companion", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.di.AppModule.Companion Companion = null;
    
    public AppModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.domain.repository.DeviceRepository bindDeviceRepository(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.repository.DeviceRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.domain.repository.AudioFileRepository bindAudioFileRepository(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.repository.AudioFileRepositoryImpl impl);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lcom/cyboglabs/diskey/di/AppModule$Companion;", "", "()V", "provideBluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "context", "Landroid/content/Context;", "provideGson", "Lcom/google/gson/Gson;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final android.bluetooth.BluetoothAdapter provideBluetoothAdapter(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.google.gson.Gson provideGson() {
            return null;
        }
    }
}