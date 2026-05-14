package com.cyboglabs.diskey.ble.scanner

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.os.ParcelUuid
import com.cyboglabs.diskey.ble.BleConstants
import com.cyboglabs.diskey.domain.model.Device
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BleScanner @Inject constructor(
    private val bluetoothAdapter: BluetoothAdapter
) {

    /**
     * Emits discovered devices as a cold Flow.
     * Scanning stops when the Flow collector is cancelled.
     */
    fun scan(filterByService: Boolean = true): Flow<Device> = callbackFlow {
        val scanner = bluetoothAdapter.bluetoothLeScanner ?: run {
            Timber.e("BleScanner: BluetoothLeScanner not available")
            close()
            return@callbackFlow
        }

        val filters = if (filterByService) {
            listOf(
                ScanFilter.Builder()
                    .setServiceUuid(ParcelUuid(BleConstants.SERVICE_UUID))
                    .build()
            )
        } else emptyList()

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
            .setNumOfMatches(ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT)
            .build()

        val callback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                val device = result.device
                val name = device.name ?: return // skip nameless devices
                trySend(
                    Device(
                        id = device.address,
                        name = name,
                        address = device.address,
                        rssi = result.rssi,
                        lastSeenMs = System.currentTimeMillis()
                    )
                )
            }

            override fun onScanFailed(errorCode: Int) {
                Timber.e("BleScanner: scan failed with code $errorCode")
                close(ScanFailedException(errorCode))
            }
        }

        Timber.d("BleScanner: starting scan (filterByService=$filterByService)")
        scanner.startScan(filters, settings, callback)

        awaitClose {
            Timber.d("BleScanner: stopping scan")
            try {
                scanner.stopScan(callback)
            } catch (e: Exception) {
                Timber.w(e, "BleScanner: stopScan threw")
            }
        }
    }

    class ScanFailedException(val errorCode: Int) : Exception("BLE scan failed: $errorCode")
}
