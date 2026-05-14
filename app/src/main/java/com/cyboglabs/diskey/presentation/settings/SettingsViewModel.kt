package com.cyboglabs.diskey.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.data.datastore.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val autoReconnect: Boolean = true,
    val saveWav: Boolean = false,
    val screenTimeoutMin: Int = 5,
    val autoPowerOffMin: Int = 30,
    val darkMode: Boolean = true,
    val pairedDeviceName: String? = null,
    val pairedDeviceAddress: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = combine(
        appPreferences.autoReconnect,
        appPreferences.saveWav,
        appPreferences.screenTimeoutMin,
        appPreferences.autoPowerOffMin,
        appPreferences.darkMode
    ) { autoReconnect, saveWav, screenTimeout, autoPowerOff, darkMode ->
        SettingsUiState(
            autoReconnect = autoReconnect,
            saveWav = saveWav,
            screenTimeoutMin = screenTimeout,
            autoPowerOffMin = autoPowerOff,
            darkMode = darkMode
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUiState())

    fun setAutoReconnect(enabled: Boolean) = viewModelScope.launch {
        appPreferences.setAutoReconnect(enabled)
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
        appPreferences.clearPairing()
    }
}
