package com.cyboglabs.diskey.wifi;

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
 * Foreground service that manages the WiFi TCP socket connection to the T240.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiService;", "Landroidx/lifecycle/LifecycleService;", "()V", "binder", "Lcom/cyboglabs/diskey/wifi/WifiService$LocalBinder;", "wifiTransferManager", "Lcom/cyboglabs/diskey/wifi/WifiTransferManager;", "getWifiTransferManager", "()Lcom/cyboglabs/diskey/wifi/WifiTransferManager;", "setWifiTransferManager", "(Lcom/cyboglabs/diskey/wifi/WifiTransferManager;)V", "buildNotification", "Landroid/app/Notification;", "createChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "LocalBinder", "app_debug"})
public final class WifiService extends androidx.lifecycle.LifecycleService {
    @javax.inject.Inject()
    public com.cyboglabs.diskey.wifi.WifiTransferManager wifiTransferManager;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.wifi.WifiService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CONNECT = "com.cyboglabs.diskey.WIFI_CONNECT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_DISCONNECT = "com.cyboglabs.diskey.WIFI_DISCONNECT";
    private static final int NOTIFICATION_ID = 1002;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "diskey_wifi";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.wifi.WifiService.Companion Companion = null;
    
    public WifiService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.wifi.WifiTransferManager getWifiTransferManager() {
        return null;
    }
    
    public final void setWifiTransferManager(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.wifi.WifiTransferManager p0) {
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
    
    private final void createChannel() {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiService$Companion;", "", "()V", "ACTION_CONNECT", "", "ACTION_DISCONNECT", "CHANNEL_ID", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/cyboglabs/diskey/wifi/WifiService;)V", "getService", "Lcom/cyboglabs/diskey/wifi/WifiService;", "app_debug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cyboglabs.diskey.wifi.WifiService getService() {
            return null;
        }
    }
}