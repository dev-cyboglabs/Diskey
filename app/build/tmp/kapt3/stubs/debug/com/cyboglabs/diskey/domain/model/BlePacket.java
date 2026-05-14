package com.cyboglabs.diskey.domain.model;

/**
 * Sealed hierarchy of parsed BLE notification packets.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\u0003\u0004\u0005\u0006\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/cyboglabs/diskey/domain/model/BlePacket;", "", "()V", "Lcom/cyboglabs/diskey/domain/model/AudioDataPacket;", "Lcom/cyboglabs/diskey/domain/model/BatteryPacket;", "Lcom/cyboglabs/diskey/domain/model/FileCompletePacket;", "Lcom/cyboglabs/diskey/domain/model/FileListPacket;", "Lcom/cyboglabs/diskey/domain/model/HandshakePacket;", "Lcom/cyboglabs/diskey/domain/model/OtaProgressPacket;", "Lcom/cyboglabs/diskey/domain/model/UnknownPacket;", "app_debug"})
public abstract class BlePacket {
    
    private BlePacket() {
        super();
    }
}