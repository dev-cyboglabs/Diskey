package com.cyboglabs.diskey.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    secondary = Secondary,
    secondaryContainer = SecondaryContainer,
    onSecondary = OnSecondary,
    tertiary = Tertiary,
    tertiaryContainer = TertiaryContainer,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    error = Error
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    secondary = Secondary,
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = Color(0xFF1A1C1E),
    onSurface = Color(0xFF1A1C1E),
    error = Error
)

@Composable
fun DisKeyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (darkTheme) Background else BackgroundLight,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = DisKeyTypography,
        content = content
    )
}
