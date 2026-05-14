package com.cyboglabs.diskey.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import dagger.internal.DaggerGenerated;
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
public final class ReconnectWorker_Factory {
  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  public ReconnectWorker_Factory(Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  public ReconnectWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, bleConnectionManagerProvider.get(), appPreferencesProvider.get());
  }

  public static ReconnectWorker_Factory create(
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<AppPreferences> appPreferencesProvider) {
    return new ReconnectWorker_Factory(bleConnectionManagerProvider, appPreferencesProvider);
  }

  public static ReconnectWorker newInstance(Context context, WorkerParameters workerParams,
      BleConnectionManager bleConnectionManager, AppPreferences appPreferences) {
    return new ReconnectWorker(context, workerParams, bleConnectionManager, appPreferences);
  }
}
