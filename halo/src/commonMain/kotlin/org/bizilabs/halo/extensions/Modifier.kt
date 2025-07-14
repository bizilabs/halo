package org.bizilabs.halo.extensions

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme

/**
 * Adds a dashed border around a Composable component.
 *
 * @param color The color of the dashed border.
 * @param shape The shape of the dashed border.
 * @param strokeWidth The width of the dashed border stroke.
 * @param dashLength The length of each dash in the border.
 * @param gapLength The length of the gap between each dash.
 * @param cap The style of the stroke caps at the ends of dashes.
 *
 * @return A Modifier with the dashed border applied.
 */
fun Modifier.dashedBorder(
    color: Color,
    shape: Shape = RectangleShape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round,
) = dashedBorder(brush = SolidColor(color), shape, strokeWidth, dashLength, gapLength, cap)

/**
 * Adds a dashed border around a Composable component.
 *
 * @param brush The brush of the dashed border.
 * @param shape The shape of the dashed border.
 * @param strokeWidth The width of the dashed border stroke.
 * @param dashLength The length of each dash in the border.
 * @param gapLength The length of the gap between each dash.
 * @param cap The style of the stroke caps at the ends of dashes.
 *
 * @return A Modifier with the dashed border applied.
 */
fun Modifier.dashedBorder(
    brush: Brush,
    shape: Shape = RectangleShape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round,
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, density = this)

    val dashedStroke =
        Stroke(
            cap = cap,
            width = strokeWidth.toPx(),
            pathEffect =
                PathEffect.dashPathEffect(
                    intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx()),
                ),
        )

    drawContent()

    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = brush,
    )
}

/**
 * Applies a shimmering gradient animation to the composable's background, simulating a loading placeholder.
 *
 * This is useful for skeleton UIs while waiting for content to load. The shimmer effect moves a gradient
 * across the component horizontally using an infinite transition.
 *
 * @param colors A list of colors to be used in the shimmer gradient. By default, it uses subtle variations
 * of `onSurface` with low opacity to produce a neutral shimmer.
 *
 * @return A [Modifier] with a shimmering background effect applied.
 *
 * Usage:
 * ```
 * Box(
 *     modifier = Modifier
 *         .size(80.dp)
 *         .shimmerEffect()
 * )
 * ```
 */
@Composable
fun Modifier.shimmerEffect(
    colors: List<Color> =
        listOf(
            HaloTheme.colorScheme.background.onSurface
                .copy(0.1f),
            HaloTheme.colorScheme.background.onSurface
                .copy(0.7f),
            HaloTheme.colorScheme.background.onSurface
                .copy(0.1f),
        ),
): Modifier {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec =
            infiniteRepeatable(
                animation = tween(1000),
            ),
        label = "",
    )

    return background(
        brush =
            Brush.linearGradient(
                colors = colors,
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat()),
            ),
    ).onGloballyPositioned {
        size = it.size
    }
}
