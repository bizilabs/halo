package org.bizilabs.halo.charts.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.bizilabs.halo.charts.data.LineChartData
import org.bizilabs.halo.charts.data.Point
import org.bizilabs.halo.charts.draw.drawXAxis
import org.bizilabs.halo.charts.draw.drawYAxis
import org.bizilabs.halo.charts.draw.generatePath
import org.bizilabs.halo.charts.helpers.dpToPx
import org.bizilabs.halo.charts.helpers.format
import org.bizilabs.halo.charts.helpers.pxToDp
import org.bizilabs.halo.charts.style.IndicatorStyle
import org.bizilabs.halo.charts.style.LineChartStyle
import org.bizilabs.halo.charts.style.LineStyle
import kotlin.math.abs

@Composable
fun HaloLineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    style: LineChartStyle,
    onPointSelected: (Point?) -> Unit,
) {
    val textMeasurer = rememberTextMeasurer()
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    // Combine all points from all lines for calculations.
    val allPoints = remember(lineChartData) { lineChartData.lines.flatMap { it.points } }
    if (allPoints.isEmpty()) return

    // Animation progress for drawing the lines.
    val animationProgress = remember { Animatable(0f) }

    // State to hold user touch location and selected point index.
    var touchLocation by remember { mutableStateOf<Offset?>(null) }
    var selectedIndex by remember { mutableStateOf<Int?>(lineChartData.defaultSelectedIndex) }

    // Find min/max values to scale the chart correctly.
    val minX = remember(allPoints) { allPoints.minOf { it.x } }
    val maxX = remember(allPoints) { allPoints.maxOf { it.x } }
    val minY = remember(allPoints) { allPoints.minOf { it.y } }
    val maxY = remember(allPoints) { allPoints.maxOf { it.y } }

    // Calculate Y-axis labels and the required padding.
    val yAxisLabels =
        remember(minY, maxY, style.yAxisStyle) {
            (0..style.yAxisStyle.labelCount).map { i ->
                val value = minY + (maxY - minY) * i / style.yAxisStyle.labelCount
//                "%.1f".format(value)
                value.format(1)
            }
        }
    val yAxisPadding =
        remember(yAxisLabels, style.yAxisStyle) {
            with(density) {
                (
                    yAxisLabels.maxOfOrNull { textMeasurer.measure(AnnotatedString(it)).size.width }
                        ?: 0
                ) + 16.dp.toPx()
            }
        }.toFloat()

    val xAxisPadding =
        remember(style.xAxisStyle) {
            with(density) { 30.dp.toPx() } // Fixed padding for X-axis labels
        }.toFloat()

    // Trigger animation when data changes.
    LaunchedEffect(lineChartData) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(1f, animationSpec = lineChartData.animationSpec)
    }

    // Update selected point callback when selection changes
    LaunchedEffect(selectedIndex) {
        val selectedPoint =
            selectedIndex?.let {
                // Find the point from the first line for the callback. This can be customized.
                lineChartData.lines
                    .firstOrNull()
                    ?.points
                    ?.getOrNull(it)
            }
        onPointSelected(selectedPoint)
    }

    val scrollState = rememberScrollState()

    BoxWithConstraints(modifier = modifier) {
        val chartHeight = constraints.maxHeight.toFloat()
        val chartWidth = constraints.maxWidth.toFloat()

        val drawingWidth = (allPoints.distinctBy { it.x }.size - 1) * style.pointSpacing.dpToPx()
        val drawingHeight = chartHeight - xAxisPadding

        // Lambdas to convert data points to canvas coordinates, accounting for padding.
        val toPxX: (Float) -> Float = { x ->
            yAxisPadding + (
                ((x - minX) / ((maxX - minX).takeIf { it > 0 } ?: 1f))
            ) * (drawingWidth - yAxisPadding)
        }
        val toPxY: (Float) -> Float = { y ->
            drawingHeight - (((y - minY) / ((maxY - minY).takeIf { it > 0 } ?: 1f))) * drawingHeight
        }

        // Draw Y-Axis labels and grid lines
        Canvas(modifier = Modifier.fillMaxHeight().width(yAxisPadding.dp)) {
            drawYAxis(yAxisLabels, toPxY, style.yAxisStyle, textMeasurer, chartWidth)
        }

        Spacer(modifier = Modifier.width(yAxisPadding.dp))

        Box(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .horizontalScroll(scrollState),
        ) {
            Canvas(
                modifier =
                    Modifier
                        .width(drawingWidth.toInt().pxToDp())
                        .fillMaxHeight()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { offset ->
                                    touchLocation = offset
                                    scope.launch { awaitRelease() }
                                    // Keep selection on tap release unless configured otherwise
                                },
                            )
                        }.pointerInput(allPoints) {
                            detectDragGestures(
                                onDragStart = { offset -> touchLocation = offset },
                                onDragEnd = {
                                    touchLocation = null
                                    // Optional: Clear selection on drag end
                                    // selectedIndex = null
                                    // onPointSelected(null)
                                },
                                onDragCancel = {
                                    touchLocation = null
                                    // selectedIndex = null
                                    // onPointSelected(null)
                                },
                            ) { change, _ ->
                                touchLocation = change.position
                            }
                        },
            ) {
                // Find the closest point index to the touch location
                val currentTouchLocation = touchLocation
                if (currentTouchLocation != null) {
                    val uniqueXPoints = allPoints.distinctBy { it.x }
                    val closestIndex =
                        uniqueXPoints.indices.minByOrNull {
                            abs(toPxX(uniqueXPoints[it].x) - (currentTouchLocation.x + scrollState.value))
                        }
                    if (selectedIndex != closestIndex) {
                        selectedIndex = closestIndex
                    }
                }

                // Draw X-Axis labels and grid lines
                drawXAxis(allPoints, toPxX, style.xAxisStyle, textMeasurer, drawingHeight)

                // Draw each line
                lineChartData.lines.forEach { line ->
                    val linePath = generatePath(line.points, toPxX, toPxY, animationProgress.value)
                    val fillPath =
                        Path().apply {
                            addPath(linePath)
                            lineTo(toPxX(line.points.last().x), drawingHeight)
                            lineTo(toPxX(line.points.first().x), drawingHeight)
                            close()
                        }

                    // Draw fill gradient
                    line.style.fillGradient?.let {
                        drawPath(path = fillPath, brush = it)
                    }

                    // Draw the line stroke
                    drawPath(
                        path = linePath,
                        color = line.style.color,
                        style =
                            Stroke(
                                width = line.style.strokeWidth.toPx(),
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round,
                                pathEffect = line.style.pathEffect,
                            ),
                    )
                }

                // Draw indicator for the selected point index
                selectedIndex?.let { index ->
                    val pointsAtSelectedIndex =
                        lineChartData.lines.mapNotNull { it.points.getOrNull(index) }
                    val lineStyles = lineChartData.lines.map { it.style }

                    // Find the point closest to the touch vertically to display its label
                    val primaryPoint =
                        currentTouchLocation?.let { touch ->
                            pointsAtSelectedIndex.minByOrNull { abs(toPxY(it.y) - touch.y) }
                        } ?: pointsAtSelectedIndex.firstOrNull()

                    if (primaryPoint != null) {
                        drawIndicator(
                            points = pointsAtSelectedIndex,
                            primaryPoint = primaryPoint,
                            toPxX = toPxX,
                            toPxY = toPxY,
                            style = style.indicatorStyle,
                            lineStyles = lineStyles,
                            textMeasurer = textMeasurer,
                            drawingHeight = drawingHeight,
                        )
                    }
                }
            }
        }
    }
}

