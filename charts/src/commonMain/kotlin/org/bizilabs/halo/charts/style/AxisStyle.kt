package org.bizilabs.halo.charts.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Styling for the X and Y axes.
 * @param labelTextStyle The style for the text labels on the axis.
 * @param labelCount The approximate number of labels to display.
 * @param showGridLines Whether to show faint grid lines for this axis.
 */
data class AxisStyle(
    val labelTextStyle: TextStyle = TextStyle(color = Color.Gray, fontSize = 12.sp),
    val labelCount: Int = 4,
    val showGridLines: Boolean = true,
    val gridLineColor: Color = Color.Gray.copy(alpha = 0.2f),
    val axisBackgroundColor: Color = Color.Gray,
    val gridLineWidth: Dp = 1.dp,
)
