package com.cyboglabs.diskey.ble.protocol;

import com.cyboglabs.diskey.ble.BleCommand;
import com.cyboglabs.diskey.utils.CrcUtils;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Builds binary command packets for the T240 BLE protocol.
 *
 * Standard command packet layout:
 *  [type:1][cmd:1][sub:1][...optional payload]
 *  where type=0x01, cmd=command byte, sub=0x00
 *
 * Audio packet layout:
 *  [type:1][cmd:1][sub:1][index_hi:1][index_lo:1][audio:N]
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0004J\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004J\u0016\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0004J\u0006\u0010\u0019\u001a\u00020\u0004\u00a8\u0006\u001a"}, d2 = {"Lcom/cyboglabs/diskey/ble/protocol/PacketBuilder;", "", "()V", "downloadFile", "", "filename", "", "enableWifi", "getBattery", "getFileList", "handshakeResponse", "timestamp", "", "appUuid", "otaEnd", "otaPacket", "index", "", "data", "otaStart", "fileSize", "crc", "pauseRecording", "resumeRecording", "startRecording", "stopRecording", "app_debug"})
public final class PacketBuilder {
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.ble.protocol.PacketBuilder INSTANCE = null;
    
    private PacketBuilder() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] startRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] stopRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] pauseRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] resumeRecording() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getBattery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] enableWifi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getFileList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] downloadFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] handshakeResponse(long timestamp, @org.jetbrains.annotations.NotNull()
    java.lang.String appUuid) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] otaStart(int fileSize, int crc) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] otaPacket(int index, @org.jetbrains.annotations.NotNull()
    byte[] data) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] otaEnd() {
        return null;
    }
}