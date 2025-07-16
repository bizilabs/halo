package org.bizilabs.halo.components.chips

import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText

/**
 * A composable that displays an outlined selection chip with optional icons and a text label.
 *
 * This is a high-level component built on top of [HaloBaseChip] with the [ChipMode.OUTLINED]
 * and [ChipType.SELECTION] presets applied. It supports leading and trailing icons, selection state,
 * disabled state, and a click action for the chip itself (trailing icon is not clickable in this variant).
 *
 * A custom ripple effect can be passed via [ripple], or a contextually styled ripple will be used
 * when the chip is selected.
 *
 * @param modifier Modifier to apply to the chip container.
 * @param colors Optional [HaloChipColors] used to define the chip's visual styling across different states.
 * @param ripple Optional [Indication] used to show visual feedback (e.g., ripple) when the chip is clicked.
 *               If `null`, a default styled ripple is used based on selection state and theming.
 * @param onClick Optional lambda triggered when the chip is clicked.
 * @param leadingIcon Optional [ImageVector] displayed at the start of the chip.
 * @param trailingIcon Optional [ImageVector] displayed at the end of the chip (not clickable in this variant).
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with a 10.dp radius.
 * @param selected Whether the chip is currently selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled and can be interacted with. Defaults to `true`.
 * @param text The text label to display inside the chip.
 *
 * ### Example Usage
 * ```
 * HaloOutlinedSelectionChip(
 *     text = "Option A",
 *     selected = true,
 *     ripple = rememberRipple(), // Optional override
 *     onClick = { /* Handle selection */ },
 *     leadingIcon = Icons.Default.Check
 * )
 * ```
 */
@Composable
fun HaloOutlinedSelectionChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors? = null,
    ripple: Indication? = null,
    onClick: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    selected: Boolean = false,
    enabled: Boolean = true,
    text: String,
) {
    val defaultRippleIndication =
        if (selected) {
            ripple(
                color = colors?.default?.content ?: HaloTheme.colorScheme.background.surface,
                bounded = true,
            )
        } else {
            ripple
        }

    HaloBaseChip(
        modifier = modifier,
        ripple = defaultRippleIndication,
        onClickChip = onClick,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isSelected = selected,
        colors = colors,
        onClickTrailingIcon = null,
        shape = shape,
        enabled = enabled,
        chipMode = ChipMode.OUTLINED,
        chipType = ChipType.SELECTION,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
