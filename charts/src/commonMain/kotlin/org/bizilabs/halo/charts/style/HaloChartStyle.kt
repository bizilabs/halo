package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface HaloChartStyle {
    /**
     * A container for all chart styling options.
     * @param pointSpacing The horizontal distance between data points. If Dp.Unspecified, calculates dynamically. Otherwise, uses fixed value.
     * @param minPointSpacing Minimum spacing if calculated dynamically
     * @param yAxisStyle Styling for the Y-axis.
     * @param xAxisStyle Styling for the X-axis.
     * @param indicatorStyle Styling for the interactive indicator.
     */
    class HaloLineChartStyle(
        val pointSpacing: Dp = 60.dp,
        val minPointSpacing: Dp = 20.dp,
        val yAxisStyle: HaloAxisStyle = HaloAxisStyle(),
        val xAxisStyle: HaloAxisStyle = HaloAxisStyle(),
        val indicatorStyle: HaloIndicatorStyle = HaloIndicatorStyle(),
    ) : HaloChartStyle
}

@Composable
fun haloLineChartStyle(
    pointSpacing: Dp = 60.dp,
    minPointSpacing: Dp = 20.dp,
    yAxisStyle: HaloAxisStyle = haloAxisStyle(),
    xAxisStyle: HaloAxisStyle = haloAxisStyle(),
    indicatorStyle: HaloIndicatorStyle = haloIndicatorStyle(),
): HaloChartStyle.HaloLineChartStyle =
    HaloChartStyle.HaloLineChartStyle(
        pointSpacing = pointSpacing,
        minPointSpacing = minPointSpacing,
        yAxisStyle = yAxisStyle,
        xAxisStyle = xAxisStyle,
        indicatorStyle = indicatorStyle,
    )
