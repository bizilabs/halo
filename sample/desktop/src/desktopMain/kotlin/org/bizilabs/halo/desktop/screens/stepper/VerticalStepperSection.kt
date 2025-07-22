package org.bizilabs.halo.desktop.screens.stepper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.stepper.HaloVerticalStep
import org.bizilabs.halo.components.stepper.HaloVerticalStepper
import org.bizilabs.halo.components.stepper.StepType

@Composable
fun VerticalStepperSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        HaloText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Steppers display progress through a sequence of logical and numbered steps.",
            color = HaloTheme.colorScheme.content.stronger,
            style = HaloTheme.typography.bodyMedium,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Column(modifier = Modifier.fillMaxHeight()) {
                    HaloText(
                        text = "Design",
                        style = HaloTheme.typography.subTitle,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        HaloSlotCard(modifier = Modifier.width(150.dp)) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                HaloSlotCard(modifier = Modifier.fillMaxWidth()) {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "indicator",
                                    )
                                }
                            }
                        }
                        HaloSlotCard(modifier = Modifier.width(150.dp)) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                HaloSlotCard(modifier = Modifier.fillMaxWidth()) {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "divider",
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item {
                Column(modifier = Modifier.fillMaxHeight()) {
                    HaloText(
                        text = "Usage",
                        style = HaloTheme.typography.subTitle,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HaloVerticalStepper(
                        steps = 5,
                        selected = 2,
                    ) {
                        Row {
                            HaloVerticalStep(
                                index = it,
                                last = it == 4,
                                selected = 2,
                            )
                            Column {
                                HaloText(
                                    modifier = Modifier.padding(8.dp),
                                    text = "Step ${it + 1}",
                                    style = HaloTheme.typography.labelMedium,
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column {
                    HaloText(
                        text = "States",
                        style = HaloTheme.typography.subTitle,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HaloVerticalStepper(steps = 5, selected = 2) { index ->
                        Row {
                            HaloVerticalStep(
                                index = index,
                                last = index == 4,
                                selected = 3,
                                type = StepType.Dot,
                                state =
                                    when (index) {
                                        0 -> ComponentState.Success
                                        1 -> ComponentState.Error
                                        2 -> ComponentState.Loading
                                        3 -> ComponentState.Default
                                        else -> ComponentState.Default
                                    },
                            )
                            HaloText(
                                modifier = Modifier.padding(8.dp),
                                text =
                                    when (index) {
                                        0 -> "Success"
                                        1 -> "Error"
                                        2 -> "Loading"
                                        3 -> "Current"
                                        else -> "Default"
                                    },
                                style = HaloTheme.typography.labelMedium,
                            )
                        }
                    }
                }
            }
            item {
                Column {
                    HaloText(
                        text = "Types",
                        style = HaloTheme.typography.subTitle,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HaloText(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = "Dot",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloVerticalStepper(
                                steps = 5,
                                selected = 2,
                            ) {
                                HaloVerticalStep(
                                    index = it,
                                    last = it == 4,
                                    selected = 2,
                                    type = StepType.Dot,
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            HaloText(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = "Icon",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloVerticalStepper(
                                steps = 5,
                                selected = 2,
                            ) {
                                HaloVerticalStep(
                                    index = it,
                                    last = it == 4,
                                    selected = 2,
                                    type = StepType.Icon,
                                )
                            }
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HaloText(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = "Number",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloVerticalStepper(
                                steps = 5,
                                selected = 2,
                            ) {
                                HaloVerticalStep(
                                    index = it,
                                    last = it == 4,
                                    selected = 2,
                                    type = StepType.Number,
                                )
                            }
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HaloText(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = "Number & Icon",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloVerticalStepper(
                                steps = 5,
                                selected = 2,
                            ) {
                                HaloVerticalStep(
                                    index = it,
                                    last = it == 4,
                                    selected = 2,
                                    type = StepType.NumberAndIcon,
                                )
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(36.dp)) }
        }
    }
}
