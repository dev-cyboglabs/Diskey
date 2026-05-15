package com.cyboglabs.diskey.presentation.player

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.presentation.theme.Primary
import com.cyboglabs.diskey.presentation.theme.WaveformActive
import com.cyboglabs.diskey.presentation.theme.WaveformInactive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioPlayerScreen(
    filename: String,
    onBack: () -> Unit,
    viewModel: AudioPlayerViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showSpeedMenu by remember { mutableStateOf(false) }

    // Auto-play when screen opens
    LaunchedEffect(Unit) {
        viewModel.playPause()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        state.filename.removeSuffix(".opus"),
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") }
                },
                actions = {
                    IconButton(onClick = { showSpeedMenu = true }) {
                        Icon(Icons.Default.Speed, "Speed")
                    }
                    DropdownMenu(expanded = showSpeedMenu, onDismissRequest = { showSpeedMenu = false }) {
                        listOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f).forEach { speed ->
                            DropdownMenuItem(
                                text = { Text("${speed}x ${if (speed == state.playbackSpeed) "✓" else ""}") },
                                onClick = { viewModel.setPlaybackSpeed(speed); showSpeedMenu = false }
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.height(32.dp))

            // Album art / waveform placeholder
            Card(
                modifier = Modifier.size(200.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    if (state.isLoading) {
                        CircularProgressIndicator(color = Primary)
                    } else {
                        // Simple waveform bars
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(20) { i ->
                                val barHeight = (30..120).random().dp
                                val isActive = i < (20 * state.progress).toInt()
                                Box(
                                    modifier = Modifier
                                        .size(6.dp, barHeight)
                                        .background(
                                            if (isActive) WaveformActive else WaveformInactive,
                                            RoundedCornerShape(3.dp)
                                        )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Error
            state.error?.let { err ->
                Text(err, color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium)
            }

            // Progress
            Column(modifier = Modifier.fillMaxWidth()) {
                Slider(
                    value = state.progress,
                    onValueChange = { viewModel.seekTo((it * state.durationMs).toLong()) },
                    colors = SliderDefaults.colors(thumbColor = Primary, activeTrackColor = Primary),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text(formatMs(state.positionMs), style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface)
                    Text(formatMs(state.durationMs), style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }

            // Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = viewModel::seekBack, modifier = Modifier.size(48.dp)) {
                    Icon(Icons.Default.Replay10, "Rewind 10s", modifier = Modifier.size(32.dp))
                }

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(Primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = viewModel::playPause) {
                        Icon(
                            if (state.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            if (state.isPlaying) "Pause" else "Play",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                IconButton(onClick = viewModel::seekForward, modifier = Modifier.size(48.dp)) {
                    Icon(Icons.Default.Forward10, "Forward 10s", modifier = Modifier.size(32.dp))
                }
            }

            // Speed indicator
            Text("${state.playbackSpeed}x speed",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center)

            Spacer(Modifier.height(24.dp))
        }
    }
}

private fun formatMs(ms: Long): String {
    val totalSec = ms / 1000
    val min = totalSec / 60
    val sec = totalSec % 60
    return "%d:%02d".format(min, sec)
}
