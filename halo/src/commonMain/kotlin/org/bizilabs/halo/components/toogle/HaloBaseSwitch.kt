package org.bizilabs.halo.components.toogle

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloColor

/**
 * A customizable switch component for toggling between two states (on/off).
 *
 * `HaloBaseSwitch` is a base-level implementation of a switch used in the Halo design system.
 * It provides options for size, shape, colors, animations, and additional UI content such as
 * indicators and custom thumb content.
 *
 * The switch animates the thumb and indicator offset when toggled, applies proper theming,
 * and supports both filled and outlined variants.
 *
 * ### Example usage:
 * ```
 * HaloBaseSwitch(
 *     toggled = isEnabled,
 *     onToggled = { isEnabled = it },
 *     size = ComponentSize.Large,
 *     switchVariant = SwitchVariant.Outline
 * )
 * ```
 *
 * @param toggled Current checked state of the switch. `true` means ON, `false` means OFF.
 * @param modifier Modifier to be applied to the switch container for styling, sizing, or interaction.
 * @param onToggled Callback invoked when the switch is toggled, passing the new state.
 * @param enabled Controls whether the switch is enabled for user interaction. Defaults to `true`.
 * @param shape The shape applied to the track and thumb. Defaults to `HaloTheme.shapes.full`.
 * @param colors The color scheme for the switch, including track, thumb, and border colors.
 *               Defaults to [HaloSwitchDefaults.switchColors].
 * @param size Defines the size of the switch (track and thumb). Defaults to [ComponentSize.Medium].
 * @param contentPadding Padding applied inside the switch layout. Defaults to `0.dp`.
 * @param interactionSource An optional [MutableInteractionSource] for observing and customizing
 *        interaction events (e.g., pressed, focused, hovered). If null, a new one is remembered.
 * @param indication Visual feedback for user interactions (e.g., ripple effect).
 *                   Defaults to [LocalIndication.current].
 * @param thumbContent Optional composable displayed inside the thumb (e.g., icon or image).
 * @param switchVariant Variant of the switch track:
 *  - [SwitchVariant.Filled]: A solid filled background (no border).
 *  - [SwitchVariant.Outline]: Transparent fill with a visible border.
 * @param indicator Optional composable displayed inside the track opposite to the thumb,
 *        useful for showing icons or contextual states.
 *
 * ### Notes:
 * - The switch animates its thumb and indicator positions when toggled using a short tween animation.
 * - Colors automatically adjust depending on `enabled` and `toggled` state.
 * - The track width and thumb width are measured dynamically to ensure accurate offset calculations.
 */
