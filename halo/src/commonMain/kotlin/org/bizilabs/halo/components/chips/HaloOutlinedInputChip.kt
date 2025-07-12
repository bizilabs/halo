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
 * A composable that displays an outlined input chip with optional icons and a trailing action.
 *
 * This chip variant is used for user-editable content like tags or filters, styled with an
 * outlined container. It uses [ChipMode.OUTLINED] and [ChipType.INPUT] by default.
 *
 * The chip does not respond to clicks on the chip body — only the trailing icon (if provided)
 * can trigger actions, typically used for removal or editing.
 *
 * @param modifier Modifier to apply to the chip container.
 * @param colors Optional [HaloChipColors] to define styling for different states (default, focused, disabled).
 * @param onClickTrailingIcon Optional lambda invoked when the trailing icon is clicked (e.g., to remove the chip).
 * @param leadingIcon Optional [ImageVector] shown before the text.
 * @param trailingIcon Optional [ImageVector] shown after the text, often used for the delete/remove icon.
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with a 10.dp corner radius.
 * @param selected Whether the chip is visually marked as selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled and interactive. Defaults to `true`.
 * @param text The label text displayed inside the chip.
 *
 * ### Example Usage
 * ```
 * HaloOutlinedInputChip(
 *     text = "UX",
 *     leadingIcon = Icons.Default.Tag,
 *     trailingIcon = Icons.Default.Close,
 *     onClickTrailingIcon = { /* Handle removal */ }
 * )
 * ```
 */
@Composable
fun HaloOutlinedInputChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    selected: Boolean = false,
    enabled: Boolean = true,
    text: String,
) {
    HaloBaseChip(
        modifier = modifier,
        onClickChip = null,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isSelected = selected,
        colors = colors,
        onClickTrailingIcon = onClickTrailingIcon,
        shape = shape,
        enabled = enabled,
        chipMode = ChipMode.OUTLINED,
        chipType = ChipType.INPUT,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
