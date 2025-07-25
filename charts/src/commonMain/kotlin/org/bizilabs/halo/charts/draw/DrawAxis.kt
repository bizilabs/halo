package org.bizilabs.halo.charts.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.charts.data.Point
import org.bizilabs.halo.charts.style.AxisStyle

/**
 * Draws the Y-axis labels and grid lines.
 */
internal fun DrawScope.drawYAxis(
    labels: List<String>,
    toPxY: (Float) -> Float,
    style: AxisStyle,
    textMeasurer: TextMeasurer,
    yAxisLabelMaxWidth: Float,
) {
    labels.forEach { label ->
        val yValue = label.toFloat()
        val y = toPxY(yValue)

        val textLayoutResult =
            textMeasurer.measure(
                text = AnnotatedString(label),
                style = style.labelTextStyle,
            )
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(yAxisLabelMaxWidth - textLayoutResult.size.width, y - textLayoutResult.size.height / 2),
        )
    }
}

/**
 * Draws the X-axis labels and grid lines.
 */
internal fun DrawScope.drawXAxis(
    points: List<Point>,
    toPxX: (Float) -> Float,
    style: AxisStyle,
    textMeasurer: TextMeasurer,
    drawingHeight: Float,
) {
    val uniquePoints = points.distinctBy { it.x }
    uniquePoints.forEachIndexed { index, point ->
        point.xLabel?.let { label ->
            val textLayoutResult =
                textMeasurer.measure(
                    text = AnnotatedString(label),
                    style = style.labelTextStyle,
                )
            val x =
                toPxX(point.x)
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(x - textLayoutResult.size.width / 2, drawingHeight + 8.dp.toPx()),
            )
        }
    }
}
