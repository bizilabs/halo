package org.bizilabs.halo

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.font.FontFamily
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloFonts
import org.bizilabs.halo.base.HaloShapes
import org.bizilabs.halo.base.LocalHaloColorScheme
import org.bizilabs.halo.base.LocalThickness
import org.bizilabs.halo.base.Thickness
import org.bizilabs.halo.base.getHaloColorScheme
import org.bizilabs.halo.base.provideHaloColorScheme
import org.bizilabs.halo.base.provideThickness

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
}

/**
 * @param shapes
 */
@Composable
fun HaloTheme(
    font: FontFamily = HaloDefaults.Fonts.Regular,
    shapes: Shapes = HaloDefaults.Shapes.None,
    colorScheme: HaloColorScheme = getHaloColorScheme(isDarkThemeEnabled = isSystemInDarkTheme()),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        provideThickness(),
        provideHaloColorScheme(colorScheme = colorScheme),
    ) {
        MaterialTheme(shapes = shapes, content = content)
    }
}
