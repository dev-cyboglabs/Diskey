package com.cyboglabs.diskey.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cyboglabs.diskey.data.db.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices ORDER BY lastConnectedMs DESC")
    fun getAllDevices(): Flow<List<DeviceEntity>>

    @Query("SELECT * FROM devices WHERE address = :address LIMIT 1")
    suspend fun getDevice(address: String): DeviceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(device: DeviceEntity)

    @Query("UPDATE devices SET batteryLevel = :level WHERE address = :address")
    suspend fun updateBattery(address: String, level: Int)

    @Query("UPDATE devices SET lastConnectedMs = :timeMs WHERE address = :address")
    suspend fun updateLastConnected(address: String, timeMs: Long)

    @Query("UPDATE devices SET isPaired = :paired WHERE address = :address")
    suspend fun updatePairingState(address: String, paired: Boolean)

    @Query("DELETE FROM devices WHERE address = :address")
    suspend fun deleteDevice(address: String)
}
