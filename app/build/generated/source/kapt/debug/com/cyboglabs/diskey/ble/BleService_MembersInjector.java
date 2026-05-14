package com.cyboglabs.diskey.ble;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class BleService_MembersInjector implements MembersInjector<BleService> {
  private final Provider<BleDeviceManager> bleDeviceManagerProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  public BleService_MembersInjector(Provider<BleDeviceManager> bleDeviceManagerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider) {
    this.bleDeviceManagerProvider = bleDeviceManagerProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
  }

  public static MembersInjector<BleService> create(
      Provider<BleDeviceManager> bleDeviceManagerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider) {
    return new BleService_MembersInjector(bleDeviceManagerProvider, bleConnectionManagerProvider);
  }

  @Override
  public void injectMembers(BleService instance) {
    injectBleDeviceManager(instance, bleDeviceManagerProvider.get());
    injectBleConnectionManager(instance, bleConnectionManagerProvider.get());
  }

  @InjectedFieldSignature("com.cyboglabs.diskey.ble.BleService.bleDeviceManager")
  public static void injectBleDeviceManager(BleService instance,
      BleDeviceManager bleDeviceManager) {
    instance.bleDeviceManager = bleDeviceManager;
  }

  @InjectedFieldSignature("com.cyboglabs.diskey.ble.BleService.bleConnectionManager")
  public static void injectBleConnectionManager(BleService instance,
      BleConnectionManager bleConnectionManager) {
    instance.bleConnectionManager = bleConnectionManager;
  }
}
