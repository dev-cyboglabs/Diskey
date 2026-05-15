package com.cyboglabs.diskey.presentation.files;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.audio.AudioDownloadService;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.audio.SyncPhase;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.model.AudioFile;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.utils.FileUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u001b\u001a\u00020\u0015J\u0006\u0010\u001c\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0018J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006("}, d2 = {"Lcom/cyboglabs/diskey/presentation/files/FileBrowserViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "audioFileRepository", "Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;", "syncManager", "Lcom/cyboglabs/diskey/audio/SyncManager;", "appPreferences", "Lcom/cyboglabs/diskey/data/datastore/AppPreferences;", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "(Landroid/content/Context;Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;Lcom/cyboglabs/diskey/audio/SyncManager;Lcom/cyboglabs/diskey/data/datastore/AppPreferences;Lcom/cyboglabs/diskey/ble/BleConnectionManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/presentation/files/FileBrowserUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearSelection", "", "deleteFile", "filename", "", "deleteSelected", "downloadFile", "downloadSelected", "forceReload", "forceReloadSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadFilesFromDatabase", "observeSyncCompletion", "refreshLocalFileState", "setSearchQuery", "query", "setSortOrder", "order", "Lcom/cyboglabs/diskey/presentation/files/SortOrder;", "toggleSelect", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class FileBrowserViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.domain.repository.AudioFileRepository audioFileRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.audio.SyncManager syncManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.presentation.files.FileBrowserUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.files.FileBrowserUiState> uiState = null;
    
    @javax.inject.Inject()
    public FileBrowserViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.repository.AudioFileRepository audioFileRepository, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.SyncManager syncManager, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.files.FileBrowserUiState> getUiState() {
        return null;
    }
    
    /**
     * Primary load: read all known files from Room DB.
     * Files that exist on disk are marked isDownloaded = true.
     * This shows the full picture: previously synced files are immediately visible.
     */
    private final void loadFilesFromDatabase() {
    }
    
    /**
     * Force reload using one-shot query to get files directly from database
     * This ensures files are loaded even if the Flow doesn't emit.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object forceReloadSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * When the SyncManager finishes a sync cycle, reload the file list from database
     * so newly synced files show up immediately.
     */
    private final void observeSyncCompletion() {
    }
    
    /**
     * Re-scan the filesystem to flip isDownloaded for files that just arrived.
     * Called after a sync completes.
     */
    private final void refreshLocalFileState() {
    }
    
    public final void downloadFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
    }
    
    public final void downloadSelected() {
    }
    
    public final void toggleSelect(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
    }
    
    public final void clearSelection() {
    }
    
    public final void deleteFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
    }
    
    public final void deleteSelected() {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void setSortOrder(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.presentation.files.SortOrder order) {
    }
    
    public final void forceReload() {
    }
}