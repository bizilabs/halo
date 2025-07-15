package org.bizilabs.halo.desktop.screens.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.buttons.HaloIconButton
import org.bizilabs.halo.components.buttons.HaloOutlinedIconButton
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun IconButtonSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "An icon button is a compact, clickable element that triggers an action using only an icon.",
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
                HaloSlotCard(modifier = Modifier) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        HaloSlotCard {
                            HaloText(
                                modifier = Modifier.padding(16.dp),
                                text = "Content",
                            )
                        }
                    }
                }
            }
            item {
                Row {
                    HaloText(
                        modifier = Modifier.weight(1f).padding(bottom = 16.dp),
                        text = "Base",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                    HaloText(
                        modifier = Modifier.weight(1f).padding(bottom = 16.dp),
                        text = "Outlined",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Extra Small", fontWeight = FontWeight.Bold)
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraSmall,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraSmall,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraSmall,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraSmall,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Small", fontWeight = FontWeight.Bold)
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Small,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Small,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Small,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Small,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Medium", fontWeight = FontWeight.Bold)
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Medium,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Medium,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Medium,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Medium,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Large", fontWeight = FontWeight.Bold)
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Large,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.Large,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Large,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.Large,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Extra Large", fontWeight = FontWeight.Bold)
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraLarge,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraLarge,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Enabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraLarge,
                                        containerPadding = PaddingValues(8.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            item {
                                Column {
                                    HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Disabled")
                                    HaloOutlinedIconButton(
                                        onClick = {},
                                        size = ComponentSize.ExtraLarge,
                                        containerPadding = PaddingValues(8.dp),
                                        enabled = false,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ShoppingCart,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
