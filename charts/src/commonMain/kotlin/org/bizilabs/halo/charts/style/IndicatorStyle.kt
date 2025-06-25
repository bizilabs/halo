package org.bizilabs.halo.charts.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Defines the visual style for the interactive indicator.
 * @param color The color of the vertical indicator line.
 * @param indicatorCircleRadius The radius of the circle on the data point.
 * @param indicatorCircleStrokeWidth The thickness of the stroke for the indicator circle.
 * @param labelBackgroundColor The background color of the label popup.
 * @param labelTextStyle The text style for the label.
 */
data class IndicatorStyle(
    val color: Color = Color.DarkGray,
    val indicatorCircleRadius: Dp = 6.dp,
    val indicatorCircleStrokeWidth: Dp = 2.dp,
    val labelBackgroundColor: Color = Color.White,
    val labelTextStyle: TextStyle =
        TextStyle(
            color = Color.Black,
            fontSize = 12.sp,
        ),
)
