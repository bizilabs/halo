package org.bizilabs.halo.base

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.base.colors.HaloDarkColorScheme
import org.bizilabs.halo.base.colors.HaloLightColorScheme
import org.bizilabs.halo.base.colors.PolarDarkColorScheme
import org.bizilabs.halo.base.colors.PolarLightColorScheme

/***
 * @param stronger This is a variant of neutral that's highly contrasting on the theme selected.
 * On Light Mode it could be the darkest version of the neutral color while on dark mode,
 * it could be the lightest version of the neutral color
 * @param strong TThis is a variant of neutral provides medium contrast, suitable for prominent elements.
 * @param neutral This is the main version of the color
 * @param weak This is a variant of neutral offers subtle contrast. It's ideal for subtle elements like borders or dividers
 * @param weaker This is a variant of neutral that's least contrasting on the theme selected.
 */

@Immutable
data class HaloColorValue(
    val stronger: Color,
    val strong: Color,
    val neutral: Color,
    val weak: Color,
    val weaker: Color,
)

@Immutable
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

@Immutable
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

        val Polar =
            HaloColorTheme(
                light = PolarLightColorScheme,
                dark = PolarDarkColorScheme,
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
