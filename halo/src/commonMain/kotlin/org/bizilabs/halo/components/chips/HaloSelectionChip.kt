package org.bizilabs.halo.components.chips

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.HaloText

/**
 * A composable function that displays a customizable selection chip with optional icons and click actions.
 *
 * This chip component is built on top of [HaloBaseChip] and supports leading and trailing icons,
 * selection state, disabled state, and shape customization.
 *
 * @param modifier Modifier to be applied to the chip.
 * @param colors Optional [HaloChipColors] to define the chip's color styling.
 * @param onClick Optional lambda triggered when the chip itself is clicked.
 * @param onClickTrailingIcon Optional lambda triggered when the trailing icon is clicked (if present).
 * @param leadingIcon Optional [ImageVector] to be displayed at the start of the chip.
 * @param trailingIcon Optional [ImageVector] to be displayed at the end of the chip.
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with 10.dp radius.
 * @param selected Whether the chip is currently selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled for interaction. Defaults to `false`.
 * @param text The text label displayed inside the chip.
 *
 * ### Example
 * ```
 * SelectionChip(
 *     text = "Option A",
 *     selected = true,
 *     onClick = { /* handle click */ },
 *     leadingIcon = Icons.Default.Check
 * )
 * ```
 */

@Composable
fun HaloSelectionChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors? = null,
    onClick: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    selected: Boolean = false,
    enabled: Boolean = true,
    text: String,
) {
    HaloBaseChip(
        modifier = modifier,
        onClickChip = onClick,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        selected = selected,
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
