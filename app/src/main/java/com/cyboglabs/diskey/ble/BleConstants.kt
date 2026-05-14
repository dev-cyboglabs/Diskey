package com.cyboglabs.diskey.ble

import java.util.UUID

/** UUIDs and timing constants for the T240 BLE protocol. */
object BleConstants {
    val SERVICE_UUID: UUID = UUID.fromString("00001910-0000-1000-8000-00805f9b34fb")
    val NOTIFY_CHARACTERISTIC_UUID: UUID = UUID.fromString("00001911-0000-1000-8000-00805f9b34fb")
    val WRITE_CHARACTERISTIC_UUID: UUID = UUID.fromString("00001912-0000-1000-8000-00805f9b34fb")

    const val SCAN_PERIOD_MS = 15_000L
    const val CONNECT_TIMEOUT_MS = 10_000L
    const val HANDSHAKE_TIMEOUT_MS = 8_000L
    const val MTU_SIZE = 512
    const val DEFAULT_MTU = 23
    const val MAX_RETRY_COUNT = 3
    const val RECONNECT_DELAY_MS = 2_000L
    const val MAX_RECONNECT_DELAY_MS = 30_000L

    const val DEVICE_NAME_PREFIX = "T240"
    const val ANDROID_APP_UUID = "a1b2c3d4-e5f6-7890-abcd-ef1234567890"
}

/** All T240 binary command codes. */
object BleCommand {
    // Type byte
    const val TYPE_CONTROL: Byte = 0x01

    // Control commands
    const val CMD_BATTERY: Byte = 0x09
    const val CMD_WIFI_ENABLE: Byte = 0x0A
    const val CMD_START_RECORDING: Byte = 0x14
    const val CMD_PAUSE_RECORDING: Byte = 0x15
    const val CMD_RESUME_RECORDING: Byte = 0x16
    const val CMD_STOP_RECORDING: Byte = 0x17
    const val CMD_GET_FILE_LIST: Byte = 0x1B
    const val CMD_DOWNLOAD_FILE: Byte = 0x1C
    const val CMD_FILE_COMPLETE: Byte = 0x1D

    // Handshake sub-types (second byte of cmd)
    const val HS_DEVICE_HELLO: Byte = 0x01
    const val HS_APP_RESPONSE: Byte = 0x01
    const val HS_SUCCESS: Byte = 0x02

    // OTA commands
    const val CMD_OTA_START: Byte = 0xA0.toByte()
    const val CMD_OTA_PACKET_START: Byte = 0xA1.toByte()
    const val CMD_OTA_PACKET: Byte = 0xA2.toByte()
    const val CMD_OTA_PACKET_END: Byte = 0xA3.toByte()
    const val CMD_OTA_PROGRESS: Byte = 0xA4.toByte()
    const val CMD_OTA_END: Byte = 0xA5.toByte()
}
