package org.bizilabs.halo.charts.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme

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

    sealed interface HaloPieChartStyle : HaloChartStyle {
        companion object {
            val DefaultStyle: HaloPieChartStyle
                get() = HaloPieChartStrokeStyle()
        }

        val backgroundColor: Color
            get() = Color.Gray.copy(alpha = 0.1f)

        val backgroundScale: Float
            get() = 0f

        class HaloPieChartStrokeStyle(
            val strokeWidth: Dp = 48.dp,
            val strokeCap: StrokeCap = StrokeCap.Square,
            val sliceSpacing: Dp = 4.dp,
            override val backgroundColor: Color = Color.Gray.copy(alpha = 0.1f),
            override val backgroundScale: Float = 0.5f,
        ) : HaloPieChartStyle

        class HaloPieChartFilledStyle(
            val spacing: Dp = 4.dp,
            override val backgroundColor: Color = Color.Gray.copy(alpha = 0.1f),
            override val backgroundScale: Float = 0f,
        ) : HaloPieChartStyle
    }
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

@Composable
fun haloPieChartStrokeStyle(
    strokeWidth: Dp = 48.dp,
    strokeCap: StrokeCap = StrokeCap.Square,
    spacing: Dp = 0.dp,
    backgroundColor: Color = HaloTheme.colorScheme.background.low,
    backgroundScale: Float = 1f,
) = HaloChartStyle.HaloPieChartStyle.HaloPieChartStrokeStyle(
    strokeWidth = strokeWidth,
    strokeCap = strokeCap,
    sliceSpacing = spacing,
    backgroundColor = backgroundColor,
    backgroundScale = backgroundScale,
)

@Composable
fun haloPieChartFilledStyle(
    spacing: Dp = 16.dp,
    backgroundColor: Color = HaloTheme.colorScheme.background.low,
    backgroundScale: Float = 1f,
) = HaloChartStyle.HaloPieChartStyle.HaloPieChartFilledStyle(
    spacing = spacing,
    backgroundColor = backgroundColor,
    backgroundScale = backgroundScale,
)
