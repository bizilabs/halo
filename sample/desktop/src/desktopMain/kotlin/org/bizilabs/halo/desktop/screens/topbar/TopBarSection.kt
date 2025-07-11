package org.bizilabs.halo.desktop.screens.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.HaloTopBar
import org.bizilabs.halo.components.cards.HaloSlotCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "The Top Bar displays information and actions relating to the current screen. " +
                        "It helps users understand where they are in the app and provides quick access to key actions.",
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
                            modifier = Modifier.weight(0.7f),
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Content",
                            )
                        }
                        HaloSlotCard(
                            modifier = Modifier.weight(0.2f),
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Actions",
                            )
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Top Bar with Back Button")
                HaloTopBar(
                    title = {
                        Text(
                            text = "Top Bar",
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back icon",
                        )
                    },
                )
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Top Bar without Back Button")
                HaloTopBar(
                    title = {
                        Text(
                            text = "Top Bar",
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
