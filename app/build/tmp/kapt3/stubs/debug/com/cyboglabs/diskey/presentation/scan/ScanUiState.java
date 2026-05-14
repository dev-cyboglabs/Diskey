package com.cyboglabs.diskey.presentation.scan;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.BleService;
import com.cyboglabs.diskey.ble.scanner.BleScanner;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import com.cyboglabs.diskey.domain.model.Device;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u00c6\u0001J\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\nH\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0017R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f\u00a8\u0006%"}, d2 = {"Lcom/cyboglabs/diskey/presentation/scan/ScanUiState;", "", "isScanning", "", "devices", "", "Lcom/cyboglabs/diskey/domain/model/Device;", "connectionState", "Lcom/cyboglabs/diskey/domain/model/ConnectionState;", "connectingAddress", "", "error", "searchQuery", "(ZLjava/util/List;Lcom/cyboglabs/diskey/domain/model/ConnectionState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getConnectingAddress", "()Ljava/lang/String;", "getConnectionState", "()Lcom/cyboglabs/diskey/domain/model/ConnectionState;", "getDevices", "()Ljava/util/List;", "getError", "filteredDevices", "getFilteredDevices", "()Z", "getSearchQuery", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ScanUiState {
    private final boolean isScanning = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.cyboglabs.diskey.domain.model.Device> devices = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.domain.model.ConnectionState connectionState = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String connectingAddress = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String searchQuery = null;
    
    public ScanUiState(boolean isScanning, @org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.domain.model.Device> devices, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.ConnectionState connectionState, @org.jetbrains.annotations.Nullable()
    java.lang.String connectingAddress, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery) {
        super();
    }
    
    public final boolean isScanning() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.Device> getDevices() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.domain.model.ConnectionState getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConnectingAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.Device> getFilteredDevices() {
        return null;
    }
    
    public ScanUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.Device> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.domain.model.ConnectionState component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.scan.ScanUiState copy(boolean isScanning, @org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.domain.model.Device> devices, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.ConnectionState connectionState, @org.jetbrains.annotations.Nullable()
    java.lang.String connectingAddress, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery) {
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