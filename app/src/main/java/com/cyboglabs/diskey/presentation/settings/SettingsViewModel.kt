package com.cyboglabs.diskey.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import com.cyboglabs.diskey.domain.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SettingsUiState(
    val autoReconnect: Boolean = true,
    val autoSync: Boolean = true,
    val saveWav: Boolean = false,
    val screenTimeoutMin: Int = 5,
    val autoPowerOffMin: Int = 30,
    val darkMode: Boolean = true,
    val pairedDeviceName: String? = null,
    val pairedDeviceAddress: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val bleConnectionManager: BleConnectionManager,
    private val deviceRepository: DeviceRepository,
    private val audioFileRepository: AudioFileRepository
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = combine(
        combine(
            appPreferences.autoReconnect,
            appPreferences.autoSync,
            appPreferences.saveWav,
            appPreferences.screenTimeoutMin,
            appPreferences.autoPowerOffMin,
            appPreferences.darkMode
        ) { values ->
            SettingsUiState(
                autoReconnect = values[0] as Boolean,
                autoSync = values[1] as Boolean,
                saveWav = values[2] as Boolean,
                screenTimeoutMin = values[3] as Int,
                autoPowerOffMin = values[4] as Int,
                darkMode = values[5] as Boolean
            )
        },
        appPreferences.pairedAddress,
        appPreferences.pairedName
    ) { state, pairedAddress, pairedName ->
        state.copy(
            pairedDeviceAddress = pairedAddress,
            pairedDeviceName = pairedName
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUiState())

    fun setAutoReconnect(enabled: Boolean) = viewModelScope.launch {
        appPreferences.setAutoReconnect(enabled)
    }

    fun setAutoSync(enabled: Boolean) = viewModelScope.launch {
        appPreferences.setAutoSync(enabled)
    }

    fun setSaveWav(enabled: Boolean) = viewModelScope.launch {
        appPreferences.setSaveWav(enabled)
    }

    fun setScreenTimeout(minutes: Int) = viewModelScope.launch {
        appPreferences.setScreenTimeout(minutes)
    }

    fun setAutoPowerOff(minutes: Int) = viewModelScope.launch {
        appPreferences.setAutoPowerOff(minutes)
    }

    fun setDarkMode(enabled: Boolean) = viewModelScope.launch {
        appPreferences.setDarkMode(enabled)
    }

    fun clearPairing() = viewModelScope.launch {
        try {
            // Get paired device address before clearing
            val pairedAddress = appPreferences.pairedAddress.first()
            
            Timber.d("SettingsViewModel: clearing pairing for device: $pairedAddress")
            
            // 1. Disconnect BLE connection
            bleConnectionManager.disconnect()
            Timber.d("SettingsViewModel: disconnected BLE")
            
            // 2. Clear preferences (paired address, name, device UUID)
            appPreferences.clearPairing()
            Timber.d("SettingsViewModel: cleared preferences")
            
            // 3. Delete device from database if address exists
            if (pairedAddress != null) {
                deviceRepository.deleteDevice(pairedAddress)
                Timber.d("SettingsViewModel: deleted device from DB")
                
                // 4. Delete all audio files for this device
                audioFileRepository.deleteAllForDevice(pairedAddress)
                Timber.d("SettingsViewModel: deleted audio files")
            }
            
            Timber.i("SettingsViewModel: ✓ pairing cleared successfully")
        } catch (e: Exception) {
            Timber.e(e, "SettingsViewModel: failed to clear pairing")
        }
    }
}
