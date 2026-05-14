package com.cyboglabs.diskey.domain.model

data class Device(
    val id: String,
    val name: String,
    val address: String,
    val rssi: Int,
    val uuid: String = "",
    val firmwareVersion: String = "",
    val batteryLevel: Int = -1,
    val isConnected: Boolean = false,
    val isPaired: Boolean = false,
    val lastSeenMs: Long = System.currentTimeMillis()
)

data class DeviceInfo(
    val name: String,
    val firmware: String,
    val uuid: String,
    val batteryPercent: Int,
    val storageUsedMb: Float,
    val storageTotalMb: Float,
    val isRecording: Boolean,
    val isWifiEnabled: Boolean,
    val wifiSsid: String = "",
    val wifiPassword: String = ""
)
