package org.bizilabs.halo.components.dot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.base.ComponentSize

@Composable
fun HaloDot(size: ComponentSize = ComponentSize.ExtraSmall) {
    val modifier =
        Modifier.size(
            when (size) {
                ComponentSize.ExtraSmall -> 8.dp
                ComponentSize.Small -> 16.dp
                ComponentSize.Medium -> 24.dp
                ComponentSize.Large -> 32.dp
                ComponentSize.ExtraLarge -> 48.dp
            },
        )
    HaloDot(modifier = modifier)
}

@Composable
fun HaloDot(
    color: Color = LocalContentColor.current,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(100),
) {
    Box(
        modifier =
            modifier
                .clip(shape)
                .background(color),
    )
}
