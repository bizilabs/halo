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
                            xAxisStyle = haloAxisStyle(labelCount = 11, showLabels = false),
                        ),
                    lines =
                        listOf(
                            HaloChartLine(
                                points =
                                    listOf(
                                        HaloChartPoint(x = 1f, 1f),
                                        HaloChartPoint(x = 2f, 2f),
                                        HaloChartPoint(x = 3f, 3f),
                                        HaloChartPoint(x = 4f, 4f),
                                        HaloChartPoint(x = 5f, 5f),
                                        HaloChartPoint(x = 6f, 6f),
                                        HaloChartPoint(x = 7f, 7f),
                                        HaloChartPoint(x = 8f, 8f),
                                        HaloChartPoint(x = 9f, 9f),
                                        HaloChartPoint(x = 10f, 10f),
                                    ),
                                style = haloLineStyle(color = HaloTheme.colorScheme.error.neutral),
                                label = "One",
                            ),
                            HaloChartLine(
                                points =
                                    listOf(
                                        HaloChartPoint(x = 1f, 10f),
                                        HaloChartPoint(x = 2f, 9f),
                                        HaloChartPoint(x = 3f, 8f),
                                        HaloChartPoint(x = 4f, 7f),
                                        HaloChartPoint(x = 5f, 6f),
                                        HaloChartPoint(x = 6f, 5f),
                                        HaloChartPoint(x = 7f, 4f),
                                        HaloChartPoint(x = 8f, 3f),
                                        HaloChartPoint(x = 9f, 2f),
                                        HaloChartPoint(x = 10f, 1f),
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
