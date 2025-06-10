package org.bizilabs.halo.desktop.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.cards.HaloCard
import org.bizilabs.halo.components.cards.HaloOutlineCard
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun CardSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloCard(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Box(
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                    )
                }
            }
            item {
                HaloOutlineCard(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Box(
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                    )
                }
            }
            item {
                HaloSlotCard(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                    )
                }
            }
        }
    }
}
