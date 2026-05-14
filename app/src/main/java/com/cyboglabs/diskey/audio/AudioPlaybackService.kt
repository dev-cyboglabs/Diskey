package com.cyboglabs.diskey.audio

import android.content.Context
import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File

/**
 * Background audio playback service backed by ExoPlayer + Media3 MediaSession.
 * Provides system-level playback controls (notification, lock screen, headsets).
 */
@AndroidEntryPoint
class AudioPlaybackService : MediaSessionService() {

    private var mediaSession: MediaSession? = null
    private var player: ExoPlayer? = null

    override fun onCreate() {
        super.onCreate()
        val exo = ExoPlayer.Builder(this).build().also { player = it }
        mediaSession = MediaSession.Builder(this, exo)
            .setId("DisKeyPlayback")
            .build()
        Timber.d("AudioPlaybackService: created")
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        player = null
        super.onDestroy()
        Timber.d("AudioPlaybackService: destroyed")
    }

    companion object {
        fun playFile(context: Context, file: File) {
            val intent = Intent(context, AudioPlaybackService::class.java)
            intent.putExtra("file_path", file.absolutePath)
            context.startService(intent)
        }
    }
}
