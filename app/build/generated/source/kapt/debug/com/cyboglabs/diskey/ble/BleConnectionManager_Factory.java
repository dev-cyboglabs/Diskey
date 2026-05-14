package com.cyboglabs.diskey.ble;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class BleConnectionManager_Factory implements Factory<BleConnectionManager> {
  private final Provider<Context> contextProvider;

  private final Provider<BluetoothAdapter> bluetoothAdapterProvider;

  private final Provider<BleDeviceManager> bleDeviceManagerProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  private final Provider<Gson> gsonProvider;

  public BleConnectionManager_Factory(Provider<Context> contextProvider,
      Provider<BluetoothAdapter> bluetoothAdapterProvider,
      Provider<BleDeviceManager> bleDeviceManagerProvider,
      Provider<AppPreferences> appPreferencesProvider, Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.bluetoothAdapterProvider = bluetoothAdapterProvider;
    this.bleDeviceManagerProvider = bleDeviceManagerProvider;
    this.appPreferencesProvider = appPreferencesProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public BleConnectionManager get() {
    return newInstance(contextProvider.get(), bluetoothAdapterProvider.get(), bleDeviceManagerProvider.get(), appPreferencesProvider.get(), gsonProvider.get());
  }

  public static BleConnectionManager_Factory create(Provider<Context> contextProvider,
      Provider<BluetoothAdapter> bluetoothAdapterProvider,
      Provider<BleDeviceManager> bleDeviceManagerProvider,
      Provider<AppPreferences> appPreferencesProvider, Provider<Gson> gsonProvider) {
    return new BleConnectionManager_Factory(contextProvider, bluetoothAdapterProvider, bleDeviceManagerProvider, appPreferencesProvider, gsonProvider);
  }

  public static BleConnectionManager newInstance(Context context, BluetoothAdapter bluetoothAdapter,
      BleDeviceManager bleDeviceManager, AppPreferences appPreferences, Gson gson) {
    return new BleConnectionManager(context, bluetoothAdapter, bleDeviceManager, appPreferences, gson);
  }
}
