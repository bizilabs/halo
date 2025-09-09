package org.bizilabs.halo.base

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.base.colors.HaloDarkColorScheme
import org.bizilabs.halo.base.colors.HaloElevationColor
import org.bizilabs.halo.base.colors.HaloLightColorScheme
import org.bizilabs.halo.base.colors.PolarDarkColorScheme
import org.bizilabs.halo.base.colors.PolarLightColorScheme

/**
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
) {
    val filled: HaloColor
        get() =
            HaloColor(
                container = neutral,
                content = weaker,
                border = Color.Transparent,
            )

    val outlined: HaloColor
        get() =
            HaloColor(
                container = weaker,
                content = strong,
                border = weak,
            )
}

@Immutable
data class HaloColor(
    val container: Color,
    val content: Color,
    val border: Color,
)

data class HaloColorTheme(
    val light: HaloColorScheme,
    val dark: HaloColorScheme,
)

@Immutable
data class HaloColorScheme(
    val primary: HaloColorValue,
    val background: HaloElevationColor,
    val content: HaloColorValue,
    val info: HaloColorValue,
    val error: HaloColorValue,
    val success: HaloColorValue,
    val warning: HaloColorValue,
    val disabled: HaloColor,
) {
    companion object {
        val Sky =
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
        true -> HaloDefaults.ColorThemes.Polar.dark
        false -> HaloDefaults.ColorThemes.Polar.light
    }

internal val LocalHaloColorScheme = staticCompositionLocalOf { HaloLightColorScheme }

internal fun provideHaloColorScheme(colorScheme: HaloColorScheme) = LocalHaloColorScheme provides colorScheme

internal fun provideContentColor(color: Color) = LocalContentColor provides color

internal fun provideRippleIndication(ripple: Indication) = LocalIndication provides ripple
