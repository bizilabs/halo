package org.bizilabs.halo.desktop.screens.accordion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloAccordion
import org.bizilabs.halo.components.HaloFilledAccordion
import org.bizilabs.halo.components.HaloOutlinedAccordion
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun AccordionSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            HaloText(
                text = "Accordion",
                color = HaloTheme.colorScheme.background.content,
                fontWeight = FontWeight.SemiBold,
            )
            HaloText(
                text = "An accordion allows users to expand and collapse sections of content.",
                color = HaloTheme.colorScheme.background.content,
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
                            HaloText(modifier = Modifier.fillMaxWidth().padding(16.dp), text = "Header")
                        }
                        Spacer(modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
                        HaloSlotCard {
                            HaloText(modifier = Modifier.fillMaxWidth().padding(16.dp), text = "Content")
                        }
                    }
                }
            }
            item {
                var open by remember { mutableStateOf(false) }
                HaloText(text = "Base")
                HaloAccordion(
                    collapsed = open,
                    onClick = { open = !open },
                    header = {
                        HaloText(text = "Title")
                    },
                ) {
                    HaloText(text = "Content")
                }
            }
            item {
                var filledOpened by remember { mutableStateOf(false) }
                HaloText(text = "Filled")
                HaloFilledAccordion(
                    collapsed = filledOpened,
                    onClick = { filledOpened = !filledOpened },
                    header = {
                        HaloText(text = "Filled Title")
                    },
                ) {
                    HaloText(text = "Filled Content")
                }
            }
            item {
                var outlinedOpened by remember { mutableStateOf(false) }
                HaloText(text = "Outlined")
                HaloOutlinedAccordion(
                    collapsed = outlinedOpened,
                    onClick = { outlinedOpened = !outlinedOpened },
                    header = {
                        HaloText(text = "Outlined Title")
                    },
                ) {
                    HaloText(text = "Outlined Content")
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
