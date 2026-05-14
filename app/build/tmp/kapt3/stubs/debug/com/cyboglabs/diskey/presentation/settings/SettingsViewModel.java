package com.cyboglabs.diskey.presentation.settings;

import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lcom/cyboglabs/diskey/presentation/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "appPreferences", "Lcom/cyboglabs/diskey/data/datastore/AppPreferences;", "(Lcom/cyboglabs/diskey/data/datastore/AppPreferences;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/cyboglabs/diskey/presentation/settings/SettingsUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearPairing", "Lkotlinx/coroutines/Job;", "setAutoPowerOff", "minutes", "", "setAutoReconnect", "enabled", "", "setDarkMode", "setSaveWav", "setScreenTimeout", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.settings.SettingsUiState> uiState = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setAutoReconnect(boolean enabled) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setSaveWav(boolean enabled) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setScreenTimeout(int minutes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setAutoPowerOff(int minutes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setDarkMode(boolean enabled) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job clearPairing() {
        return null;
    }
}