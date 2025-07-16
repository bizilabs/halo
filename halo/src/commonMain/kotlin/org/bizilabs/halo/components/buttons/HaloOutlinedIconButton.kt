package org.bizilabs.halo.components.buttons

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.state.HaloBorder

/**
 * A composable icon button that displays an outlined (bordered) clickable surface for icons,
 * extending [BaseIconButton] with a default border.
 *
 * `HaloOutlinedIconButton` is ideal for actions that need a subtle yet clear visual distinction without a filled background.
 * Common use cases include secondary or low-priority actions such as “More options,” “Edit,” or "Refresh".
 *
 * ### Features:
 * - Outlined by default using [HaloBorder] (customizable)
 * - Size-aware via [ComponentSize] (e.g., ExtraSmall to ExtraLarge)
 * - Supports optional shape and content padding via [Shape] and [containerPadding]
 * - Ripple effect and interaction tracking via [MutableInteractionSource]
 * - Fully theme-aware and composable content
 *
 * @param modifier Modifier applied to the icon button.
 * @param enabled Whether the button is clickable. Defaults to `true`.
 * @param colors Optional color configuration for enabled and disabled states using [IconButtonColors].
 * @param interactionSource Optional interaction source to observe user interactions.
 * @param shape Optional shape of the button container. Defaults to a circle if not specified.
 * @param border Border style applied to the icon button. Defaults to a 1.dp border using the theme's `onSurface` color.
 * @param size The size of the button. Defaults to [ComponentSize.Small].
 * @param containerPadding Padding applied inside the button's container around the icon.
 * @param ripple Optional [Indication] used to show visual feedback (e.g., ripple effect) when the button is clicked.
 *               If `null`, the theme's default indication is used.
 * @param onClick Callback triggered when the icon button is clicked.
 * @param content The icon or any composable content to display inside the button.
 *
 * ### Usage:
 * ```
 * HaloOutlinedIconButton(
 *     onClick = { /* Refresh */ },
 *     size = ComponentSize.Medium,
 *     containerPadding = PaddingValues(8.dp),
 *     ripple = rememberRipple() // Optional: customize the ripple effect
 * ) {
 *     Icon(Icons.Default.Refresh, contentDescription = "Refresh")
 * }
 * ```
 */
@Composable
fun HaloOutlinedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape? = null,
    border: HaloBorder = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.content.strong),
    size: ComponentSize = ComponentSize.Small,
    containerPadding: PaddingValues = PaddingValues(),
    ripple: Indication? = null,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val defaultIconColors =
        IconButtonColors(
            default =
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.content.strong,
                    border = HaloTheme.colorScheme.content.strong,
                ),
            disabled =
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.disabled.content,
                    border = HaloTheme.colorScheme.disabled.container,
                ),
        )

    BaseIconButton(
        modifier =
            modifier
                .size(resolveIconButtonSizeDp(size)),
        enabled = enabled,
        colors = colors ?: defaultIconColors,
        interactionSource = interactionSource,
        border = border,
        shape = shape,
        containerPadding = containerPadding,
        ripple = ripple,
        onClick = onClick,
        content = content,
    )
}
