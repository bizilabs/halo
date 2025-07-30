package org.bizilabs.halo.components.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ProvideContentColorTextStyle

/**
 * Scope for the content of [HaloBottomBar].
 *
 * This scope provides access to the shared logic of the bottom bar, such as the `onItemSelected`
 * callback and the function to report an item's position. Composable functions that are to be used
 * as items inside the [HaloBottomBar] must be an extension of this scope.
 */
interface HaloBottomBarScope : RowScope {
    /**
     * The callback that is invoked when an item is selected. It provides the index of the newly selected item.
     */
    val onItemSelected: (Int) -> Unit

    /**
     * Reports the position and size of a child item back to the parent bar.
     * This function is used internally to animate the indicator.
     *
     * @param index The index of the item reporting its position.
     * @param layoutCoordinates The [LayoutCoordinates] of the item.
     */
    fun reportItemPosition(
        index: Int,
        layoutCoordinates: LayoutCoordinates,
    )
}

/**
 * A single item to be used within a [HaloBottomBar].
 *
 * This composable must be placed inside the content lambda of a [HaloBottomBar]. It automatically
 * handles its own click events and reports its layout position back to the parent bar for indicator animations.
 *
 * @param modifier The modifier to be applied to the item.
 * @param index The index of this item. This is used to track the selected item and its position.
 * @param isSelected Whether this item is currently selected.
 * @param label The composable lambda for the item's text label.
 * @param icon The composable lambda for the item's icon. It receives the content color as a parameter.
 * @param enabled Controls the enabled state of the item. When false, the item is not clickable.
 * @param colors The color set for the item's different states (selected, unselected, disabled).
 * @param interactionSource The [MutableInteractionSource] to be used for handling interactions like press and hover.
 * @param contentOrientation The orientation of the icon and label, either [HaloBottomBarItemContentOrientation.Vertical] or [HaloBottomBarItemContentOrientation.Horizontal].
 * @param onClick This callback provides a custom behaviour but does not change the selectedIndex
 */
@Composable
fun HaloBottomBarScope.HaloBottomBarItem(
    modifier: Modifier = Modifier,
    index: Int,
    isSelected: Boolean,
    label: @Composable (() -> Unit)? = null,
    icon: @Composable ((contentColor: Color) -> Unit)? = null,
    enabled: Boolean = true,
    colors: HaloBottomBarItemColors = HaloBottomBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
    indication: Indication = ripple(color = HaloTheme.colorScheme.primary.weaker),
    contentOrientation: HaloBottomBarItemContentOrientation = HaloBottomBarItemContentOrientation.Vertical,
    onClick: (() -> Unit)? = null,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val contentColor by animateColorAsState(
        targetValue =
            when {
                isSelected -> colors.selected.content
                !enabled -> colors.disabled.content
                else -> colors.unSelected.content
            },
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "contentColor",
    )
    val containerColor = Color.Transparent

    Box(
        modifier =
            modifier
                .selectable(
                    selected = isSelected,
                    onClick = {
                        if (onClick != null) {
                            onClick()
                        } else {
                            onItemSelected(index)
                        }
                    },
                    enabled = enabled,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = indication,
                )
                .background(containerColor)
                .weight(1f)
                .wrapContentHeight()
                .onGloballyPositioned { layoutCoordinates ->
                    reportItemPosition(index, layoutCoordinates)
                },
        contentAlignment = Alignment.Center,
    ) {
        when (contentOrientation) {
            HaloBottomBarItemContentOrientation.Vertical -> {
                Column(
                    modifier =
                        Modifier
                            .background(Color.Transparent)
                            .padding(top = 8.dp, bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    ProvideContentColorTextStyle(
                        contentColor = contentColor,
                        textStyle = HaloTheme.typography.labelMedium,
                    ) {
                        if (icon != null) {
                            icon(contentColor)
                        }
                        if (label != null) {
                            label()
                        }
                    }
                }
            }

            HaloBottomBarItemContentOrientation.Horizontal -> {
                Row(
                    modifier =
                        Modifier
                            .background(Color.Transparent)
                            .padding(
                                horizontal = 16.dp,
                                vertical = 12.dp,
                            ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    ProvideContentColorTextStyle(
                        contentColor = contentColor,
                        textStyle = HaloTheme.typography.labelMedium,
                    ) {
                        if (icon != null) {
                            icon(contentColor)
                        }
                        if (label != null) {
                            label()
                        }
                    }
                }
            }
        }
    }
}
