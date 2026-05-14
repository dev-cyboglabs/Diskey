package com.cyboglabs.diskey.wifi;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class WifiService_MembersInjector implements MembersInjector<WifiService> {
  private final Provider<WifiTransferManager> wifiTransferManagerProvider;

  public WifiService_MembersInjector(Provider<WifiTransferManager> wifiTransferManagerProvider) {
    this.wifiTransferManagerProvider = wifiTransferManagerProvider;
  }

  public static MembersInjector<WifiService> create(
      Provider<WifiTransferManager> wifiTransferManagerProvider) {
    return new WifiService_MembersInjector(wifiTransferManagerProvider);
  }

  @Override
  public void injectMembers(WifiService instance) {
    injectWifiTransferManager(instance, wifiTransferManagerProvider.get());
  }

  @InjectedFieldSignature("com.cyboglabs.diskey.wifi.WifiService.wifiTransferManager")
  public static void injectWifiTransferManager(WifiService instance,
      WifiTransferManager wifiTransferManager) {
    instance.wifiTransferManager = wifiTransferManager;
  }
}
