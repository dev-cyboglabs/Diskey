package com.cyboglabs.diskey.presentation.scan;

import android.content.Context;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.scanner.BleScanner;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
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

  private final Provider<AppPreferences> appPreferencesProvider;

  public ScanViewModel_Factory(Provider<Context> contextProvider,
      Provider<BleScanner> bleScannerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    this.contextProvider = contextProvider;
    this.bleScannerProvider = bleScannerProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.deviceRepositoryProvider = deviceRepositoryProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public ScanViewModel get() {
    return newInstance(contextProvider.get(), bleScannerProvider.get(), bleConnectionManagerProvider.get(), deviceRepositoryProvider.get(), appPreferencesProvider.get());
  }

  public static ScanViewModel_Factory create(Provider<Context> contextProvider,
      Provider<BleScanner> bleScannerProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    return new ScanViewModel_Factory(contextProvider, bleScannerProvider, bleConnectionManagerProvider, deviceRepositoryProvider, appPreferencesProvider);
  }

  public static ScanViewModel newInstance(Context context, BleScanner bleScanner,
      BleConnectionManager bleConnectionManager, DeviceRepository deviceRepository,
      AppPreferences appPreferences) {
    return new ScanViewModel(context, bleScanner, bleConnectionManager, deviceRepository, appPreferences);
  }
}
