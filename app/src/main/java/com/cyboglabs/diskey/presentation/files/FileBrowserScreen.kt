package com.cyboglabs.diskey.presentation.files

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.domain.model.AudioFile
import com.cyboglabs.diskey.presentation.theme.Connected
import com.cyboglabs.diskey.presentation.theme.Primary
import com.cyboglabs.diskey.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FileBrowserScreen(
    onPlayFile: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: FileBrowserViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showSortMenu by remember { mutableStateOf(false) }
    val pullState = rememberPullToRefreshState()

    BackHandler { onBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (state.selectedFiles.isEmpty())
                            "Recordings (${state.filteredFiles.size})"
                        else "${state.selectedFiles.size} selected",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showSortMenu = true }) {
                        Icon(Icons.Default.Sort, "Sort")
                    }
                    DropdownMenu(
                        expanded = showSortMenu,
                        onDismissRequest = { showSortMenu = false }
                    ) {
                        SortOrder.values().forEach { order ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        order.name.replace("_", " ").lowercase()
                                            .replaceFirstChar { it.uppercase() }
                                    )
                                },
                                onClick = {
                                    viewModel.setSortOrder(order)
                                    showSortMenu = false
                                }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(visible = state.selectedFiles.isNotEmpty()) {
                FloatingActionButton(
                    onClick = viewModel::downloadSelected,
                    containerColor = Primary
                ) {
                    Icon(Icons.Default.CloudDownload, "Download selected",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .nestedScroll(pullState.nestedScrollConnection)
        ) {
            Column {
                // Search bar
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = viewModel::setSearchQuery,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search recordings…") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                if (state.isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                // Empty state
                if (!state.hasAnyFiles && !state.isLoading) {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Headphones, null,
                                modifier = Modifier.size(72.dp),
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                "No recordings yet",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Sync from the dashboard to download recordings from the device SD card.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                    return@Column
                }

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // ── Downloaded — ready to play ────────────────────────────
                    if (state.playableFiles.isNotEmpty()) {
                        stickyHeader {
                            SectionHeader(
                                icon = Icons.Default.PlayCircleFilled,
                                title = "On This Phone",
                                count = state.playableFiles.size,
                                iconTint = Connected
                            )
                        }
                        items(state.playableFiles, key = { "p_${it.filename}" }) { file ->
                            AudioFileCard(
                                file = file,
                                isSelected = file.filename in state.selectedFiles,
                                isDownloading = file.filename in state.downloadingFiles,
                                isDownloaded = true,
                                onCardClick = {
                                    if (state.selectedFiles.isNotEmpty()) {
                                        viewModel.toggleSelect(file.filename)
                                    } else {
                                        onPlayFile(file.filename)
                                    }
                                },
                                onLongClick = { viewModel.toggleSelect(file.filename) },
                                onActionClick = { onPlayFile(file.filename) }
                            )
                        }
                    }

                    // ── On device SD card — not yet downloaded ────────────────
                    if (state.pendingFiles.isNotEmpty()) {
                        stickyHeader {
                            SectionHeader(
                                icon = Icons.Default.DownloadForOffline,
                                title = "On Device SD Card",
                                count = state.pendingFiles.size,
                                iconTint = Primary
                            )
                        }
                        items(state.pendingFiles, key = { "d_${it.filename}" }) { file ->
                            AudioFileCard(
                                file = file,
                                isSelected = file.filename in state.selectedFiles,
                                isDownloading = file.filename in state.downloadingFiles,
                                isDownloaded = false,
                                onCardClick = {
                                    if (state.selectedFiles.isNotEmpty()) {
                                        viewModel.toggleSelect(file.filename)
                                    } else {
                                        viewModel.downloadFile(file.filename)
                                    }
                                },
                                onLongClick = { viewModel.toggleSelect(file.filename) },
                                onActionClick = { viewModel.downloadFile(file.filename) }
                            )
                        }
                    }

                    item { Spacer(Modifier.height(80.dp)) }
                }
            }

            PullToRefreshContainer(
                state = pullState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

// ─── Section header ──────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    count: Int,
    iconTint: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = iconTint, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(6.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .background(iconTint.copy(alpha = 0.15f), CircleShape)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                "$count",
                style = MaterialTheme.typography.labelSmall,
                color = iconTint,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ─── Audio file card ─────────────────────────────────────────────────────────

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AudioFileCard(
    file: AudioFile,
    isSelected: Boolean,
    isDownloading: Boolean,
    isDownloaded: Boolean,
    onCardClick: () -> Unit,
    onLongClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onCardClick, onLongClick = onLongClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isSelected -> Primary.copy(alpha = 0.12f)
                isDownloaded -> MaterialTheme.colorScheme.surfaceVariant
                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDownloaded) 2.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading icon — selection check or audio icon
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        color = if (isDownloaded) Connected.copy(alpha = 0.1f)
                        else Primary.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(Icons.Default.CheckCircle, null, tint = Primary,
                        modifier = Modifier.size(24.dp))
                } else {
                    Icon(
                        if (isDownloaded) Icons.Default.Headphones else Icons.Default.AudioFile,
                        null,
                        tint = if (isDownloaded) Connected else Primary.copy(alpha = 0.6f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            // File info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    file.displayName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(2.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    MetaChip(file.durationDisplay)
                    MetaChip(file.sizeDisplay)
                }
                Spacer(Modifier.height(2.dp))
                Text(
                    file.createdAtEpoch.toFormattedDate("MMM dd, yyyy · HH:mm"),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                // Per-file download progress
                if (isDownloading) {
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = Primary
                    )
                }
            }

            Spacer(Modifier.width(8.dp))

            // Action button — PLAY or DOWNLOAD
            if (isDownloading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    strokeWidth = 3.dp,
                    color = Primary
                )
            } else if (isDownloaded) {
                // Large prominent PLAY button
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            "Play",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            } else {
                // Download button
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.12f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Icon(
                            Icons.Default.CloudDownload,
                            "Download",
                            tint = Primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaChip(text: String) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.outline.copy(alpha = 0.1f),
                RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
