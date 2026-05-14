package com.cyboglabs.diskey.wifi.protocol;

import com.cyboglabs.diskey.utils.CrcUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/cyboglabs/diskey/wifi/protocol/WifiPacketCommands;", "", "()V", "FILE_DOWNLOAD_COMPLETE", "", "FILE_DOWNLOAD_DATA", "FILE_DOWNLOAD_ERROR", "FILE_DOWNLOAD_REQUEST", "FILE_LIST_REQUEST", "FILE_LIST_RESPONSE", "HEARTBEAT", "OTA_COMPLETE", "OTA_DATA", "OTA_START", "app_debug"})
public final class WifiPacketCommands {
    public static final int HEARTBEAT = 65280;
    public static final int FILE_LIST_REQUEST = 256;
    public static final int FILE_LIST_RESPONSE = 257;
    public static final int FILE_DOWNLOAD_REQUEST = 512;
    public static final int FILE_DOWNLOAD_DATA = 513;
    public static final int FILE_DOWNLOAD_COMPLETE = 514;
    public static final int FILE_DOWNLOAD_ERROR = 515;
    public static final int OTA_START = 768;
    public static final int OTA_DATA = 769;
    public static final int OTA_COMPLETE = 770;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.wifi.protocol.WifiPacketCommands INSTANCE = null;
    
    private WifiPacketCommands() {
        super();
    }
}