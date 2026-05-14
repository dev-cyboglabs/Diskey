package com.cyboglabs.diskey.audio;

import timber.log.Timber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Wraps raw OPUS data in an OGG container so ExoPlayer can decode it.
 *
 * This is needed when the T240 device sends raw Opus frames instead of
 * pre-packaged Ogg Opus files.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0002J(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J*\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u0004J8\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002J6\u0010!\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0#2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002J0\u0010$\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/cyboglabs/diskey/audio/OggOpusWrapper;", "", "()V", "DEFAULT_FRAME_DURATION_MS", "", "MAX_OGG_SEGMENTS", "MAX_SEGMENT_SIZE", "OPUS_SAMPLE_RATE", "SERIAL_NUMBER", "buildLacingForPacket", "", "packet", "buildOpusHeadPacket", "buildOpusTagsPacket", "calculateOggCrc", "header", "payload", "wrapOpusDataInOggPages", "", "rawOpusFile", "Ljava/io/File;", "out", "Ljava/io/FileOutputStream;", "frameSizeBytes", "frameDurationMs", "wrapRawOpus", "outputOggFile", "writeOggPage", "segmentTable", "sequenceNumber", "headerType", "granulePosition", "", "writeOggPagePackets", "packets", "", "writeOggPageSinglePacket", "writeOpusHeaders", "app_debug"})
public final class OggOpusWrapper {
    private static final int OPUS_SAMPLE_RATE = 48000;
    private static final int DEFAULT_FRAME_DURATION_MS = 20;
    private static final int MAX_OGG_SEGMENTS = 255;
    private static final int MAX_SEGMENT_SIZE = 255;
    private static final int SERIAL_NUMBER = 1145656139;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.audio.OggOpusWrapper INSTANCE = null;
    
    private OggOpusWrapper() {
        super();
    }
    
    /**
     * Wraps raw OPUS data in minimal OGG Opus container.
     * Returns the wrapped file, or null on failure.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.io.File wrapRawOpus(@org.jetbrains.annotations.NotNull()
    java.io.File rawOpusFile, @org.jetbrains.annotations.NotNull()
    java.io.File outputOggFile, int frameSizeBytes, int frameDurationMs) {
        return null;
    }
    
    private final void writeOpusHeaders(java.io.FileOutputStream out) {
    }
    
    private final byte[] buildOpusHeadPacket() {
        return null;
    }
    
    private final byte[] buildOpusTagsPacket() {
        return null;
    }
    
    private final void wrapOpusDataInOggPages(java.io.File rawOpusFile, java.io.FileOutputStream out, int frameSizeBytes, int frameDurationMs) {
    }
    
    private final void writeOggPageSinglePacket(java.io.FileOutputStream out, byte[] payload, int sequenceNumber, int headerType, long granulePosition) {
    }
    
    private final void writeOggPagePackets(java.io.FileOutputStream out, java.util.List<byte[]> packets, int sequenceNumber, int headerType, long granulePosition) {
    }
    
    private final byte[] buildLacingForPacket(byte[] packet) {
        return null;
    }
    
    private final void writeOggPage(java.io.FileOutputStream out, byte[] segmentTable, byte[] payload, int sequenceNumber, int headerType, long granulePosition) {
    }
    
    private final int calculateOggCrc(byte[] header, byte[] payload) {
        return 0;
    }
}