package com.cyboglabs.diskey.presentation.navigation;

import androidx.compose.runtime.Composable;
import androidx.navigation.NavHostController;
import com.cyboglabs.diskey.presentation.debug.DebugScreen;
import com.cyboglabs.diskey.presentation.ota.OtaScreen;
import com.cyboglabs.diskey.presentation.player.AudioPlayerScreen;
import com.cyboglabs.diskey.presentation.scan.ScanScreen;
import com.cyboglabs.diskey.presentation.settings.SettingsScreen;
import com.cyboglabs.diskey.presentation.splash.SplashScreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u0007\b\t\n\u000b\f\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\b\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "AudioPlayer", "Dashboard", "Debug", "FileBrowser", "Ota", "Scan", "Settings", "Splash", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$AudioPlayer;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Dashboard;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Debug;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$FileBrowser;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Ota;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Scan;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Settings;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen$Splash;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$AudioPlayer;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "createRoute", "", "filename", "app_debug"})
    public static final class AudioPlayer extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.AudioPlayer INSTANCE = null;
        
        private AudioPlayer() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(@org.jetbrains.annotations.NotNull()
        java.lang.String filename) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Dashboard;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Dashboard extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Dashboard INSTANCE = null;
        
        private Dashboard() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Debug;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Debug extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Debug INSTANCE = null;
        
        private Debug() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$FileBrowser;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class FileBrowser extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.FileBrowser INSTANCE = null;
        
        private FileBrowser() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Ota;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Ota extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Ota INSTANCE = null;
        
        private Ota() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Scan;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Scan extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Scan INSTANCE = null;
        
        private Scan() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Settings;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cyboglabs/diskey/presentation/navigation/Screen$Splash;", "Lcom/cyboglabs/diskey/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Splash extends com.cyboglabs.diskey.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.cyboglabs.diskey.presentation.navigation.Screen.Splash INSTANCE = null;
        
        private Splash() {
        }
    }
}