package org.bizilabs.halo.charts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.charts.data.ChartData
import org.bizilabs.halo.charts.data.Line
import org.bizilabs.halo.charts.data.LineChartData
import org.bizilabs.halo.charts.data.Point
import org.bizilabs.halo.charts.helpers.format
import org.bizilabs.halo.charts.style.AxisStyle
import org.bizilabs.halo.charts.style.ChartDefaults
import org.bizilabs.halo.charts.style.IndicatorStyle
import org.bizilabs.halo.charts.style.LineChartStyle
import org.bizilabs.halo.charts.style.LineStyle
import org.bizilabs.halo.charts.ui.HaloLineChart
import kotlin.random.Random

/**
 * The main entry point for creating a chart.
 * It observes the [ChartData] and delegates drawing to the appropriate chart type.
 *
 * @param chartData The data to be displayed in the chart.
 * @param modifier Modifier for this composable.
 * @param style The overall styling for the chart.
 * @param onPointSelected A callback that provides the currently selected point.
 */
@Composable
fun HaloChart(
    chartData: ChartData,
    modifier: Modifier = Modifier,
    style: LineChartStyle = ChartDefaults.lineChartStyle(),
    onPointSelected: (Point?) -> Unit = {},
) {
    when (chartData) {
        is LineChartData -> {
            HaloLineChart(
                lineChartData = chartData,
                modifier = modifier,
                style = style,
                onPointSelected = onPointSelected,
            )
        }
    }
}

@Composable
fun HaloLineChartSample() {
    val months =
        listOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "December",
        )
    val samplePoints1 =
        List(12) { i ->
            Point(i.toFloat(), Random.nextDouble(40.0, 110.0).toFloat(), months[i])
        }
    val samplePoints2 =
        List(12) { i ->
            Point(i.toFloat(), Random.nextDouble(0.0, 90.0).toFloat(), months[i])
        }

    val lineChartData =
        LineChartData(
            lines =
                listOf(
                    Line(
                        points = samplePoints1,
                        style =
                            LineStyle(
                                color = Color(0xFFC5A4FF),
                                strokeWidth = 3.dp,
                            ),
                        label = "Line 1",
                    ),
                    Line(
                        points = samplePoints2,
                        style =
                            LineStyle(
                                color = Color(0xFF43D4C4),
                                strokeWidth = 3.dp,
                            ),
                        label = "Line 2",
                    ),
                ),
            defaultSelectedIndex = 4,
        )

    var selectedValue by remember { mutableStateOf<String?>("Tap or Drag") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Investment Value: ${selectedValue ?: "None"}",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(16.dp))
        HaloChart(
            chartData = lineChartData,
            modifier = Modifier.fillMaxSize().height(240.dp).padding(horizontal = 16.dp),
            style =
                ChartDefaults.lineChartStyle().copy(
                    pointSpacing = 80.dp,
                    yAxisStyle =
                        AxisStyle(
                            labelCount = 10,
                            axisBackgroundColor = HaloTheme.colorScheme.background.base,
                        ),
                    indicatorStyle = IndicatorStyle(Color.Blue),
                    legendTextStyle = TextStyle(color = HaloTheme.colorScheme.content.stronger)
                ),
            onPointSelected = { point ->
                selectedValue = point?.y?.format(2) ?: "N/A"
            },
        )
    }
}
