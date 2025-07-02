package org.bizilabs.halo.base

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.base.colors.HaloDarkColorScheme
import org.bizilabs.halo.base.colors.HaloLightColorScheme

/***
 * @param dark This is the darker version of the color.
 * @param base This is the main version of the color
 * @param subtle This is a lighter version of the color, which could be used for borders
 * @param onBase This is the lightest version of the color and contrasts with the base
 */

data class HaloColorValue(
    val dark: Color,
    val base: Color,
    val subtle: Color,
    val onBase: Color,
)

data class HaloBackgroundValue(
    val surface: Color,
    val base: Color,
    val onSurface: Color,
    val onBase: Color,
)

data class HaloColorTheme(
    val light: HaloColorScheme,
    val dark: HaloColorScheme,
)

data class HaloColorScheme(
    val primary: HaloColorValue,
    val background: HaloBackgroundValue,
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
