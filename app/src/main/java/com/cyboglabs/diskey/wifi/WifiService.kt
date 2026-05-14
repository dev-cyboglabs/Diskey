package com.cyboglabs.diskey.wifi

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.cyboglabs.diskey.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/** Foreground service that manages the WiFi TCP socket connection to the T240. */
@AndroidEntryPoint
class WifiService : LifecycleService() {

    @Inject lateinit var wifiTransferManager: WifiTransferManager

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): WifiService = this@WifiService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_CONNECT -> lifecycleScope.launch {
                wifiTransferManager.connect()
            }
            ACTION_DISCONNECT -> {
                wifiTransferManager.disconnect()
                stopSelf()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        wifiTransferManager.disconnect()
        super.onDestroy()
    }

    private fun createChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID, "WiFi Transfer", NotificationManager.IMPORTANCE_LOW
        ).apply { description = "High-speed file transfer via WiFi" }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val pi = PendingIntent.getActivity(this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Diskey WiFi Transfer")
            .setContentText("Connected to T240 hotspot")
            .setSmallIcon(android.R.drawable.ic_menu_share)
            .setContentIntent(pi)
            .setOngoing(true)
            .build()
    }

    companion object {
        const val ACTION_CONNECT = "com.cyboglabs.diskey.WIFI_CONNECT"
        const val ACTION_DISCONNECT = "com.cyboglabs.diskey.WIFI_DISCONNECT"
        private const val NOTIFICATION_ID = 1002
        private const val CHANNEL_ID = "diskey_wifi"
    }
}