@Composable
fun HaloBaseSwitch(
    toggled: Boolean,
    modifier: Modifier = Modifier,
    onToggled: (Boolean) -> Unit,
    enabled: Boolean = true,
    shape: Shape = HaloTheme.shapes.full,
    colors: SwitchColors = HaloSwitchDefaults.switchColors(),
    size: ComponentSize = ComponentSize.Medium,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource? = null,
    indication: Indication = LocalIndication.current,
    thumbContent: (@Composable () -> Unit)? = null,
    switchVariant: SwitchVariant = SwitchVariant.Filled,
    indicator: (@Composable () -> Unit)? = null,
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    var trackWidth by remember { mutableStateOf(0.dp) }
    var thumbWidth by remember { mutableStateOf(0.dp) }

    val layoutDirection = LocalLayoutDirection.current

    val paddingStart = contentPadding.calculateStartPadding(layoutDirection)
    val paddingEnd = contentPadding.calculateEndPadding(layoutDirection)

    val actualTrackWidth by derivedStateOf {
        trackWidth - paddingStart - paddingEnd
    }

    val thumbOffset by animateDpAsState(
        targetValue = if (toggled) actualTrackWidth - thumbWidth else 0.dp,
        animationSpec = tween(durationMillis = 100),
        label = "SwitchOffset",
    )

    val indicatorOffsetX by animateDpAsState(
        targetValue = if (toggled) 0.dp else actualTrackWidth - thumbWidth,
        animationSpec = tween(durationMillis = 100),
        label = "IndicatorOffset",
    )

    val borderColor =
        when (enabled) {
            true -> if (toggled) colors.checked.border else colors.default.border
            false -> colors.disabled.border
        }
    val trackColor =
        when (enabled) {
            true -> if (toggled) colors.checked.container else colors.default.container
            false -> colors.disabled.container
        }
    val thumbColor =
        when (enabled) {
            true -> if (toggled) colors.checked.content else colors.default.content
            false -> colors.disabled.content
        }

    val density = LocalDensity.current

    val (borderWidth, color) =
        when (switchVariant) {
            SwitchVariant.Filled -> {
                Pair(0.dp, Color.Transparent)
            }

            SwitchVariant.Outline -> {
                Pair(2.dp, borderColor)
            }
        }

    Box(
        modifier =
            modifier
                .widthIn(min = HaloSwitchDefaults.minWidth(size))
                .clip(shape)
                .background(trackColor, shape)
                .border(borderWidth, color, shape)
                .onSizeChanged {
                    trackWidth =
                        with(density) {
                            it.width.toDp()
                        }
                }.toggleable(
                    value = toggled,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = indication,
                    role = Role.Switch,
                    onValueChange = onToggled,
                ).padding(contentPadding),
    ) {
        Box(
            Modifier
                .align(Alignment.CenterStart)
                .size(HaloSwitchDefaults.thumbSize(size))
                .offset { IntOffset(indicatorOffsetX.roundToPx(), 0) },
            contentAlignment = Alignment.Center,
        ) {
            indicator?.let {
                it()
            }
        }
        Box(
            Modifier
                .align(Alignment.CenterStart)
                .size(HaloSwitchDefaults.thumbSize(size))
                .offset { IntOffset(thumbOffset.roundToPx(), 0) }
                .onSizeChanged {
                    thumbWidth =
                        with(density) {
                            it.width.toDp()
                        }
                }.clip(shape)
                .background(thumbColor, shape),
        ) {
            thumbContent?.let {
                thumbContent.invoke()
            }
        }
    }
}

enum class SwitchVariant { Filled, Outline }

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
 * Provides default values and helpers for configuring [HaloBaseSwitch].
 *
 * Use this object to retrieve theme-aware colors for switches in various states.
 * These defaults follow the Halo design system, ensuring consistent appearance
 * and accessibility across the app.
 *
 * ### Example:
 * ```
 * HaloBaseSwitch(
 *     checked = isEnabled,
 *     onCheckedChange = { isEnabled = it },
 *     colors = HaloSwitchDefaults.switchColors()
 * )
 * ```
 */
object HaloSwitchDefaults {
    fun thumbSize(size: ComponentSize): Dp =
        when (size) {
            ComponentSize.ExtraSmall -> 8.dp
            ComponentSize.Small -> 16.dp
            ComponentSize.Medium -> 24.dp
            ComponentSize.Large -> 32.dp
            ComponentSize.ExtraLarge -> 40.dp
        }

    fun minWidth(size: ComponentSize): Dp =
        when (size) {
            ComponentSize.ExtraSmall -> 40.dp
            ComponentSize.Small -> 48.dp
            ComponentSize.Medium -> 56.dp
            ComponentSize.Large -> 64.dp
            ComponentSize.ExtraLarge -> 72.dp
        }

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
        track: Color = HaloTheme.colorScheme.background.high,
        thumb: Color = HaloTheme.colorScheme.content.strong,
        border: Color = HaloTheme.colorScheme.content.strong,
        disabledTrack: Color = HaloTheme.colorScheme.disabled.container,
        disabledThumb: Color = HaloTheme.colorScheme.disabled.content,
        disabledBorder: Color = HaloTheme.colorScheme.disabled.border,
        checkedTrack: Color = HaloTheme.colorScheme.content.stronger,
        checkedThumb: Color = HaloTheme.colorScheme.background.high,
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
