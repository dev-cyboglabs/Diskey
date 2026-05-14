package com.cyboglabs.diskey.presentation.files;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.audio.AudioDownloadService;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.audio.SyncPhase;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.model.AudioFile;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.utils.FileUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\f\u00a2\u0006\u0002\u0010\u000fJ\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\nH\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u00c6\u0003Jc\u0010*\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\bH\u00d6\u0001R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0018\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u00060"}, d2 = {"Lcom/cyboglabs/diskey/presentation/files/FileBrowserUiState;", "", "files", "", "Lcom/cyboglabs/diskey/domain/model/AudioFile;", "isLoading", "", "searchQuery", "", "sortOrder", "Lcom/cyboglabs/diskey/presentation/files/SortOrder;", "selectedFiles", "", "error", "downloadingFiles", "(Ljava/util/List;ZLjava/lang/String;Lcom/cyboglabs/diskey/presentation/files/SortOrder;Ljava/util/Set;Ljava/lang/String;Ljava/util/Set;)V", "getDownloadingFiles", "()Ljava/util/Set;", "getError", "()Ljava/lang/String;", "getFiles", "()Ljava/util/List;", "filteredFiles", "getFilteredFiles", "hasAnyFiles", "getHasAnyFiles", "()Z", "pendingFiles", "getPendingFiles", "playableFiles", "getPlayableFiles", "getSearchQuery", "getSelectedFiles", "getSortOrder", "()Lcom/cyboglabs/diskey/presentation/files/SortOrder;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class FileBrowserUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> files = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.presentation.files.SortOrder sortOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> selectedFiles = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> downloadingFiles = null;
    
    public FileBrowserUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> files, boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.presentation.files.SortOrder sortOrder, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> selectedFiles, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> downloadingFiles) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> getFiles() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.files.SortOrder getSortOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getSelectedFiles() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getDownloadingFiles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> getFilteredFiles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> getPlayableFiles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> getPendingFiles() {
        return null;
    }
    
    public final boolean getHasAnyFiles() {
        return false;
    }
    
    public FileBrowserUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.files.SortOrder component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.presentation.files.FileBrowserUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> files, boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.presentation.files.SortOrder sortOrder, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> selectedFiles, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> downloadingFiles) {
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