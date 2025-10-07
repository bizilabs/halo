package org.bizilabs.halo.desktop.screens.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.charts.HaloChart
import org.bizilabs.halo.charts.data.HaloPieChartData
import org.bizilabs.halo.charts.data.pie.HaloPieSlice
import org.bizilabs.halo.charts.style.haloPieChartFilledStyle
import org.bizilabs.halo.charts.style.haloPieChartStrokeStyle

@Composable
fun PieChartSection() {
    val slices =
        listOf(
            HaloPieSlice(41f, Color(0xFFF47A7A), "Marketing"),
            HaloPieSlice(12f, Color(0xFF7AC5F4), "HR"),
            HaloPieSlice(18f, Color(0xFFB57AF4), "Development"),
            HaloPieSlice(10f, Color(0xFF7AF48A), "Design"),
            HaloPieSlice(8f, Color(0xFF7A88F4), "Operations"),
            HaloPieSlice(12f, Color(0xFFF4C97A), "Support"),
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HaloChart(
            data =
                HaloPieChartData(
                    slices = slices,
                    style =
                        haloPieChartStrokeStyle(
                            backgroundScale = 1f,
                            spacing = 2.dp,
                            strokeCap = StrokeCap.Butt,
                        ),
                ),
            modifier = Modifier.size(250.dp),
        )
        Spacer(modifier = Modifier.padding(16.dp))
        HaloChart(
            data =
                HaloPieChartData(
                    slices = slices,
                    style =
                        haloPieChartFilledStyle(
                            spacing = 0.dp,
                            backgroundScale = 1f,
                        ),
                ),
            modifier = Modifier.size(250.dp),
        )
    }
}
