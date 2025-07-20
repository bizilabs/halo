package org.bizilabs.halo.desktop.screens.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
import org.bizilabs.halo.components.textfields.HaloFilledTextField
import org.bizilabs.halo.components.textfields.HaloOutlinedTextField

@Composable
fun TextFieldSection() {
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
                HaloSlotCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            HaloSlotCard(modifier = Modifier.weight(1f)) {
                                HaloText(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Label",
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            HaloSlotCard {
                                HaloText(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Count",
                                )
                            }
                        }

                        HaloSlotCard(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                HaloSlotCard {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Leading",
                                    )
                                }
                                HaloSlotCard(
                                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                                ) {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Content",
                                    )
                                }
                                HaloSlotCard {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Trailing",
                                    )
                                }
                            }
                        }
                        HaloSlotCard(modifier = Modifier.fillMaxWidth()) {
                            HaloText(
                                modifier = Modifier.padding(16.dp),
                                text = "Helper",
                            )
                        }
                    }
                }
            }
            item {
                HaloText(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Filled",
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
                        var content by remember { mutableStateOf("content") }
                        HaloFilledTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Disabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloFilledTextField(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false,
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Error",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloFilledTextField(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true,
                            isError = true,
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloFilledTextField(
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                Icon(Icons.Default.Error, "error")
                            },
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
                        var content by remember { mutableStateOf("content") }
                        HaloOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Disabled",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = false,
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Error",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            isError = true,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                HaloText(text = "Leading")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloOutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            value = content,
                            onValueChange = { content = it },
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                            leading = {
                                HaloText(text = "Leading")
                            },
                            trailing = {
                                Icon(Icons.Default.Error, "error")
                            },
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
