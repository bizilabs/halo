package org.bizilabs.halo.desktop.screens.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentMode
import org.bizilabs.halo.components.BadgeProperties
import org.bizilabs.halo.components.HaloBadge
import org.bizilabs.halo.components.HaloFilledBadge
import org.bizilabs.halo.components.HaloOutlinedBadge
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun BadgeSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "A Badge is a visual label that describes an attribute of an object.",
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
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Base")
                HaloBadge(
                    properties =
                        BadgeProperties.default().copy(
                            backgroundColor = HaloTheme.colorScheme.background.low,
                            contentColor = HaloTheme.colorScheme.content.strong,
                        ),
                ) {
                    HaloText(text = "Content")
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Filled")
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        HaloFilledBadge(mode = ComponentMode.Default) {
                            HaloText(text = "Default")
                        }
                    }
                    item {
                        HaloFilledBadge(mode = ComponentMode.Primary) {
                            HaloText(text = "Primary")
                        }
                    }
                    item {
                        HaloFilledBadge(mode = ComponentMode.Info) {
                            HaloText(text = "Info")
                        }
                    }

                    item {
                        HaloFilledBadge(mode = ComponentMode.Success) {
                            HaloText(text = "Success")
                        }
                    }

                    item {
                        HaloFilledBadge(mode = ComponentMode.Warning) {
                            HaloText(text = "Warning")
                        }
                    }

                    item {
                        HaloFilledBadge(mode = ComponentMode.Error) {
                            HaloText(text = "Error")
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Outlined")
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Default) {
                            HaloText(text = "Default")
                        }
                    }
                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Primary) {
                            HaloText(text = "Primary")
                        }
                    }
                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Info) {
                            HaloText(text = "Info")
                        }
                    }

                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Success) {
                            HaloText(text = "Success")
                        }
                    }

                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Warning) {
                            HaloText(text = "Warning")
                        }
                    }

                    item {
                        HaloOutlinedBadge(mode = ComponentMode.Error) {
                            HaloText(text = "Error")
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
