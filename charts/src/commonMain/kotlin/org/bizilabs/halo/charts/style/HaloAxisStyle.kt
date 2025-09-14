package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bizilabs.halo.HaloTheme

/**
 * Styling for chart's X and Y axes, grid and background.
 * @param labelCount The approximate number of labels to display.
 * @param showGridLines Whether to show faint grid lines for this axis.
 */
@ExposedCopyVisibility
data class HaloAxisStyle internal constructor(
    val showLabels: Boolean = true,
    val labelCount: Int = 4,
    val labelStyle: HaloLabelStyle = HaloLabelStyle(Color.Gray, TextStyle(fontSize = 12.sp)),
    val showGridLines: Boolean = true,
    val gridLineColor: Color = Color.Gray.copy(alpha = 0.2f),
    val axisBackgroundColor: Color = Color.Gray,
    val gridLineWidth: Dp = 1.dp,
)

@Composable
fun haloAxisStyle(
    showLabels: Boolean = true,
    labelCount: Int = 4,
    labelStyle: HaloLabelStyle = HaloLabelStyle.Default,
    showGridLines: Boolean = true,
    gridLineWidth: Dp = 1.dp,
    gridLineColor: Color =
        HaloTheme.colorScheme.content.weaker
            .copy(0.25f),
    axisBackgroundColor: Color = HaloTheme.colorScheme.background.lowest,
): HaloAxisStyle =
    HaloAxisStyle(
        showLabels = showLabels,
        labelCount = labelCount,
        labelStyle = labelStyle,
        showGridLines = showGridLines,
        gridLineColor = gridLineColor,
        axisBackgroundColor = axisBackgroundColor,
        gridLineWidth = gridLineWidth,
    )

@ExposedCopyVisibility
data class HaloLabelStyle internal constructor(
    val color: Color,
    val textStyle: TextStyle,
) {
    companion object {
        val Default
            @Composable
            get() = haloLabelStyle()
    }
}

@Composable
fun haloLabelStyle(
    color: Color = HaloTheme.colorScheme.content.neutral,
    textStyle: TextStyle = HaloTheme.typography.bodySmall,
): HaloLabelStyle =
    HaloLabelStyle(
        color = color,
        textStyle = textStyle,
    )
