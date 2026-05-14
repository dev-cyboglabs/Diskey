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
import com.cyboglabs.diskey.audio.OggOpusWrapper
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
                val buf = ByteArray(32)
                val n = opusFile.inputStream().use { it.read(buf) }
                if (n <= 0) "" else buf.copyOf(n).joinToString(" ") { b -> "%02X".format(b) }
            }.getOrDefault("")
            val headerAscii = runCatching {
                val buf = ByteArray(32)
                val n = opusFile.inputStream().use { it.read(buf) }
                if (n <= 0) "" else buf.copyOf(n).map { if (it in 32..126) it.toInt().toChar() else '.' }.joinToString("")
            }.getOrDefault("")
            Timber.w("AudioPlayerViewModel: file='${opusFile.name}' size=${opusFile.length()}B")
            Timber.w("AudioPlayerViewModel: header HEX: $headerHex")
            Timber.w("AudioPlayerViewModel: header ASCII: $headerAscii")

            val isOggContainer = runCatching {
                val buf = ByteArray(4)
                val n = opusFile.inputStream().use { it.read(buf) }
                n == 4 && buf.contentEquals(byteArrayOf('O'.code.toByte(), 'g'.code.toByte(), 'g'.code.toByte(), 'S'.code.toByte()))
            }.getOrDefault(false)

            val metaDurationMs = runCatching { audioFileRepository.getFile(filename)?.durationMs ?: 0L }
                .getOrDefault(0L)

            // Determine playback strategy based on file format
            val playbackFile = when {
                // Already OGG Opus - play directly
                isOggContainer -> {
                    Timber.d("AudioPlayerViewModel: OGG Opus detected, playing directly")
                    opusFile
                }
                
                // Raw Opus - wrap into OGG container (fixed-size frames)
                else -> {
                    val frameDurationMs = 20
                    val inferredFrameCount = if (metaDurationMs > 0L) {
                        ((metaDurationMs + (frameDurationMs / 2)) / frameDurationMs).coerceAtLeast(1L)
                    } else {
                        0L
                    }
                    val inferredFrameSize = if (inferredFrameCount > 0L) {
                        val size = (opusFile.length() / inferredFrameCount).toInt()
                        if (size > 0) size else 80
                    } else {
                        80
                    }

                    Timber.d(
                        "AudioPlayerViewModel: Raw Opus detected; durationMs=$metaDurationMs inferredFrameSize=$inferredFrameSize"
                    )

                    val oggFile = File(opusFile.parent, opusFile.nameWithoutExtension + ".ogg")
                    OggOpusWrapper.wrapRawOpus(
                        rawOpusFile = opusFile,
                        outputOggFile = oggFile,
                        frameSizeBytes = inferredFrameSize,
                        frameDurationMs = frameDurationMs
                    ) ?: run {
                        _uiState.update { it.copy(isLoading = false, error = "Failed to prepare audio file") }
                        return@launch
                    }
                }
            }

            val exo = ExoPlayer.Builder(context).build().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(C.USAGE_MEDIA)
                        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                        .build(),
                    true
                )
                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.fromFile(playbackFile))
                    .setMimeType(MimeTypes.AUDIO_OGG)
                    .build()
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
