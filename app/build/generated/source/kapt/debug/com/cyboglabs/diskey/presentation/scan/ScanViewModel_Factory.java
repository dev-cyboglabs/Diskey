package com.cyboglabs.diskey.presentation.scan;

import android.content.Context;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.scanner.BleScanner;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ScanViewModel_Factory implements Factory<ScanViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<BleScanner> bleScannerProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<DeviceRepository> deviceRepositoryProvider;

  public ScanViewModel_Factory(Provider<Context> contextProvider,
      Provider<BleScanner> bleScannerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.bleScannerProvider = bleScannerProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.deviceRepositoryProvider = deviceRepositoryProvider;
  }

  @Override
  public ScanViewModel get() {
    return newInstance(contextProvider.get(), bleScannerProvider.get(), bleConnectionManagerProvider.get(), deviceRepositoryProvider.get());
  }

  public static ScanViewModel_Factory create(Provider<Context> contextProvider,
      Provider<BleScanner> bleScannerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider) {
    return new ScanViewModel_Factory(contextProvider, bleScannerProvider, bleConnectionManagerProvider, deviceRepositoryProvider);
  }

  public static ScanViewModel newInstance(Context context, BleScanner bleScanner,
      BleConnectionManager bleConnectionManager, DeviceRepository deviceRepository) {
    return new ScanViewModel(context, bleScanner, bleConnectionManager, deviceRepository);
  }
}
