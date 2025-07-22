package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState

@Composable
fun HaloVerticalStep(
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
    dividerMinHeight: Dp = 24.dp,
    dividerColor: Color? = null,
    divider: @Composable () -> Unit = {
        val color =
            when {
                dividerColor != null -> dividerColor
                state == ComponentState.Success -> HaloTheme.colorScheme.success.filled.container
                else -> HaloTheme.colorScheme.disabled.container
            }

        VerticalDivider(
            color = color,
            modifier =
                Modifier
                    .fillMaxHeight()
                    .defaultMinSize(minHeight = dividerMinHeight),
            thickness = thickness,
        )
    },
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        indicator(index)
        if (!last) divider()
    }
}

@Composable
fun HaloVerticalStepper(
    steps: Int,
    modifier: Modifier = Modifier,
    selected: Int? = null,
    stepMinHeight: Dp = 24.dp,
    step: @Composable (Int) -> Unit = { index ->
        HaloVerticalStep(
            modifier = Modifier.fillMaxWidth(),
            index = index,
            selected = selected,
            last = index == steps - 1,
            dividerMinHeight = stepMinHeight,
            mode = StepMode.Dot,
            state =
                when {
                    selected != null && index < selected -> ComponentState.Success
                    else -> ComponentState.Default
                },
        )
    },
) {
    LazyColumn(modifier) {
        items(steps) { value ->
            HaloVerticalStepContent { step(value) }
        }
    }
}

@Composable
private fun HaloVerticalStepContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(),
        ) {
            content()
        }
    }
}
