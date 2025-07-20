package org.bizilabs.halo.desktop.screens.stepper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import org.bizilabs.halo.components.HaloOutlinedBadge
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.stepper.HaloHorizontalStepper
import org.bizilabs.halo.components.stepper.StepperProperties

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
                HaloSlotCard(modifier = Modifier) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        HaloSlotCard(modifier = Modifier) {
                            HaloText(
                                modifier = Modifier.padding(16.dp),
                                text = "Content",
                            )
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
                    steps = 3,
                    current = 1,
                    state = {
                        when (it) {
                            0 -> ComponentState.Success
                            1 -> ComponentState.Loading
                            else -> ComponentState.Default
                        }
                    },
                    properties = StepperProperties.Default.copy(width = 150.dp),
                ) { step ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        HaloText(
                            text = "Title  ${step + 1}",
                            style = HaloTheme.typography.bodyMedium,
                        )
                        HaloText(
                            text = "Subtitle  ${step + 1}",
                            style = HaloTheme.typography.bodySmall,
                        )
                        HaloOutlinedBadge {
                            HaloText(
                                text = "Badge  ${step + 1}",
                                style = HaloTheme.typography.labelSmall,
                            )
                        }
                    }
                }
            }
            item {
                HaloText(
                    text = "States",
                    style = HaloTheme.typography.subTitle,
                )
                Spacer(modifier = Modifier.height(8.dp))
                HaloHorizontalStepper(
                    steps = 2,
                    current = 1,
                    state = {
                        when (it) {
                            0 -> ComponentState.Success
                            1 -> ComponentState.Error
                            else -> ComponentState.Default
                        }
                    },
                    properties = StepperProperties.Default.copy(width = 150.dp),
                ) { step ->
                    val title =
                        when (step) {
                            0 -> "Success"
                            1 -> "Error"
                            else -> "Default"
                        }
                    HaloText(text = title)
                }
            }
            item {
                HaloHorizontalStepper(
                    steps = 2,
                    current = 1,
                    state = {
                        when (it) {
                            0 -> ComponentState.Default
                            1 -> ComponentState.Default
                            else -> ComponentState.Default
                        }
                    },
                    properties = StepperProperties.Default.copy(width = 150.dp),
                ) { step ->
                    val title =
                        when (step) {
                            0 -> "Default"
                            1 -> "Default Highlighted"
                            else -> "Default"
                        }
                    HaloText(text = title)
                }
            }
            item {
                HaloHorizontalStepper(
                    steps = 2,
                    current = 1,
                    state = {
                        when (it) {
                            0 -> ComponentState.Loading
                            1 -> ComponentState.Loading
                            else -> ComponentState.Default
                        }
                    },
                    properties = StepperProperties.Default.copy(width = 150.dp),
                ) { step ->
                    val title =
                        when (step) {
                            0 -> "Loading"
                            1 -> "Loading Highlighted"
                            else -> "Loading"
                        }
                    HaloText(text = title)
                }
            }
            item { Spacer(modifier = Modifier.padding(36.dp)) }
        }
    }
}
