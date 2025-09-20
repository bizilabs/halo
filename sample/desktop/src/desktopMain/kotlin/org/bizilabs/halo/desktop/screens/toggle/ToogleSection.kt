package org.bizilabs.halo.desktop.screens.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.toogle.HaloBaseSwitch

@Composable
fun ToggleSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "Switches toggle the selection of an item on or" +
                        " off. Switches are the best way to let" +
                        " users adjust settings.",
                color = HaloTheme.colorScheme.content.stronger,
                fontWeight = FontWeight.Light,
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloText(text = "Active unchecked")
            }
            item {
                HaloBaseSwitch(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            item {
                HaloText(text = "Active checked")
            }
            item {
                HaloBaseSwitch(
                    checked = true,
                    onCheckedChange = {},
                )
            }
            item {
                HaloText(text = "Disabled unchecked")
            }
            item {
                HaloBaseSwitch(
                    checked = false,
                    onCheckedChange = {},
                    enabled = false,
                )
            }
            item {
                HaloText(text = "Disabled checked")
            }
            item {
                HaloBaseSwitch(
                    checked = true,
                    onCheckedChange = {},
                    enabled = false,
                )
            }
        }
    }
}
