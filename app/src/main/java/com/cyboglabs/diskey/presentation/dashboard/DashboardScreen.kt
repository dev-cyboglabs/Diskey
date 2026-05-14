package com.cyboglabs.diskey.presentation.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SyncProblem
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cyboglabs.diskey.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.audio.SyncPhase
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.presentation.theme.Connected
import com.cyboglabs.diskey.presentation.theme.Disconnected
import com.cyboglabs.diskey.presentation.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToFiles: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToOta: () -> Unit,
    onNavigateToDebug: () -> Unit,
    onDisconnected: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.connectionState) {
        if (state.connectionState == ConnectionState.DISCONNECTED) onDisconnected()
    }

    // Navigate to files automatically when sync finishes
    LaunchedEffect(state.syncState.phase) {
        if (state.syncState.phase == SyncPhase.COMPLETE && state.syncState.newFilesCount > 0) {
            onNavigateToFiles()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    if (state.connectionState == ConnectionState.CONNECTED)
                                        Connected else Disconnected,
                                    CircleShape
                                )
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Diskey", fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToDebug) {
                        Icon(Icons.Default.BugReport, "Debug")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ── Device Status Card ────────────────────────────────────────────
            DeviceStatusCard(state)

            // ── Sync Card ─────────────────────────────────────────────────────
            SyncCard(
                state = state,
                onSync = viewModel::syncFiles,
                onCancel = viewModel::cancelSync,
                onViewFiles = onNavigateToFiles
            )

            // ── Quick Actions ─────────────────────────────────────────────────
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilledTonalButton(
                    onClick = onNavigateToFiles,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.FolderOpen, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Files")
                }
                FilledTonalButton(
                    onClick = viewModel::enableWifi,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Wifi, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("WiFi")
                }
                FilledTonalButton(
                    onClick = onNavigateToOta,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.SystemUpdate, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("OTA")
                }
            }
        }
    }
}

// ─── Device Status Card ─────────────────────────────────────────────────────

@Composable
private fun DeviceStatusCard(state: DashboardUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        state.deviceName.ifBlank { "Device" },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pendant_device),
                        contentDescription = "Device",
                        modifier = Modifier.size(62.dp)
                    )
                }
                Text(
                    state.connectionState.name.replace("_", " "),
                    style = MaterialTheme.typography.bodySmall,
                    color = when (state.connectionState) {
                        ConnectionState.CONNECTED -> Connected
                        ConnectionState.ERROR -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )
                if (state.batteryLevel >= 0) {
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.BatteryFull,
                            null,
                            tint = androidx.compose.ui.graphics.Color(0xFF4CAF50),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            "${state.batteryLevel}%",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

// ─── Sync Card ──────────────────────────────────────────────────────────────

@Composable
private fun SyncCard(
    state: DashboardUiState,
    onSync: () -> Unit,
    onCancel: () -> Unit,
    onViewFiles: () -> Unit
) {
    val sync = state.syncState

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Phase icon
                when (sync.phase) {
                    SyncPhase.IDLE, SyncPhase.FETCHING_LIST, SyncPhase.DOWNLOADING ->
                        Icon(Icons.Default.CloudDownload, null, tint = Primary,
                            modifier = Modifier.size(24.dp))
                    SyncPhase.COMPLETE ->
                        Icon(Icons.Default.CheckCircle, null, tint = Connected,
                            modifier = Modifier.size(24.dp))
                    SyncPhase.ERROR ->
                        Icon(Icons.Default.SyncProblem, null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(24.dp))
                }

                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "SD Card Sync",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        sync.statusText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (state.isSyncing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        strokeWidth = 3.dp,
                        color = Primary
                    )
                }
            }

            // Progress bars shown while syncing
            AnimatedVisibility(visible = state.isSyncing || sync.phase == SyncPhase.COMPLETE) {
                Column {
                    Spacer(Modifier.height(14.dp))

                    // Overall progress (files downloaded / total new files)
                    if (sync.newFilesCount > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Overall",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                "${sync.downloadedFiles} / ${sync.newFilesCount} files",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = sync.overallProgress,
                            modifier = Modifier.fillMaxWidth(),
                            color = Connected,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    // Per-file progress (shown during DOWNLOADING phase)
                    AnimatedVisibility(
                        visible = sync.phase == SyncPhase.DOWNLOADING &&
                            sync.currentFile.isNotBlank()
                    ) {
                        Column {
                            Spacer(Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    sync.currentFile.removeSuffix(".opus"),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    "${(sync.currentFileProgress * 100).toInt()}%",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Primary
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = sync.currentFileProgress,
                                modifier = Modifier.fillMaxWidth(),
                                color = Primary
                            )
                        }
                    }

                    // Summary after completion
                    AnimatedVisibility(visible = sync.phase == SyncPhase.COMPLETE) {
                        Column {
                            Spacer(Modifier.height(10.dp))
                            if (sync.totalFiles > 0) {
                                Text(
                                    "${sync.totalFiles} file${if (sync.totalFiles != 1) "s" else ""} on device SD card",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            // Action buttons
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                when (sync.phase) {
                    SyncPhase.IDLE, SyncPhase.ERROR -> {
                        Button(
                            onClick = onSync,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.CloudDownload, null,
                                modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Sync from SD Card")
                        }
                    }
                    SyncPhase.FETCHING_LIST, SyncPhase.DOWNLOADING -> {
                        OutlinedButton(
                            onClick = onCancel,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Cancel")
                        }
                    }
                    SyncPhase.COMPLETE -> {
                        Button(
                            onClick = onViewFiles,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Connected)
                        ) {
                            Icon(Icons.Default.FolderOpen, null,
                                modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("View Files", color = MaterialTheme.colorScheme.onPrimary)
                        }
                        OutlinedButton(
                            onClick = onSync,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Sync Again")
                        }
                    }
                }
            }
        }
    }
}

