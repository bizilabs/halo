package org.bizilabs.halo.charts.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import org.bizilabs.halo.charts.data.HaloChartPoint

/**
 * Generates a smooth, curved path for a set of points using cubic bezier interpolation.
 */
internal fun generatePath(
    points: List<HaloChartPoint>,
    toPxX: (Float) -> Float,
    toPxY: (Float) -> Float,
    progress: Float,
): Path {
    val path = Path()
    if (points.isEmpty()) return path

    val pathPoints = points.map { Offset(toPxX(it.x), toPxY(it.y)) }
    // Animate path drawing by taking a sublist based on progress
    val subPathPoints = pathPoints.take((pathPoints.size * progress).toInt().coerceAtLeast(0))

    if (subPathPoints.size < 2) {
        if (subPathPoints.isNotEmpty()) {
            val firstPoint = subPathPoints.first()
            path.moveTo(firstPoint.x, firstPoint.y)
        }
        return path
    }

    var previousPoint = subPathPoints.first()
    path.moveTo(previousPoint.x, previousPoint.y)

    for (i in 1 until subPathPoints.size) {
        val currentPoint = subPathPoints[i]
        // Using cubic bezier for smooth curves
        val controlPoint1 = Offset((currentPoint.x + previousPoint.x) / 2f, previousPoint.y)
        val controlPoint2 = Offset((currentPoint.x + previousPoint.x) / 2f, currentPoint.y)
        path.cubicTo(
            controlPoint1.x,
            controlPoint1.y,
            controlPoint2.x,
            controlPoint2.y,
            currentPoint.x,
            currentPoint.y,
        )
        previousPoint = currentPoint
    }
    return path
}
