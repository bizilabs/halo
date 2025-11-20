package org.bizilabs.halo.charts.data

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import org.bizilabs.halo.charts.data.pie.HaloPieSlice
import org.bizilabs.halo.charts.style.HaloChartStyle

/**
 * A sealed interface representing the data for any type of chart.
 * This allows for easy extension to other chart types like Bar or Pie charts.
 */
sealed interface HaloChartData {
    val animationSpec: AnimationSpec<Float>
        get() = tween(durationMillis = 1000)
}

object HaloChartDefault

/**
 * The specific data model for a Line Chart.
 * @param lines A list of [HaloChartLine]s to be drawn on the chart.
 * @param defaultSelectedIndex The index of the point to be selected initially.
 */
data class HaloLineChartData(
    val lines: List<HaloChartLine>,
    val defaultSelectedIndex: Int? = lines.lastIndex,
    val style: HaloChartStyle.HaloLineChartStyle = HaloChartStyle.HaloLineChartStyle(),
) : HaloChartData

data class HaloPieChartData(
    val slices: List<HaloPieSlice>,
    val style: HaloChartStyle.HaloPieChartStyle = HaloChartStyle.HaloPieChartStyle.DefaultStyle,
) : HaloChartData
