package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState

@Composable
fun HaloHorizontalStepItem(
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

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon(index)
            if (!last) {
                HorizontalDivider(
                    color = color.container,
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = properties.padding.calculateTopPadding()),
                    thickness = properties.thickness,
                )
            }
        }
        content()
    }
}

@Composable
fun HaloHorizontalStepper(
    steps: Int,
    current: Int,
    state: (Int) -> ComponentState,
    modifier: Modifier = Modifier,
    properties: StepperProperties = StepperProperties.Default,
    header: @Composable (Int) -> Unit,
) {
    LazyRow(modifier) {
        items(steps) { value ->
            HaloHorizontalStepItem(
                index = value,
                selected = value == current,
                last = value == steps - 1,
                state = state(value),
                modifier = Modifier.width(properties.width),
                content = { header(value) },
            )
        }
    }
}
