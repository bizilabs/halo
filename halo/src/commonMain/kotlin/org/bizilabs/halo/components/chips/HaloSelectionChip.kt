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
 * A composable function that displays a customizable selection chip with optional icons and click actions.
 *
 * This chip component is built on top of [HaloBaseChip] and uses [ChipMode.FILLED] and [ChipType.SELECTION] by default.
 * It supports leading and trailing icons, selection state, disabled state, ripple customization, and shape styling.
 *
 * When the chip is selected, a default ripple is applied using the chip’s color scheme unless a custom [ripple] is provided.
 *
 * @param modifier Modifier to be applied to the chip.
 * @param colors Optional [HaloChipColors] to define the chip's color styling across different states.
 * @param ripple Optional [Indication] used to show visual feedback (e.g., ripple) when the chip is clicked.
 *               If `null`, a default styled ripple is used based on selection state and theming.
 * @param onClick Optional lambda triggered when the chip itself is clicked.
 * @param leadingIcon Optional [ImageVector] to be displayed at the start of the chip.
 * @param trailingIcon Optional [ImageVector] to be displayed at the end of the chip (non-clickable in this variant).
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with 10.dp radius.
 * @param selected Whether the chip is currently selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled for interaction. Defaults to `true`.
 * @param text The text label displayed inside the chip.
 *
 * ### Example
 * ```
 * HaloSelectionChip(
 *     text = "Option A",
 *     selected = true,
 *     ripple = rememberRipple(), // Optional custom ripple
 *     onClick = { /* handle click */ },
 *     leadingIcon = Icons.Default.Check
 * )
 * ```
 */
@Composable
fun HaloSelectionChip(
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
        chipType = ChipType.SELECTION,
        chipMode = ChipMode.FILLED,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
