package com.cyboglabs.diskey.presentation.settings;

import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J]\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0007H\u00d6\u0001J\t\u0010%\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f\u00a8\u0006&"}, d2 = {"Lcom/cyboglabs/diskey/presentation/settings/SettingsUiState;", "", "autoReconnect", "", "autoSync", "saveWav", "screenTimeoutMin", "", "autoPowerOffMin", "darkMode", "pairedDeviceName", "", "pairedDeviceAddress", "(ZZZIIZLjava/lang/String;Ljava/lang/String;)V", "getAutoPowerOffMin", "()I", "getAutoReconnect", "()Z", "getAutoSync", "getDarkMode", "getPairedDeviceAddress", "()Ljava/lang/String;", "getPairedDeviceName", "getSaveWav", "getScreenTimeoutMin", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class SettingsUiState {
    private final boolean autoReconnect = false;
    private final boolean autoSync = false;
    private final boolean saveWav = false;
    private final int screenTimeoutMin = 0;
    private final int autoPowerOffMin = 0;
    private final boolean darkMode = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pairedDeviceName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pairedDeviceAddress = null;
    
    public SettingsUiState(boolean autoReconnect, boolean autoSync, boolean saveWav, int screenTimeoutMin, int autoPowerOffMin, boolean darkMode, @org.jetbrains.annotations.Nullable()
    java.lang.String pairedDeviceName, @org.jetbrains.annotations.Nullable()
    java.lang.String pairedDeviceAddress) {
        super();
    }
    
    public final boolean getAutoReconnect() {
        return false;
    }
    
    public final boolean getAutoSync() {
        return false;
    }
    
    public final boolean getSaveWav() {
        return false;
    }
    
    public final int getScreenTimeoutMin() {
        return 0;
    }
    
    public final int getAutoPowerOffMin() {
        return 0;
    }
    
    public final boolean getDarkMode() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPairedDeviceName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPairedDeviceAddress() {
        return null;
    }
    
    public SettingsUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.settings.SettingsUiState copy(boolean autoReconnect, boolean autoSync, boolean saveWav, int screenTimeoutMin, int autoPowerOffMin, boolean darkMode, @org.jetbrains.annotations.Nullable()
    java.lang.String pairedDeviceName, @org.jetbrains.annotations.Nullable()
    java.lang.String pairedDeviceAddress) {
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