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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u000bH\u00c6\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001J\t\u0010 \u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006!"}, d2 = {"Lcom/cyboglabs/diskey/presentation/dashboard/DashboardUiState;", "", "connectionState", "Lcom/cyboglabs/diskey/domain/model/ConnectionState;", "batteryLevel", "", "isWifiEnabled", "", "syncState", "Lcom/cyboglabs/diskey/audio/SyncState;", "deviceAddress", "", "(Lcom/cyboglabs/diskey/domain/model/ConnectionState;IZLcom/cyboglabs/diskey/audio/SyncState;Ljava/lang/String;)V", "getBatteryLevel", "()I", "getConnectionState", "()Lcom/cyboglabs/diskey/domain/model/ConnectionState;", "getDeviceAddress", "()Ljava/lang/String;", "isSyncing", "()Z", "getSyncState", "()Lcom/cyboglabs/diskey/audio/SyncState;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DashboardUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.domain.model.ConnectionState connectionState = null;
    private final int batteryLevel = 0;
    private final boolean isWifiEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.audio.SyncState syncState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String deviceAddress = null;
    
    public DashboardUiState(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.ConnectionState connectionState, int batteryLevel, boolean isWifiEnabled, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.SyncState syncState, @org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.domain.model.ConnectionState getConnectionState() {
        return null;
    }
    
    public final int getBatteryLevel() {
        return 0;
    }
    
    public final boolean isWifiEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.audio.SyncState getSyncState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceAddress() {
        return null;
    }
    
    public final boolean isSyncing() {
        return false;
    }
    
    public DashboardUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.domain.model.ConnectionState component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.audio.SyncState component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.dashboard.DashboardUiState copy(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.ConnectionState connectionState, int batteryLevel, boolean isWifiEnabled, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.SyncState syncState, @org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}