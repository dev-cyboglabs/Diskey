package com.cyboglabs.diskey.audio;

import android.content.Context;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
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
public final class AudioDownloadManager_Factory implements Factory<AudioDownloadManager> {
  private final Provider<Context> contextProvider;

  private final Provider<BleConnectionManager> bleConnectionManagerProvider;

  private final Provider<OpusFileManager> opusFileManagerProvider;

  private final Provider<AudioFileRepository> audioFileRepositoryProvider;

  public AudioDownloadManager_Factory(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<OpusFileManager> opusFileManagerProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.bleConnectionManagerProvider = bleConnectionManagerProvider;
    this.opusFileManagerProvider = opusFileManagerProvider;
    this.audioFileRepositoryProvider = audioFileRepositoryProvider;
  }

  @Override
  public AudioDownloadManager get() {
    return newInstance(contextProvider.get(), bleConnectionManagerProvider.get(), opusFileManagerProvider.get(), audioFileRepositoryProvider.get());
  }

  public static AudioDownloadManager_Factory create(Provider<Context> contextProvider,
      Provider<BleConnectionManager> bleConnectionManagerProvider,
      Provider<OpusFileManager> opusFileManagerProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    return new AudioDownloadManager_Factory(contextProvider, bleConnectionManagerProvider, opusFileManagerProvider, audioFileRepositoryProvider);
  }

  public static AudioDownloadManager newInstance(Context context,
      BleConnectionManager bleConnectionManager, OpusFileManager opusFileManager,
      AudioFileRepository audioFileRepository) {
    return new AudioDownloadManager(context, bleConnectionManager, opusFileManager, audioFileRepository);
  }
}
