package com.cyboglabs.diskey.workers

import android.content.Context
import android.content.Intent
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cyboglabs.diskey.audio.AudioDownloadService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

/** WorkManager worker that initiates a background audio file download. */
@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val filename = inputData.getString(KEY_FILENAME) ?: return Result.failure()
        Timber.d("DownloadWorker: enqueuing download for '$filename'")

        val intent = Intent(applicationContext, AudioDownloadService::class.java).apply {
            putExtra(AudioDownloadService.EXTRA_FILENAME, filename)
        }
        applicationContext.startForegroundService(intent)
        return Result.success()
    }

    companion object {
        const val KEY_FILENAME = "filename"
    }
}
