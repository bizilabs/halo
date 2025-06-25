package org.bizilabs.halo.charts.style

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines the visual style for a line in the chart.
 * @param color The color of the line.
 * @param strokeWidth The thickness of the line.
 * @param fillGradient A brush to fill the area beneath the line.
 * @param pathEffect Optional effect for the line, e.g., dashed.
 */
data class LineStyle(
    val color: Color = Color.Magenta,
    val strokeWidth: Dp = 3.dp,
    val fillGradient: Brush? =
        Brush.verticalGradient(
            colors = listOf(color.copy(alpha = 0.4f), Color.Transparent),
        ),
    val pathEffect: PathEffect? = null,
)
