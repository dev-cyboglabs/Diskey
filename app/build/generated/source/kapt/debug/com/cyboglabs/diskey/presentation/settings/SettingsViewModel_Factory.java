package com.cyboglabs.diskey.presentation.settings;

import com.cyboglabs.diskey.data.datastore.AppPreferences;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<AppPreferences> appPreferencesProvider;

  public SettingsViewModel_Factory(Provider<AppPreferences> appPreferencesProvider) {
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(appPreferencesProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<AppPreferences> appPreferencesProvider) {
    return new SettingsViewModel_Factory(appPreferencesProvider);
  }

  public static SettingsViewModel newInstance(AppPreferences appPreferences) {
    return new SettingsViewModel(appPreferences);
  }
}
