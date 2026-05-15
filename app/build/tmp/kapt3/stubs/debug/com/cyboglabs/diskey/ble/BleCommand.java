package com.cyboglabs.diskey.ble;

import java.util.UUID;

/**
 * All T240 binary command codes.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0014\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleCommand;", "", "()V", "CMD_BATTERY", "", "CMD_DELETE_FILE", "CMD_DOWNLOAD_FILE", "CMD_FILE_COMPLETE", "CMD_GET_FILE_LIST", "CMD_OTA_END", "CMD_OTA_PACKET", "CMD_OTA_PACKET_END", "CMD_OTA_PACKET_START", "CMD_OTA_PROGRESS", "CMD_OTA_START", "CMD_PAUSE_RECORDING", "CMD_RESUME_RECORDING", "CMD_START_RECORDING", "CMD_STOP_RECORDING", "CMD_WIFI_ENABLE", "HS_APP_RESPONSE", "HS_DEVICE_HELLO", "HS_SUCCESS", "TYPE_CONTROL", "app_debug"})
public final class BleCommand {
    public static final byte TYPE_CONTROL = (byte)1;
    public static final byte CMD_BATTERY = (byte)9;
    public static final byte CMD_WIFI_ENABLE = (byte)10;
    public static final byte CMD_START_RECORDING = (byte)20;
    public static final byte CMD_PAUSE_RECORDING = (byte)21;
    public static final byte CMD_RESUME_RECORDING = (byte)22;
    public static final byte CMD_STOP_RECORDING = (byte)23;
    public static final byte CMD_GET_FILE_LIST = (byte)27;
    public static final byte CMD_DOWNLOAD_FILE = (byte)28;
    public static final byte CMD_FILE_COMPLETE = (byte)29;
    public static final byte CMD_DELETE_FILE = (byte)30;
    public static final byte HS_DEVICE_HELLO = (byte)1;
    public static final byte HS_APP_RESPONSE = (byte)1;
    public static final byte HS_SUCCESS = (byte)2;
    public static final byte CMD_OTA_START = (byte)-96;
    public static final byte CMD_OTA_PACKET_START = (byte)-95;
    public static final byte CMD_OTA_PACKET = (byte)-94;
    public static final byte CMD_OTA_PACKET_END = (byte)-93;
    public static final byte CMD_OTA_PROGRESS = (byte)-92;
    public static final byte CMD_OTA_END = (byte)-91;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.ble.BleCommand INSTANCE = null;
    
    private BleCommand() {
        super();
    }
}