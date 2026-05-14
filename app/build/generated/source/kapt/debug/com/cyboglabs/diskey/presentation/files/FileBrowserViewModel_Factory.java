package com.cyboglabs.diskey.presentation.files;

import android.content.Context;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
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
public final class FileBrowserViewModel_Factory implements Factory<FileBrowserViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<AudioFileRepository> audioFileRepositoryProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  public FileBrowserViewModel_Factory(Provider<Context> contextProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider,
      Provider<SyncManager> syncManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    this.contextProvider = contextProvider;
    this.audioFileRepositoryProvider = audioFileRepositoryProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public FileBrowserViewModel get() {
    return newInstance(contextProvider.get(), audioFileRepositoryProvider.get(), syncManagerProvider.get(), appPreferencesProvider.get());
  }

  public static FileBrowserViewModel_Factory create(Provider<Context> contextProvider,
      Provider<AudioFileRepository> audioFileRepositoryProvider,
      Provider<SyncManager> syncManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    return new FileBrowserViewModel_Factory(contextProvider, audioFileRepositoryProvider, syncManagerProvider, appPreferencesProvider);
  }

  public static FileBrowserViewModel newInstance(Context context,
      AudioFileRepository audioFileRepository, SyncManager syncManager,
      AppPreferences appPreferences) {
    return new FileBrowserViewModel(context, audioFileRepository, syncManager, appPreferences);
  }
}
