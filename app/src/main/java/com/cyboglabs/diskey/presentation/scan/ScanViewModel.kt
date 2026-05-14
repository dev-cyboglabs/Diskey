package com.cyboglabs.diskey.presentation.scan

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.BleService
import com.cyboglabs.diskey.ble.scanner.BleScanner
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.domain.model.Device
import com.cyboglabs.diskey.domain.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class ScanUiState(
    val isScanning: Boolean = false,
    val devices: List<Device> = emptyList(),
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val connectingAddress: String? = null,
    val error: String? = null,
    val searchQuery: String = ""
) {
    val filteredDevices: List<Device> get() = if (searchQuery.isBlank()) devices
    else devices.filter { it.name.contains(searchQuery, ignoreCase = true) }
}

@HiltViewModel
class ScanViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bleScanner: BleScanner,
    private val bleConnectionManager: BleConnectionManager,
    private val deviceRepository: DeviceRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    private var scanJob: Job? = null
    private val discoveredDevices = mutableMapOf<String, Device>()
    private var pairedDeviceAddress: String? = null
    private var autoReconnectEnabled = true

    init {
        observeConnectionState()
        loadPairedDevice()
    }

    private fun loadPairedDevice() {
        viewModelScope.launch {
            pairedDeviceAddress = appPreferences.pairedAddress.first()
            autoReconnectEnabled = appPreferences.autoReconnect.first()
            Timber.d("ScanViewModel: Paired device loaded: $pairedDeviceAddress, auto-reconnect: $autoReconnectEnabled")
        }
    }

    fun startScan() {
        if (_uiState.value.isScanning) return
        discoveredDevices.clear()
        _uiState.update { it.copy(isScanning = true, error = null, devices = emptyList()) }
        Timber.d("ScanViewModel: starting scan")

        scanJob = viewModelScope.launch {
            bleScanner.scan(filterByService = false)
                .catch { e ->
                    Timber.e(e, "ScanViewModel: scan error")
                    _uiState.update { it.copy(isScanning = false, error = e.message) }
                }
                .collect { device ->
                    discoveredDevices[device.address] = device
                    _uiState.update { state ->
                        state.copy(devices = discoveredDevices.values.sortedByDescending { it.rssi })
                    }
                    
                    // Auto-connect if this is the paired device and auto-reconnect is enabled
                    if (autoReconnectEnabled && device.address == pairedDeviceAddress && _uiState.value.connectionState != ConnectionState.CONNECTED) {
                        Timber.d("ScanViewModel: Paired device found, auto-connecting: ${device.address}")
                        connect(device)
                    }
                }
        }
    }

    fun stopScan() {
        scanJob?.cancel()
        scanJob = null
        _uiState.update { it.copy(isScanning = false) }
        Timber.d("ScanViewModel: scan stopped")
    }

    fun connect(device: Device) {
        stopScan()
        _uiState.update { it.copy(connectingAddress = device.address) }

        // Save device name to preferences
        viewModelScope.launch {
            appPreferences.savePairedDevice(device.address, device.name)
        }

        // Start BLE foreground service
        val intent = Intent(context, BleService::class.java).apply {
            action = BleService.ACTION_CONNECT
            putExtra(BleService.EXTRA_ADDRESS, device.address)
        }
        context.startForegroundService(intent)

        viewModelScope.launch {
            deviceRepository.saveDevice(device)
        }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    private fun observeConnectionState() {
        viewModelScope.launch {
            bleConnectionManager.connectionState.collect { state ->
                _uiState.update { it.copy(connectionState = state) }
                if (state == ConnectionState.ERROR) {
                    _uiState.update { it.copy(connectingAddress = null, error = "Connection failed") }
                }
            }
        }
    }

    override fun onCleared() {
        stopScan()
        super.onCleared()
    }
}
