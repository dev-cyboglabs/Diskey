package com.cyboglabs.diskey.presentation.debug;

import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.domain.model.BlePacket;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/cyboglabs/diskey/presentation/debug/DebugViewModel;", "Landroidx/lifecycle/ViewModel;", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "(Lcom/cyboglabs/diskey/ble/BleConnectionManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/presentation/debug/DebugUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addLog", "", "entry", "", "addPacket", "packet", "clearLogs", "sendRawCommand", "hexString", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DebugViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.presentation.debug.DebugUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.debug.DebugUiState> uiState = null;
    
    @javax.inject.Inject()
    public DebugViewModel(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.debug.DebugUiState> getUiState() {
        return null;
    }
    
    private final void addLog(java.lang.String entry) {
    }
    
    private final void addPacket(java.lang.String packet) {
    }
    
    public final void clearLogs() {
    }
    
    public final void sendRawCommand(@org.jetbrains.annotations.NotNull()
    java.lang.String hexString) {
    }
}