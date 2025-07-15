package org.bizilabs.halo.components.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import org.bizilabs.halo.base.ComponentSize

/**
 * A composable icon button that provides a clickable surface for icons, built on top of [BaseIconButton].
 *
 * `HaloIconButton` is used for compact, icon-only actions (e.g., "Edit", "Close", "Share") and is size-aware via [ComponentSize].
 * It supports customization of colors, shape, internal padding, and interactions, while maintaining accessibility and minimum touch targets.
 *
 * ### Features:
 * - Built-in sizing using [ComponentSize] (e.g., ExtraSmall to ExtraLarge)
 * - Support for custom shapes and colors via [Shape] and [IconButtonColors]
 * - Optional internal padding via [containerPadding]
 * - Ripple effect and interaction tracking via [MutableInteractionSource]
 * - Fully themeable and composable content
 *
 * @param modifier Modifier applied to the icon button.
 * @param enabled Whether the button is clickable. Defaults to `true`.
 * @param colors Optional color configuration for enabled and disabled states using [IconButtonColors].
 * @param interactionSource Optional interaction source to observe touch events.
 * @param shape Optional shape of the button container. Defaults to a circle if not specified.
 * @param size The size of the button. Defaults to [ComponentSize.Small].
 * @param containerPadding Optional padding applied inside the button's container, around the icon.
 * @param onClick Callback triggered when the icon button is clicked.
 * @param content The icon or any composable content to display inside the button.
 *
 * ### Usage:
 * ```
 * HaloIconButton(
 *     onClick = { /* Do something */ },
 *     size = ComponentSize.Medium,
 *     containerPadding = PaddingValues(8.dp)
 * ) {
 *     Icon(Icons.Default.Favorite, contentDescription = "Favorite")
 * }
 * ```
 */
@Composable
fun HaloIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape? = null,
    size: ComponentSize = ComponentSize.Small,
    containerPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    BaseIconButton(
        modifier =
            modifier
                .size(resolveIconButtonSizeDp(size)),
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        shape = shape,
        containerPadding = containerPadding,
        onClick = onClick,
        content = content,
    )
}
