package com.cyboglabs.diskey.presentation.ota

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.ota.OtaState
import com.cyboglabs.diskey.presentation.theme.Connected
import com.cyboglabs.diskey.presentation.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtaScreen(
    onBack: () -> Unit,
    viewModel: OtaViewModel = hiltViewModel()
) {
    val progress by viewModel.progress.collectAsState()

    val filePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri -> uri?.let { viewModel.startUpdate(it) } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OTA Firmware Update", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                when (progress.state) {
                    OtaState.COMPLETE -> Icons.Default.CheckCircle
                    OtaState.FAILED -> Icons.Default.Error
                    else -> Icons.Default.SystemUpdate
                },
                contentDescription = null,
                tint = when (progress.state) {
                    OtaState.COMPLETE -> Connected
                    OtaState.FAILED -> MaterialTheme.colorScheme.error
                    else -> Primary
                },
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                when (progress.state) {
                    OtaState.IDLE -> "Select firmware file to begin OTA update"
                    OtaState.STARTING -> "Initializing OTA update…"
                    OtaState.TRANSFERRING -> "Transferring firmware: ${(progress.percent * 100).toInt()}%"
                    OtaState.VERIFYING -> "Verifying firmware…"
                    OtaState.COMPLETE -> "Firmware update complete!"
                    OtaState.FAILED -> "Update failed: ${progress.errorMessage}"
                },
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            if (progress.state == OtaState.TRANSFERRING || progress.state == OtaState.VERIFYING) {
                Spacer(Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = progress.percent,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Text("${progress.bytesSent} / ${progress.totalBytes} bytes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface)
            }

            Spacer(Modifier.height(32.dp))

            if (progress.state == OtaState.IDLE || progress.state == OtaState.FAILED ||
                progress.state == OtaState.COMPLETE) {
                Button(
                    onClick = { filePicker.launch("*/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (progress.state == OtaState.COMPLETE) "Update Again" else "Select Firmware File")
                }

                if (progress.state != OtaState.IDLE) {
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = viewModel::reset, modifier = Modifier.fillMaxWidth()) {
                        Text("Reset")
                    }
                }
            }
        }
    }
}
