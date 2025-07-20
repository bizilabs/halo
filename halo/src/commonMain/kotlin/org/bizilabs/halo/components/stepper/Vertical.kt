package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState

@Composable
fun HaloVerticalStepItem(
    index: Int,
    selected: Boolean,
    last: Boolean,
    state: ComponentState,
    modifier: Modifier = Modifier,
    icon: @Composable (Int) -> Unit = {
        HaloStep(
            index = it,
            state = state,
            selected = selected,
        )
    },
    properties: StepperProperties = StepperProperties.Default,
    content: @Composable () -> Unit,
) {
    val color =
        when (state) {
            ComponentState.Default -> HaloTheme.colorScheme.disabled
            ComponentState.Loading -> HaloTheme.colorScheme.disabled
            ComponentState.Error -> HaloTheme.colorScheme.disabled
            ComponentState.Success -> HaloTheme.colorScheme.success.filled
        }

    Row(
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(),
        ) {
            icon(index)
            if (!last) {
                VerticalDivider(
                    color = color.container,
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .defaultMinSize(minHeight = properties.height),
                    thickness = properties.thickness,
                )
            }
        }
        content()
    }
}

@Composable
fun HaloVerticalStepper(
    steps: Int,
    current: Int,
    state: (Int) -> ComponentState,
    modifier: Modifier = Modifier,
    properties: StepperProperties = StepperProperties.Default,
    header: @Composable (Int) -> Unit,
) {
    LazyColumn(modifier) {
        items(steps) { value ->
            HaloVerticalStepItem(
                modifier = Modifier.fillMaxWidth(),
                index = value,
                selected = value == current,
                last = value == steps - 1,
                state = state(value),
                properties = properties,
                content = { header(value) },
            )
        }
    }
}
