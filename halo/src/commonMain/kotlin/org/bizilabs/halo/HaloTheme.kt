package org.bizilabs.halo

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloFonts
import org.bizilabs.halo.base.HaloShapes
import org.bizilabs.halo.base.HaloTypography
import org.bizilabs.halo.base.LocalHaloColorScheme
import org.bizilabs.halo.base.LocalHaloShapes
import org.bizilabs.halo.base.LocalHaloTypography
import org.bizilabs.halo.base.LocalThickness
import org.bizilabs.halo.base.Thickness
import org.bizilabs.halo.base.asMaterialShapes
import org.bizilabs.halo.base.getHaloColorScheme
import org.bizilabs.halo.base.provideContentColor
import org.bizilabs.halo.base.provideHaloColorScheme
import org.bizilabs.halo.base.provideHaloShapes
import org.bizilabs.halo.base.provideRippleIndication
import org.bizilabs.halo.base.provideThickness
import org.bizilabs.halo.base.provideTypography

data object HaloDefaults {
    val Fonts = HaloFonts()
    val Shapes = HaloShapes
    val ColorThemes = HaloColorScheme
}

data object HaloTheme {
    val colorScheme: HaloColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalHaloColorScheme.current

    val thickness: Thickness
        @Composable
        get() = LocalThickness.current

    val typography: HaloTypography
        @Composable
        get() = LocalHaloTypography.current

    val shapes: HaloShapes
        @Composable
        get() = LocalHaloShapes.current
}

/**
 * @param shapes
 */
@Composable
fun HaloTheme(
    font: FontFamily = HaloDefaults.Fonts.Regular,
    typography: HaloTypography = HaloTypography(fontFamily = font),
    shapes: HaloShapes = HaloDefaults.Shapes.Default,
    colorScheme: HaloColorScheme = getHaloColorScheme(isDarkThemeEnabled = isSystemInDarkTheme()),
    ripple: Indication? = null,
    content: @Composable () -> Unit,
) {
    val defaultRippleColor =
        getHaloColorScheme(isDarkThemeEnabled = isSystemInDarkTheme()).content.neutral
    val rippleIndication =
        ripple ?: remember(defaultRippleColor) {
            ripple(color = defaultRippleColor, bounded = true)
        }

    CompositionLocalProvider(
        provideThickness(),
        provideHaloColorScheme(colorScheme = colorScheme),
        provideTypography(typography = typography),
        provideContentColor(color = colorScheme.content.stronger),
        provideRippleIndication(ripple = rippleIndication),
        provideHaloShapes(shapes = shapes),
    ) {
        MaterialTheme(shapes = shapes.asMaterialShapes(), content = content)
    }
}
