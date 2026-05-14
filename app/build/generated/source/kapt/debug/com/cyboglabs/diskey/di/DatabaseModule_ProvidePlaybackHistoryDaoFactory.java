package com.cyboglabs.diskey.di;

import com.cyboglabs.diskey.data.db.AppDatabase;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
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
public final class DatabaseModule_ProvidePlaybackHistoryDaoFactory implements Factory<PlaybackHistoryDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvidePlaybackHistoryDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlaybackHistoryDao get() {
    return providePlaybackHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlaybackHistoryDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlaybackHistoryDaoFactory(dbProvider);
  }

  public static PlaybackHistoryDao providePlaybackHistoryDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlaybackHistoryDao(db));
  }
}
