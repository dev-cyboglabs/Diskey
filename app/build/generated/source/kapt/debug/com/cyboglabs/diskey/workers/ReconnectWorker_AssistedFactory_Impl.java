package com.cyboglabs.diskey.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ReconnectWorker_AssistedFactory_Impl implements ReconnectWorker_AssistedFactory {
  private final ReconnectWorker_Factory delegateFactory;

  ReconnectWorker_AssistedFactory_Impl(ReconnectWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ReconnectWorker create(Context arg0, WorkerParameters arg1) {
    return delegateFactory.get(arg0, arg1);
  }

  public static Provider<ReconnectWorker_AssistedFactory> create(
      ReconnectWorker_Factory delegateFactory) {
    return InstanceFactory.create(new ReconnectWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ReconnectWorker_AssistedFactory> createFactoryProvider(
      ReconnectWorker_Factory delegateFactory) {
    return InstanceFactory.create(new ReconnectWorker_AssistedFactory_Impl(delegateFactory));
  }
}
