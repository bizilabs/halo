package org.bizilabs.halo.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.charts.data.HaloChartData
import org.bizilabs.halo.charts.data.HaloChartPadding
import org.bizilabs.halo.charts.data.HaloChartPoint
import org.bizilabs.halo.charts.data.HaloLineChartData
import org.bizilabs.halo.charts.ui.HaloLineChart

/**
 * The main entry point for creating a chart.
 * It observes the [HaloChartData] and delegates drawing to the appropriate chart type.
 *
 * @param data The data to be displayed in the chart.
 * @param modifier Modifier for this composable.
 * @param style The overall styling for the chart.
 * @param onPointSelected A callback that provides the currently selected point.
 */
@Composable
fun HaloChart(
    data: HaloChartData,
    modifier: Modifier = Modifier,
    padding: HaloChartPadding = HaloChartPadding(start = 16.dp, end = 16.dp),
    onPointSelected: (HaloChartPoint?) -> Unit = {},
) {
    when (data) {
        is HaloLineChartData ->
            HaloLineChart(
                modifier = modifier,
                data = data,
                style = data.style,
                contentPadding = padding,
                onPointSelected = onPointSelected,
            )
    }
}
