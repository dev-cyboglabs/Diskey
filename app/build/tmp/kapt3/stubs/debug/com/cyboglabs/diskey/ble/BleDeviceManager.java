package com.cyboglabs.diskey.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import com.cyboglabs.diskey.ble.protocol.PacketParser;
import com.cyboglabs.diskey.domain.model.BlePacket;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import no.nordicsemi.android.ble.BleManager;
import no.nordicsemi.android.ble.data.Data;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Nordic BLE Manager for T240 device communication.
 * Handles service discovery, notifications, and write operations.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010!\u001a\u00020\tH\u0014J\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/cyboglabs/diskey/ble/BleDeviceManager;", "Lno/nordicsemi/android/ble/BleManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_connectionReady", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_gattCallback", "Lno/nordicsemi/android/ble/BleManager$BleManagerGattCallback;", "_mtu", "", "connectionReady", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionReady", "()Lkotlinx/coroutines/flow/StateFlow;", "mtu", "getMtu", "notifyCharacteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onDeviceDisconnected", "Lkotlin/Function0;", "", "getOnDeviceDisconnected", "()Lkotlin/jvm/functions/Function0;", "setOnDeviceDisconnected", "(Lkotlin/jvm/functions/Function0;)V", "packetChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cyboglabs/diskey/domain/model/BlePacket;", "getPacketChannel", "()Lkotlinx/coroutines/channels/Channel;", "writeCharacteristic", "getGattCallback", "sendCommand", "bytes", "", "app_debug"})
public final class BleDeviceManager extends no.nordicsemi.android.ble.BleManager {
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothGattCharacteristic writeCharacteristic;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothGattCharacteristic notifyCharacteristic;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cyboglabs.diskey.domain.model.BlePacket> packetChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _mtu = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> mtu = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _connectionReady = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> connectionReady = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDeviceDisconnected;
    @org.jetbrains.annotations.Nullable()
    private no.nordicsemi.android.ble.BleManager.BleManagerGattCallback _gattCallback;
    
    @javax.inject.Inject()
    public BleDeviceManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cyboglabs.diskey.domain.model.BlePacket> getPacketChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getMtu() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getConnectionReady() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnDeviceDisconnected() {
        return null;
    }
    
    public final void setOnDeviceDisconnected(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    protected no.nordicsemi.android.ble.BleManager.BleManagerGattCallback getGattCallback() {
        return null;
    }
    
    public final void sendCommand(@org.jetbrains.annotations.NotNull()
    byte[] bytes) {
    }
}