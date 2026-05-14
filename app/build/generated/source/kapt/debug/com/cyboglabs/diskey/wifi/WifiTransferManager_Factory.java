package com.cyboglabs.diskey.wifi;

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
public final class WifiTransferManager_Factory implements Factory<WifiTransferManager> {
  private final Provider<Context> contextProvider;

  public WifiTransferManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WifiTransferManager get() {
    return newInstance(contextProvider.get());
  }

  public static WifiTransferManager_Factory create(Provider<Context> contextProvider) {
    return new WifiTransferManager_Factory(contextProvider);
  }

  public static WifiTransferManager newInstance(Context context) {
    return new WifiTransferManager(context);
  }
}