/**
 * Draws the indicator on the canvas.
 */
internal fun DrawScope.drawIndicator(
    points: List<Point>,
    primaryPoint: Point,
    toPxX: (Float) -> Float,
    toPxY: (Float) -> Float,
    style: IndicatorStyle,
    lineStyles: List<LineStyle>,
    textMeasurer: TextMeasurer,
    drawingHeight: Float,
) {
    val pointX = toPxX(primaryPoint.x)

    // Draw vertical line
    drawLine(
        color = style.color,
        start = Offset(pointX, 0f),
        end = Offset(pointX, drawingHeight),
        strokeWidth = 1.dp.toPx(),
    )

    // Draw circles on each line for the selected X-value
    points.forEachIndexed { index, point ->
        val lineStyle = lineStyles.getOrNull(index) ?: return@forEachIndexed
        val pointY = toPxY(point.y)

        // Outer stroke for the circle
        drawCircle(
            color = Color.White, // or a theme-appropriate background color
            radius = style.indicatorCircleRadius.toPx(),
            center = Offset(pointX, pointY),
        )

        // Inner colored circle matching the line
        drawCircle(
            color = lineStyle.color,
            radius = (style.indicatorCircleRadius - style.indicatorCircleStrokeWidth / 2).toPx(),
            center = Offset(pointX, pointY),
        )
    }

    // --- Draw the value label for the primary point ---
//    val labelText = "%.2f".format(primaryPoint.y)
    val labelText = primaryPoint.y.format(2)
    val textLayoutResult =
        textMeasurer.measure(
            text = AnnotatedString(labelText),
            style = style.labelTextStyle,
        )

    val padding = 8.dp.toPx()
    val boxWidth = textLayoutResult.size.width + padding * 2
    val boxHeight = textLayoutResult.size.height + padding
    val pointY = toPxY(primaryPoint.y)

    val boxTopLeft =
        Offset(
            x = (pointX - boxWidth / 2).coerceIn(0f, size.width - boxWidth),
            y =
                (pointY - boxHeight - style.indicatorCircleRadius.toPx() - padding / 2).coerceAtLeast(
                    0f,
                ),
        )

    // Draw rounded rect background for the label
    drawRoundRect(
        color = style.labelBackgroundColor,
        topLeft = boxTopLeft,
        size = Size(boxWidth, boxHeight),
        cornerRadius =
            androidx.compose.ui.geometry
                .CornerRadius(8.dp.toPx()),
    )

    // Draw the text itself
    drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(boxTopLeft.x + padding, boxTopLeft.y + padding / 2),
    )
}
