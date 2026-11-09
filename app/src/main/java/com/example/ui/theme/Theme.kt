package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val SktDarkColorScheme = darkColorScheme(
    primary = SktGold,
    onPrimary = SktNavyDark,
    primaryContainer = SktNavySurface,
    onPrimaryContainer = SktGoldLight,
    secondary = SktElectricBlue,
    onSecondary = SktNavyDark,
    secondaryContainer = SktNavyCard,
    onSecondaryContainer = SktTextPrimary,
    tertiary = SktPurple,
    onTertiary = SktWhite,
    background = SktNavyDark,
    onBackground = SktTextPrimary,
    surface = SktNavyCard,
    onSurface = SktTextPrimary,
    surfaceVariant = SktNavySurface,
    onSurfaceVariant = SktTextSecondary,
    outline = SktBorder
)

private val SktLightColorScheme = darkColorScheme(
    // We enforce the bold sports tech dark aesthetic for SKT SPORTS branding
    primary = SktGold,
    onPrimary = SktNavyDark,
    primaryContainer = SktNavySurface,
    onPrimaryContainer = SktGoldLight,
    secondary = SktElectricBlue,
    onSecondary = SktNavyDark,
    background = SktNavyDark,
    onBackground = SktTextPrimary,
    surface = SktNavyCard,
    onSurface = SktTextPrimary,
    surfaceVariant = SktNavySurface,
    onSurfaceVariant = SktTextSecondary,
    outline = SktBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = SktDarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = SktNavyDark.toArgb()
            window.navigationBarColor = SktNavyDark.toArgb()
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = false
            insetsController.isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
