package com.cyboglabs.diskey.workers

import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cyboglabs.diskey.ble.BleConnectionManager
import com.cyboglabs.diskey.ble.BleService
import com.cyboglabs.diskey.data.datastore.AppPreferences
import com.cyboglabs.diskey.domain.model.ConnectionState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import timber.log.Timber

/**
 * WorkManager worker that attempts BLE reconnect on app restart or connectivity change.
 */
@HiltWorker
class ReconnectWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val bleConnectionManager: BleConnectionManager,
    private val appPreferences: AppPreferences
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val currentState = bleConnectionManager.connectionState.first()
        if (currentState == ConnectionState.CONNECTED) {
            Timber.d("ReconnectWorker: already connected, skipping")
            return Result.success()
        }

        val address = appPreferences.pairedAddress.first()
        if (address == null) {
            Timber.d("ReconnectWorker: no paired device, skipping")
            return Result.success()
        }

        val autoReconnect = appPreferences.autoReconnect.first()
        if (!autoReconnect) {
            Timber.d("ReconnectWorker: auto-reconnect disabled, skipping")
            return Result.success()
        }

        Timber.d("ReconnectWorker: attempting reconnect to $address")
        val intent = Intent(applicationContext, BleService::class.java).apply {
            action = BleService.ACTION_CONNECT
            putExtra(BleService.EXTRA_ADDRESS, address)
        }
        applicationContext.startForegroundService(intent)

        return Result.success()
    }
}
