package com.cyboglabs.diskey.data.repository;

import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AudioFileRepositoryImpl_Factory implements Factory<AudioFileRepositoryImpl> {
  private final Provider<AudioFileDao> audioFileDaoProvider;

  private final Provider<PlaybackHistoryDao> playbackHistoryDaoProvider;

  public AudioFileRepositoryImpl_Factory(Provider<AudioFileDao> audioFileDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider) {
    this.audioFileDaoProvider = audioFileDaoProvider;
    this.playbackHistoryDaoProvider = playbackHistoryDaoProvider;
  }

  @Override
  public AudioFileRepositoryImpl get() {
    return newInstance(audioFileDaoProvider.get(), playbackHistoryDaoProvider.get());
  }

  public static AudioFileRepositoryImpl_Factory create(Provider<AudioFileDao> audioFileDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider) {
    return new AudioFileRepositoryImpl_Factory(audioFileDaoProvider, playbackHistoryDaoProvider);
  }

  public static AudioFileRepositoryImpl newInstance(AudioFileDao audioFileDao,
      PlaybackHistoryDao playbackHistoryDao) {
    return new AudioFileRepositoryImpl(audioFileDao, playbackHistoryDao);
  }
}
