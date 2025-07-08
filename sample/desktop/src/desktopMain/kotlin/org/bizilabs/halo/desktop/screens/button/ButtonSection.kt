package org.bizilabs.halo.desktop.screens.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloButton
import org.bizilabs.halo.components.HaloOutlineButton
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.HaloTextButton
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun ButtonSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "Button",
                color = HaloTheme.colorScheme.background.onBase,
                fontWeight = FontWeight.SemiBold,
            )
            HaloText(
                text = "A Button is a clickable element which communicates that users can trigger an action.",
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
                HaloButton(
                    onClick = {},
                ) {
                    HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Filled")
                HaloButton(onClick = {}) {
                    HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Outlined")
                HaloOutlineButton(onClick = {}) {
                    HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Text")
                HaloTextButton(onClick = {}) {
                    HaloText(modifier = Modifier.padding(8.dp), text = "Content")
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
