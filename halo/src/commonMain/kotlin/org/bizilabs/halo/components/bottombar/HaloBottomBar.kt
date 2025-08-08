package org.bizilabs.halo.components.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ProvideContentColorTextStyle

/**
 * A customizable bottom navigation bar with a composable slot for a fully custom indicator.
 *
 * This composable provides a flexible container for a set of [HaloBottomBarItem]s, automatically
 * managing the animation of a visual indicator that tracks the currently selected item. The
 * indicator's position and size are calculated based on the layout of its children.
 *
 * @param modifier The modifier to be applied to the bottom bar.
 * @param containerColor The background color of the bottom bar. Defaults to the surface color from [HaloTheme].
 * @param selectedIndex The index of the currently selected item. This value drives the indicator's animation.
 * @param onItemSelected The callback that is invoked when an item is selected. It provides the index of the newly selected item.
 * @param indicator A custom composable for the indicator.
 * @param content The composable lambda that contains the [HaloBottomBarItem]s. This lambda is executed within a [HaloBottomBarScope].
 */
@Composable
fun HaloBottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = HaloTheme.colorScheme.background.surface,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit = {},
    indicator: @Composable (
    (
        modifier: Modifier,
        density: Density,
        indicatorOffset: Animatable<Float, AnimationVector1D>,
        indicatorWidth: Animatable<Float, AnimationVector1D>,
    ) -> Unit
    ) = { indicatorModifier, density, indicatorOffset, indicatorWidth ->
        HaloBottomBarIndicators.LineIndicator(
            modifier = indicatorModifier,
            density = density,
            indicatorOffset = indicatorOffset,
            indicatorWidth = indicatorWidth,
        )
    },
    content: @Composable HaloBottomBarScope.() -> Unit,
) = BaseHaloBottomBar(
    modifier = modifier,
    containerColor = containerColor,
    selectedIndex = selectedIndex,
    onItemSelected = onItemSelected,
    indicator = indicator,
    content = content,
)

/**
 * A customizable bottom navigation bar with a choice of pre-configured Halo indicators.
 *
 * This composable provides a flexible container for a set of [HaloBottomBarItem]s, automatically
 * managing the animation of a visual indicator that tracks the currently selected item. The
 * indicator's position and size are calculated based on the layout of its children.
 *
 * @param modifier The modifier to be applied to the bottom bar.
 * @param containerColor The background color of the bottom bar. Defaults to the surface color from [HaloTheme].
 * @param selectedIndex The index of the currently selected item. This value drives the indicator's animation.
 * @param onItemSelected The callback that is invoked when an item is selected. It provides the index of the newly selected item.
 * @param indicator The type of indicator to be used (e.g., LineTop, LineBottom, Pill). Defaults to [HaloBottomBarIndicatorType.LineBottom].
 * @param indicatorProperties A data class containing properties for the indicator, such as colors and shapes.
 * @param indicator Halo predefined bottom bars.
 * @param content The composable lambda that contains the [HaloBottomBarItem]s. This lambda is executed within a [HaloBottomBarScope].
 */
@Composable
fun HaloBottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = HaloTheme.colorScheme.background.surface,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit = {},
    indicator: HaloBottomBarIndicatorType = HaloBottomBarIndicatorType.LineBottom,
    indicatorProperties: IndicatorProperties = IndicatorProperties.default(),
    content: @Composable HaloBottomBarScope.() -> Unit,
) = BaseHaloBottomBar(
    modifier = modifier,
    containerColor = containerColor,
    selectedIndex = selectedIndex,
    onItemSelected = onItemSelected,
    indicator = { indicatorModifier, density, indicatorOffset, indicatorWidth ->
        Box { // Only used because of the align modifier
            when (indicator) {
                HaloBottomBarIndicatorType.LineTop ->
                    HaloBottomBarIndicators.LineIndicator(
                        modifier = indicatorModifier.align(Alignment.TopStart),
                        density,
                        indicatorOffset,
                        indicatorWidth,
                        indicatorProperties,
                    )

                HaloBottomBarIndicatorType.LineBottom ->
                    HaloBottomBarIndicators.LineIndicator(
                        modifier = indicatorModifier.align(Alignment.Center), // TODO This does not correctly position
                        density,
                        indicatorOffset,
                        indicatorWidth,
                        indicatorProperties,
                    )

                HaloBottomBarIndicatorType.Pill ->
                    HaloBottomBarIndicators.PillIndicator(
                        modifier = indicatorModifier.align(Alignment.Center),
                        density,
                        indicatorOffset,
                        indicatorWidth,
                        indicatorProperties,
                    )
            }
        }
    },
    content = content,
)

/**
 * A customizable bottom navigation bar that supports different types of animated indicators.
 *
 * This composable provides a flexible container for a set of [HaloBottomBarItem]s, automatically
 * managing the animation of a visual indicator that tracks the currently selected item. The
 * indicator's position and size are calculated based on the layout of its children.
 *
 * @param modifier The modifier to be applied to the bottom bar.
 * @param containerColor The background color of the bottom bar. Defaults to the surface color from [HaloTheme].
 * @param selectedIndex The index of the currently selected item. This value drives the indicator's animation.
 * @param onItemSelected The callback that is invoked when an item is selected. It provides the index of the newly selected item.
 * @param indicatorType The type of indicator to be used (e.g., LineTop, LineBottom, Pill). Defaults to [HaloBottomBarIndicatorType.LineBottom].
 * @param indicatorProperties A data class containing properties for the indicator, such as colors and shapes.
 * @param indicator A custom composable for the indicator. This can be used to override the default indicator types when [indicatorType] is [HaloBottomBarIndicatorType.None].
 * @param content The composable lambda that contains the [HaloBottomBarItem]s. This lambda is executed within a [HaloBottomBarScope].
 */
