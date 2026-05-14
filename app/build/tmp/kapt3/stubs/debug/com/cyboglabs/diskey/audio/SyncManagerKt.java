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

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0002\u00a8\u0006\u0004"}, d2 = {"asStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "T", "Lkotlinx/coroutines/flow/MutableStateFlow;", "app_debug"})
public final class SyncManagerKt {
    
    private static final <T extends java.lang.Object>kotlinx.coroutines.flow.StateFlow<T> asStateFlow(kotlinx.coroutines.flow.MutableStateFlow<T> $this$asStateFlow) {
        return null;
    }
}