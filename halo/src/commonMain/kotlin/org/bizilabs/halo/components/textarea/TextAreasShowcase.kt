package org.bizilabs.halo.components.textarea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.TextAreaHeightMode
import org.bizilabs.halo.components.HaloSurface
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.textfields.HaloBaseTextField
import org.bizilabs.halo.components.textfields.HaloFilledTextField
import org.bizilabs.halo.components.textfields.HaloOutlinedTextField
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun BaseTextAreaPreview(){
    var text by remember { mutableStateOf("") }

    HaloTheme {
        HaloSurface(
            color = HaloTheme.colorScheme.background.lowest,
            contentColor = HaloTheme.colorScheme.content.stronger,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                HaloFilledTextArea(
                    value = text,
                    lines = 5,
                    heightMode = TextAreaHeightMode.Fixed,
                    placeholder = "This is the Filled text area",
                    label = { HaloText(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Filled ")
                        }
                        append("text area")
                    }) },
                    onValueChange = { value -> text = value }
                )

                HaloOutlinedTextArea(
                    value = text,
                    lines = 5,
                    heightMode = TextAreaHeightMode.Fixed,
                    placeholder = "This is the Filled text area",
                    label = { HaloText(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Filled ")
                        }
                        append("text area")
                    }) },
                    onValueChange = { value -> text = value }
                )
            }
        }
    }
}