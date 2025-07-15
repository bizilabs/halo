package org.bizilabs.halo.components.chips

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
 * @param modifier Modifier applied to the chip container.
 * @param enabled Whether the chip is enabled for interaction. Defaults to `true`.
 * @param colors Optional [HaloChipColors] to define chip colors for different states (default, focused, disabled).
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with 10.dp corner radius.
 * @param selected Whether the chip is currently selected. Affects visual styling, especially in filled mode.
 * @param chipMode Defines the visual style of the chip: [ChipMode.FILLED] or [ChipMode.OUTLINED].
 * @param chipType Defines the behavioral style of the chip: [ChipType.SELECTION] or [ChipType.INPUT].
 * @param onClickChip Optional lambda triggered when the chip is clicked (only works when [enabled] is true and [chipType] is [ChipType.SELECTION]).
 * @param onClickTrailingIcon Optional lambda triggered when the trailing icon is clicked (only works when [chipType] is [ChipType.INPUT]).
 * @param leadingIcon Optional [ImageVector] shown before the content.
 * @param trailingIcon Optional [ImageVector] shown after the content.
 * @param interactionSource Used to track user interaction state. Defaults to a new [MutableInteractionSource].
 * @param content The content displayed inside the chip, provided with the computed [Color] used for text and icons.
 *
 * ### Example Usage
 * ```
 * HaloBaseChip(
 *     selected = true,
 *     chipMode = ChipMode.OUTLINED,
 *     chipType = ChipType.SELECTION,
 *     onClickChip = { /* Handle chip click */ },
 *     leadingIcon = Icons.Default.Star,
 *     trailingIcon = Icons.Default.Close
 * ) { contentColor ->
 *     Text("Chip Label", color = contentColor)
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

@Composable
internal fun HaloBaseChip(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: HaloChipColors? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    isSelected: Boolean = false,
    chipMode: ChipMode = ChipMode.FILLED,
    chipType: ChipType = ChipType.SELECTION,
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
                                colors?.default?.content
                                    ?: HaloTheme.colorScheme.background.surface
                            } else {
                                colors?.default?.content
                                    ?: HaloTheme.colorScheme.content.strong
                            }

                        ChipMode.OUTLINED -> {
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors?.default?.content
                                    ?: HaloTheme.colorScheme.background.surface
                            } else {
                                colors?.default?.content
                                    ?: HaloTheme.colorScheme.content.strong
                            }
                        }
                    }
                }

                false -> {
                    when (chipMode) {
                        ChipMode.FILLED -> {
                            colors?.disabled?.content ?: HaloTheme.colorScheme.disabled.content
                        }

                        ChipMode.OUTLINED ->
                            colors?.disabled?.content ?: HaloTheme.colorScheme.disabled.content
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
                                colors?.default?.container
                                    ?: HaloTheme.colorScheme.content.strong
                            } else {
                                colors?.default?.container
                                    ?: HaloTheme.colorScheme.background.surface
                            }
                        }

                        ChipMode.OUTLINED -> {
                            if (isSelected && chipType == ChipType.SELECTION) {
                                colors?.default?.container
                                    ?: HaloTheme.colorScheme.content.strong
                            } else {
                                colors?.default?.container ?: Color.Transparent
                            }
                        }
                    }
                }

                false -> {
                    when (chipMode) {
                        ChipMode.FILLED -> {
                            colors?.disabled?.container ?: HaloTheme.colorScheme.disabled.container
                        }

                        ChipMode.OUTLINED -> {
                            colors?.disabled?.container ?: Color.Transparent
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
                        colors?.default?.border ?: HaloTheme.colorScheme.content.stronger
                            .copy(0.25f)
                    } else {
                        colors?.default?.border ?: HaloTheme.colorScheme.content.stronger
                            .copy(0.15f)
                    }
                }

                false ->
                    colors?.disabled?.border ?: HaloTheme.colorScheme.content.stronger
                        .copy(0.1f)
            },
    )

    val isChipClickable = onClickChip != null && enabled

    HaloSurface(
        modifier =
            modifier
                .clip(shape)
                .then(
                    if (isChipClickable && chipType == ChipType.SELECTION) {
                        Modifier.clickable(onClick = onClickChip)
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
                                            indication = LocalIndication.current,
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
