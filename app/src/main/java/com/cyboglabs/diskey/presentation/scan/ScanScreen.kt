package com.cyboglabs.diskey.presentation.scan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.domain.model.Device
import com.cyboglabs.diskey.presentation.theme.Connected
import com.cyboglabs.diskey.presentation.theme.Primary
import com.cyboglabs.diskey.utils.toRssiDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(
    onDeviceConnected: () -> Unit,
    viewModel: ScanViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.connectionState) {
        if (state.connectionState == ConnectionState.CONNECTED) {
            onDeviceConnected()
        }
    }

    LaunchedEffect(Unit) { viewModel.startScan() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Find Device", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {
                        if (state.isScanning) viewModel.stopScan()
                        else viewModel.startScan()
                    }) {
                        Icon(
                            if (state.isScanning) Icons.Default.Close else Icons.Default.Refresh,
                            contentDescription = if (state.isScanning) "Stop" else "Scan"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            // Search bar
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = viewModel::setSearchQuery,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search devices…") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Scanning indicator
            AnimatedVisibility(visible = state.isScanning) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = Primary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Scanning for T240 devices…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            if (state.filteredDevices.isEmpty() && !state.isScanning) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Bluetooth, null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
                        Spacer(Modifier.height(16.dp))
                        Text("No devices found",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = viewModel::startScan) { Text("Scan Again") }
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.filteredDevices, key = { it.address }) { device ->
                        DeviceCard(
                            device = device,
                            isConnecting = state.connectingAddress == device.address &&
                                state.connectionState != ConnectionState.CONNECTED,
                            onConnect = { viewModel.connect(device) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceCard(
    device: Device,
    isConnecting: Boolean,
    onConnect: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.15f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "scale"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .scale(if (isConnecting) scale else 1f)
                    .background(Primary.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Bluetooth, null, tint = Primary, modifier = Modifier.size(24.dp))
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = device.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = device.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.SignalCellularAlt, null,
                        modifier = Modifier.size(14.dp),
                        tint = when {
                            device.rssi >= -60 -> Connected
                            device.rssi >= -75 -> Primary
                            else -> MaterialTheme.colorScheme.error
                        })
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${device.rssi} dBm · ${device.rssi.toRssiDescription()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            if (isConnecting) {
                CircularProgressIndicator(modifier = Modifier.size(28.dp), strokeWidth = 2.dp)
            } else {
                Button(onClick = onConnect, shape = RoundedCornerShape(8.dp)) {
                    Text("Connect")
                }
            }
        }
    }
}
