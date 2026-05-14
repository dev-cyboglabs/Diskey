package com.cyboglabs.diskey.wifi.protocol;

import com.cyboglabs.diskey.utils.CrcUtils;
import com.cyboglabs.diskey.wifi.WifiConstants;
import timber.log.Timber;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Serializes/deserializes MeChoWifi TCP packets.
 *
 * Wire format (all multi-byte fields are little-endian):
 *  [header:14][command:2][sequence:4][length:4][crc:2][data:N][tail:12]
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/cyboglabs/diskey/wifi/protocol/WifiPacketSerializer;", "", "()V", "FIXED_OVERHEAD", "", "HEADER_BYTES", "", "TAIL_BYTES", "deserialize", "Lcom/cyboglabs/diskey/wifi/protocol/WifiPacket;", "bytes", "heartbeat", "sequence", "serialize", "packet", "app_debug"})
public final class WifiPacketSerializer {
    @org.jetbrains.annotations.NotNull()
    private static final byte[] HEADER_BYTES = null;
    @org.jetbrains.annotations.NotNull()
    private static final byte[] TAIL_BYTES = null;
    private static final int FIXED_OVERHEAD = 38;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.wifi.protocol.WifiPacketSerializer INSTANCE = null;
    
    private WifiPacketSerializer() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] serialize(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.wifi.protocol.WifiPacket packet) {
        return null;
    }
    
    /**
     * Deserializes bytes from the TCP stream into a WifiPacket.
     * Returns null on header/tail/CRC mismatch.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.cyboglabs.diskey.wifi.protocol.WifiPacket deserialize(@org.jetbrains.annotations.NotNull()
    byte[] bytes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] heartbeat(int sequence) {
        return null;
    }
}