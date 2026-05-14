package com.cyboglabs.diskey.presentation.player

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.PlaybackException
import androidx.media3.exoplayer.ExoPlayer
import com.cyboglabs.diskey.audio.OpusConverter
import com.cyboglabs.diskey.domain.repository.AudioFileRepository
import com.cyboglabs.diskey.utils.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

data class PlayerUiState(
    val filename: String = "",
    val isLoading: Boolean = true,
    val isPlaying: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L,
    val playbackSpeed: Float = 1.0f,
    val error: String? = null
) {
    val progress: Float get() = if (durationMs > 0L) positionMs.toFloat() / durationMs else 0f
}

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val opusConverter: OpusConverter,
    private val audioFileRepository: AudioFileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private var player: ExoPlayer? = null
    private val filename: String = savedStateHandle["filename"] ?: ""
    private var pendingPlayRequest = false

    init {
        _uiState.update { it.copy(filename = filename) }
        initializePlayer()
        startPositionUpdater()
    }

    private fun initializePlayer() {
        viewModelScope.launch {
            val opusFile = FileUtils.getOpusFile(context, filename)
            if (!opusFile.exists()) {
                _uiState.update { it.copy(isLoading = false, error = "File not found") }
                return@launch
            }

            val headerHex = runCatching {
                val buf = ByteArray(16)
                val n = opusFile.inputStream().use { it.read(buf) }
                if (n <= 0) "" else buf.copyOf(n).joinToString(" ") { b -> "%02X".format(b) }
            }.getOrDefault("")
            Timber.d("AudioPlayerViewModel: file='${opusFile.name}' size=${opusFile.length()}B header=$headerHex")

            val isOggContainer = runCatching {
                val buf = ByteArray(4)
                val n = opusFile.inputStream().use { it.read(buf) }
                n == 4 && buf.contentEquals(byteArrayOf('O'.code.toByte(), 'g'.code.toByte(), 'g'.code.toByte(), 'S'.code.toByte()))
            }.getOrDefault(false)

            // Try direct OPUS playback first; fall back to WAV conversion
            val playbackFile = if (canPlayOpusDirect(isOggContainer)) {
                opusFile
            } else {
                Timber.d("AudioPlayerViewModel: converting OPUS → WAV")
                val wavFile = FileUtils.getWavFile(context, filename)
                opusConverter.ensureWav(opusFile, wavFile) ?: run {
                    _uiState.update { it.copy(isLoading = false, error = "Conversion failed") }
                    return@launch
                }
            }

            if (playbackFile == opusFile && !isOggContainer) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Unsupported OPUS format (not OGG container). Please re-download or enable OPUS→WAV conversion."
                    )
                }
                return@launch
            }

            val exo = ExoPlayer.Builder(context).build().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(C.USAGE_MEDIA)
                        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                        .build(),
                    true
                )
                val mediaItem = if (playbackFile == opusFile) {
                    MediaItem.Builder()
                        .setUri(Uri.fromFile(playbackFile))
                        .setMimeType(MimeTypes.AUDIO_OGG)
                        .build()
                } else {
                    MediaItem.fromUri(Uri.fromFile(playbackFile))
                }
                setMediaItem(mediaItem)
                prepare()
                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _uiState.update { it.copy(isPlaying = isPlaying) }
                    }
                    override fun onPlaybackStateChanged(state: Int) {
                        if (state == Player.STATE_READY) {
                            _uiState.update { it.copy(isLoading = false, durationMs = duration) }
                            if (pendingPlayRequest) {
                                pendingPlayRequest = false
                                play()
                            }
                        }
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        Timber.e(error, "AudioPlayerViewModel: player error")
                        _uiState.update { it.copy(isLoading = false, error = error.message ?: "Playback error") }
                    }
                })
            }
            player = exo

            // Restore resume position
            val resumePos = audioFileRepository.getResumePosition(filename)
            if (resumePos > 0L) exo.seekTo(resumePos)
        }
    }

    private fun canPlayOpusDirect(isOggContainer: Boolean): Boolean {
        return isOggContainer
    }

    private fun startPositionUpdater() {
        viewModelScope.launch {
            while (isActive) {
                delay(500)
                player?.let { p ->
                    _uiState.update { it.copy(positionMs = p.currentPosition) }
                }
            }
        }
    }

    fun playPause() {
        val p = player
        if (p == null) {
            pendingPlayRequest = true
            return
        }
        if (p.isPlaying) p.pause() else p.play()
    }

    fun seekTo(positionMs: Long) {
        player?.seekTo(positionMs)
        _uiState.update { it.copy(positionMs = positionMs) }
    }

    fun setPlaybackSpeed(speed: Float) {
        player?.setPlaybackSpeed(speed)
        _uiState.update { it.copy(playbackSpeed = speed) }
    }

    fun seekForward() = player?.let { seekTo((it.currentPosition + 10_000).coerceAtMost(it.duration)) }
    fun seekBack() = player?.let { seekTo((it.currentPosition - 10_000).coerceAtLeast(0L)) }

    override fun onCleared() {
        player?.release()
        player = null
        super.onCleared()
    }
}
