package org.bizilabs.halo.charts.data

import org.bizilabs.halo.charts.style.HaloLineStyle

/**
 * Represents a single data point on the chart.
 * @property x The value on the X-axis.
 * @property y The value on the Y-axis.
 * @property xLabel The optional label to display on the X-axis for this point.
 */
data class HaloChartPoint(
    val x: Float,
    val y: Float,
    val xLabel: String? = null,
)

/**
 * Represents a single line in a Line Chart.
 * @param points The list of data points that make up the line.
 * @param style The visual styling for this specific line.
 */
data class HaloChartLine(
    val points: List<HaloChartPoint>,
    val style: HaloLineStyle = HaloLineStyle(),
    val label: String,
)
