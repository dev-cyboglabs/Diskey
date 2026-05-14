package com.cyboglabs.diskey.presentation.debug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.domain.model.BlePacket
import com.cyboglabs.diskey.domain.model.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class DebugUiState(
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val logEntries: List<String> = emptyList(),
    val packets: List<String> = emptyList()
)

@HiltViewModel
class DebugViewModel @Inject constructor(
    private val bleConnectionManager: BleConnectionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DebugUiState())
    val uiState: StateFlow<DebugUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bleConnectionManager.connectionState.collect { state ->
                addLog("Connection: $state")
                _uiState.update { it.copy(connectionState = state) }
            }
        }
        viewModelScope.launch {
            bleConnectionManager.packets.collect { packet ->
                addPacket(packet.toString())
            }
        }
    }

    private fun addLog(entry: String) {
        val timestamp = java.time.LocalTime.now().toString().take(8)
        _uiState.update { state ->
            state.copy(logEntries = (listOf("[$timestamp] $entry") + state.logEntries).take(200))
        }
    }

    private fun addPacket(packet: String) {
        val timestamp = java.time.LocalTime.now().toString().take(8)
        _uiState.update { state ->
            state.copy(packets = (listOf("[$timestamp] $packet") + state.packets).take(100))
        }
    }

    fun clearLogs() = _uiState.update { it.copy(logEntries = emptyList(), packets = emptyList()) }

    fun sendRawCommand(hexString: String) {
        try {
            val bytes = hexString.replace(" ", "")
                .chunked(2)
                .map { it.toInt(16).toByte() }
                .toByteArray()
            bleConnectionManager.sendCommand(bytes)
            addLog("TX: $hexString")
        } catch (e: Exception) {
            addLog("TX Error: ${e.message}")
        }
    }
}
