package com.cyboglabs.diskey.presentation.player;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.PlaybackException;
import androidx.media3.exoplayer.ExoPlayer;
import com.cyboglabs.diskey.audio.OpusConverter;
import com.cyboglabs.diskey.audio.OggOpusWrapper;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.utils.FileUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\u0006\u0010\u001b\u001a\u00020\u0019J\r\u0010\u001c\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0002\u0010\u001dJ\r\u0010\u001e\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0002\u0010\u001dJ\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020\u0019H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006&"}, d2 = {"Lcom/cyboglabs/diskey/presentation/player/AudioPlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "opusConverter", "Lcom/cyboglabs/diskey/audio/OpusConverter;", "audioFileRepository", "Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;", "(Landroid/content/Context;Landroidx/lifecycle/SavedStateHandle;Lcom/cyboglabs/diskey/audio/OpusConverter;Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/presentation/player/PlayerUiState;", "filename", "", "pendingPlayRequest", "", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "initializePlayer", "", "onCleared", "playPause", "seekBack", "()Lkotlin/Unit;", "seekForward", "seekTo", "positionMs", "", "setPlaybackSpeed", "speed", "", "startPositionUpdater", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AudioPlayerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.audio.OpusConverter opusConverter = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.domain.repository.AudioFileRepository audioFileRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.presentation.player.PlayerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.player.PlayerUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.media3.exoplayer.ExoPlayer player;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String filename = null;
    private boolean pendingPlayRequest = false;
    
    @javax.inject.Inject()
    public AudioPlayerViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.audio.OpusConverter opusConverter, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.repository.AudioFileRepository audioFileRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.presentation.player.PlayerUiState> getUiState() {
        return null;
    }
    
    private final void initializePlayer() {
    }
    
    private final void startPositionUpdater() {
    }
    
    public final void playPause() {
    }
    
    public final void seekTo(long positionMs) {
    }
    
    public final void setPlaybackSpeed(float speed) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Unit seekForward() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Unit seekBack() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}