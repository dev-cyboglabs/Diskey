package com.cyboglabs.diskey.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cyboglabs.diskey.presentation.dashboard.DashboardScreen
import com.cyboglabs.diskey.presentation.debug.DebugScreen
import com.cyboglabs.diskey.presentation.files.FileBrowserScreen
import com.cyboglabs.diskey.presentation.ota.OtaScreen
import com.cyboglabs.diskey.presentation.player.AudioPlayerScreen
import com.cyboglabs.diskey.presentation.scan.ScanScreen
import com.cyboglabs.diskey.presentation.settings.SettingsScreen
import com.cyboglabs.diskey.presentation.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Scan : Screen("scan")
    object Dashboard : Screen("dashboard")
    object FileBrowser : Screen("files")
    object AudioPlayer : Screen("player/{filename}") {
        fun createRoute(filename: String) = "player/$filename"
    }
    object Settings : Screen("settings")
    object Ota : Screen("ota")
    object Debug : Screen("debug")
}

@Composable
fun DisKeyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToScan = {
                    navController.navigate(Screen.Scan.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Scan.route) {
            ScanScreen(
                onDeviceConnected = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Scan.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToFiles = {
                    navController.navigate(Screen.FileBrowser.route) {
                        launchSingleTop = true
                    }
                },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToOta = { navController.navigate(Screen.Ota.route) },
                onNavigateToDebug = { navController.navigate(Screen.Debug.route) },
                onDisconnected = {
                    navController.navigate(Screen.Scan.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.FileBrowser.route) {
            FileBrowserScreen(
                onPlayFile = { filename ->
                    navController.navigate(Screen.AudioPlayer.createRoute(filename))
                },
                onBack = {
                    val popped = navController.popBackStack(Screen.Dashboard.route, inclusive = false)
                    if (!popped) navController.popBackStack()
                }
            )
        }

        composable(Screen.AudioPlayer.route) { backStack ->
            val filename = backStack.arguments?.getString("filename") ?: ""
            AudioPlayerScreen(
                filename = filename,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Ota.route) {
            OtaScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Debug.route) {
            DebugScreen(onBack = { navController.popBackStack() })
        }
    }
}
