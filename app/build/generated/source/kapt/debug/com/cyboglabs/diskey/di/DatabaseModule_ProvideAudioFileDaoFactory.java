package com.cyboglabs.diskey.di;

import com.cyboglabs.diskey.data.db.AppDatabase;
import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideAudioFileDaoFactory implements Factory<AudioFileDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideAudioFileDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AudioFileDao get() {
    return provideAudioFileDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideAudioFileDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideAudioFileDaoFactory(dbProvider);
  }

  public static AudioFileDao provideAudioFileDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAudioFileDao(db));
  }
}
