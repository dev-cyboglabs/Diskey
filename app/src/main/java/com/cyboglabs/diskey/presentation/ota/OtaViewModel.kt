package com.cyboglabs.diskey.presentation.ota

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyboglabs.diskey.ota.OtaManager
import com.cyboglabs.diskey.ota.OtaProgress
import com.cyboglabs.diskey.ota.OtaState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class OtaViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val otaManager: OtaManager
) : ViewModel() {

    val progress: StateFlow<OtaProgress> = otaManager.progress

    fun startUpdate(uri: Uri) {
        viewModelScope.launch {
            try {
                val tempFile = File(context.cacheDir, "firmware.bin")
                context.contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(tempFile).use { output -> input.copyTo(output) }
                }
                otaManager.startUpdate(tempFile)
            } catch (e: Exception) {
                Timber.e(e, "OtaViewModel: failed to start OTA")
            }
        }
    }

    fun reset() = otaManager.reset()
}
