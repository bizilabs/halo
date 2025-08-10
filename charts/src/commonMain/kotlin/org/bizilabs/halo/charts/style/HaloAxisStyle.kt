package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bizilabs.halo.HaloTheme

/**
 * Styling for the X and Y axes.
 * @param labelTextStyle The style for the text labels on the axis.
 * @param labelCount The approximate number of labels to display.
 * @param showGridLines Whether to show faint grid lines for this axis.
 */
@ExposedCopyVisibility
data class HaloAxisStyle internal constructor(
    val showLabels: Boolean = true,
    val labelTextStyle: TextStyle = TextStyle(color = Color.Gray, fontSize = 12.sp),
    val labelCount: Int = 4,
    val labelTextColor: Color = Color.Gray,
    val showGridLines: Boolean = true,
    val gridLineColor: Color = Color.Gray.copy(alpha = 0.2f),
    val axisBackgroundColor: Color = Color.Gray,
    val gridLineWidth: Dp = 1.dp,
)

@Composable
fun haloAxisStyle(
    showLabels: Boolean = true,
    labelCount: Int = 4,
    labelTextStyle: TextStyle = HaloTheme.typography.bodySmall,
    labelTextColor: Color = HaloTheme.colorScheme.content.neutral,
    showGridLines: Boolean = true,
    gridLineWidth: Dp = 1.dp,
    gridLineColor: Color =
        HaloTheme.colorScheme.content.weaker
            .copy(0.25f),
    axisBackgroundColor: Color = HaloTheme.colorScheme.background.base,
): HaloAxisStyle =
    HaloAxisStyle(
        showLabels = showLabels,
        labelTextStyle = labelTextStyle,
        labelCount = labelCount,
        labelTextColor = labelTextColor,
        showGridLines = showGridLines,
        gridLineColor = gridLineColor,
        axisBackgroundColor = axisBackgroundColor,
        gridLineWidth = gridLineWidth,
    )
