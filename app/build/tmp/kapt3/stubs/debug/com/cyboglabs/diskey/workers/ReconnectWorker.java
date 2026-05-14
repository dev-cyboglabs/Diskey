package com.cyboglabs.diskey.workers;

import android.content.Context;
import android.content.Intent;
import androidx.hilt.work.HiltWorker;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.BleService;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import timber.log.Timber;

/**
 * WorkManager worker that attempts BLE reconnect on app restart or connectivity change.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/cyboglabs/diskey/workers/ReconnectWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "appPreferences", "Lcom/cyboglabs/diskey/data/datastore/AppPreferences;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/cyboglabs/diskey/ble/BleConnectionManager;Lcom/cyboglabs/diskey/data/datastore/AppPreferences;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.hilt.work.HiltWorker()
public final class ReconnectWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences = null;
    
    @dagger.assisted.AssistedInject()
    public ReconnectWorker(@dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
}