package org.bizilabs.halo.charts.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.charts.data.ChartPadding
import org.bizilabs.halo.charts.data.LineChartData
import org.bizilabs.halo.charts.data.Point
import org.bizilabs.halo.charts.draw.drawXAxis
import org.bizilabs.halo.charts.draw.drawYAxis
import org.bizilabs.halo.charts.draw.generatePath
import org.bizilabs.halo.charts.helpers.dpToPx
import org.bizilabs.halo.charts.helpers.format
import org.bizilabs.halo.charts.helpers.pxToDp
import org.bizilabs.halo.charts.style.ChartDefaults
import org.bizilabs.halo.charts.style.IndicatorStyle
import org.bizilabs.halo.charts.style.LineChartStyle
import org.bizilabs.halo.charts.style.LineStyle
import org.bizilabs.halo.components.HaloText
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun HaloLineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    contentPadding: ChartPadding =
        ChartPadding(
            start = 16.dp,
            end = 16.dp,
        ),
    style: LineChartStyle = ChartDefaults.lineChartStyle(),
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

    // Calculate Y-axis labels(In whole numbers) and the required padding.
    val yAxisLabels =
        remember(minY, maxY, style.yAxisStyle) {
            val axisMin = floor(minY).toInt()
            var axisMax = ceil(maxY).toInt()

            // Edge case: If min and max data values result in the same floor/ceil (e.g., 5.1 and 5.3
            // both yield 5 for floor and 6 for ceil, but if 5.0 and 5.0, both yield 5),
            // we need at least one step to show a range.
            if (axisMin == axisMax) {
                axisMax += 1 // Ensure the max is at least one greater than the min.
            }

            (0..style.yAxisStyle.labelCount).map { i ->
                val value = axisMin + (axisMax - axisMin) * i / style.yAxisStyle.labelCount
                value.toString()
            }
        }

    val yAxisLabelMaxWidth =
        remember(yAxisLabels, style.yAxisStyle) {
            (
                yAxisLabels.maxOfOrNull {
                    textMeasurer
                        .measure(
                            AnnotatedString(it),
                            style = style.yAxisStyle.labelTextStyle,
                        ).size.width
                } ?: 0
            )
        }.toFloat()

    val yAxisPadding =
        yAxisLabelMaxWidth + with(density) { 8.dp.toPx() } // Add some padding to the right of the labels

    val xAxisPadding =
        remember(style.xAxisStyle) {
            with(density) { 30.dp.toPx() } // Fixed padding for X-axis labels
        }.toFloat()

    // This buffer accounts for half of the label height at both top and bottom edges
    // plus a small extra visual padding.
    val labelVerticalMarginPx =
        with(density) {
            style.yAxisStyle.labelTextStyle.fontSize
                .toPx() / 2 + 4.dp.toPx()
        }

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

    Column(modifier = modifier.fillMaxWidth()) {
        // Legend
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = style.legendBottomSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            lineChartData.lines.forEach { line ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp),
                ) {
                    Spacer(
                        modifier =
                            Modifier
                                .size(16.dp)
                                .clip(HaloTheme.shapes.medium)
                                .background(line.style.color),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    HaloText(
                        text = line.label,
                        style = style.legendTextStyle,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth().weight(1f)) {
            val chartHeight = constraints.maxHeight.toFloat()
            val chartWidth = constraints.maxWidth.toFloat()

            // Calculate the width of the chart content and add padding at the end.
            val startPadding = with(density) { contentPadding.start.toPx() }
            val endPadding = with(density) { contentPadding.end.toPx() }

            // Calculate width for chart content (excluding Y-axis padding, including start/end contentPadding)
            val availableContentWidthForDynamicSpacing =
                chartWidth - yAxisPadding - with(density) { contentPadding.start.toPx() } -
                    with(
                        density,
                    ) { contentPadding.end.toPx() }
            val numUniqueXPoints = allPoints.distinctBy { it.x }.size

            val calculatedPointSpacingPx =
                if (numUniqueXPoints > 1) {
                    if (style.pointSpacing == Dp.Unspecified) {
                        // Calculate dynamic spacing
                        (availableContentWidthForDynamicSpacing / (numUniqueXPoints - 1))
                            .coerceAtLeast(style.minPointSpacing.dpToPx())
                    } else {
                        // Use fixed spacing
                        style.pointSpacing.dpToPx()
                    }
                } else {
                    0f // If 0 or 1 data point, spacing is not applicable or 0
                }

            // Recalculate contentWidth based on potentially dynamic spacing
            val contentWidth =
                if (numUniqueXPoints > 1) {
                    (numUniqueXPoints - 1) * calculatedPointSpacingPx
                } else {
                    0f // For 0 or 1 point, contentWidth is 0
                }

            val drawingWidth =
                yAxisPadding + startPadding + contentWidth + endPadding // Total width of the scrollable canvas

            val drawingHeight = chartHeight - xAxisPadding

            // Lambdas to convert data points to canvas coordinates.
            // The X mapping is done over the contentWidth so it doesn't stretch into the padding.
            val toPxX: (Float) -> Float = { x ->
                val xRange = (maxX - minX).takeIf { it > 0f } ?: 1f
                yAxisPadding + startPadding + ((x - minX) / xRange) * contentWidth
            }
            val toPxY: (Float) -> Float = { y ->
                val yRange = (maxY - minY).takeIf { it > 0f } ?: 1f
                drawingHeight - ((y - minY) / yRange) * drawingHeight
            }
            Spacer(modifier = Modifier.width(yAxisPadding.dp))

            // Main chart content box (scrollable area)
            Box(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .horizontalScroll(scrollState)
                        .width(drawingWidth.toInt().pxToDp()),
            ) {
                Canvas(
                    modifier =
                        Modifier
                            .matchParentSize()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = { offset ->
                                        touchLocation = offset
                                        scope.launch { awaitRelease() }
                                        // Keep selection on tap release unless configured otherwise
                                    },
                                )
                            },
//                            .pointerInput(allPoints) {
                    //                            detectDragGestures(
                    //                                onDragStart = { offset -> touchLocation = offset },
                    //                                onDragEnd = {
                    //                                    touchLocation = null
                    //                                    // Optional: Clear selection on drag end
                    //                                    // selectedIndex = null
                    //                                    // onPointSelected(null)
                    //                                },
                    //                                onDragCancel = {
                    //                                    touchLocation = null
                    //                                    // selectedIndex = null
                    //                                    // onPointSelected(null)
                    //                                },
                    //                            ) { change, _ ->
                    //                                touchLocation = change.position
                    //                            }
//                        },
                ) {
                    // Draw Y-Axis grid lines *before* drawing the chart lines, so they appear in the background
                    if (style.yAxisStyle.showGridLines) {
                        yAxisLabels.forEach { label ->
                            val yValue = label.toFloat()
                            val y = toPxY(yValue)
                            // Grid line starts after the y-axis labels area and extends to the end of the chart width
                            drawLine(
                                color = style.yAxisStyle.gridLineColor,
                                start = Offset(yAxisPadding, y),
                                end =
                                    Offset(
                                        drawingWidth,
                                        y,
                                    ),
                                // Use drawingWidth as the end point for grid lines
                                strokeWidth = style.yAxisStyle.gridLineWidth.toPx(),
                            )
                        }
                    }

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
                        val linePath =
                            generatePath(line.points, toPxX, toPxY, animationProgress.value)
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
                    if (lineChartData.selectedLineVisible) {
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

            // Draw Y-Axis labels and grid lines
            Canvas(
                modifier =
                    Modifier
                        // Shift the whole Y-axis canvas up by the calculated extension
                        .offset(y = (-labelVerticalMarginPx).pxToDp())
                        // Set the height of the Y-axis canvas to cover the drawingHeight plus the top extension
                        .height(with(density) { (chartHeight + labelVerticalMarginPx).pxToDp() })
                        .width(yAxisPadding.pxToDp()),
            ) {
                // Draw the background rectangle first
                drawRect(
                    color = style.yAxisStyle.axisBackgroundColor,
                    // The rectangle starts at 0,0 of this Canvas. This Canvas itself is offset.
                    topLeft = Offset.Zero,
                    size = size,
                )
                // Local toPxY for labels, mapping data to this specific Canvas's coordinates
                val toPxYLabelsCanvas: (Float) -> Float = { y ->
                    val yRange = (maxY - minY).takeIf { it > 0f } ?: 1f
                    val yNormalized =
                        (y - minY) / yRange

                    // Map yNormalized (0 to 1) to the Y-axis canvas coordinates.
                    // We want:
                    // - When y=maxY (yNormalized=1), it maps to labelVerticalMarginPx (top effective chart area within this Canvas)
                    // - When y=minY (yNormalized=0), it maps to labelVerticalMarginPx + drawingHeight (bottom effective chart area within this Canvas)
                    (labelVerticalMarginPx + drawingHeight) - yNormalized * drawingHeight
                }

                drawYAxis(
                    labels = yAxisLabels,
                    toPxY = toPxYLabelsCanvas,
                    style = style.yAxisStyle,
                    textMeasurer = textMeasurer,
                    yAxisLabelMaxWidth = yAxisLabelMaxWidth,
                )
            }
        }
    }
}

/**
 * Draws the indicator on the canvas.
 */
internal fun DrawScope.drawIndicator(
    points: List<Point>,
    toPxX: (Float) -> Float,
    toPxY: (Float) -> Float,
    style: IndicatorStyle,
    lineStyles: List<LineStyle>,
    textMeasurer: TextMeasurer,
    drawingHeight: Float,
) {
    val pointX = toPxX(points.first().x)

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
            color = Color.White,
            radius = style.indicatorCircleRadius.toPx(),
            center = Offset(pointX, pointY),
        )

        // Inner colored circle matching the line
        drawCircle(
            color = lineStyle.color,
            radius = (style.indicatorCircleRadius - style.indicatorCircleStrokeWidth / 2).toPx(),
            center = Offset(pointX, pointY),
        )

        // --- Draw the value label for the primary point ---
//    val labelText = "%.2f".format(primaryPoint.y)
        val labelText = point.y.format(2)
        val textLayoutResult =
            textMeasurer.measure(
                text = AnnotatedString(labelText),
                style = style.labelTextStyle,
            )

        val padding = 8.dp.toPx()
        val boxWidth = textLayoutResult.size.width + padding * 2
        val boxHeight = textLayoutResult.size.height + padding

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
}
