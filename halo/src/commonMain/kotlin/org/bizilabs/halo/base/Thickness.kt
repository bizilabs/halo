package org.bizilabs.halo.base

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Thickness(
    val small: Dp = 1.dp,
    val medium: Dp = 2.dp,
    val large: Dp = 4.dp,
)

internal val LocalThickness = staticCompositionLocalOf<Thickness> { error("No thickness provided") }

internal fun provideThickness(thickness: Thickness = Thickness()) =
    LocalThickness provides thickness
