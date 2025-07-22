package org.bizilabs.halo.desktop.screens.stepper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.stepper.HaloHorizontalStep
import org.bizilabs.halo.components.stepper.HaloHorizontalStepper
import org.bizilabs.halo.components.stepper.StepMode

@Composable
fun HorizontalStepperSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        HaloText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Steppers display progress through a sequence of logical and numbered steps.",
            color = HaloTheme.colorScheme.content.stronger,
            style = HaloTheme.typography.bodyMedium,
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloText(
                    text = "Design",
                    style = HaloTheme.typography.subTitle,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    HaloSlotCard {
                        Column(modifier = Modifier.padding(8.dp)) {
                            HaloSlotCard(modifier = Modifier) {
                                HaloText(
                                    modifier = Modifier.padding(16.dp),
                                    text = "indicator",
                                )
                            }
                        }
                    }
                    HaloSlotCard(modifier = Modifier.fillMaxWidth(0.5f)) {
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
            item {
                HaloText(
                    text = "Usage",
                    style = HaloTheme.typography.subTitle,
                )
                Spacer(modifier = Modifier.height(8.dp))
                HaloHorizontalStepper(
                    steps = 5,
                    selected = 2,
                ) {
                    Column {
                        HaloHorizontalStep(
                            index = it,
                            last = it == 4,
                            selected = 2,
                        )
                        HaloText(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "Step ${it + 1}",
                            style = HaloTheme.typography.labelMedium,
                        )
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
                    HaloHorizontalStepper(steps = 5, selected = 2) { index ->
                        Column {
                            HaloHorizontalStep(
                                index = index,
                                last = index == 4,
                                selected = 3,
                                mode = StepMode.Dot,
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
                                modifier = Modifier.padding(top = 8.dp),
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
                HaloText(
                    text = "Types",
                    style = HaloTheme.typography.subTitle,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloText(
                        text = "Dot",
                        style = HaloTheme.typography.bodyMedium,
                    )
                    HaloHorizontalStepper(
                        steps = 5,
                        selected = 2,
                    ) {
                        HaloHorizontalStep(
                            index = it,
                            last = it == 4,
                            selected = 2,
                            mode = StepMode.Dot,
                        )
                    }
                    HaloText(
                        text = "Icon",
                        style = HaloTheme.typography.bodyMedium,
                    )
                    HaloHorizontalStepper(
                        steps = 5,
                        selected = 2,
                    ) {
                        HaloHorizontalStep(
                            index = it,
                            last = it == 4,
                            selected = 2,
                            mode = StepMode.Icon,
                        )
                    }
                    HaloText(
                        text = "Number",
                        style = HaloTheme.typography.bodyMedium,
                    )
                    HaloHorizontalStepper(
                        steps = 5,
                        selected = 2,
                    ) {
                        HaloHorizontalStep(
                            index = it,
                            last = it == 4,
                            selected = 2,
                            mode = StepMode.Number,
                        )
                    }
                    HaloText(
                        text = "Number & Icon",
                        style = HaloTheme.typography.bodyMedium,
                    )
                    HaloHorizontalStepper(
                        steps = 5,
                        selected = 2,
                    ) {
                        HaloHorizontalStep(
                            index = it,
                            last = it == 4,
                            selected = 2,
                            mode = StepMode.NumberAndIcon,
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(36.dp)) }
        }
    }
}
