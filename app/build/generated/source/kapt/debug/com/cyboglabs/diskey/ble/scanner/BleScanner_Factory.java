package com.cyboglabs.diskey.ble.scanner;

import android.bluetooth.BluetoothAdapter;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class BleScanner_Factory implements Factory<BleScanner> {
  private final Provider<BluetoothAdapter> bluetoothAdapterProvider;

  public BleScanner_Factory(Provider<BluetoothAdapter> bluetoothAdapterProvider) {
    this.bluetoothAdapterProvider = bluetoothAdapterProvider;
  }

  @Override
  public BleScanner get() {
    return newInstance(bluetoothAdapterProvider.get());
  }

  public static BleScanner_Factory create(Provider<BluetoothAdapter> bluetoothAdapterProvider) {
    return new BleScanner_Factory(bluetoothAdapterProvider);
  }

  public static BleScanner newInstance(BluetoothAdapter bluetoothAdapter) {
    return new BleScanner(bluetoothAdapter);
  }
}
