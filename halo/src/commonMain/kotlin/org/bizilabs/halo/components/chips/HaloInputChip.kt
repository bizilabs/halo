package org.bizilabs.halo.components.chips

import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.HaloText

/**
 * A composable that displays a filled input chip with optional icons and a removable action.
 *
 * This chip is designed for use cases like user-generated tags, filters, or selections that can
 * be dismissed or edited. It uses [ChipMode.FILLED] and [ChipType.INPUT] by default and supports
 * a trailing icon with a click action (e.g., to remove the chip).
 *
 * Unlike selection chips, this chip does not respond to chip-level click actions — only the trailing icon
 * can be interactive (if [onClickTrailingIcon] is provided).
 *
 * @param modifier Modifier to apply to the chip container.
 * @param colors Optional [HaloChipColors] to customize the chip's appearance across states.
 * @param ripple Optional [Indication] used to show visual feedback (e.g., ripple) when the trailing icon is clicked.
 *               If `null`, falls back to [LocalIndication.current], typically defined by the theme.
 * @param onClickTrailingIcon Optional lambda triggered when the trailing icon is clicked (e.g., to remove the chip).
 * @param leadingIcon Optional [ImageVector] displayed before the chip's text.
 * @param trailingIcon Optional [ImageVector] displayed after the chip's text, typically used for removal.
 * @param shape The shape of the chip. Defaults to [RoundedCornerShape] with a 10.dp corner radius.
 * @param selected Whether the chip is currently in a selected state. Defaults to `false`.
 * @param enabled Whether the chip is enabled and interactive. Defaults to `true`.
 * @param text The label text displayed inside the chip.
 *
 * ### Example Usage
 * ```
 * HaloInputChip(
 *     text = "Design",
 *     leadingIcon = Icons.Default.Tag,
 *     trailingIcon = Icons.Default.Close,
 *     ripple = rememberRipple(), // Optional ripple customization
 *     onClickTrailingIcon = { /* Remove chip */ }
 * )
 * ```
 */
@Composable
fun HaloInputChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors? = null,
    ripple: Indication? = null,
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
        ripple = ripple,
        onClickTrailingIcon = onClickTrailingIcon,
        shape = shape,
        enabled = enabled,
        chipType = ChipType.INPUT,
        chipMode = ChipMode.FILLED,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
