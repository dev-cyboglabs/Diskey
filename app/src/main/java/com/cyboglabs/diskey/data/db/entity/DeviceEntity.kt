package com.cyboglabs.diskey.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey val address: String,
    val name: String,
    val uuid: String,
    val firmwareVersion: String,
    val isPaired: Boolean,
    val lastConnectedMs: Long,
    val batteryLevel: Int = -1
)
