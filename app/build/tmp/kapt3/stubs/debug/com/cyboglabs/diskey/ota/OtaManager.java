package com.cyboglabs.diskey.ota;

import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.protocol.PacketBuilder;
import com.cyboglabs.diskey.domain.model.OtaProgressPacket;
import com.cyboglabs.diskey.utils.CrcUtils;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages OTA firmware updates over BLE.
 *
 * Sequence:
 *  1. Send OTA_START with file size + CRC
 *  2. Stream firmware in chunks with OTA_PACKET
 *  3. Send OTA_END
 *  4. Device confirms with OTA_PROGRESS packets
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/cyboglabs/diskey/ota/OtaManager;", "", "bleConnectionManager", "Lcom/cyboglabs/diskey/ble/BleConnectionManager;", "(Lcom/cyboglabs/diskey/ble/BleConnectionManager;)V", "_progress", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/ota/OtaProgress;", "progress", "Lkotlinx/coroutines/flow/StateFlow;", "getProgress", "()Lkotlinx/coroutines/flow/StateFlow;", "reset", "", "startUpdate", "firmwareFile", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class OtaManager {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.ota.OtaProgress> _progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.ota.OtaProgress> progress = null;
    
    @javax.inject.Inject()
    public OtaManager(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ble.BleConnectionManager bleConnectionManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.ota.OtaProgress> getProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object startUpdate(@org.jetbrains.annotations.NotNull()
    java.io.File firmwareFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void reset() {
    }
}