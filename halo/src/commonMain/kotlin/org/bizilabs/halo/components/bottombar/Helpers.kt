package org.bizilabs.halo.components.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor

data class HaloBottomBarItemColors(
    val selected: HaloColor,
    val unSelected: HaloColor,
    val disabled: HaloColor,
)

object HaloBottomBarItemDefaults {
    @Composable
    fun colors(): HaloBottomBarItemColors =
        HaloBottomBarItemColors(
            selected =
                HaloColor(
                    container = HaloTheme.colorScheme.primary.weaker,
                    content = HaloTheme.colorScheme.primary.strong,
                    border = HaloTheme.colorScheme.primary.strong,
                ),
            unSelected =
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.content.neutral,
                    border = Color.Transparent,
                ),
            disabled =
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.content.weaker,
                    border = Color.Transparent,
                ),
        )

    val minBottomBarHeight: Dp = 56.dp
}

enum class HaloBottomBarItemContentOrientation {
    Vertical,
    Horizontal,
}

// Might we need different classes for the different indicator types
data class IndicatorProperties(
    val color: HaloColor,
    val shape: Shape,
    val lineHeight: Dp,
    val pillHeight: Dp,
    val pillPadding: PaddingValues,
) {
    companion object {
        @Composable
        fun default() =
            IndicatorProperties(
                color =
                    HaloColor(
                        container = HaloTheme.colorScheme.primary.weaker,
                        content = HaloTheme.colorScheme.primary.strong,
                        border = HaloTheme.colorScheme.primary.strong,
                    ),
                shape = HaloTheme.shapes.large,
                lineHeight = 2.dp,
                pillHeight = HaloBottomBarItemDefaults.minBottomBarHeight,
                pillPadding = PaddingValues(4.dp),
            )
    }
}

enum class HaloBottomBarIndicatorType {
    LineTop,
    LineBottom,
    Pill,
    // TODO Think of more indicators
}