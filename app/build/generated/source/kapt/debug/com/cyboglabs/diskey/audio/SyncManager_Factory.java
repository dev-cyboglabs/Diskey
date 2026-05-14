package com.cyboglabs.diskey.audio;

import android.content.Context;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.google.gson.Gson;
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
public final class SyncManager_Factory implements Factory<SyncManager> {
  private final Provider<Context> contextProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<AudioFileRepository> audioFileRepositoryProvider;

  private final Provider<OpusFileManager> opusFileManagerProvider;

  private final Provider<Gson> gsonProvider;

  public SyncManager_Factory(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider,
      Provider<OpusFileManager> opusFileManagerProvider, Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.audioFileRepositoryProvider = audioFileRepositoryProvider;
    this.opusFileManagerProvider = opusFileManagerProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public SyncManager get() {
    return newInstance(contextProvider.get(), bleConnectionManagerProvider.get(), audioFileRepositoryProvider.get(), opusFileManagerProvider.get(), gsonProvider.get());
  }

  public static SyncManager_Factory create(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider,
      Provider<OpusFileManager> opusFileManagerProvider, Provider<Gson> gsonProvider) {
    return new SyncManager_Factory(contextProvider, bleConnectionManagerProvider, audioFileRepositoryProvider, opusFileManagerProvider, gsonProvider);
  }

  public static SyncManager newInstance(Context context, BleConnectionManager bleConnectionManager,
      AudioFileRepository audioFileRepository, OpusFileManager opusFileManager, Gson gson) {
    return new SyncManager(context, bleConnectionManager, audioFileRepository, opusFileManager, gson);
  }
}
