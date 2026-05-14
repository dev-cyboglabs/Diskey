package com.cyboglabs.diskey.presentation.ota;

import android.content.Context;
import com.cyboglabs.diskey.ota.OtaManager;
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
public final class OtaViewModel_Factory implements Factory<OtaViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<OtaManager> otaManagerProvider;

  public OtaViewModel_Factory(Provider<Context> contextProvider,
      Provider<OtaManager> otaManagerProvider) {
    this.contextProvider = contextProvider;
    this.otaManagerProvider = otaManagerProvider;
  }

  @Override
  public OtaViewModel get() {
    return newInstance(contextProvider.get(), otaManagerProvider.get());
  }

  public static OtaViewModel_Factory create(Provider<Context> contextProvider,
      Provider<OtaManager> otaManagerProvider) {
    return new OtaViewModel_Factory(contextProvider, otaManagerProvider);
  }

  public static OtaViewModel newInstance(Context context, OtaManager otaManager) {
    return new OtaViewModel(context, otaManager);
  }
}
