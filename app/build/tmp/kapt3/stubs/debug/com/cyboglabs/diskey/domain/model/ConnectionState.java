package com.cyboglabs.diskey.domain.model;

/**
 * BLE connection lifecycle states.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lcom/cyboglabs/diskey/domain/model/ConnectionState;", "", "(Ljava/lang/String;I)V", "DISCONNECTED", "SCANNING", "CONNECTING", "DISCOVERING_SERVICES", "ENABLING_NOTIFICATIONS", "HANDSHAKING", "CONNECTED", "RECONNECTING", "ERROR", "app_debug"})
public enum ConnectionState {
    /*public static final*/ DISCONNECTED /* = new DISCONNECTED() */,
    /*public static final*/ SCANNING /* = new SCANNING() */,
    /*public static final*/ CONNECTING /* = new CONNECTING() */,
    /*public static final*/ DISCOVERING_SERVICES /* = new DISCOVERING_SERVICES() */,
    /*public static final*/ ENABLING_NOTIFICATIONS /* = new ENABLING_NOTIFICATIONS() */,
    /*public static final*/ HANDSHAKING /* = new HANDSHAKING() */,
    /*public static final*/ CONNECTED /* = new CONNECTED() */,
    /*public static final*/ RECONNECTING /* = new RECONNECTING() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    ConnectionState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.domain.model.ConnectionState> getEntries() {
        return null;
    }
}