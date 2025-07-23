package org.bizilabs.halo.components.chips

import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.HaloText

/**
 * A composable that displays an **outlined input chip** with optional icons and a trailing action.
 *
 * Typically used for editable or removable content like tags, filters, or categories, this chip
 * uses [ChipMode.OUTLINED] and [ChipType.INPUT] by default. It presents a transparent background
 * with a border and supports a trailing icon that can trigger actions (e.g., delete).
 *
 * Unlike selection chips, the main body of the chip is not clickable — only the trailing icon is interactive,
 * assuming [onClickTrailingIcon] is provided.
 *
 * ### Key Features:
 * - Outlined style with transparent background
 * - Leading/trailing icon support
 * - Themed visual states (default, selected, disabled)
 * - Optional ripple for the trailing icon
 *
 * @param modifier Modifier applied to the chip container.
 * @param colors Required [HaloChipColors] for visual state styling. By default, the container is transparent to reflect an outlined look.
 *               Use [HaloChipDefaults.chipColors] and override as needed.
 * @param ripple Optional [Indication] for ripple feedback when the trailing icon is clicked.
 *               Falls back to [LocalIndication.current] if not set.
 * @param onClickTrailingIcon Optional callback triggered when the trailing icon is clicked.
 * @param leadingIcon Optional [ImageVector] shown before the chip's label.
 * @param trailingIcon Optional [ImageVector] shown after the label, typically for removing the chip.
 * @param shape The shape of the chip container. Defaults to [RoundedCornerShape] with 10.dp radius.
 * @param selected Whether the chip is visually marked as selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled and interactive. Defaults to `true`.
 * @param text The text label shown inside the chip.
 *
 * ### Example
 * ```
 * HaloOutlinedInputChip(
 *     text = "UX",
 *     leadingIcon = Icons.Default.Tag,
 *     trailingIcon = Icons.Default.Close,
 *     ripple = rememberRipple(),
 *     onClickTrailingIcon = { /* Remove chip */ }
 * )
 * ```
 *
 * @see ChipMode
 * @see ChipType
 * @see HaloChipDefaults
 * @see HaloChipColors
 */
@Composable
fun HaloOutlinedInputChip(
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
        chipMode = ChipMode.OUTLINED,
        chipType = ChipType.INPUT,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
