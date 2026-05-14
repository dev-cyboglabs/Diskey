package com.cyboglabs.diskey.audio;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import timber.log.Timber;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Converts OPUS files to WAV using FFmpegKit when direct OPUS playback
 * is not supported by ExoPlayer on the target device.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\fJ \u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/cyboglabs/diskey/audio/OpusConverter;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "ensureWav", "Ljava/io/File;", "opusFile", "wavFile", "(Ljava/io/File;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDurationMs", "", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "opusToWav", "app_debug"})
public final class OpusConverter {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public OpusConverter(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Converts an OPUS file to WAV using FFmpegKit.
     * Returns the WAV File on success, null on failure.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object opusToWav(@org.jetbrains.annotations.NotNull()
    java.io.File opusFile, @org.jetbrains.annotations.NotNull()
    java.io.File wavFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    /**
     * Returns WAV file if it already exists, otherwise converts.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureWav(@org.jetbrains.annotations.NotNull()
    java.io.File opusFile, @org.jetbrains.annotations.NotNull()
    java.io.File wavFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    /**
     * Gets audio duration in milliseconds using FFprobe.
     * Approximate — uses file size and expected bitrate if probe fails.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDurationMs(@org.jetbrains.annotations.NotNull()
    java.io.File opusFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
}