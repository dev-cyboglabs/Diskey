package com.cyboglabs.diskey.ota;

import com.cyboglabs.diskey.ble.BleConnectionManager;
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
public final class OtaManager_Factory implements Factory<OtaManager> {
  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  public OtaManager_Factory(Provider<BleConnectionManager> bleConnectionManagerProvider) {
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
  }

  @Override
  public OtaManager get() {
    return newInstance(bleConnectionManagerProvider.get());
  }

  public static OtaManager_Factory create(
      Provider<BleConnectionManager> bleConnectionManagerProvider) {
    return new OtaManager_Factory(bleConnectionManagerProvider);
  }

  public static OtaManager newInstance(BleConnectionManager bleConnectionManager) {
    return new OtaManager(bleConnectionManager);
  }
}
