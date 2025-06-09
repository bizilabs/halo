package org.bizilabs.halo

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.bizilabs.halo.base.HaloFonts
import org.bizilabs.halo.base.HaloShapes

data object HaloDefaults {
    val Fonts = HaloFonts()
    val Shapes = HaloShapes
}

data object HaloTheme

/**
 * @param shapes
 */
@Composable
fun HaloTheme(
    font: FontFamily = HaloDefaults.Fonts.Regular,
    shapes: Shapes = HaloDefaults.Shapes.None,
    content: @Composable () -> Unit,
) {
    MaterialTheme(shapes = shapes, content = content)
}
