package com.cyboglabs.diskey.ble;

import android.content.Context;
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
public final class BleDeviceManager_Factory implements Factory<BleDeviceManager> {
  private final Provider<Context> contextProvider;

  public BleDeviceManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BleDeviceManager get() {
    return newInstance(contextProvider.get());
  }

  public static BleDeviceManager_Factory create(Provider<Context> contextProvider) {
    return new BleDeviceManager_Factory(contextProvider);
  }

  public static BleDeviceManager newInstance(Context context) {
    return new BleDeviceManager(context);
  }
}
