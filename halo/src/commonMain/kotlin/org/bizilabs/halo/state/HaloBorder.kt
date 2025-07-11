package org.bizilabs.halo.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

data class HaloBorder(
    val width: Dp,
    val color: Color,
    val shape: Shape? = null,
)
