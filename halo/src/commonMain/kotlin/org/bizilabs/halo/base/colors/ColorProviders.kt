package org.bizilabs.halo.base.colors

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
internal fun ProvideContentColor(
    color: Color,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalContentColor provides color) {
        content()
    }
}
