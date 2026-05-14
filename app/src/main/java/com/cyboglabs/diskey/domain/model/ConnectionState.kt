package com.cyboglabs.diskey.domain.model

/** BLE connection lifecycle states. */
enum class ConnectionState {
    DISCONNECTED,
    SCANNING,
    CONNECTING,
    DISCOVERING_SERVICES,
    ENABLING_NOTIFICATIONS,
    HANDSHAKING,
    CONNECTED,
    RECONNECTING,
    ERROR
}
