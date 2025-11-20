package org.bizilabs.halo.base.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class HaloElevationColor(
    val highest: Color,
    val high: Color,
    val middle: Color,
    val low: Color,
    val lowest: Color,
)
