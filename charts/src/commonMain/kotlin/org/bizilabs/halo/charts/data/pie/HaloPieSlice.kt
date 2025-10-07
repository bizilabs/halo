package org.bizilabs.halo.charts.data.pie

import androidx.compose.ui.graphics.Color

data class HaloPieSlice(
    val value: Float,
    val color: Color,
    val label: String? = null,
)
