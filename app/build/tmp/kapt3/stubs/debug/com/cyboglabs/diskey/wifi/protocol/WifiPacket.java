package com.cyboglabs.diskey.wifi.protocol;

import com.cyboglabs.diskey.utils.CrcUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * WiFi TCP packet for the T240 MeChoWifi protocol.
 *
 * Wire format:
 *  [header: "MeChoWifiStart"][command:2][sequence:4][length:4][crc:2][data:N][tail: "MeChoWifiEnd"]
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/cyboglabs/diskey/wifi/protocol/WifiPacket;", "", "command", "", "sequence", "data", "", "(II[B)V", "getCommand", "()I", "getData", "()[B", "getSequence", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class WifiPacket {
    private final int command = 0;
    private final int sequence = 0;
    @org.jetbrains.annotations.NotNull()
    private final byte[] data = null;
    
    public WifiPacket(int command, int sequence, @org.jetbrains.annotations.NotNull()
    byte[] data) {
        super();
    }
    
    public final int getCommand() {
        return 0;
    }
    
    public final int getSequence() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getData() {
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
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cyboglabs.diskey.wifi.protocol.WifiPacket copy(int command, int sequence, @org.jetbrains.annotations.NotNull()
    byte[] data) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}