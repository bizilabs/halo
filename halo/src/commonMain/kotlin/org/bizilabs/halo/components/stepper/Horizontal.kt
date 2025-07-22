package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState

@Composable
fun HaloHorizontalStep(
    index: Int,
    last: Boolean,
    modifier: Modifier = Modifier,
    selected: Int? = null,
    mode: StepMode = StepMode.NumberAndIcon,
    state: ComponentState = if (selected != null && index < selected) ComponentState.Success else ComponentState.Default,
    indicator: @Composable (Int) -> Unit = {
        HaloStepIndicator(
            index = index,
            selected = index == selected,
            mode = mode,
            state = state,
            shape = RoundedCornerShape(100f),
        )
    },
    thickness: Dp = 2.dp,
    dividerMinWidth: Dp = 24.dp,
    dividerColor: Color? = null,
    divider: @Composable RowScope.() -> Unit = {
        val color =
            when {
                dividerColor != null -> dividerColor
                state == ComponentState.Success -> HaloTheme.colorScheme.success.filled.container
                else -> HaloTheme.colorScheme.disabled.container
            }
        HorizontalDivider(
            color = color,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minWidth = dividerMinWidth),
            thickness = thickness,
        )
    },
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        indicator(index)
        if (!last) divider()
    }
}

@Composable
fun HaloHorizontalStepper(
    steps: Int,
    modifier: Modifier = Modifier,
    selected: Int? = null,
    stepMinWidth: Dp = 24.dp,
    step: @Composable (Int) -> Unit = { index ->
        HaloHorizontalStep(
            index = index,
            selected = selected,
            last = index == (steps - 1),
            mode = StepMode.Dot,
            dividerMinWidth = stepMinWidth,
            state =
                when {
                    selected != null && index < selected -> ComponentState.Success
                    else -> ComponentState.Default
                },
        )
    },
) {
    LazyRow(modifier) {
        items(steps) { value ->
            HaloHorizontalStepContent { step(value) }
        }
    }
}

@Composable
private fun HaloHorizontalStepContent(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Min),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
