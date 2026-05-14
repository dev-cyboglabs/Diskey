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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/cyboglabs/diskey/ota/OtaState;", "", "(Ljava/lang/String;I)V", "IDLE", "STARTING", "TRANSFERRING", "VERIFYING", "COMPLETE", "FAILED", "app_debug"})
public enum OtaState {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ STARTING /* = new STARTING() */,
    /*public static final*/ TRANSFERRING /* = new TRANSFERRING() */,
    /*public static final*/ VERIFYING /* = new VERIFYING() */,
    /*public static final*/ COMPLETE /* = new COMPLETE() */,
    /*public static final*/ FAILED /* = new FAILED() */;
    
    OtaState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.ota.OtaState> getEntries() {
        return null;
    }
}