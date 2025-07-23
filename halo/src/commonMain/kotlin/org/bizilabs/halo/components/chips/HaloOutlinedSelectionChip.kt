package org.bizilabs.halo.components.chips

import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.HaloText

/**
 * A composable that displays an **outlined selection chip** with a text label and optional icons.
 *
 * This is a high-level wrapper over [HaloBaseChip], preconfigured with [ChipMode.OUTLINED] and
 * [ChipType.SELECTION]. It is suitable for use in filter groups, toggleable options, and category pickers
 * where visual selection and theming consistency matter.
 *
 * The chip itself is clickable (via [onClick]), but the trailing icon is purely decorative in this variant.
 *
 * ### Key Features:
 * - Outlined style with transparent background
 * - Supports visual selection state and theming
 * - Leading and trailing icon support
 * - Ripple effect applied to the chip body only
 *
 * @param modifier Modifier applied to the chip container.
 * @param colors Required [HaloChipColors] for defining appearance across states. Defaults to transparent container for outlined style.
 *               Use [HaloChipDefaults.chipColors] and override as needed.
 * @param ripple Optional [Indication] used to provide visual feedback on chip click.
 *               If not provided, a selection-aware ripple is used automatically.
 * @param onClick Callback triggered when the chip is clicked.
 * @param leadingIcon Optional [ImageVector] shown at the start of the chip.
 * @param trailingIcon Optional [ImageVector] shown at the end of the chip (not clickable).
 * @param shape Shape of the chip container. Defaults to [RoundedCornerShape] with 10.dp radius.
 * @param selected Whether the chip appears visually selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled and can respond to interaction. Defaults to `true`.
 * @param text The text label to be displayed inside the chip.
 *
 * ### Example
 * ```
 * HaloOutlinedSelectionChip(
 *     text = "Option A",
 *     selected = true,
 *     ripple = rememberRipple(), // Optional
 *     onClick = { /* Handle selection */ },
 *     leadingIcon = Icons.Default.Check
 * )
 * ```
 *
 * @see ChipMode
 * @see ChipType
 * @see HaloChipDefaults
 * @see HaloBaseChip
 */
@Composable
fun HaloOutlinedSelectionChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors =
        HaloChipDefaults.chipColors(
            default =
                HaloChipDefaults.chipColors().default.copy(
                    container = Color.Transparent,
                ),
            disabled =
                HaloChipDefaults.chipColors().disabled.copy(
                    container = Color.Transparent,
                ),
        ),
    ripple: Indication? = null,
    onClick: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    selected: Boolean = false,
    enabled: Boolean = true,
    text: String,
) {
    val rippleColor =
        if (selected) {
            colors.focused.content
        } else {
            colors.default.content
        }
    val defaultRippleIndication = ripple ?: rememberSelectedRippleIndication(selected = selected, color = rippleColor)

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
