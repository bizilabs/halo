package org.bizilabs.halo.charts.data

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween

/**
 * A sealed interface representing the data for any type of chart.
 * This allows for easy extension to other chart types like Bar or Pie charts.
 */
sealed interface ChartData {
    val animationSpec: AnimationSpec<Float>
        get() = tween(durationMillis = 1000)
}

/**
 * The specific data model for a Line Chart.
 * @param lines A list of [Line]s to be drawn on the chart.
 * @param defaultSelectedIndex The index of the point to be selected initially.
 */
data class LineChartData(
    val lines: List<Line>,
    val defaultSelectedIndex: Int? = 0,
) : ChartData
