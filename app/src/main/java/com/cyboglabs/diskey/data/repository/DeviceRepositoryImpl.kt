package com.cyboglabs.diskey.data.repository

import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.data.db.dao.DeviceDao
import com.cyboglabs.diskey.data.db.entity.DeviceEntity
import com.cyboglabs.diskey.domain.model.Device
import com.cyboglabs.diskey.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao,
    private val appPreferences: AppPreferences
) : DeviceRepository {

    override fun getAllDevices(): Flow<List<Device>> =
        deviceDao.getAllDevices().map { list -> list.map { it.toDomain() } }

    override suspend fun getDevice(address: String): Device? =
        deviceDao.getDevice(address)?.toDomain()

    override suspend fun saveDevice(device: Device) {
        deviceDao.insertOrUpdate(device.toEntity())
    }

    override suspend fun updateBattery(address: String, level: Int) {
        deviceDao.updateBattery(address, level)
    }

    override suspend fun markPaired(address: String, name: String) {
        deviceDao.updatePairingState(address, true)
        deviceDao.updateLastConnected(address, System.currentTimeMillis())
        appPreferences.savePairedDevice(address, name)
    }

    override suspend fun getPairedAddress(): String? =
        appPreferences.pairedAddress.first()

    override suspend fun deleteDevice(address: String) {
        deviceDao.deleteDevice(address)
    }

    private fun DeviceEntity.toDomain() = Device(
        id = address,
        name = name,
        address = address,
        rssi = -1,
        uuid = uuid,
        firmwareVersion = firmwareVersion,
        batteryLevel = batteryLevel,
        isPaired = isPaired,
        lastSeenMs = lastConnectedMs
    )

    private fun Device.toEntity() = DeviceEntity(
        address = address,
        name = name,
        uuid = uuid,
        firmwareVersion = firmwareVersion,
        isPaired = isPaired,
        lastConnectedMs = lastSeenMs,
        batteryLevel = batteryLevel
    )
}
