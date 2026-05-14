package com.cyboglabs.diskey.audio;

import android.content.Context;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.protocol.PacketBuilder;
import com.cyboglabs.diskey.domain.model.AudioDataPacket;
import com.cyboglabs.diskey.domain.model.AudioFile;
import com.cyboglabs.diskey.domain.model.FileCompletePacket;
import com.cyboglabs.diskey.domain.model.FileListPacket;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/cyboglabs/diskey/audio/SyncPhase;", "", "(Ljava/lang/String;I)V", "IDLE", "FETCHING_LIST", "DOWNLOADING", "COMPLETE", "ERROR", "app_debug"})
public enum SyncPhase {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ FETCHING_LIST /* = new FETCHING_LIST() */,
    /*public static final*/ DOWNLOADING /* = new DOWNLOADING() */,
    /*public static final*/ COMPLETE /* = new COMPLETE() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    SyncPhase() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.audio.SyncPhase> getEntries() {
        return null;
    }
}