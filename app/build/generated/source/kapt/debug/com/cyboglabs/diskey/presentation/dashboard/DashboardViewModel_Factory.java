package com.cyboglabs.diskey.presentation.dashboard;

import android.content.Context;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  public DashboardViewModel_Factory(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<SyncManager> syncManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    this.contextProvider = contextProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(contextProvider.get(), bleConnectionManagerProvider.get(), syncManagerProvider.get(), appPreferencesProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<SyncManager> syncManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    return new DashboardViewModel_Factory(contextProvider, bleConnectionManagerProvider, syncManagerProvider, appPreferencesProvider);
  }

  public static DashboardViewModel newInstance(Context context,
      BleConnectionManager bleConnectionManager, SyncManager syncManager,
      AppPreferences appPreferences) {
    return new DashboardViewModel(context, bleConnectionManager, syncManager, appPreferences);
  }
}
