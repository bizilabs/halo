package org.bizilabs.halo.base

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.base.colors.HaloDarkColorScheme
import org.bizilabs.halo.base.colors.HaloLightColorScheme

data class HaloColorValue(
    val container: Color,
    val content: Color,
)

data class HaloColorTheme(
    val light: HaloColorScheme,
    val dark: HaloColorScheme,
)

data class HaloColorScheme(
    val background: HaloColorValue,
    val surface: HaloColorValue,
    val info: HaloColorValue,
    val error: HaloColorValue,
    val success: HaloColorValue,
    val warning: HaloColorValue,
) {
    companion object {
        val Default =
            HaloColorTheme(
                light = HaloLightColorScheme,
                dark = HaloDarkColorScheme,
            )
    }
}

internal fun getHaloColorScheme(isDarkThemeEnabled: Boolean) =
    when (isDarkThemeEnabled) {
        true -> HaloDefaults.ColorThemes.Default.dark
        false -> HaloDefaults.ColorThemes.Default.light
    }

internal val LocalHaloColorScheme = staticCompositionLocalOf { HaloLightColorScheme }

internal fun provideHaloColorScheme(colorScheme: HaloColorScheme) = LocalHaloColorScheme provides colorScheme
