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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u00c6\u0003J3\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lcom/cyboglabs/diskey/ota/OtaProgress;", "", "state", "Lcom/cyboglabs/diskey/ota/OtaState;", "bytesSent", "", "totalBytes", "errorMessage", "", "(Lcom/cyboglabs/diskey/ota/OtaState;IILjava/lang/String;)V", "getBytesSent", "()I", "getErrorMessage", "()Ljava/lang/String;", "percent", "", "getPercent", "()F", "getState", "()Lcom/cyboglabs/diskey/ota/OtaState;", "getTotalBytes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class OtaProgress {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.ota.OtaState state = null;
    private final int bytesSent = 0;
    private final int totalBytes = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    
    public OtaProgress(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ota.OtaState state, int bytesSent, int totalBytes, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.ota.OtaState getState() {
        return null;
    }
    
    public final int getBytesSent() {
        return 0;
    }
    
    public final int getTotalBytes() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final float getPercent() {
        return 0.0F;
    }
    
    public OtaProgress() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.ota.OtaState component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.ota.OtaProgress copy(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.ota.OtaState state, int bytesSent, int totalBytes, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
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