package com.cyboglabs.diskey.ble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;
import com.cyboglabs.diskey.ble.BleConstants;
import com.cyboglabs.diskey.domain.model.Device;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/cyboglabs/diskey/ble/scanner/BleScanner;", "", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "(Landroid/bluetooth/BluetoothAdapter;)V", "scan", "Lkotlinx/coroutines/flow/Flow;", "Lcom/cyboglabs/diskey/domain/model/Device;", "filterByService", "", "ScanFailedException", "app_debug"})
public final class BleScanner {
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.BluetoothAdapter bluetoothAdapter = null;
    
    @javax.inject.Inject()
    public BleScanner(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothAdapter bluetoothAdapter) {
        super();
    }
    
    /**
     * Emits discovered devices as a cold Flow.
     * Scanning stops when the Flow collector is cancelled.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.cyboglabs.diskey.domain.model.Device> scan(boolean filterByService) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/cyboglabs/diskey/ble/scanner/BleScanner$ScanFailedException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorCode", "", "(I)V", "getErrorCode", "()I", "app_debug"})
    public static final class ScanFailedException extends java.lang.Exception {
        private final int errorCode = 0;
        
        public ScanFailedException(int errorCode) {
            super();
        }
        
        public final int getErrorCode() {
            return 0;
        }
    }
}