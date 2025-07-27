package org.bizilabs.halo.desktop.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloFilledCard
import org.bizilabs.halo.components.cards.HaloOutlinedCard

@Composable
fun CardSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            HaloText(
                text = "A card is a contained unit of information related to a topic. ",
            )
        }
        Divider(modifier = Modifier.fillMaxWidth(), color = HaloTheme.colorScheme.content.weak)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Column {
                    HaloText(
                        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                        text = "Filled",
                        style = HaloTheme.typography.subTitle,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Normal",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloFilledCard {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Clickable (Enabled)",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloFilledCard(enabled = true, onClick = {}) {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Clickable (Disabled)",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloFilledCard(enabled = false, onClick = {}) {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column {
                    HaloText(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Outlined",
                        style = HaloTheme.typography.subTitle,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Normal",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloOutlinedCard {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Clickable (Enabled)",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloOutlinedCard(enabled = true, onClick = {}) {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            HaloText(
                                modifier = Modifier.padding(vertical = 16.dp),
                                text = "Clickable (Disabled)",
                                style = HaloTheme.typography.bodyMedium,
                            )
                            HaloOutlinedCard(enabled = false, onClick = {}) {
                                Box(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(150.dp),
                                )
                            }
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(24.dp))
            }
        }
    }
}
