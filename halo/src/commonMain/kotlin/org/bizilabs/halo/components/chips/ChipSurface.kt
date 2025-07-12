package org.bizilabs.halo.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.HaloColorScheme

internal enum class ChipMode {
    FILLED,
    OUTLINED,
}

data class HaloChipColors(
    val default: HaloColor,
    val focused: HaloColor,
    val disabled: HaloColor,
)

@Composable
fun ChipSurface(
    modifier: Modifier = Modifier,
    backgroundColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.15f),
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.background(backgroundColor).padding(paddingValues),
    ) {
        content()
    }
}

@Composable
fun HaloColorScheme.chipColors(
    containerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.15f),
    contentColor: Color = HaloTheme.colorScheme.background.onBase,
    iconColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    disabledContainerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    disabledContentColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
) {
}
