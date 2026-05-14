package com.cyboglabs.diskey.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showClearPairingDialog by remember { mutableStateOf(false) }

    if (showClearPairingDialog) {
        AlertDialog(
            onDismissRequest = { showClearPairingDialog = false },
            title = { Text("Clear Pairing") },
            text = { Text("This will forget the paired device. You will need to reconnect manually.") },
            confirmButton = {
                TextButton(onClick = { viewModel.clearPairing(); showClearPairingDialog = false }) {
                    Text("Clear", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearPairingDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            SettingsSection("Connection") {
                SettingsToggle(
                    title = "Auto Reconnect",
                    subtitle = "Automatically reconnect when T240 is nearby",
                    checked = state.autoReconnect,
                    onCheckedChange = viewModel::setAutoReconnect
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            SettingsSection("Recording") {
                SettingsToggle(
                    title = "Save WAV Copy",
                    subtitle = "Convert OPUS files to WAV after download",
                    checked = state.saveWav,
                    onCheckedChange = viewModel::setSaveWav
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            SettingsSection("Display") {
                SettingsToggle(
                    title = "Dark Mode",
                    subtitle = "Use dark color scheme",
                    checked = state.darkMode,
                    onCheckedChange = viewModel::setDarkMode
                )

                Spacer(Modifier.height(8.dp))
                Text("Screen Timeout: ${state.screenTimeoutMin} min",
                    style = MaterialTheme.typography.bodyLarge)
                Slider(
                    value = state.screenTimeoutMin.toFloat(),
                    onValueChange = { viewModel.setScreenTimeout(it.toInt()) },
                    valueRange = 1f..30f,
                    steps = 28
                )

                Spacer(Modifier.height(8.dp))
                Text("Auto Power Off: ${state.autoPowerOffMin} min",
                    style = MaterialTheme.typography.bodyLarge)
                Slider(
                    value = state.autoPowerOffMin.toFloat(),
                    onValueChange = { viewModel.setAutoPowerOff(it.toInt()) },
                    valueRange = 5f..120f,
                    steps = 22
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            SettingsSection("Device") {
                TextButton(
                    onClick = { showClearPairingDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear Pairing Data", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
private fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Text(title, style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
    Spacer(Modifier.height(8.dp))
    content()
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun SettingsToggle(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
