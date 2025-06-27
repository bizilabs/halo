package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A container for all chart styling options.
 * @param pointSpacing The horizontal distance between data points.
 * @param yAxisStyle Styling for the Y-axis.
 * @param xAxisStyle Styling for the X-axis.
 * @param indicatorStyle Styling for the interactive indicator.
 */
data class LineChartStyle(
    val pointSpacing: Dp = 60.dp,
    val yAxisStyle: AxisStyle = AxisStyle(),
    val xAxisStyle: AxisStyle = AxisStyle(),
    val indicatorStyle: IndicatorStyle = IndicatorStyle(),
    val legendTextStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 14.sp), // Added legend text style
    val legendBottomSpacing: Dp = 24.dp
)

/**
 * A container for default styling values.
 */
object ChartDefaults {
    @Composable
    fun lineChartStyle() = LineChartStyle()
}