@Composable
fun BaseHaloBottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = HaloTheme.colorScheme.background.surface,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit = {},
    indicator: @Composable (
    (
        modifier: Modifier,
        density: Density,
        indicatorOffset: Animatable<Float, AnimationVector1D>,
        indicatorWidth: Animatable<Float, AnimationVector1D>,
    ) -> Unit
    ) = { indicatorModifier, density, indicatorOffset, indicatorWidth ->
        HaloBottomBarIndicators.LineIndicator(
            modifier = indicatorModifier,
            density = density,
            indicatorOffset = indicatorOffset,
            indicatorWidth = indicatorWidth,
        )
    },
    content: @Composable HaloBottomBarScope.() -> Unit,
) {
    // State to store the position and width of each item
    val itemPositions = remember { mutableStateMapOf<Int, Pair<Float, Float>>() }
    val density = LocalDensity.current

    // State to track the overall width of the bottom bar
    val bottomBarWidth = remember { mutableStateOf(0) }

    // Animatable states for the indicator's position and width
    val indicatorOffset = remember { Animatable(0f) }
    val indicatorWidth = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(selectedIndex, bottomBarWidth.value) {
        // Wait for layout to be measured and available
        if (itemPositions.containsKey(selectedIndex)) {
            val (xPos, width) = itemPositions[selectedIndex]!!

            coroutineScope.launch {
                indicatorOffset.animateTo(
                    targetValue = xPos,
                    animationSpec = tween(durationMillis = 300),
                )
            }
            coroutineScope.launch {
                indicatorWidth.animateTo(
                    targetValue = width,
                    animationSpec = tween(durationMillis = 300),
                )
            }
        }
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .onSizeChanged { size -> bottomBarWidth.value = size.width }
                .defaultMinSize(minHeight = HaloBottomBarItemDefaults.minBottomBarHeight)
                .background(containerColor),
    ) {
        // The sliding indicator bar
        indicator(
            Modifier,
            density,
            indicatorOffset,
            indicatorWidth,
        )

        // The 'this' in its lambda is a RowScope.
        Row(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // We create our custom scope object here, delegating to the RowScope
            val scope =
                remember(itemPositions.keys, onItemSelected) {
                    object : HaloBottomBarScope, RowScope by this {
                        override val onItemSelected: (Int) -> Unit = onItemSelected

                        override fun reportItemPosition(
                            index: Int,
                            layoutCoordinates: LayoutCoordinates,
                        ) {
                            itemPositions[index] =
                                Pair(
                                    layoutCoordinates.positionInParent().x,
                                    layoutCoordinates.size.width.toFloat(),
                                )
                        }
                    }
                }
            scope.content()
        }
    }
}

object HaloBottomBarIndicators {
    /**
     * A line-style indicator for the [HaloBottomBar].
     *
     * @param modifier The modifier to be applied to the indicator.
     * @param density The density of the current screen, used for converting dp to pixels.
     * @param indicatorOffset An [Animatable] that controls the horizontal offset of the indicator.
     * @param indicatorWidth An [Animatable] that controls the width of the indicator.
     * @param properties The properties of the indicator.
     */
    @Composable
    fun LineIndicator(
        modifier: Modifier = Modifier,
        density: Density,
        indicatorOffset: Animatable<Float, AnimationVector1D>,
        indicatorWidth: Animatable<Float, AnimationVector1D>,
        properties: IndicatorProperties = IndicatorProperties.default(),
    ) {
        Box(
            modifier =
                modifier
                    .offset(
                        x = with(density) { indicatorOffset.value.toDp() },
                    )
                    .width(with(density) { indicatorWidth.value.toDp() })
                    .height(properties.lineHeight)
                    .background(properties.color.border, properties.shape),
        )
    }

    /**
     * A pill-style indicator for the [HaloBottomBar].
     *
     * @param modifier The modifier to be applied to the indicator.
     * @param density The density of the current screen, used for converting dp to pixels.
     * @param indicatorOffset An [Animatable] that controls the horizontal offset of the indicator.
     * @param indicatorWidth An [Animatable] that controls the width of the indicator.
     * @param properties The properties of the indicator.
     */
    @Composable
    fun PillIndicator(
        modifier: Modifier = Modifier,
        density: Density,
        indicatorOffset: Animatable<Float, AnimationVector1D>,
        indicatorWidth: Animatable<Float, AnimationVector1D>,
        properties: IndicatorProperties = IndicatorProperties.default(),
    ) {
        Box(
            modifier =
                modifier
                    .offset(x = with(density) { indicatorOffset.value.toDp() })
                    .width(with(density) { indicatorWidth.value.toDp() })
                    .height(properties.pillHeight)
                    .padding(properties.pillPadding)
                    .background(
                        color = properties.color.container,
                        shape = properties.shape,
                    ),
        )
    }
}

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
