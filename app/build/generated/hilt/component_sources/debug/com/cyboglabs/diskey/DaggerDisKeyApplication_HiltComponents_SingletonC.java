package com.cyboglabs.diskey;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.cyboglabs.diskey.audio.AudioDownloadManager;
import com.cyboglabs.diskey.audio.AudioDownloadService;
import com.cyboglabs.diskey.audio.AudioDownloadService_MembersInjector;
import com.cyboglabs.diskey.audio.AudioPlaybackService;
import com.cyboglabs.diskey.audio.OpusConverter;
import com.cyboglabs.diskey.audio.OpusFileManager;
import com.cyboglabs.diskey.audio.SyncManager;
import com.cyboglabs.diskey.ble.BleConnectionManager;
import com.cyboglabs.diskey.ble.BleDeviceManager;
import com.cyboglabs.diskey.ble.BleService;
import com.cyboglabs.diskey.ble.BleService_MembersInjector;
import com.cyboglabs.diskey.ble.scanner.BleScanner;
import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.data.db.AppDatabase;
import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import com.cyboglabs.diskey.data.db.dao.DeviceDao;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
import com.cyboglabs.diskey.data.repository.AudioFileRepositoryImpl;
import com.cyboglabs.diskey.data.repository.DeviceRepositoryImpl;
import com.cyboglabs.diskey.di.AppModule_Companion_ProvideBluetoothAdapterFactory;
import com.cyboglabs.diskey.di.AppModule_Companion_ProvideGsonFactory;
import com.cyboglabs.diskey.di.DatabaseModule_ProvideAudioFileDaoFactory;
import com.cyboglabs.diskey.di.DatabaseModule_ProvideDatabaseFactory;
import com.cyboglabs.diskey.di.DatabaseModule_ProvideDeviceDaoFactory;
import com.cyboglabs.diskey.di.DatabaseModule_ProvidePlaybackHistoryDaoFactory;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import com.cyboglabs.diskey.ota.OtaManager;
import com.cyboglabs.diskey.presentation.MainActivity;
import com.cyboglabs.diskey.presentation.dashboard.DashboardViewModel;
import com.cyboglabs.diskey.presentation.dashboard.DashboardViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.debug.DebugViewModel;
import com.cyboglabs.diskey.presentation.debug.DebugViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.files.FileBrowserViewModel;
import com.cyboglabs.diskey.presentation.files.FileBrowserViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.ota.OtaViewModel;
import com.cyboglabs.diskey.presentation.ota.OtaViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.player.AudioPlayerViewModel;
import com.cyboglabs.diskey.presentation.player.AudioPlayerViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.scan.ScanViewModel;
import com.cyboglabs.diskey.presentation.scan.ScanViewModel_HiltModules;
import com.cyboglabs.diskey.presentation.settings.SettingsViewModel;
import com.cyboglabs.diskey.presentation.settings.SettingsViewModel_HiltModules;
import com.cyboglabs.diskey.wifi.WifiService;
import com.cyboglabs.diskey.wifi.WifiService_MembersInjector;
import com.cyboglabs.diskey.wifi.WifiTransferManager;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerDisKeyApplication_HiltComponents_SingletonC {
  private DaggerDisKeyApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public DisKeyApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements DisKeyApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements DisKeyApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements DisKeyApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements DisKeyApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements DisKeyApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements DisKeyApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements DisKeyApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public DisKeyApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends DisKeyApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends DisKeyApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends DisKeyApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends DisKeyApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(7).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel, AudioPlayerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel, DashboardViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_debug_DebugViewModel, DebugViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_files_FileBrowserViewModel, FileBrowserViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_ota_OtaViewModel, OtaViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_scan_ScanViewModel, ScanViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_cyboglabs_diskey_presentation_files_FileBrowserViewModel = "com.cyboglabs.diskey.presentation.files.FileBrowserViewModel";

      static String com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel = "com.cyboglabs.diskey.presentation.dashboard.DashboardViewModel";

      static String com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel = "com.cyboglabs.diskey.presentation.player.AudioPlayerViewModel";

      static String com_cyboglabs_diskey_presentation_debug_DebugViewModel = "com.cyboglabs.diskey.presentation.debug.DebugViewModel";

      static String com_cyboglabs_diskey_presentation_ota_OtaViewModel = "com.cyboglabs.diskey.presentation.ota.OtaViewModel";

      static String com_cyboglabs_diskey_presentation_settings_SettingsViewModel = "com.cyboglabs.diskey.presentation.settings.SettingsViewModel";

      static String com_cyboglabs_diskey_presentation_scan_ScanViewModel = "com.cyboglabs.diskey.presentation.scan.ScanViewModel";

      @KeepFieldType
      FileBrowserViewModel com_cyboglabs_diskey_presentation_files_FileBrowserViewModel2;

      @KeepFieldType
      DashboardViewModel com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel2;

      @KeepFieldType
      AudioPlayerViewModel com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel2;

      @KeepFieldType
      DebugViewModel com_cyboglabs_diskey_presentation_debug_DebugViewModel2;

      @KeepFieldType
      OtaViewModel com_cyboglabs_diskey_presentation_ota_OtaViewModel2;

      @KeepFieldType
      SettingsViewModel com_cyboglabs_diskey_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      ScanViewModel com_cyboglabs_diskey_presentation_scan_ScanViewModel2;
    }
  }

  private static final class ViewModelCImpl extends DisKeyApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AudioPlayerViewModel> audioPlayerViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DebugViewModel> debugViewModelProvider;

    private Provider<FileBrowserViewModel> fileBrowserViewModelProvider;

    private Provider<OtaViewModel> otaViewModelProvider;

    private Provider<ScanViewModel> scanViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.audioPlayerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.debugViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.fileBrowserViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.otaViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.scanViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(7).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel, ((Provider) audioPlayerViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel, ((Provider) dashboardViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_debug_DebugViewModel, ((Provider) debugViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_files_FileBrowserViewModel, ((Provider) fileBrowserViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_ota_OtaViewModel, ((Provider) otaViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_scan_ScanViewModel, ((Provider) scanViewModelProvider)).put(LazyClassKeyProvider.com_cyboglabs_diskey_presentation_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel = "com.cyboglabs.diskey.presentation.player.AudioPlayerViewModel";

      static String com_cyboglabs_diskey_presentation_settings_SettingsViewModel = "com.cyboglabs.diskey.presentation.settings.SettingsViewModel";

      static String com_cyboglabs_diskey_presentation_ota_OtaViewModel = "com.cyboglabs.diskey.presentation.ota.OtaViewModel";

      static String com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel = "com.cyboglabs.diskey.presentation.dashboard.DashboardViewModel";

      static String com_cyboglabs_diskey_presentation_debug_DebugViewModel = "com.cyboglabs.diskey.presentation.debug.DebugViewModel";

      static String com_cyboglabs_diskey_presentation_files_FileBrowserViewModel = "com.cyboglabs.diskey.presentation.files.FileBrowserViewModel";

      static String com_cyboglabs_diskey_presentation_scan_ScanViewModel = "com.cyboglabs.diskey.presentation.scan.ScanViewModel";

      @KeepFieldType
      AudioPlayerViewModel com_cyboglabs_diskey_presentation_player_AudioPlayerViewModel2;

      @KeepFieldType
      SettingsViewModel com_cyboglabs_diskey_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      OtaViewModel com_cyboglabs_diskey_presentation_ota_OtaViewModel2;

      @KeepFieldType
      DashboardViewModel com_cyboglabs_diskey_presentation_dashboard_DashboardViewModel2;

      @KeepFieldType
      DebugViewModel com_cyboglabs_diskey_presentation_debug_DebugViewModel2;

      @KeepFieldType
      FileBrowserViewModel com_cyboglabs_diskey_presentation_files_FileBrowserViewModel2;

      @KeepFieldType
      ScanViewModel com_cyboglabs_diskey_presentation_scan_ScanViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.cyboglabs.diskey.presentation.player.AudioPlayerViewModel 
          return (T) new AudioPlayerViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), viewModelCImpl.savedStateHandle, singletonCImpl.opusConverterProvider.get(), singletonCImpl.bindAudioFileRepositoryProvider.get());

          case 1: // com.cyboglabs.diskey.presentation.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bleConnectionManagerProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.appPreferencesProvider.get());

          case 2: // com.cyboglabs.diskey.presentation.debug.DebugViewModel 
          return (T) new DebugViewModel(singletonCImpl.bleConnectionManagerProvider.get());

          case 3: // com.cyboglabs.diskey.presentation.files.FileBrowserViewModel 
          return (T) new FileBrowserViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bindAudioFileRepositoryProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.appPreferencesProvider.get());

          case 4: // com.cyboglabs.diskey.presentation.ota.OtaViewModel 
          return (T) new OtaViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.otaManagerProvider.get());

          case 5: // com.cyboglabs.diskey.presentation.scan.ScanViewModel 
          return (T) new ScanViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bleScannerProvider.get(), singletonCImpl.bleConnectionManagerProvider.get(), singletonCImpl.bindDeviceRepositoryProvider.get());

          case 6: // com.cyboglabs.diskey.presentation.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.appPreferencesProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends DisKeyApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends DisKeyApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectAudioDownloadService(AudioDownloadService audioDownloadService) {
      injectAudioDownloadService2(audioDownloadService);
    }

    @Override
    public void injectAudioPlaybackService(AudioPlaybackService audioPlaybackService) {
    }

    @Override
    public void injectBleService(BleService bleService) {
      injectBleService2(bleService);
    }

    @Override
    public void injectWifiService(WifiService wifiService) {
      injectWifiService2(wifiService);
    }

    private AudioDownloadService injectAudioDownloadService2(AudioDownloadService instance) {
      AudioDownloadService_MembersInjector.injectAudioDownloadManager(instance, singletonCImpl.audioDownloadManagerProvider.get());
      return instance;
    }

    private BleService injectBleService2(BleService instance) {
      BleService_MembersInjector.injectBleDeviceManager(instance, singletonCImpl.bleDeviceManagerProvider.get());
      BleService_MembersInjector.injectBleConnectionManager(instance, singletonCImpl.bleConnectionManagerProvider.get());
      return instance;
    }

    private WifiService injectWifiService2(WifiService instance) {
      WifiService_MembersInjector.injectWifiTransferManager(instance, singletonCImpl.wifiTransferManagerProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends DisKeyApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OpusConverter> opusConverterProvider;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<AudioFileRepositoryImpl> audioFileRepositoryImplProvider;

    private Provider<AudioFileRepository> bindAudioFileRepositoryProvider;

    private Provider<BluetoothAdapter> provideBluetoothAdapterProvider;

    private Provider<BleDeviceManager> bleDeviceManagerProvider;

    private Provider<AppPreferences> appPreferencesProvider;

    private Provider<Gson> provideGsonProvider;

    private Provider<BleConnectionManager> bleConnectionManagerProvider;

    private Provider<OpusFileManager> opusFileManagerProvider;

    private Provider<SyncManager> syncManagerProvider;

    private Provider<OtaManager> otaManagerProvider;

    private Provider<BleScanner> bleScannerProvider;

    private Provider<DeviceRepositoryImpl> deviceRepositoryImplProvider;

    private Provider<DeviceRepository> bindDeviceRepositoryProvider;

    private Provider<AudioDownloadManager> audioDownloadManagerProvider;

    private Provider<WifiTransferManager> wifiTransferManagerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private AudioFileDao audioFileDao() {
      return DatabaseModule_ProvideAudioFileDaoFactory.provideAudioFileDao(provideDatabaseProvider.get());
    }

    private PlaybackHistoryDao playbackHistoryDao() {
      return DatabaseModule_ProvidePlaybackHistoryDaoFactory.providePlaybackHistoryDao(provideDatabaseProvider.get());
    }

    private DeviceDao deviceDao() {
      return DatabaseModule_ProvideDeviceDaoFactory.provideDeviceDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.opusConverterProvider = DoubleCheck.provider(new SwitchingProvider<OpusConverter>(singletonCImpl, 0));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.audioFileRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 1);
      this.bindAudioFileRepositoryProvider = DoubleCheck.provider((Provider) audioFileRepositoryImplProvider);
      this.provideBluetoothAdapterProvider = DoubleCheck.provider(new SwitchingProvider<BluetoothAdapter>(singletonCImpl, 4));
      this.bleDeviceManagerProvider = DoubleCheck.provider(new SwitchingProvider<BleDeviceManager>(singletonCImpl, 5));
      this.appPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<AppPreferences>(singletonCImpl, 6));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 7));
      this.bleConnectionManagerProvider = DoubleCheck.provider(new SwitchingProvider<BleConnectionManager>(singletonCImpl, 3));
      this.opusFileManagerProvider = DoubleCheck.provider(new SwitchingProvider<OpusFileManager>(singletonCImpl, 9));
      this.syncManagerProvider = DoubleCheck.provider(new SwitchingProvider<SyncManager>(singletonCImpl, 8));
      this.otaManagerProvider = DoubleCheck.provider(new SwitchingProvider<OtaManager>(singletonCImpl, 10));
      this.bleScannerProvider = DoubleCheck.provider(new SwitchingProvider<BleScanner>(singletonCImpl, 11));
      this.deviceRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 12);
      this.bindDeviceRepositoryProvider = DoubleCheck.provider((Provider) deviceRepositoryImplProvider);
      this.audioDownloadManagerProvider = DoubleCheck.provider(new SwitchingProvider<AudioDownloadManager>(singletonCImpl, 13));
      this.wifiTransferManagerProvider = DoubleCheck.provider(new SwitchingProvider<WifiTransferManager>(singletonCImpl, 14));
    }

    @Override
    public void injectDisKeyApplication(DisKeyApplication disKeyApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.cyboglabs.diskey.audio.OpusConverter 
          return (T) new OpusConverter(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.cyboglabs.diskey.data.repository.AudioFileRepositoryImpl 
          return (T) new AudioFileRepositoryImpl(singletonCImpl.audioFileDao(), singletonCImpl.playbackHistoryDao());

          case 2: // com.cyboglabs.diskey.data.db.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.cyboglabs.diskey.ble.BleConnectionManager 
          return (T) new BleConnectionManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideBluetoothAdapterProvider.get(), singletonCImpl.bleDeviceManagerProvider.get(), singletonCImpl.appPreferencesProvider.get(), singletonCImpl.provideGsonProvider.get());

          case 4: // android.bluetooth.BluetoothAdapter 
          return (T) AppModule_Companion_ProvideBluetoothAdapterFactory.provideBluetoothAdapter(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.cyboglabs.diskey.ble.BleDeviceManager 
          return (T) new BleDeviceManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.cyboglabs.diskey.data.datastore.AppPreferences 
          return (T) new AppPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.google.gson.Gson 
          return (T) AppModule_Companion_ProvideGsonFactory.provideGson();

          case 8: // com.cyboglabs.diskey.audio.SyncManager 
          return (T) new SyncManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bleConnectionManagerProvider.get(), singletonCImpl.bindAudioFileRepositoryProvider.get(), singletonCImpl.opusFileManagerProvider.get(), singletonCImpl.provideGsonProvider.get());

          case 9: // com.cyboglabs.diskey.audio.OpusFileManager 
          return (T) new OpusFileManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 10: // com.cyboglabs.diskey.ota.OtaManager 
          return (T) new OtaManager(singletonCImpl.bleConnectionManagerProvider.get());

          case 11: // com.cyboglabs.diskey.ble.scanner.BleScanner 
          return (T) new BleScanner(singletonCImpl.provideBluetoothAdapterProvider.get());

          case 12: // com.cyboglabs.diskey.data.repository.DeviceRepositoryImpl 
          return (T) new DeviceRepositoryImpl(singletonCImpl.deviceDao(), singletonCImpl.appPreferencesProvider.get());

          case 13: // com.cyboglabs.diskey.audio.AudioDownloadManager 
          return (T) new AudioDownloadManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bleConnectionManagerProvider.get(), singletonCImpl.opusFileManagerProvider.get(), singletonCImpl.bindAudioFileRepositoryProvider.get());

          case 14: // com.cyboglabs.diskey.wifi.WifiTransferManager 
          return (T) new WifiTransferManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
