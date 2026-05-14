package com.cyboglabs.diskey.presentation.dashboard

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.audio.SyncManager
import com.cyboglabs.diskey.audio.SyncPhase
import com.cyboglabs.diskey.audio.SyncState
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.BleService
import com.cyboglabs.diskey.ble.protocol.PacketBuilder
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.model.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class DashboardUiState(
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val batteryLevel: Int = -1,
    val isWifiEnabled: Boolean = false,
    val syncState: SyncState = SyncState(),
    val deviceAddress: String = "",
    val deviceName: String = ""
) {
    val isSyncing: Boolean get() = syncState.phase == SyncPhase.FETCHING_LIST ||
        syncState.phase == SyncPhase.DOWNLOADING
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bleConnectionManager: BleConnectionManager,
    private val syncManager: SyncManager,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        observeConnectionState()
        observeBattery()
        observeSync()
        loadPairedDevice()
    }

    private fun observeConnectionState() {
        viewModelScope.launch {
            bleConnectionManager.connectionState.collect { state ->
                _uiState.update { it.copy(connectionState = state) }

                if (state == ConnectionState.CONNECTED) {
                    requestBattery()
                }
            }
        }
    }

    private fun observeBattery() {
        viewModelScope.launch {
            bleConnectionManager.batteryLevel.collect { level ->
                if (level >= 0) _uiState.update { it.copy(batteryLevel = level) }
            }
        }
    }

    private fun observeSync() {
        viewModelScope.launch {
            syncManager.syncState.collect { syncState ->
                _uiState.update { it.copy(syncState = syncState) }
            }
        }
    }

    private fun requestBattery() {
        viewModelScope.launch {
            runCatching { bleConnectionManager.sendCommand(PacketBuilder.getBattery()) }
        }
    }

    private fun loadPairedDevice() {
        viewModelScope.launch {
            val address = appPreferences.pairedAddress.first() ?: ""
            val name = appPreferences.pairedName.first() ?: ""
            _uiState.update { it.copy(deviceAddress = address, deviceName = name) }
        }
    }

    // ─── Sync ──────────────────────────────────────────────────────────────

    /**
     * Kick off a full SD card sync:
     *   1. Request file list from device
     *   2. Download all files not already on this phone
     */
    fun syncFiles() {
        if (_uiState.value.connectionState != ConnectionState.CONNECTED) {
            Timber.w("DashboardViewModel: sync ignored - device not connected")
            return
        }
        val address = _uiState.value.deviceAddress.ifBlank { "current_device" }
        syncManager.startSync(address)
        Timber.d("DashboardViewModel: sync started")
    }

    fun cancelSync() {
        syncManager.cancel()
        Timber.d("DashboardViewModel: sync cancelled")
    }

    // ─── WiFi / disconnect ─────────────────────────────────────────────────

    fun enableWifi() {
        bleConnectionManager.sendCommand(PacketBuilder.enableWifi())
        _uiState.update { it.copy(isWifiEnabled = true) }
    }

    fun disconnect() {
        context.startService(
            Intent(context, BleService::class.java).apply {
                action = BleService.ACTION_DISCONNECT
            }
        )
    }
}
