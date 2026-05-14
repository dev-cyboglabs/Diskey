package com.cyboglabs.diskey.domain.repository

import com.cyboglabs.diskey.domain.model.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getAllDevices(): Flow<List<Device>>
    suspend fun getDevice(address: String): Device?
    suspend fun saveDevice(device: Device)
    suspend fun updateBattery(address: String, level: Int)
    suspend fun markPaired(address: String, name: String)
    suspend fun getPairedAddress(): String?
    suspend fun deleteDevice(address: String)
}
