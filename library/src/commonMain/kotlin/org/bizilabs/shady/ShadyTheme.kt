package org.bizilabs.shady

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.bizilabs.shady.base.ShadyFonts
import org.bizilabs.shady.base.ShadyShapes

data object ShadyDefaults {
    val Fonts = ShadyFonts()
    val Shapes = ShadyShapes
}

data object ShadyTheme

/**
 * @param shapes
 */
@Composable
fun ShadyTheme(
    font: FontFamily = ShadyDefaults.Fonts.Regular,
    shapes: Shapes = ShadyDefaults.Shapes.None,
    content: @Composable () -> Unit,
) {
    MaterialTheme(shapes = shapes, content = content)
}
