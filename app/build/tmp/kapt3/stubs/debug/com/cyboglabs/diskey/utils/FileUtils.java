package com.cyboglabs.diskey.utils;

import android.content.Context;
import android.os.Environment;
import timber.log.Timber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\u0018"}, d2 = {"Lcom/cyboglabs/diskey/utils/FileUtils;", "", "()V", "appendBytesToFile", "", "file", "Ljava/io/File;", "data", "", "deleteFile", "formatSize", "", "bytes", "", "getAudioDirectory", "context", "Landroid/content/Context;", "getOpusFile", "filename", "getTempDirectory", "getWavFile", "opusFilename", "isOggContainer", "writeBytesToFile", "app_debug"})
public final class FileUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.utils.FileUtils INSTANCE = null;
    
    private FileUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getAudioDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getOpusFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getWavFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String opusFilename) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getTempDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final boolean writeBytesToFile(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    byte[] data) {
        return false;
    }
    
    public final boolean appendBytesToFile(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    byte[] data) {
        return false;
    }
    
    public final boolean deleteFile(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatSize(long bytes) {
        return null;
    }
    
    public final boolean isOggContainer(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return false;
    }
}