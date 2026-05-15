package com.cyboglabs.diskey.presentation.splash

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyboglabs.diskey.R
import com.cyboglabs.diskey.ble.BleService
import com.cyboglabs.diskey.presentation.theme.Primary
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.cyboglabs.diskey.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    onNavigateToScan: () -> Unit,
    onNavigateToDashboard: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val permissions = PermissionUtils.getRequiredBlePermissions().toList() +
        PermissionUtils.getRequiredNotificationPermissions().toList()

    val permissionsState = rememberMultiplePermissionsState(permissions)
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800), label = "splash_alpha"
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(200)
        if (!permissionsState.allPermissionsGranted) {
            permissionsState.launchMultiplePermissionRequest()
        }
        delay(800)
        
        try {
            // Check if device is paired in app preferences
            val prefs = com.cyboglabs.diskey.data.datastore.AppPreferences(context)
            val pairedAddress = prefs.pairedAddress.first()
            val autoReconnectEnabled = prefs.autoReconnect.first()
            
            if (pairedAddress != null && pairedAddress.isNotEmpty() && autoReconnectEnabled) {
                // Device is paired and auto-reconnect is enabled, trigger auto-reconnect
                coroutineScope.launch {
                    try {
                        val intent = Intent(context, BleService::class.java).apply {
                            action = BleService.ACTION_CONNECT
                            putExtra(BleService.EXTRA_ADDRESS, pairedAddress)
                        }
                        context.startForegroundService(intent)
                    } catch (e: Exception) {
                        timber.log.Timber.e(e, "SplashScreen: Failed to start BLE service")
                    }
                }
                onNavigateToDashboard()
            } else if (pairedAddress != null && pairedAddress.isNotEmpty()) {
                // Device is paired but auto-reconnect is disabled, go to dashboard without connecting
                onNavigateToDashboard()
            } else {
                // No paired device, go to scan screen
                onNavigateToScan()
            }
        } catch (e: Exception) {
            timber.log.Timber.e(e, "SplashScreen: Error during initialization")
            // Navigate to scan screen as fallback
            onNavigateToScan()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .alpha(alpha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.diskey_logo),
            contentDescription = "Diskey Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "DISKEY",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 8.sp
        )
    }
}
