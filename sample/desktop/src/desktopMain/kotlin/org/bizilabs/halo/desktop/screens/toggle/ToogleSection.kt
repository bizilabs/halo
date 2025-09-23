package org.bizilabs.halo.desktop.screens.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloShapes
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.toogle.HaloFilledSwitch
import org.bizilabs.halo.components.toogle.HaloOutlineSwitch

@Composable
fun ToggleSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "Switches toggle the selection of an item on or" +
                        " off. Switches are the best way to let" +
                        " users adjust settings.",
                color = HaloTheme.colorScheme.content.stronger,
                fontWeight = FontWeight.Light,
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloText(text = "Design")
                Spacer(modifier = Modifier.height(8.dp))
                HaloSlotCard(
                    modifier =
                        Modifier
                            .width(180.dp),
                ) {
                    Box(
                        modifier = Modifier.padding(8.dp).height(40.dp).wrapContentWidth(),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            HaloSlotCard(
                                modifier =
                                    Modifier
                                        .width(80.dp),
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    HaloText(text = "Thumb")
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.Center,
                            ) {
                                HaloText(text = "Indicator")
                            }
                        }
                    }
                }
            }
            item {
                HaloText(text = "Filled enabled toggle")
            }
            item {
                FilledEnabledToggleSection()
            }

            item {
                HaloText(text = "Filled disabled toggle")
            }
            item {
                FilledDisabledToggleSection()
            }

            item {
                HaloText(text = "Filled with indicator")
            }
            item {
                FilledToggleWithIndicator()
            }
            item {
                HaloText(text = "Outline enabled toggle")
            }
            item {
                OutlineEnabledToggleSection()
            }
            item {
                HaloText(text = "Outline disabled toggle")
            }
            item {
                OutlineDisabledToggleSection()
            }
            item {
                HaloText(text = "Outline with indicator")
            }
            item {
                OutlineToggleWithIndicator()
            }
        }
    }
}

@Composable
fun FilledEnabledToggleSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )

        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )

        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )

        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
    }
}

@Composable
fun FilledDisabledToggleSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )

        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )

        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )

        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloFilledSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
    }
}

@Composable
fun FilledToggleWithIndicator() {
    var toggled by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloFilledSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloFilledSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloFilledSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloFilledSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloFilledSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloFilledSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloFilledSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloFilledSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloFilledSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloFilledSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
    }
}

@Composable
fun OutlineEnabledToggleSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
        )
    }
}

@Composable
fun OutlineDisabledToggleSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = false,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
        HaloOutlineSwitch(
            toggled = true,
            onToggled = {},
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = false,
        )
    }
}

@Composable
fun OutlineToggleWithIndicator() {
    var toggled by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HaloOutlineSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(toggled)
            },
        )

        HaloOutlineSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraSmall,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.small,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.medium,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.large,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
        HaloOutlineSwitch(
            toggled = !toggled,
            onToggled = {
                toggled = it
            },
            shape = HaloShapes.Rounded.extraLarge,
            contentPadding = PaddingValues(4.dp),
            enabled = true,
            indicator = {
                IndicatorIcon(!toggled)
            },
        )
    }
}

@Composable
fun IndicatorIcon(toggled: Boolean) {
    Icon(
        imageVector = if (toggled) Icons.Default.DarkMode else Icons.Default.LightMode,
        contentDescription = null,
        tint = if (toggled) HaloTheme.colorScheme.background.high else HaloTheme.colorScheme.content.strong,
    )
}
