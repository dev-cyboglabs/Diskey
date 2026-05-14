package com.cyboglabs.diskey.presentation.player;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import com.cyboglabs.diskey.audio.OpusConverter;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
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
public final class AudioPlayerViewModel_Factory implements Factory<AudioPlayerViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<OpusConverter> opusConverterProvider;

  private final Provider<AudioFileRepository> audioFileRepositoryProvider;

  public AudioPlayerViewModel_Factory(Provider<Context> contextProvider,
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<OpusConverter> opusConverterProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.opusConverterProvider = opusConverterProvider;
    this.audioFileRepositoryProvider = audioFileRepositoryProvider;
  }

  @Override
  public AudioPlayerViewModel get() {
    return newInstance(contextProvider.get(), savedStateHandleProvider.get(), opusConverterProvider.get(), audioFileRepositoryProvider.get());
  }

  public static AudioPlayerViewModel_Factory create(Provider<Context> contextProvider,
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<OpusConverter> opusConverterProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider) {
    return new AudioPlayerViewModel_Factory(contextProvider, savedStateHandleProvider, opusConverterProvider, audioFileRepositoryProvider);
  }

  public static AudioPlayerViewModel newInstance(Context context, SavedStateHandle savedStateHandle,
      OpusConverter opusConverter, AudioFileRepository audioFileRepository) {
    return new AudioPlayerViewModel(context, savedStateHandle, opusConverter, audioFileRepository);
  }
}
