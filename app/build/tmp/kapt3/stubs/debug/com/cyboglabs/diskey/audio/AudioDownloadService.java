package com.cyboglabs.diskey.audio;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import com.cyboglabs.diskey.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;
import javax.inject.Inject;

/**
 * Foreground service that runs audio file downloads in the background.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\"\u0010\u0018\u001a\u00020\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/cyboglabs/diskey/audio/AudioDownloadService;", "Landroidx/lifecycle/LifecycleService;", "()V", "audioDownloadManager", "Lcom/cyboglabs/diskey/audio/AudioDownloadManager;", "getAudioDownloadManager", "()Lcom/cyboglabs/diskey/audio/AudioDownloadManager;", "setAudioDownloadManager", "(Lcom/cyboglabs/diskey/audio/AudioDownloadManager;)V", "binder", "Lcom/cyboglabs/diskey/audio/AudioDownloadService$LocalBinder;", "buildNotification", "Landroid/app/Notification;", "text", "", "createChannel", "", "observeProgress", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "LocalBinder", "app_debug"})
public final class AudioDownloadService extends androidx.lifecycle.LifecycleService {
    @javax.inject.Inject()
    public com.cyboglabs.diskey.audio.AudioDownloadManager audioDownloadManager;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.audio.AudioDownloadService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_FILENAME = "filename";
    private static final int NOTIFICATION_ID = 1003;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "diskey_download";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.audio.AudioDownloadService.Companion Companion = null;
    
    public AudioDownloadService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.audio.AudioDownloadManager getAudioDownloadManager() {
        return null;
    }
    
    public final void setAudioDownloadManager(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.AudioDownloadManager p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void observeProgress() {
    }
    
    private final void createChannel() {
    }
    
    private final android.app.Notification buildNotification(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cyboglabs/diskey/audio/AudioDownloadService$Companion;", "", "()V", "CHANNEL_ID", "", "EXTRA_FILENAME", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/cyboglabs/diskey/audio/AudioDownloadService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/cyboglabs/diskey/audio/AudioDownloadService;)V", "getService", "Lcom/cyboglabs/diskey/audio/AudioDownloadService;", "app_debug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cyboglabs.diskey.audio.AudioDownloadService getService() {
            return null;
        }
    }
}