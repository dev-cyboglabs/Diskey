package com.cyboglabs.diskey.utils;

/**
 * CRC-16/CCITT-FALSE used by the T240 protocol for packet integrity checks.
 * Initial value: 0xFFFF, polynomial: 0x1021, no reflection, XOR out: 0x0000.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004J*\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cyboglabs/diskey/utils/CrcUtils;", "", "()V", "INIT", "", "POLY", "crcTable", "", "appendCrc", "", "data", "compute", "offset", "length", "computeIncremental", "initialCrc", "extractAndVerify", "dataWithCrc", "initialValue", "validate", "", "expectedCrc", "app_debug"})
public final class CrcUtils {
    private static final int POLY = 4129;
    private static final int INIT = 65535;
    @org.jetbrains.annotations.NotNull()
    private static final int[] crcTable = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.utils.CrcUtils INSTANCE = null;
    
    private CrcUtils() {
        super();
    }
    
    public final int compute(@org.jetbrains.annotations.NotNull()
    byte[] data, int offset, int length) {
        return 0;
    }
    
    public final int initialValue() {
        return 0;
    }
    
    public final int computeIncremental(int initialCrc, @org.jetbrains.annotations.NotNull()
    byte[] data, int offset, int length) {
        return 0;
    }
    
    public final boolean validate(@org.jetbrains.annotations.NotNull()
    byte[] data, int expectedCrc) {
        return false;
    }
    
    /**
     * Append 2-byte CRC (little-endian) to data.
     */
    @org.jetbrains.annotations.NotNull()
    public final byte[] appendCrc(@org.jetbrains.annotations.NotNull()
    byte[] data) {
        return null;
    }
    
    /**
     * Extract and verify trailing 2-byte CRC (little-endian). Returns null if invalid.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] extractAndVerify(@org.jetbrains.annotations.NotNull()
    byte[] dataWithCrc) {
        return null;
    }
}