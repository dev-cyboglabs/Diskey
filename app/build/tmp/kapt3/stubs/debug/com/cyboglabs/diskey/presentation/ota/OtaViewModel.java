package com.cyboglabs.diskey.presentation.ota;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.ota.OtaManager;
import com.cyboglabs.diskey.ota.OtaProgress;
import com.cyboglabs.diskey.ota.OtaState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import java.io.File;
import java.io.FileOutputStream;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/cyboglabs/diskey/presentation/ota/OtaViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "otaManager", "Lcom/cyboglabs/diskey/ota/OtaManager;", "(Landroid/content/Context;Lcom/cyboglabs/diskey/ota/OtaManager;)V", "progress", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/cyboglabs/diskey/ota/OtaProgress;", "getProgress", "()Lkotlinx/coroutines/flow/StateFlow;", "reset", "", "startUpdate", "uri", "Landroid/net/Uri;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OtaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ota.OtaManager otaManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.ota.OtaProgress> progress = null;
    
    @javax.inject.Inject()
    public OtaViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ota.OtaManager otaManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.ota.OtaProgress> getProgress() {
        return null;
    }
    
    public final void startUpdate(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    public final void reset() {
    }
}