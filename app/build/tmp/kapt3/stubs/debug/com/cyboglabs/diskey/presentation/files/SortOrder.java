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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cyboglabs/diskey/presentation/files/SortOrder;", "", "(Ljava/lang/String;I)V", "DATE_DESC", "DATE_ASC", "SIZE_DESC", "NAME", "app_debug"})
public enum SortOrder {
    /*public static final*/ DATE_DESC /* = new DATE_DESC() */,
    /*public static final*/ DATE_ASC /* = new DATE_ASC() */,
    /*public static final*/ SIZE_DESC /* = new SIZE_DESC() */,
    /*public static final*/ NAME /* = new NAME() */;
    
    SortOrder() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.presentation.files.SortOrder> getEntries() {
        return null;
    }
}