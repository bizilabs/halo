package org.bizilabs.halo.desktop.screens.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.chips.HaloInputChip
import org.bizilabs.halo.components.chips.HaloOutlinedInputChip
import org.bizilabs.halo.components.chips.HaloOutlinedSelectionChip
import org.bizilabs.halo.components.chips.HaloSelectionChip

@Composable
fun ChipSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "Chips are compact UI elements that represent an input, attribute, or action. " +
                        "They allow users to make selections, filter content, or " +
                        "trigger events in a lightweight, visually distinct format.",
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
                HaloSlotCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        HaloSlotCard(
                            modifier = Modifier.weight(0.1f),
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Icon",
                            )
                        }
                        HaloSlotCard(
                            modifier = Modifier.weight(0.8f),
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Content",
                            )
                        }
                        HaloSlotCard(
                            modifier = Modifier.weight(0.1f),
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Icon",
                            )
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Selection")
                var selectedIndex by remember { mutableIntStateOf(0) }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        val isSelected = selectedIndex == 0
                        HaloSelectionChip(
                            onClick = { selectedIndex = 0 },
                            selected = isSelected,
                            text = "Content",
                        )
                    }
                    item {
                        val isSelected = selectedIndex == 1
                        HaloSelectionChip(
                            onClick = { selectedIndex = 1 },
                            selected = isSelected,
                            text = "Content",
                            leadingIcon = Icons.Default.ShoppingCart,
                        )
                    }
                    item {
                        val isSelected = selectedIndex == 2
                        HaloSelectionChip(
                            onClick = { selectedIndex = 2 },
                            selected = isSelected,
                            text = "Content",
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        )
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Selection Disabled")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloSelectionChip(
                            text = "Content",
                            enabled = false,
                        )
                    }
                    item {
                        HaloSelectionChip(
                            text = "Content",
                            enabled = false,
                            leadingIcon = Icons.Default.ShoppingCart,
                        )
                    }
                    item {
                        HaloSelectionChip(
                            text = "Content",
                            enabled = false,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        )
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Selection Outlined")
                var selectedIndex by remember { mutableIntStateOf(0) }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 0 },
                            selected = selectedIndex == 0,
                            text = "Content",
                        )
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 1 },
                            selected = selectedIndex == 1,
                            leadingIcon = Icons.Default.ShoppingCart,
                            text = "Content",
                        )
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 2 },
                            selected = selectedIndex == 2,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                        )
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Selection Outlined Disabled")
                var selectedIndex by remember { mutableIntStateOf(0) }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 0 },
                            selected = selectedIndex == 0,
                            text = "Content",
                            enabled = false,
                        )
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 1 },
                            selected = selectedIndex == 1,
                            leadingIcon = Icons.Default.ShoppingCart,
                            text = "Content",
                            enabled = false,
                        )
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = { selectedIndex = 2 },
                            selected = selectedIndex == 2,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                            enabled = false,
                        )
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Input")
                var selectedIndex by remember { mutableIntStateOf(0) }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloInputChip(
                            onClickTrailingIcon = { selectedIndex = 2 },
                            selected = selectedIndex == 2,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                        )
                    }
                    item {
                        HaloInputChip(
                            onClickTrailingIcon = { selectedIndex = 2 },
                            selected = selectedIndex == 2,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                        )
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Input Outlined")
                var selectedIndex by remember { mutableIntStateOf(0) }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloOutlinedInputChip(
                            onClickTrailingIcon = { selectedIndex = 1 },
                            selected = selectedIndex == 1,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                        )
                    }
                    item {
                        HaloOutlinedInputChip(
                            onClickTrailingIcon = { selectedIndex = 2 },
                            selected = selectedIndex == 2,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                            text = "Content",
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
