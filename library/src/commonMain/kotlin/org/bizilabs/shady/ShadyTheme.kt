package org.bizilabs.shady

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import org.bizilabs.shady.base.ShadyShapes

@Composable
fun ShadyTheme(
    shapes: Shapes = ShadyShapes.None,
    content: @Composable () -> Unit,
) {
    MaterialTheme(shapes = shapes, content = content)
}
