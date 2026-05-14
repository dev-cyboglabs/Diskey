package com.cyboglabs.diskey.data.repository;

import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.data.db.dao.DeviceDao;
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
public final class DeviceRepositoryImpl_Factory implements Factory<DeviceRepositoryImpl> {
  private final Provider<DeviceDao> deviceDaoProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  public DeviceRepositoryImpl_Factory(Provider<DeviceDao> deviceDaoProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    this.deviceDaoProvider = deviceDaoProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public DeviceRepositoryImpl get() {
    return newInstance(deviceDaoProvider.get(), appPreferencesProvider.get());
  }

  public static DeviceRepositoryImpl_Factory create(Provider<DeviceDao> deviceDaoProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    return new DeviceRepositoryImpl_Factory(deviceDaoProvider, appPreferencesProvider);
  }

  public static DeviceRepositoryImpl newInstance(DeviceDao deviceDao,
      AppPreferences appPreferences) {
    return new DeviceRepositoryImpl(deviceDao, appPreferences);
  }
}
