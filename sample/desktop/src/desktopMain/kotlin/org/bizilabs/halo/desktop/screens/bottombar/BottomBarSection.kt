package org.bizilabs.halo.desktop.screens.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.bottombar.HaloBottomBar
import org.bizilabs.halo.components.bottombar.HaloBottomBarIndicatorType
import org.bizilabs.halo.components.bottombar.HaloBottomBarItem
import org.bizilabs.halo.components.bottombar.HaloBottomBarItemContentOrientation
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun BottomBarSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "A Bottom Bar is a clickable element which communicates that users can trigger an action.",
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
                    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        HaloSlotCard {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Content",
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(16.dp)) }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "BottomBar")

                Spacer(modifier = Modifier.padding(8.dp))

                var selectedItemIndex by remember { mutableStateOf(0) }
                HaloBottomBar(
                    selectedIndex = selectedItemIndex,
                    onItemSelected = { index -> selectedItemIndex = index },
                ) {
                    List(4) { i ->
                        HaloBottomBarItem(
                            index = i,
                            isSelected = selectedItemIndex == i,
                            label = { HaloText(text = "Home") },
                            icon = { color ->
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Home",
                                    tint = color,
                                )
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))

                var selectedItemIndex2 by remember { mutableStateOf(0) }
                HaloBottomBar(
                    selectedIndex = selectedItemIndex2,
                    onItemSelected = { index ->
                        selectedItemIndex2 = index
                    },
                    indicator = HaloBottomBarIndicatorType.LineTop,
                ) {
                    List(4) { i ->
                        HaloBottomBarItem(
                            index = i,
                            isSelected = selectedItemIndex2 == i,
                            label = { HaloText(text = "Home") },
                            icon = { color ->
                                Icon(
                                    Icons.Rounded.Home,
                                    contentDescription = "Home",
                                    tint = color,
                                )
                            },
                            contentOrientation = HaloBottomBarItemContentOrientation.Horizontal,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))

                var selectedItemIndex3 by remember { mutableStateOf(0) }
                HaloBottomBar(
                    selectedIndex = selectedItemIndex3,
                    onItemSelected = { index ->
                        selectedItemIndex3 = index
                    },
                    indicator = HaloBottomBarIndicatorType.Pill,
                ) {
                    List(4) { i ->
                        HaloBottomBarItem(
                            index = i,
                            isSelected = selectedItemIndex3 == i,
                            label = { HaloText(text = "Home") },
                            icon = { color ->
                                Icon(
                                    Icons.Rounded.Home,
                                    contentDescription = "Home",
                                    tint = color,
                                )
                            },
                            contentOrientation = HaloBottomBarItemContentOrientation.Horizontal,
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
