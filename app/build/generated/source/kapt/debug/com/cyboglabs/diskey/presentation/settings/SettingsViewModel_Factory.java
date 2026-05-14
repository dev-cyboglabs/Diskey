package com.cyboglabs.diskey.presentation.settings;

import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<AppPreferences> appPreferencesProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<DeviceRepository> deviceRepositoryProvider;

  private final Provider<AudioFileRepository> audioFileRepositoryProvider;

  public SettingsViewModel_Factory(Provider<AppPreferences> appPreferencesProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    this.appPreferencesProvider = appPreferencesProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.deviceRepositoryProvider = deviceRepositoryProvider;
    this.audioFileRepositoryProvider = audioFileRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(appPreferencesProvider.get(), bleConnectionManagerProvider.get(), deviceRepositoryProvider.get(), audioFileRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<AppPreferences> appPreferencesProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<DeviceRepository> deviceRepositoryProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    return new SettingsViewModel_Factory(appPreferencesProvider, bleConnectionManagerProvider, deviceRepositoryProvider, audioFileRepositoryProvider);
  }

  public static SettingsViewModel newInstance(AppPreferences appPreferences,
      BleConnectionManager bleConnectionManager, DeviceRepository deviceRepository,
      AudioFileRepository audioFileRepository) {
    return new SettingsViewModel(appPreferences, bleConnectionManager, deviceRepository, audioFileRepository);
  }
}
