package org.bizilabs.halo.base

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Thickness(
    val extraSmall: Dp = 0.5.dp,
    val small: Dp = 1.dp,
    val medium: Dp = 2.dp,
    val large: Dp = 4.dp,
    val extraLarge: Dp = 8.dp,
)

internal val LocalThickness = staticCompositionLocalOf { Thickness() }

internal fun provideThickness(thickness: Thickness = Thickness()) = LocalThickness provides thickness
