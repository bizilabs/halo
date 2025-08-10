package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
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
@ExposedCopyVisibility
data class HaloLineStyle internal constructor(
    val color: Color = Color.Magenta,
    val strokeWidth: Dp = 3.dp,
    val fillGradient: Brush? =
        Brush.verticalGradient(
            colors = listOf(color.copy(alpha = 0.4f), Color.Transparent),
        ),
    val pathEffect: PathEffect? = null,
)

@Composable
fun haloLineStyle(
    color: Color = Color.Magenta,
    strokeWidth: Dp = 3.dp,
    fillGradient: Brush? =
        Brush.verticalGradient(
            colors = listOf(color.copy(alpha = 0.4f), Color.Transparent),
        ),
    pathEffect: PathEffect? = null,
): HaloLineStyle =
    HaloLineStyle(
        color = color,
        strokeWidth = strokeWidth,
        fillGradient = fillGradient,
        pathEffect = pathEffect,
    )
