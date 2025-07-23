package org.bizilabs.halo.components.loaders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.extensions.shimmerEffect

/**
 * A lightweight composable that displays a shimmering placeholder inside a shaped container.
 *
 * This is typically used as a skeleton UI element to indicate that content is loading.
 * The shimmer animation gives users visual feedback while waiting for data to render.
 *
 * Internally, it clips the container to the given [shape] and applies a shimmer gradient
 * using [shimmerEffect].
 *
 * @param modifier Modifier applied to the shimmer container.
 * @param colors The gradient colors used in the shimmer animation. Defaults to subtle variations
 * of `onSurface` to match neutral loading states.
 * @param shape The shape of the shimmer container. Defaults to [CircleShape], ideal for avatar placeholders.
 *
 * Usage:
 * ```
 * HaloShimmerCard(
 *     modifier = Modifier.size(40.dp),
 *     shape = RoundedCornerShape(8.dp)
 * )
 * ```
 */
@Composable
fun HaloShimmerBox(
    modifier: Modifier = Modifier,
    colors: List<Color> =
        listOf(
            HaloTheme.colorScheme.content.strong
                .copy(0.1f),
            HaloTheme.colorScheme.content.strong
                .copy(0.7f),
            HaloTheme.colorScheme.content.strong
                .copy(0.1f),
        ),
    shape: Shape = CircleShape,
) {
    Box(modifier = modifier.clip(shape).shimmerEffect(colors))
}

/**
 * A lightweight, themed circular progress indicator centered within its container.
 *
 * This is a convenience wrapper around [CircularProgressIndicator] with sensible defaults
 * based on the Halo design system. It ensures the spinner is always centered and styled
 * consistently across components.
 *
 * @param strokeWidth The thickness of the indicator's stroke. Defaults to 1.dp for a minimal look.
 * @param color The color of the indicator. Defaults to `onSurface` from the Halo theme.
 *
 * Usage:
 * ```
 * HaloCircularProgressIndicator()
 * ```
 */
@Composable
fun HaloCircularProgressIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 1.dp,
    color: Color = HaloTheme.colorScheme.content.strong,
    trackColor: Color = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            strokeWidth = 1.dp,
            color = color,
            trackColor = trackColor,
            strokeCap = strokeCap,
        )
    }
}
