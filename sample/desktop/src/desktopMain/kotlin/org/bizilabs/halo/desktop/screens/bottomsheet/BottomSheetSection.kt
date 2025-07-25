package org.bizilabs.halo.desktop.screens.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.bottomsheets.rememberHaloSheetLauncher
import org.bizilabs.halo.components.buttons.HaloFilledButton
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun BottomSheetSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "Bottom sheets are surfaces anchored to the bottom of the screen that display " +
                        "contextual content or actions. They can be used for menus, filters, " +
                        "selections, or lightweight dialogs without disrupting the main UI.",
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
                    Box(modifier = Modifier.padding(8.dp)) {
                        HaloSlotCard(
                            modifier = Modifier,
                        ) {
                            HaloText(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Content",
                            )
                        }
                    }
                }
            }
            item {
                val content: @Composable ColumnScope.() -> Unit = {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        HaloText("This is some text")
                    }
                }
                val sheetLauncher =
                    rememberHaloSheetLauncher {
                        content()
                    }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    HaloFilledButton(
                        onClick = { sheetLauncher.launch() },
                    ) {
                        HaloText("Show")
                    }
                }
            }
        }
    }
}
