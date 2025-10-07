package org.bizilabs.halo.charts.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.charts.HaloChartDefaults
import org.bizilabs.halo.charts.data.HaloPieChartData
import org.bizilabs.halo.charts.style.HaloChartStyle
import kotlin.math.min

// @Composable
// fun HaloPieChart(
//    data: HaloPieChartData,
//    modifier: Modifier = Modifier,
//    style: HaloChartStyle.HaloPieChartStyle = HaloChartDefaults.pieChartStyle(),
// ) {
//    if (data.slices.isEmpty()) return
//
//    val animationProgress = remember { Animatable(0f) }
//    val totalValue =
//        remember(data.slices) {
//            data.slices.sumOf { it.value.toDouble() }.toFloat()
//        }
//    var selectedIndex by remember { mutableStateOf<Int?>(null) }
//
//    LaunchedEffect(data) {
//        animationProgress.snapTo(0f)
//        animationProgress.animateTo(1f, animationSpec = data.animationSpec)
//    }
//
//    // Animate stroke width for selection effect
//    val animatedStrokes =
//        data.slices.mapIndexed { index, _ ->
//            val targetStroke =
//                if (selectedIndex == index) style.strokeWidth * 1.1f else style.strokeWidth
//            animateDpAsState(targetValue = targetStroke, animationSpec = tween(durationMillis = 300))
//        }
//
//    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
//        val chartSize = min(constraints.maxWidth, constraints.maxHeight).toFloat()
//        val density = LocalDensity.current
//        val baseStrokeWidthPx = with(density) { style.strokeWidth.toPx() }
//
//        Canvas(
//            modifier =
//                Modifier
//                    .size(chartSize.dp)
//                    .pointerInput(data.slices) {
//                        detectTapGestures { tapOffset ->
//                            val chartCenter = Offset(size.width / 2f, size.height / 2f)
//                            val distance = (tapOffset - chartCenter).getDistance()
//
//                            // Use the largest possible stroke width for accurate hit testing
//                            val maxStrokeWidthPx = with(density) { (style.strokeWidth * 1.1f).toPx() }
//                            val outerRadius = chartCenter.x
//                            val innerRadius = chartCenter.x - maxStrokeWidthPx
//
//                            if (distance in innerRadius..outerRadius) {
//                                // Convert tap offset to angle, adjusting for the canvas coordinate system
//                                // where 0 degrees is at the top (12 o'clock).
//                                val touchAngle =
//                                    (
//                                        atan2(
//                                            tapOffset.y - chartCenter.y,
//                                            tapOffset.x - chartCenter.x,
//                                        ) * (180f / PI.toFloat()) + 450f
//                                    ) % 360f
//
//                                var currentStartAngle = 0f
//                                data.slices.forEachIndexed { index, slice ->
//                                    val sweepAngle = (slice.value / totalValue) * 360f
//                                    val endAngle = currentStartAngle + sweepAngle
//
//                                    if (touchAngle >= currentStartAngle && touchAngle < endAngle) {
//                                        selectedIndex = if (selectedIndex == index) null else index
//                                        return@detectTapGestures
//                                    }
//                                    currentStartAngle = endAngle
//                                }
//                            } else {
//                                // Tapped outside the ring, deselect
//                                selectedIndex = null
//                            }
//                        }
//                    },
//        ) {
//            // Draw the background track
//            drawArc(
//                color = style.backgroundColor,
//                startAngle = 0f,
//                sweepAngle = 360f,
//                useCenter = false,
//                style = Stroke(width = baseStrokeWidthPx),
//            )
//
//            var startAngle = -90f
//            data.slices.forEachIndexed { index, slice ->
//                val sweepAngle = (slice.value / totalValue) * 360f
//                val animatedStrokePx = animatedStrokes[index].value.toPx()
//                drawArc(
//                    color = slice.color,
//                    startAngle = startAngle,
//                    sweepAngle = (sweepAngle - style.sliceSpacing) * animationProgress.value,
//                    useCenter = false,
//                    style = Stroke(width = animatedStrokePx, cap = StrokeCap.Butt, join = StrokeJoin.Round),
//                )
//                startAngle += sweepAngle
//            }
//        }
//    }
// }

@Composable
fun HaloPieChart(
    data: HaloPieChartData,
    modifier: Modifier = Modifier,
    style: HaloChartStyle.HaloPieChartStyle = HaloChartDefaults.pieChartStyle(),
) {
    val animationProgress = remember { Animatable(0f) }
    val totalValue =
        remember(data.slices) { data.slices.sumOf { it.value.toDouble() }.toFloat() }

    LaunchedEffect(data) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(1f, animationSpec = data.animationSpec)
    }

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val chartSize = min(constraints.maxWidth, constraints.maxHeight)
        val density = LocalDensity.current

        Canvas(
            modifier = Modifier.size(chartSize.dp),
        ) {
            var startAngle = -90f

            when (style) {
                is HaloChartStyle.HaloPieChartStyle.HaloPieChartStrokeStyle -> {
                    val strokeWidthPx = with(density) { style.strokeWidth.toPx() }

                    // Define the oval for the arcs, which is inset by half the stroke width.
                    // This is used for both the background track and the data slices to ensure they align perfectly.
                    val oval =
                        Rect(
                            left = strokeWidthPx / 2,
                            top = strokeWidthPx / 2,
                            right = size.width - strokeWidthPx / 2,
                            bottom = size.height - strokeWidthPx / 2,
                        )

                    // Draw the background track using a full arc on the same oval.
                    // The stroke width is scaled, making the background thicker or thinner.
                    drawArc(
                        color = style.backgroundColor,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidthPx * style.backgroundScale),
                        topLeft = oval.topLeft,
                        size = oval.size,
                    )

                    data.slices.forEach { slice ->
                        val sweepAngle = (slice.value / totalValue) * 360f
                        val animatedSweepAngle =
                            (sweepAngle - style.sliceSpacing.toPx()).coerceAtLeast(0f) * animationProgress.value

                        val path =
                            Path().apply {
                                addArc(oval, startAngle, animatedSweepAngle)
                            }

                        drawPath(
                            path = path,
                            color = slice.color,
                            style = Stroke(width = strokeWidthPx, cap = style.strokeCap),
                        )

                        startAngle += sweepAngle
                    }
                }

                is HaloChartStyle.HaloPieChartStyle.HaloPieChartFilledStyle -> {
                    // Define the geometry for the main pie slices
                    val pieRect = Rect(Offset.Zero, size)

                    // Scale the geometry for the background circle
                    val backgroundSize = pieRect.size * style.backgroundScale
                    val backgroundTopLeft = center - Offset(backgroundSize.width / 2, backgroundSize.height / 2)
                    val backgroundRect = Rect(backgroundTopLeft, backgroundSize)

                    // Draw the background as a filled 360-degree arc (a circle)
                    drawArc(
                        color = style.backgroundColor,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = true,
                        topLeft = backgroundRect.topLeft,
                        size = backgroundRect.size,
                    )

                    data.slices.forEach { slice ->
                        val sweepAngle = (slice.value / totalValue) * 360f
                        val animatedSweepAngle = (sweepAngle - style.spacing.toPx()).coerceAtLeast(0f) * animationProgress.value

                        val path =
                            Path().apply {
                                moveTo(center.x, center.y)
                                arcTo(
                                    rect = pieRect,
                                    startAngleDegrees = startAngle,
                                    sweepAngleDegrees = animatedSweepAngle,
                                    forceMoveTo = false,
                                )
                                close()
                            }

                        drawPath(path = path, color = slice.color)

                        startAngle += sweepAngle
                    }
                }
            }
        }
    }
}
