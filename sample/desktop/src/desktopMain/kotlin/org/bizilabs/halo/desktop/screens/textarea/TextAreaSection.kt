package org.bizilabs.halo.desktop.screens.textarea

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.TextAreaHeightMode
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.components.textarea.HaloFilledTextArea
import org.bizilabs.halo.components.textarea.HaloOutlinedTextArea

@Composable
fun TextAreaSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        HaloText(
            modifier = Modifier.padding(vertical = 16.dp),
            text =
                buildAnnotatedString {
                    append("A ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("text area")
                    }
                    append(" is a form element that lets users enter text in multiple lines.")
                },
            color = HaloTheme.colorScheme.content.stronger,
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
                                HaloSlotCard(
                                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                                ) {
                                    HaloText(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Content",
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
                        HaloFilledTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            placeholder = "Add here",
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
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
                        HaloFilledTextArea(
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
                        HaloFilledTextArea(
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
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloFilledTextArea(
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
                        HaloOutlinedTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            placeholder = "Add here",
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
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
                        HaloOutlinedTextArea(
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
                        HaloOutlinedTextArea(
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
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Read Only",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember { mutableStateOf("content") }
                        HaloOutlinedTextArea(
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
                        )
                    }
                }
            }
            item {
                HaloText(
                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp),
                    text = "Height mode",
                    style = HaloTheme.typography.subTitle,
                )
                HaloText(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text =
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Height mode")
                            }
                            append(" is a property that allows the text area to be fixed or match content in height.")
                        },
                    color = HaloTheme.colorScheme.content.stronger,
                )
                Spacer(Modifier.height(24.dp))
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
                            text = "Fixed",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember {
                            mutableStateOf(
                                "Lorem ipsum dolor sit amet consectetur adipiscing elit. " +
                                    "Placerat in id cursus mi pretium tellus duis. Urna tempor " +
                                    "pulvinar vivamus fringilla lacus nec metus." +
                                    " Integer nunc posuere ut hendrerit semper vel class." +
                                    " Conubia nostra inceptos himenaeos orci varius natoque penatibus. " +
                                    "Mus donec rhoncus eros lobortis nulla molestie mattis. Purus est " +
                                    "efficitur laoreet mauris pharetra vestibulum " +
                                    "fusce. Sodales consequat magna ante condimentum neque at luctus. " +
                                    "Ligula congue sollicitudin erat viverra ac " +
                                    "tincidunt nam. Lectus commodo augue arcu dignissim velit aliquam imperdiet." +
                                    " Cras eleifend turpis fames ",
                            )
                        }

                        HaloFilledTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            heightMode = TextAreaHeightMode.Fixed(),
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Expandable",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember {
                            mutableStateOf(
                                "Lorem ipsum dolor sit amet consectetur adipiscing elit. " +
                                    "Placerat in id cursus mi pretium tellus duis. Urna tempor " +
                                    "pulvinar vivamus fringilla lacus nec metus." +
                                    " Integer nunc posuere ut hendrerit semper vel class." +
                                    " Conubia nostra inceptos himenaeos orci varius natoque penatibus. " +
                                    "Mus donec rhoncus eros lobortis nulla molestie mattis. Purus est " +
                                    "efficitur laoreet mauris pharetra vestibulum " +
                                    "fusce. Sodales consequat magna ante condimentum neque at luctus. " +
                                    "Ligula congue sollicitudin erat viverra ac " +
                                    "tincidunt nam. Lectus commodo augue arcu dignissim velit aliquam imperdiet." +
                                    " Cras eleifend turpis fames ",
                            )
                        }

                        HaloFilledTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            heightMode = TextAreaHeightMode.Expandable(),
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
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
                            text = "Fixed",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember {
                            mutableStateOf(
                                "Lorem ipsum dolor sit amet consectetur adipiscing elit. " +
                                    "Placerat in id cursus mi pretium tellus duis. Urna tempor " +
                                    "pulvinar vivamus fringilla lacus nec metus." +
                                    " Integer nunc posuere ut hendrerit semper vel class." +
                                    " Conubia nostra inceptos himenaeos orci varius natoque penatibus. " +
                                    "Mus donec rhoncus eros lobortis nulla molestie mattis. Purus est " +
                                    "efficitur laoreet mauris pharetra vestibulum " +
                                    "fusce. Sodales consequat magna ante condimentum neque at luctus. " +
                                    "Ligula congue sollicitudin erat viverra ac " +
                                    "tincidunt nam. Lectus commodo augue arcu dignissim velit aliquam imperdiet." +
                                    " Cras eleifend turpis fames ",
                            )
                        }

                        HaloOutlinedTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            onValueChange = { content = it },
                            heightMode = TextAreaHeightMode.Fixed(),
                            label = {
                                HaloText(text = "Label")
                            },
                            count = {
                                HaloText(text = "Count")
                            },
                            helper = {
                                HaloText(text = "Helper")
                            },
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        HaloText(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Expandable",
                            style = HaloTheme.typography.subTitle,
                        )
                        var content by remember {
                            mutableStateOf(
                                "Lorem ipsum dolor sit amet consectetur adipiscing elit. " +
                                    "Placerat in id cursus mi pretium tellus duis. Urna tempor " +
                                    "pulvinar vivamus fringilla lacus nec metus." +
                                    " Integer nunc posuere ut hendrerit semper vel class." +
                                    " Conubia nostra inceptos himenaeos orci varius natoque penatibus. " +
                                    "Mus donec rhoncus eros lobortis nulla molestie mattis. Purus est " +
                                    "efficitur laoreet mauris pharetra vestibulum " +
                                    "fusce. Sodales consequat magna ante condimentum neque at luctus. " +
                                    "Ligula congue sollicitudin erat viverra ac " +
                                    "tincidunt nam. Lectus commodo augue arcu dignissim velit aliquam imperdiet." +
                                    " Cras eleifend turpis fames ",
                            )
                        }

                        HaloOutlinedTextArea(
                            modifier = Modifier.fillMaxWidth(),
                            value = content,
                            heightMode = TextAreaHeightMode.Expandable(),
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
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
