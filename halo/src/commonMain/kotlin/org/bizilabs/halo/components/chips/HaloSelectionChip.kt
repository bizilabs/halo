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
import org.bizilabs.halo.components.HaloText

/**
 * A composable that displays a **filled selection chip** with a text label and optional icons.
 *
 * This high-level component wraps [HaloBaseChip] with [ChipMode.FILLED] and [ChipType.SELECTION] presets.
 * It is typically used for filter chips, toggles, or selectable items that reflect a chosen state.
 *
 * The chip body is clickable via [onClick], while the trailing icon (if provided) is decorative only.
 * A default ripple is shown when the chip is selected, unless a custom [ripple] is provided.
 *
 * ### Key Features:
 * - Filled style with selection visual feedback
 * - Themed and customizable appearance
 * - Ripple support based on selection and theming
 * - Optional leading and trailing icons
 *
 * @param modifier Modifier applied to the chip container.
 * @param colors Required [HaloChipColors] used to style the chip across default, focused (selected), and disabled states.
 *               Use [HaloChipDefaults.chipColors] for default theme-aligned values.
 * @param ripple Optional [Indication] shown when the chip is clicked. If not provided, uses a default ripple styled by selection state.
 * @param onClick Callback triggered when the chip is clicked.
 * @param leadingIcon Optional [ImageVector] displayed before the chip text.
 * @param trailingIcon Optional [ImageVector] displayed after the chip text (non-clickable).
 * @param shape The shape of the chip container. Defaults to [RoundedCornerShape] with 10.dp radius.
 * @param selected Whether the chip is currently selected. Defaults to `false`.
 * @param enabled Whether the chip is enabled for interaction. Defaults to `true`.
 * @param text The label text shown inside the chip.
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
 *
 * @see ChipMode
 * @see ChipType
 * @see HaloChipDefaults
 * @see HaloBaseChip
 */
@Composable
fun HaloSelectionChip(
    modifier: Modifier = Modifier,
    colors: HaloChipColors = HaloChipDefaults.chipColors(),
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
        chipType = ChipType.SELECTION,
        chipMode = ChipMode.FILLED,
    ) {
        HaloText(
            modifier = Modifier.padding(8.dp),
            text = text,
        )
    }
}
