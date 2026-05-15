package com.cyboglabs.diskey.presentation.dashboard;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.audio.SyncPhase;
import com.cyboglabs.diskey.audio.SyncState;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.BleService;
import com.cyboglabs.diskey.ble.protocol.PacketBuilder;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0015J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\b\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\u0006\u0010\u001d\u001a\u00020\u0015R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lcom/cyboglabs/diskey/presentation/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "syncManager", "Lcom/cyboglabs/diskey/audio/SyncManager;", "appPreferences", "Lcom/cyboglabs/diskey/data/datastore/AppPreferences;", "(Landroid/content/Context;Lcom/cyboglabs/diskey/ble/BleConnectionManager;Lcom/cyboglabs/diskey/audio/SyncManager;Lcom/cyboglabs/diskey/data/datastore/AppPreferences;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/presentation/dashboard/DashboardUiState;", "hasAutoSynced", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "cancelSync", "", "disconnect", "enableWifi", "loadPairedDevice", "observeBattery", "observeConnectionState", "observeSync", "requestBattery", "syncFiles", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.audio.SyncManager syncManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.presentation.dashboard.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.dashboard.DashboardUiState> uiState = null;
    private boolean hasAutoSynced = false;
    
    @javax.inject.Inject()
    public DashboardViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.SyncManager syncManager, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.dashboard.DashboardUiState> getUiState() {
        return null;
    }
    
    private final void observeConnectionState() {
    }
    
    private final void observeBattery() {
    }
    
    private final void observeSync() {
    }
    
    private final void requestBattery() {
    }
    
    private final void loadPairedDevice() {
    }
    
    /**
     * Kick off a full SD card sync:
     *  1. Request file list from device
     *  2. Download all files not already on this phone
     */
    public final void syncFiles() {
    }
    
    public final void cancelSync() {
    }
    
    public final void enableWifi() {
    }
    
    public final void disconnect() {
    }
}