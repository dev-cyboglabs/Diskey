package com.cyboglabs.diskey.wifi;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiConstants;", "", "()V", "CONNECT_TIMEOUT_MS", "", "DEVICE_HOTSPOT_SSID_PREFIX", "", "HEARTBEAT_BYTES", "", "getHEARTBEAT_BYTES", "()[B", "HEARTBEAT_INTERVAL_MS", "", "MAX_RECONNECT_ATTEMPTS", "PACKET_HEADER", "PACKET_TAIL", "READ_TIMEOUT_MS", "TCP_HOST", "TCP_PORT", "app_debug"})
public final class WifiConstants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEVICE_HOTSPOT_SSID_PREFIX = "T240(WIFI)";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TCP_HOST = "192.168.1.1";
    public static final int TCP_PORT = 32769;
    public static final int CONNECT_TIMEOUT_MS = 10000;
    public static final int READ_TIMEOUT_MS = 15000;
    public static final long HEARTBEAT_INTERVAL_MS = 3000L;
    public static final int MAX_RECONNECT_ATTEMPTS = 5;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PACKET_HEADER = "MeChoWifiStart";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PACKET_TAIL = "MeChoWifiEnd";
    @org.jetbrains.annotations.NotNull()
    private static final byte[] HEARTBEAT_BYTES = {(byte)-1, (byte)0};
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.wifi.WifiConstants INSTANCE = null;
    
    private WifiConstants() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] getHEARTBEAT_BYTES() {
        return null;
    }
}