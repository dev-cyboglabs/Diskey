package com.cyboglabs.diskey.di;

import com.cyboglabs.diskey.data.db.AppDatabase;
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao;
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
public final class DatabaseModule_ProvideDownloadHistoryDaoFactory implements Factory<DownloadHistoryDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideDownloadHistoryDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DownloadHistoryDao get() {
    return provideDownloadHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDownloadHistoryDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideDownloadHistoryDaoFactory(dbProvider);
  }

  public static DownloadHistoryDao provideDownloadHistoryDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDownloadHistoryDao(db));
  }
}
