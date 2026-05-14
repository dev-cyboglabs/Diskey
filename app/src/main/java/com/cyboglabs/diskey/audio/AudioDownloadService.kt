package com.cyboglabs.diskey.audio

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

/** Foreground service that runs audio file downloads in the background. */
@AndroidEntryPoint
class AudioDownloadService : LifecycleService() {

    @Inject lateinit var audioDownloadManager: AudioDownloadManager

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): AudioDownloadService = this@AudioDownloadService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(NOTIFICATION_ID, buildNotification("Preparing download…"))
        observeProgress()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val filename = intent?.getStringExtra(EXTRA_FILENAME) ?: return START_NOT_STICKY
        audioDownloadManager.enqueue(filename)
        return START_STICKY
    }

    override fun onDestroy() {
        audioDownloadManager.cancelAll()
        super.onDestroy()
    }

    private fun observeProgress() {
        lifecycleScope.launch {
            audioDownloadManager.progress.collect { progress ->
                progress ?: return@collect
                val nm = getSystemService(NotificationManager::class.java)
                nm.notify(NOTIFICATION_ID, buildNotification(
                    "Downloading ${progress.filename} (${(progress.percent * 100).toInt()}%)"
                ))
            }
        }
    }

    private fun createChannel() {
        val channel = NotificationChannel(CHANNEL_ID, "Audio Downloads", NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification(text: String): Notification {
        val pi = PendingIntent.getActivity(this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Diskey Download")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentIntent(pi)
            .setOngoing(true)
            .build()
    }

    companion object {
        const val EXTRA_FILENAME = "filename"
        private const val NOTIFICATION_ID = 1003
        private const val CHANNEL_ID = "diskey_download"
    }
}
