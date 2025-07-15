package org.bizilabs.halo.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.state.HaloBorder

/**
 * A low-level composable that provides the base layout and interaction behavior for an icon button.
 *
 * This function is intended for internal use by higher-level Halo icon button components.
 * It handles:
 * - Shape and size clipping
 * - Background and content coloring (enabled/disabled)
 * - Optional border application
 * - Ripple indication and click handling
 * - Content alignment
 * - Custom internal padding using [containerPadding]
 *
 * It respects minimum touch target requirements and follows Material interaction patterns,
 * ensuring a consistent and accessible user experience.
 *
 * @param modifier Modifier applied to the outer Box.
 * @param enabled Whether the button is interactive. Defaults to `true`.
 * @param colors Optional [IconButtonColors] instance to customize container and content colors for enabled/disabled states.
 * @param interactionSource Optional interaction source to observe user interactions (e.g., press, hover).
 * @param border Optional [HaloBorder] to apply a stroke around the button.
 * @param shape Optional shape of the button container. Defaults to [CircleShape] if not specified.
 * @param containerPadding Padding inside the button's container, applied around the content. Defaults to no padding.
 * @param onClick Callback triggered when the button is clicked.
 * @param content The composable content inside the button, typically an icon.
 */
@Composable
internal fun BaseIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors? = null,
    interactionSource: MutableInteractionSource? = null,
    border: HaloBorder? = null,
    shape: Shape? = null,
    containerPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val enabledContainerColor =
        colors?.default?.container ?: HaloTheme.colorScheme.content.stronger
            .copy(0.15f)
    val enabledContentColor = colors?.default?.content ?: HaloIconButtonDefaults.iconButtonColors().default.content
    val disabledContainerColor = colors?.disabled?.container ?: HaloIconButtonDefaults.iconButtonColors().disabled.container
    val disabledContentColor = colors?.disabled?.content ?: HaloIconButtonDefaults.iconButtonColors().disabled.content
    val containerColor = if (enabled) enabledContainerColor else disabledContainerColor
    val contentColor = if (enabled) enabledContentColor else disabledContentColor
    val containerShape = shape ?: CircleShape
    val borderModifier =
        if (border != null) {
            Modifier.border(width = border.width, color = border.color, shape = containerShape)
        } else {
            Modifier
        }

    Box(
        modifier =
            modifier
                .minimumInteractiveComponentSize()
                .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                .clip(containerShape)
                .background(color = containerColor)
                .then(borderModifier)
                .padding(containerPadding)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = ripple(),
                ),
        contentAlignment = Alignment.Center,
    ) {
        ProvideContentColor(contentColor, content = content)
    }
}

data class IconButtonColors(
    val default: HaloColor,
    val disabled: HaloColor,
)

internal fun resolveIconButtonSizeDp(size: ComponentSize): Dp =
    when (size) {
        ComponentSize.ExtraSmall -> 32.dp
        ComponentSize.Small -> 40.dp
        ComponentSize.Medium -> 48.dp
        ComponentSize.Large -> 56.dp
        ComponentSize.ExtraLarge -> 64.dp
    }

/**
 * Contains default values and helpers for configuring [HaloIconButton] and [HaloOutlinedIconButton].
 *
 * Use this object to access theme-aware color defaults for icon buttons in various states (enabled, disabled).
 * These defaults follow the Halo design system and ensure consistent appearance across your UI.
 *
 * ### Example:
 * ```
 * HaloIconButton(
 *     onClick = { /* Handle click */ },
 *     colors = HaloIconButtonDefaults.iconButtonColors()
 * ) {
 *     Icon(Icons.Default.Settings, contentDescription = "Settings")
 * }
 * ```
 */
object HaloIconButtonDefaults {
    /**
     * Returns a [IconButtonColors] instance that provides container, content, and border colors for
     * both enabled and disabled states of an icon button.
     *
     * All colors default to theme-derived values, ensuring visual consistency with the rest of the app.
     *
     * @param container Background color when the button is enabled.
     * @param content Foreground (icon) color when the button is enabled.
     * @param border Border color when the button is enabled.
     * @param disabledContainer Background color when the button is disabled.
     * @param disabledContent Foreground (icon) color when the button is disabled.
     * @param disabledBorder Border color when the button is disabled.
     *
     * @return [IconButtonColors] containing [HaloColor] definitions for default and disabled states.
     */
    @Composable
    fun iconButtonColors(
        container: Color =
            HaloTheme.colorScheme.content.stronger
                .copy(0.15f),
        content: Color = HaloTheme.colorScheme.content.strong,
        border: Color = HaloTheme.colorScheme.content.strong,
        disabledContainer: Color = HaloTheme.colorScheme.disabled.container,
        disabledContent: Color = HaloTheme.colorScheme.disabled.content,
        disabledBorder: Color = HaloTheme.colorScheme.disabled.border,
    ): IconButtonColors =
        IconButtonColors(
            default =
                HaloColor(
                    container = container,
                    content = content,
                    border = border,
                ),
            disabled =
                HaloColor(
                    container = disabledContainer,
                    content = disabledContent,
                    border = disabledBorder,
                ),
        )
}
