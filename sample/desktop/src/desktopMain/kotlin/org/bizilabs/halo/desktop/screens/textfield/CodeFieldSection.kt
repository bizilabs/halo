package org.bizilabs.halo.desktop.screens.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.textfields.HaloCodeField
import org.bizilabs.halo.components.textfields.HaloCodeOutlinedField

@Composable
fun CodeFieldSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        HaloText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "An Input is a form element that lets users enter one of various types of text on a single line.",
            color = HaloTheme.colorScheme.content.stronger,
            style = HaloTheme.typography.bodyMedium,
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloText(
                    text = "Design",
                    style = HaloTheme.typography.subTitle,
                )
                Spacer(modifier = Modifier.height(8.dp))
                HaloSlotCard(modifier = Modifier) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        HaloSlotCard(modifier = Modifier) {
                            HaloText(
                                modifier = Modifier.padding(16.dp),
                                text = "Content",
                            )
                        }
                    }
                }
            }
            item {
                HaloText(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Base",
                    style = HaloTheme.typography.subTitle,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Enabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeField(
                            value = content,
                            count = 4,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Disabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeField(
                            value = content,
                            count = 4,
                            enabled = false,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(0.5f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeField(
                            value = content,
                            count = 4,
                            enabled = true,
                            readOnly = true,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                    Column(modifier = Modifier.weight(0.5f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Secret",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeField(
                            value = content,
                            count = 4,
                            enabled = true,
                            readOnly = false,
                            secret = true,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                }
            }
            item {
                HaloText(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Outlined",
                    style = HaloTheme.typography.subTitle,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Enabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeOutlinedField(
                            value = content,
                            count = 4,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Disabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeOutlinedField(
                            value = content,
                            count = 4,
                            enabled = false,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(0.5f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeOutlinedField(
                            value = content,
                            count = 4,
                            enabled = true,
                            readOnly = true,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                    Column(modifier = Modifier.weight(0.5f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Secret",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("12") }
                        HaloCodeOutlinedField(
                            value = content,
                            count = 4,
                            enabled = true,
                            readOnly = false,
                            secret = true,
                            onValueChange = { value, bool -> content = value },
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
