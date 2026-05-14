package com.cyboglabs.diskey.ble;

import java.util.UUID;

/**
 * UUIDs and timing constants for the T240 BLE protocol.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0016\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006\u0018"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleConstants;", "", "()V", "ANDROID_APP_UUID", "", "CONNECT_TIMEOUT_MS", "", "DEFAULT_MTU", "", "DEVICE_NAME_PREFIX", "HANDSHAKE_TIMEOUT_MS", "MAX_RECONNECT_DELAY_MS", "MAX_RETRY_COUNT", "MTU_SIZE", "NOTIFY_CHARACTERISTIC_UUID", "Ljava/util/UUID;", "getNOTIFY_CHARACTERISTIC_UUID", "()Ljava/util/UUID;", "RECONNECT_DELAY_MS", "SCAN_PERIOD_MS", "SERVICE_UUID", "getSERVICE_UUID", "WRITE_CHARACTERISTIC_UUID", "getWRITE_CHARACTERISTIC_UUID", "app_debug"})
public final class BleConstants {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID SERVICE_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID NOTIFY_CHARACTERISTIC_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.UUID WRITE_CHARACTERISTIC_UUID = null;
    public static final long SCAN_PERIOD_MS = 15000L;
    public static final long CONNECT_TIMEOUT_MS = 10000L;
    public static final long HANDSHAKE_TIMEOUT_MS = 8000L;
    public static final int MTU_SIZE = 512;
    public static final int DEFAULT_MTU = 23;
    public static final int MAX_RETRY_COUNT = 3;
    public static final long RECONNECT_DELAY_MS = 2000L;
    public static final long MAX_RECONNECT_DELAY_MS = 30000L;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEVICE_NAME_PREFIX = "T240";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ANDROID_APP_UUID = "a1b2c3d4-e5f6-7890-abcd-ef1234567890";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.ble.BleConstants INSTANCE = null;
    
    private BleConstants() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.UUID getSERVICE_UUID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.UUID getNOTIFY_CHARACTERISTIC_UUID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.UUID getWRITE_CHARACTERISTIC_UUID() {
        return null;
    }
}