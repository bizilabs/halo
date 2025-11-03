package org.bizilabs.halo.components.toogle

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloShapes

@Composable
fun HaloFilledSwitch(
    toggled: Boolean,
    modifier: Modifier = Modifier,
    onToggled: (Boolean) -> Unit,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    thumbContent: @Composable (() -> Unit)? = null,
    indicator: (@Composable () -> Unit)? = null,
    shape: Shape = HaloShapes.Rounded.full,
    size: ComponentSize = ComponentSize.Medium,
) {
    HaloBaseSwitch(
        modifier = modifier,
        toggled = toggled,
        onToggled = onToggled,
        thumbContent = thumbContent,
        enabled = enabled,
        contentPadding = contentPadding,
        shape = shape,
        size = size,
        switchVariant = SwitchVariant.Filled,
        indicator = indicator,
    )
}
