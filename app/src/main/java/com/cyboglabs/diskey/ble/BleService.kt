package com.cyboglabs.diskey.ble

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.cyboglabs.diskey.R
import com.cyboglabs.diskey.domain.model.ConnectionState
import com.cyboglabs.diskey.domain.repository.DeviceRepository
import com.cyboglabs.diskey.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Foreground service that maintains the BLE connection to the T240 device.
 * Runs as long as the user is connected; handles reconnect with exponential backoff.
 */
@AndroidEntryPoint
class BleService : LifecycleService() {

    @Inject lateinit var bleDeviceManager: BleDeviceManager
    @Inject lateinit var bleConnectionManager: BleConnectionManager

    private val binder = LocalBinder()
    private var reconnectJob: Job? = null

    inner class LocalBinder : Binder() {
        fun getService(): BleService = this@BleService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification("Connecting to device…"))
        Timber.d("BleService: created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_CONNECT -> {
                val address = intent.getStringExtra(EXTRA_ADDRESS) ?: return START_NOT_STICKY
                lifecycleScope.launch(Dispatchers.IO) { bleConnectionManager.connect(address) }
            }
            ACTION_DISCONNECT -> {
                lifecycleScope.launch(Dispatchers.IO) { bleConnectionManager.disconnect() }
                stopSelf()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        reconnectJob?.cancel()
        lifecycleScope.launch(Dispatchers.IO) { bleConnectionManager.disconnect() }
        super.onDestroy()
        Timber.d("BleService: destroyed")
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "BLE Connection",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Maintains connection to T240 device"
        }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(text: String): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Diskey")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    companion object {
        const val ACTION_CONNECT = "com.cyboglabs.diskey.ACTION_CONNECT"
        const val ACTION_DISCONNECT = "com.cyboglabs.diskey.ACTION_DISCONNECT"
        const val EXTRA_ADDRESS = "device_address"
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "diskey_ble"
    }
}
