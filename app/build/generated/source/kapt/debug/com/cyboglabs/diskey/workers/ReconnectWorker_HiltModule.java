package com.cyboglabs.diskey.workers;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = ReconnectWorker.class
)
public interface ReconnectWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.cyboglabs.diskey.workers.ReconnectWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(ReconnectWorker_AssistedFactory factory);
}
