package com.cyboglabs.diskey.ble;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import com.cyboglabs.diskey.R;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import com.cyboglabs.diskey.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

/**
 * Foreground service that maintains the BLE connection to the T240 device.
 * Runs as long as the user is connected; handles reconnect with exponential backoff.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0002#$B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\"\u0010\u001f\u001a\u00020 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 H\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleService;", "Landroidx/lifecycle/LifecycleService;", "()V", "binder", "Lcom/cyboglabs/diskey/ble/BleService$LocalBinder;", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "getBleConnectionManager", "()Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "setBleConnectionManager", "(Lcom/cyboglabs/diskey/ble/BleConnectionManager;)V", "bleDeviceManager", "Lcom/cyboglabs/diskey/ble/BleDeviceManager;", "getBleDeviceManager", "()Lcom/cyboglabs/diskey/ble/BleDeviceManager;", "setBleDeviceManager", "(Lcom/cyboglabs/diskey/ble/BleDeviceManager;)V", "reconnectJob", "Lkotlinx/coroutines/Job;", "buildNotification", "Landroid/app/Notification;", "text", "", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "Companion", "LocalBinder", "app_debug"})
public final class BleService extends androidx.lifecycle.LifecycleService {
    @javax.inject.Inject()
    public com.cyboglabs.diskey.ble.BleDeviceManager bleDeviceManager;
    @javax.inject.Inject()
    public com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleService.LocalBinder binder = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job reconnectJob;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CONNECT = "com.cyboglabs.diskey.ACTION_CONNECT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_DISCONNECT = "com.cyboglabs.diskey.ACTION_DISCONNECT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ADDRESS = "device_address";
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "diskey_ble";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.ble.BleService.Companion Companion = null;
    
    public BleService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.ble.BleDeviceManager getBleDeviceManager() {
        return null;
    }
    
    public final void setBleDeviceManager(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleDeviceManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.ble.BleConnectionManager getBleConnectionManager() {
        return null;
    }
    
    public final void setBleConnectionManager(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager p0) {
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
    
    private final void createNotificationChannel() {
    }
    
    private final android.app.Notification buildNotification(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleService$Companion;", "", "()V", "ACTION_CONNECT", "", "ACTION_DISCONNECT", "CHANNEL_ID", "EXTRA_ADDRESS", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/cyboglabs/diskey/ble/BleService;)V", "getService", "Lcom/cyboglabs/diskey/ble/BleService;", "app_debug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cyboglabs.diskey.ble.BleService getService() {
            return null;
        }
    }
}