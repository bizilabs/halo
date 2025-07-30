package org.bizilabs.halo.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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