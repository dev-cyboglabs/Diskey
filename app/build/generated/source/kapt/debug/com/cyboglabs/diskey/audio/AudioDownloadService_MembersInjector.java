package com.cyboglabs.diskey.audio;

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
public final class AudioDownloadService_MembersInjector implements MembersInjector<AudioDownloadService> {
  private final Provider<AudioDownloadManager> audioDownloadManagerProvider;

  public AudioDownloadService_MembersInjector(
      Provider<AudioDownloadManager> audioDownloadManagerProvider) {
    this.audioDownloadManagerProvider = audioDownloadManagerProvider;
  }

  public static MembersInjector<AudioDownloadService> create(
      Provider<AudioDownloadManager> audioDownloadManagerProvider) {
    return new AudioDownloadService_MembersInjector(audioDownloadManagerProvider);
  }

  @Override
  public void injectMembers(AudioDownloadService instance) {
    injectAudioDownloadManager(instance, audioDownloadManagerProvider.get());
  }

  @InjectedFieldSignature("com.cyboglabs.diskey.audio.AudioDownloadService.audioDownloadManager")
  public static void injectAudioDownloadManager(AudioDownloadService instance,
      AudioDownloadManager audioDownloadManager) {
    instance.audioDownloadManager = audioDownloadManager;
  }
}
