package com.cyboglabs.diskey.presentation.debug;

import com.cyboglabs.diskey.ble.BleConnectionManager;
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
public final class DebugViewModel_Factory implements Factory<DebugViewModel> {
  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  public DebugViewModel_Factory(Provider<BleConnectionManager> bleConnectionManagerProvider) {
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
  }

  @Override
  public DebugViewModel get() {
    return newInstance(bleConnectionManagerProvider.get());
  }

  public static DebugViewModel_Factory create(
      Provider<BleConnectionManager> bleConnectionManagerProvider) {
    return new DebugViewModel_Factory(bleConnectionManagerProvider);
  }

  public static DebugViewModel newInstance(BleConnectionManager bleConnectionManager) {
    return new DebugViewModel(bleConnectionManager);
  }
}
