package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState
import org.bizilabs.halo.components.HaloText

data class StepperProperties(
    val width: Dp = 300.dp,
    val height: Dp = 50.dp,
    val padding: PaddingValues = PaddingValues(0.dp),
    val shape: Shape = RoundedCornerShape(20),
    val thickness: Dp = 2.dp,
) {
    companion object {
        val Default = StepperProperties()
    }
}

@Composable
fun ThamaniStep(
    index: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    state: ComponentState = ComponentState.Default,
    properties: StepperProperties = StepperProperties.Default,
) {
    val color =
        when (state) {
            ComponentState.Default -> {
                if (selected) {
                    HaloTheme.colorScheme.primary.filled
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Loading -> {
                if (selected) {
                    HaloTheme.colorScheme.primary.filled
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Error -> HaloTheme.colorScheme.error.filled
            ComponentState.Success -> HaloTheme.colorScheme.success.filled
        }

    Box(
        modifier =
            modifier
                .background(color = color.container, shape = properties.shape),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            ComponentState.Default -> {
                HaloText(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp),
                    text = "${index + 1}",
                    style = HaloTheme.typography.bodyLarge,
                    color = color.content,
                )
            }

            ComponentState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp).size(24.dp),
                    strokeWidth = 3.dp,
                    color = color.content,
                )
            }

            ComponentState.Error -> {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Rounded.Error,
                    contentDescription = "error",
                    tint = color.content,
                )
            }

            ComponentState.Success -> {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "success",
                    tint = color.content,
                )
            }
        }
    }
}
