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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.chips.HaloInputChip
import org.bizilabs.halo.components.chips.HaloOutlinedSelectionChip
import org.bizilabs.halo.components.chips.HaloSelectionChip

@Composable
fun ChipSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "Chip",
                color = HaloTheme.colorScheme.background.onBase,
                fontWeight = FontWeight.SemiBold,
            )
            HaloText(
                text =
                    "Chips are compact UI elements that represent an input, attribute, or action. " +
                        "They allow users to make selections, filter content, or trigger events in a lightweight, visually distinct format.",
                color = HaloTheme.colorScheme.background.onBase,
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
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloSelectionChip(
                            onClick = {},
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloSelectionChip(
                            onClick = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloSelectionChip(
                            onClick = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Input")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloInputChip(
                            onClickTrailingIcon = {},
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloInputChip(
                            onClickTrailingIcon = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloInputChip(
                            onClickTrailingIcon = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Selection Disabled")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloSelectionChip(
                            onClick = {},
                            enabled = false,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloSelectionChip(
                            onClick = {},
                            enabled = false,
                            leadingIcon = Icons.Default.ShoppingCart,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloSelectionChip(
                            onClick = {},
                            enabled = false,
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Outline")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = {},
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                    item {
                        HaloOutlinedSelectionChip(
                            onClick = {},
                            leadingIcon = Icons.Default.ShoppingCart,
                            trailingIcon = Icons.Default.Close,
                        ) {
                            HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
