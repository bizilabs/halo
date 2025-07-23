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
 * A filled input chip composable that displays a labeled chip with optional leading and trailing icons.
 * Typically used for tags, filters, or user-generated selections that can be dismissed or removed.
 *
 * This chip uses [ChipMode.FILLED] and [ChipType.INPUT] by default. It does not handle chip-level clicks—
 * only the trailing icon can trigger an action via [onClickTrailingIcon].
 *
 * ### Key Features:
 * - Leading and trailing icon support
 * - Themed and customizable appearance using [HaloChipColors]
 * - Ripple effect for trailing icon interaction
 * - Supports selected and disabled visual states
 *
 * @param modifier Modifier to apply to the chip container.
 * @param colors Required [HaloChipColors] defining chip appearance for default, selected, and disabled states.
 *               Use [HaloChipDefaults.chipColors] for theme-aligned colors.
 * @param ripple Optional [Indication] used for ripple feedback when the trailing icon is clicked.
 *               Defaults to [LocalIndication.current] if not provided.
 * @param onClickTrailingIcon Optional callback triggered when the trailing icon is clicked (e.g., to remove the chip).
 * @param leadingIcon Optional [ImageVector] displayed before the chip text.
 * @param trailingIcon Optional [ImageVector] displayed after the chip text.
 * @param shape Shape of the chip container. Defaults to [RoundedCornerShape] with a 10.dp radius.
 * @param selected Whether the chip appears in a selected visual state. Defaults to `false`.
 * @param enabled Whether the chip is enabled. Defaults to `true`.
 * @param text The label text displayed inside the chip.
 *
 * ### Example
 * ```
 * HaloInputChip(
 *     text = "Design",
 *     leadingIcon = Icons.Default.Tag,
 *     trailingIcon = Icons.Default.Close,
 *     ripple = rememberRipple(),
 *     onClickTrailingIcon = { /* Remove chip */ }
 * )
 * ```
 *
 * @see HaloChipColors
 * @see HaloBaseChip
 */
@Composable
fun HaloInputChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors = HaloChipDefaults.chipColors(),
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
