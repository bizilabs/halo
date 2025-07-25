package org.bizilabs.halo.components.buttons.buttonDefaults

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize

/**
 * Contains the default values used by all button types.
 *
 * Default values that apply to all buttons types are [minWidth], [minHeight], [contentPadding], and
 * [textStyle].
 * */
object HaloButtonDefaults {
    fun minWidth(size: ComponentSize): Dp =
        when (size) {
            ComponentSize.ExtraSmall -> 48.dp
            ComponentSize.Small -> 64.dp
            ComponentSize.Medium -> 80.dp
            ComponentSize.Large -> 100.dp
            ComponentSize.ExtraLarge -> 120.dp
        }

    fun minHeight(size: ComponentSize): Dp =
        when (size) {
            ComponentSize.ExtraSmall -> 32.dp
            ComponentSize.Small -> 40.dp
            ComponentSize.Medium -> 48.dp
            ComponentSize.Large -> 56.dp
            ComponentSize.ExtraLarge -> 64.dp
        }

    private fun getHorizontalAndVerticalPadding(size: ComponentSize): Pair<Dp, Dp> =
        when (size) {
            ComponentSize.ExtraSmall -> Pair(16.dp, 8.dp)
            ComponentSize.Small -> Pair(20.dp, 10.dp)
            ComponentSize.Medium -> Pair(24.dp, 12.dp)
            ComponentSize.Large -> Pair(28.dp, 16.dp)
            ComponentSize.ExtraLarge -> Pair(36.dp, 18.dp)
        }

    fun contentPadding(size: ComponentSize = ComponentSize.Medium): PaddingValues {
        val (horizontal, vertical) = getHorizontalAndVerticalPadding(size)
        return PaddingValues(
            top = vertical,
            bottom = vertical,
            start = horizontal,
            end = horizontal,
        )
    }

    @Composable
    fun textStyle(size: ComponentSize = ComponentSize.Medium): TextStyle {
        return when (size) {
            ComponentSize.ExtraSmall -> HaloTheme.typography.labelMedium
            ComponentSize.Small -> HaloTheme.typography.bodySmall
            ComponentSize.Medium -> HaloTheme.typography.bodyMedium
            ComponentSize.Large -> HaloTheme.typography.bodyLarge
            ComponentSize.ExtraLarge -> HaloTheme.typography.subTitle
        }
    }
}
