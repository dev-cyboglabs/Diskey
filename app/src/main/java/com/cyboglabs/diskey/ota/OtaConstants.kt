package com.cyboglabs.diskey.ota

object OtaConstants {
    const val OTA_CHUNK_SIZE = 512
    const val OTA_TIMEOUT_MS = 300_000L   // 5 minutes
    const val OTA_PACKET_DELAY_MS = 20L   // Inter-packet delay
}
