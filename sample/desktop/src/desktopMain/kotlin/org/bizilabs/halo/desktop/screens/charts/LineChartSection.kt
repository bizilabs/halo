package org.bizilabs.halo.desktop.screens.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.charts.HaloChart
import org.bizilabs.halo.charts.data.HaloChartLine
import org.bizilabs.halo.charts.data.HaloChartPoint
import org.bizilabs.halo.charts.data.HaloLineChartData
import org.bizilabs.halo.charts.style.haloAxisStyle
import org.bizilabs.halo.charts.style.haloLineChartStyle
import org.bizilabs.halo.charts.style.haloLineStyle

@Composable
fun LineChartSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HaloChart(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            data =
                HaloLineChartData(
                    style =
                        haloLineChartStyle(
                            yAxisStyle =
                                haloAxisStyle(
                                    labelCount = 20,
                                    showLabels = true,
                                    gridLineColor =
                                        HaloTheme.colorScheme.content.weaker
                                            .copy(0.5f),
                                ),
                            xAxisStyle =
                                haloAxisStyle(
                                    labelCount = 11,
                                    showLabels = true,
                                    gridLineColor =
                                        HaloTheme.colorScheme.content.weaker
                                            .copy(0.5f),
                                ),
                        ),
                    lines =
                        listOf(
                            HaloChartLine(
                                points =
                                    listOf(
                                        HaloChartPoint(x = 1f, 1f, xLabel = "1"),
                                        HaloChartPoint(x = 2f, 2f, xLabel = "2"),
                                        HaloChartPoint(x = 3f, 3f, xLabel = "3"),
                                        HaloChartPoint(x = 4f, 4f, xLabel = "4"),
                                        HaloChartPoint(x = 5f, 5f, xLabel = "5"),
                                        HaloChartPoint(x = 6f, 6f, xLabel = "6"),
                                        HaloChartPoint(x = 7f, 7f, xLabel = "7"),
                                        HaloChartPoint(x = 8f, 8f, xLabel = "8"),
                                        HaloChartPoint(x = 9f, 9f, xLabel = "9"),
                                        HaloChartPoint(x = 10f, 10f, xLabel = "10"),
                                        HaloChartPoint(x = 11f, null, xLabel = "11"),
                                        HaloChartPoint(x = 12f, null, xLabel = "12"),
                                        HaloChartPoint(x = 13f, null, xLabel = "13"),
                                        HaloChartPoint(x = 14f, null, xLabel = "14"),
                                        HaloChartPoint(x = 15f, null, xLabel = "15"),
                                    ),
                                style = haloLineStyle(color = HaloTheme.colorScheme.error.neutral),
                                label = "One",
                            ),
                            HaloChartLine(
                                points =
                                    listOf(
                                        HaloChartPoint(x = 1f, null, xLabel = "1"),
                                        HaloChartPoint(x = 2f, null, xLabel = "2"),
                                        HaloChartPoint(x = 3f, null, xLabel = "3"),
                                        HaloChartPoint(x = 4f, null, xLabel = "4"),
                                        HaloChartPoint(x = 5f, null, xLabel = "5"),
                                        HaloChartPoint(x = 6f, null, xLabel = "6"),
                                        HaloChartPoint(x = 7f, null, xLabel = "7"),
                                        HaloChartPoint(x = 8f, null, xLabel = "8"),
                                        HaloChartPoint(x = 9f, null, xLabel = "9"),
                                        HaloChartPoint(x = 10f, null, xLabel = "10"),
                                        HaloChartPoint(x = 11f, null, xLabel = "11"),
                                        HaloChartPoint(x = 12f, null, xLabel = "12"),
                                        HaloChartPoint(x = 13f, null, xLabel = "13"),
                                        HaloChartPoint(x = 14f, null, xLabel = "14"),
                                        HaloChartPoint(x = 15f, null, xLabel = "15"),
                                    ),
                                style = haloLineStyle(color = HaloTheme.colorScheme.success.neutral),
                                label = "Two",
                            ),
                        ),
                ),
            onPointSelected = {},
        )
    }
}
