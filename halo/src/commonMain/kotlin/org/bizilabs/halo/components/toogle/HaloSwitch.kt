package org.bizilabs.halo.components.toogle

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor

/**
 * A Halo-themed wrapper around the Material [Switch] component, providing design-system–aligned
 * colors, shapes, and behavior for toggleable controls.
 *
 * This composable allows you to easily integrate switches that match the Halo design system,
 * while still supporting full customization of colors, state handling, and optional thumb content.
 *
 * ### Key Features:
 * - **Theme-aware styling:** Defaults use [HaloSwitchDefaults.switchColors] to ensure consistent
 *   appearance with the rest of the Halo theme.
 * - **State-specific colors:** Automatically handles `default`, `checked`, and `disabled` visual
 *   states with independent container (track), content (thumb), and border colors.
 * - **Optional customization:** Override default colors with a [SwitchColors] instance or provide
 *   custom thumb content.
 * - **Material behavior:** Fully leverages Material's built-in [Switch] interaction patterns for
 *   accessibility and touch feedback.
 *
 * ### Example:
 * ```
 * HaloSwitch(
 *     checked = isDarkMode,
 *     onCheckedChange = { isDarkMode = it }
 * )
 * ```
 *
 * @param modifier Modifier applied to the switch layout.
 * @param checked Whether the switch is currently in the "on" (checked) state.
 * @param onCheckedChange Callback invoked when the user toggles the switch. Receives the new state.
 * @param colors Optional [SwitchColors] to customize track, thumb, and border colors for default,
 *               checked, and disabled states. Defaults to [HaloSwitchDefaults.switchColors()].
 * @param enabled Whether the switch is interactive. If `false`, the switch appears disabled
 *                and ignores user input.
 * @param thumbContent Optional composable placed inside the switch thumb, allowing you to
 *                     display icons, images, or any custom content.
 */
@Composable
fun HaloSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: SwitchColors? = null,
    enabled: Boolean = true,
    thumbContent: @Composable (() -> Unit)? = null,
) {
    val borderColor = colors?.default?.border ?: HaloSwitchDefaults.switchColors().default.border
    val thumbColor = colors?.default?.content ?: HaloSwitchDefaults.switchColors().default.content
    val trackColor =
        colors?.default?.container ?: HaloSwitchDefaults.switchColors().default.container
    val disabledBorderColor =
        colors?.disabled?.border ?: HaloSwitchDefaults.switchColors().disabled.border
    val disabledThumbColor =
        colors?.disabled?.content ?: HaloSwitchDefaults.switchColors().disabled.content
    val disabledTrackColor =
        colors?.disabled?.container ?: HaloSwitchDefaults.switchColors().disabled.container
    val checkedThumbColor =
        colors?.checked?.content ?: HaloSwitchDefaults.switchColors().checked.content
    val checkedTrackColor =
        colors?.checked?.container ?: HaloSwitchDefaults.switchColors().checked.container
    val checkedBorderColor =
        colors?.checked?.border ?: HaloSwitchDefaults.switchColors().checked.border
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors =
            SwitchDefaults.colors(
                uncheckedThumbColor = thumbColor,
                uncheckedBorderColor = borderColor,
                uncheckedTrackColor = trackColor,
                checkedThumbColor = checkedThumbColor,
                checkedBorderColor = checkedBorderColor,
                checkedTrackColor = checkedTrackColor,
                disabledUncheckedThumbColor = disabledThumbColor,
                disabledUncheckedBorderColor = disabledBorderColor,
                disabledUncheckedTrackColor = disabledTrackColor,
                disabledCheckedThumbColor = disabledThumbColor,
                disabledCheckedBorderColor = disabledBorderColor,
                disabledCheckedTrackColor = disabledTrackColor,
            ),
        enabled = enabled,
        thumbContent = thumbContent,
    )
}

/**
 * Holds the container (track), content (thumb), and border colors for a Halo switch,
 * grouped into `default`, `disabled`, and `checked` states.
 *
 * @param disabled Colors applied when the switch is disabled.
 * @param default Colors applied when the switch is enabled but not checked.
 * @param checked Colors applied when the switch is in the checked state.
 */
data class SwitchColors(
    val disabled: HaloColor,
    val default: HaloColor,
    val checked: HaloColor,
)

/**
 * Provides default values and helpers for configuring [HaloSwitch].
 *
 * Use this object to retrieve theme-aware colors for switches in various states.
 * These defaults follow the Halo design system, ensuring consistent appearance
 * and accessibility across the app.
 *
 * ### Example:
 * ```
 * HaloSwitch(
 *     checked = isEnabled,
 *     onCheckedChange = { isEnabled = it },
 *     colors = HaloSwitchDefaults.switchColors()
 * )
 * ```
 */
object HaloSwitchDefaults {
    /**
     * Returns a [SwitchColors] instance with default colors for `default`, `disabled`,
     * and `checked` states.
     *
     * @param track Track (container) color for the default state.
     * @param thumb Thumb (content) color for the default state.
     * @param border Border color for the default state.
     * @param disabledTrack Track color for the disabled state.
     * @param disabledThumb Thumb color for the disabled state.
     * @param disabledBorder Border color for the disabled state.
     * @param checkedTrack Track color for the checked state.
     * @param checkedThumb Thumb color for the checked state.
     * @param checkedBorder Border color for the checked state.
     *
     * @return A [SwitchColors] object containing the appropriate [HaloColor] values.
     */
    @Composable
    fun switchColors(
        track: Color = HaloTheme.colorScheme.background.surface,
        thumb: Color = HaloTheme.colorScheme.content.strong,
        border: Color = HaloTheme.colorScheme.content.strong,
        disabledTrack: Color = HaloTheme.colorScheme.disabled.container,
        disabledThumb: Color = HaloTheme.colorScheme.disabled.content,
        disabledBorder: Color = HaloTheme.colorScheme.disabled.border,
        checkedTrack: Color = HaloTheme.colorScheme.content.stronger,
        checkedThumb: Color = HaloTheme.colorScheme.background.surface,
        checkedBorder: Color = HaloTheme.colorScheme.content.strong,
    ): SwitchColors =
        SwitchColors(
            default =
                HaloColor(
                    container = track,
                    content = thumb,
                    border = border,
                ),
            disabled =
                HaloColor(
                    container = disabledTrack,
                    content = disabledThumb,
                    border = disabledBorder,
                ),
            checked =
                HaloColor(
                    container = checkedTrack,
                    content = checkedThumb,
                    border = checkedBorder,
                ),
        )
}
