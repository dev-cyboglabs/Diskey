package com.cyboglabs.diskey.wifi

object WifiConstants {
    const val DEVICE_HOTSPOT_SSID_PREFIX = "T240(WIFI)"
    const val TCP_HOST = "192.168.1.1"
    const val TCP_PORT = 32769
    const val CONNECT_TIMEOUT_MS = 10_000
    const val READ_TIMEOUT_MS = 15_000
    const val HEARTBEAT_INTERVAL_MS = 3_000L
    const val MAX_RECONNECT_ATTEMPTS = 5

    const val PACKET_HEADER = "MeChoWifiStart"
    const val PACKET_TAIL = "MeChoWifiEnd"

    val HEARTBEAT_BYTES = byteArrayOf(0xFF.toByte(), 0x00)
}
