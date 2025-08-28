package org.bizilabs.halo.components.chips

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.components.HaloSurface

/**
 * A low-level composable used to build customizable chip components with optional icons, color states,
 * and interaction handling. It provides support for outlined or filled styles and different chip types
 * such as selection and input.
 *
 * This function handles:
 * - Dynamic visual state changes (enabled, disabled, selected)
 * - Optional icons on either side
 * - Custom shapes and container colors
 * - Ripple effects and interaction tracking
 * - Contextual content coloring for theme consistency
 *
 * @param modifier Modifier applied to the chip container.
 * @param enabled Whether the chip is enabled for interaction. Defaults to `true`.
 * @param colors Required [HaloChipColors] configuration that defines chip colors for different states
 *               (default, focused/selected, and disabled). Use [HaloChipDefaults.chipColors] for theme-aligned defaults.
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with 10.dp corner radius.
 * @param isSelected Whether the chip is currently selected. Affects visual styling, especially in filled mode.
 * @param chipMode Defines the visual style of the chip: [ChipMode.FILLED] or [ChipMode.OUTLINED].
 * @param chipType Defines the behavioral style of the chip: [ChipType.SELECTION] or [ChipType.INPUT].
 * @param ripple Optional [Indication] used to show interaction feedback (e.g., ripple) on click.
 *               If null, falls back to [LocalIndication.current], typically set by the theme.
 * @param onClickChip Optional lambda triggered when the chip is clicked. Only applies when [chipType] is [ChipType.SELECTION] and [enabled] is `true`.
 * @param onClickTrailingIcon Optional lambda triggered when the trailing icon is clicked. Only applies when [chipType] is [ChipType.INPUT] and [enabled] is `true`.
 * @param leadingIcon Optional [ImageVector] shown before the content.
 * @param trailingIcon Optional [ImageVector] shown after the content.
 * @param interactionSource Used to track user interaction state. Defaults to a new [MutableInteractionSource].
 * @param content The content displayed inside the chip, composable and theme-aware (e.g., text with the resolved content color).
 *
 * ### Example Usage
 * ```
 * HaloBaseChip(
 *     isSelected = true,
 *     chipMode = ChipMode.OUTLINED,
 *     chipType = ChipType.SELECTION,
 *     colors = HaloChipDefaults.chipColors(),
 *     ripple = rememberRipple(),
 *     onClickChip = { /* Handle click */ },
 *     leadingIcon = Icons.Default.Star,
 *     trailingIcon = Icons.Default.Close
 * ) {
 *     Text("Filter")
 * }
 * ```
 */

internal enum class ChipMode {
    FILLED,
    OUTLINED,
}

internal enum class ChipType {
    SELECTION,
    INPUT,
}

data class HaloChipColors(
    val default: HaloColor,
    val focused: HaloColor,
    val disabled: HaloColor,
)

object HaloChipDefaults {
    @Composable
    fun chipColors(
        default: HaloColor =
            HaloColor(
                container = HaloTheme.colorScheme.background.low,
                content = HaloTheme.colorScheme.content.strong,
                border =
                    HaloTheme.colorScheme.content.stronger
                        .copy(0.15f),
            ),
        selected: HaloColor =
            HaloColor(
                container = HaloTheme.colorScheme.content.strong,
                content = HaloTheme.colorScheme.background.low,
                border =
                    HaloTheme.colorScheme.content.stronger
                        .copy(0.25f),
            ),
        disabled: HaloColor =
            HaloColor(
                container = HaloTheme.colorScheme.disabled.container,
                content = HaloTheme.colorScheme.disabled.content,
                border =
                    HaloTheme.colorScheme.content.stronger
                        .copy(0.1f),
            ),
    ): HaloChipColors =
        HaloChipColors(
            default = default,
            focused = selected,
            disabled = disabled,
        )
}

@Composable
internal fun rememberSelectedRippleIndication(
    selected: Boolean,
    color: Color,
): Indication? =
    remember(selected, color) {
        if (selected) ripple(color = color, bounded = true) else null
    }

@Composable
internal fun HaloBaseChip(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: HaloChipColors = HaloChipDefaults.chipColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    isSelected: Boolean = false,
    chipMode: ChipMode = ChipMode.FILLED,
    chipType: ChipType = ChipType.SELECTION,
    ripple: Indication? = null,
    onClickChip: (() -> Unit)? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = MutableInteractionSource(),
    content: @Composable () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue =
            when (enabled) {
                true -> {
                    when (chipMode) {
                        ChipMode.FILLED ->
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors.focused.content
                            } else {
                                colors.default.content
                            }

                        ChipMode.OUTLINED -> {
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors.focused.content
                            } else {
                                colors.default.content
                            }
                        }
                    }
                }

                false -> {
                    when (chipMode) {
                        ChipMode.FILLED -> {
                            colors.disabled.content
                        }

                        ChipMode.OUTLINED ->
                            colors.disabled.content
                    }
                }
            },
    )

    val containerColor by animateColorAsState(
        targetValue =
            when (enabled) {
                true -> {
                    when (chipMode) {
                        ChipMode.FILLED -> {
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors.focused.container
                            } else {
                                colors.default.container
                            }
                        }

                        ChipMode.OUTLINED -> {
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors.focused.container
                            } else {
                                colors.default.container
                            }
                        }
                    }
                }

                false -> {
                    when (chipMode) {
                        ChipMode.FILLED -> {
                            colors.disabled.container
                        }

                        ChipMode.OUTLINED -> {
                            colors.disabled.container
                        }
                    }
                }
            },
    )

    val outlineColor by animateColorAsState(
        targetValue =
            when (enabled) {
                true -> {
                    if (isSelected && chipType == ChipType.SELECTION) {
                        colors.focused.border
                    } else {
                        colors.default.border
                    }
                }

                false ->
                    colors.disabled.border
            },
    )

    val isChipClickable = onClickChip != null && enabled

    HaloSurface(
        modifier =
            modifier
                .clip(shape)
                .then(
                    if (isChipClickable && chipType == ChipType.SELECTION) {
                        Modifier.clickable(
                            onClick = onClickChip,
                            interactionSource = interactionSource,
                            indication = ripple ?: LocalIndication.current,
                        )
                    } else {
                        Modifier
                    },
                ),
        shape = shape,
        color = containerColor,
        border =
            if (chipMode == ChipMode.OUTLINED) {
                BorderStroke(
                    width = 1.dp,
                    color = outlineColor,
                )
            } else {
                null
            },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(12.dp))
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = contentColor,
                )
            }
            ProvideContentColor(contentColor) {
                content()
            }
            trailingIcon?.let {
                Icon(
                    modifier =
                        Modifier
                            .then(
                                if (chipType == ChipType.INPUT && enabled) {
                                    Modifier
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = onClickTrailingIcon ?: {},
                                            interactionSource = interactionSource,
                                            indication = ripple ?: LocalIndication.current,
                                        )
                                } else {
                                    Modifier
                                },
                            ).size(16.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = contentColor,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
