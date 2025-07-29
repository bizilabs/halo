package org.bizilabs.halo.desktop.screens.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentMode
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.buttons.HaloFilledButton
import org.bizilabs.halo.components.buttons.HaloOutlineButton
import org.bizilabs.halo.components.buttons.HaloTextButton
import org.bizilabs.halo.components.cards.HaloSlotCard

@Composable
fun ButtonSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "A Button is a clickable element which communicates that users can trigger an action.",
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
            item { Spacer(modifier = Modifier.padding(16.dp)) }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Filled Active with different sizes and shapes")

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    HaloFilledButton(onClick = {}, size = ComponentSize.ExtraLarge, shape = HaloTheme.shapes.none) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        size = ComponentSize.ExtraLarge,
                        mode = ComponentMode.Primary,
                        shape = HaloTheme.shapes.small,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(onClick = {}, size = ComponentSize.Large, mode = ComponentMode.Info, shape = HaloTheme.shapes.medium) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        size = ComponentSize.Medium,
                        mode = ComponentMode.Error,
                        shape = HaloTheme.shapes.large,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        size = ComponentSize.Small,
                        mode = ComponentMode.Success,
                        shape = HaloTheme.shapes.extraLarge,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        size = ComponentSize.ExtraSmall,
                        mode = ComponentMode.Warning,
                        shape = HaloTheme.shapes.full,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Filled Disabled")
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        shape = HaloTheme.shapes.none,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        shape = HaloTheme.shapes.small,
                        mode = ComponentMode.Primary,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Info,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Error,
                        shape = HaloTheme.shapes.large,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Success,
                        shape = HaloTheme.shapes.extraLarge,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloFilledButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Warning,
                        shape = HaloTheme.shapes.full,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Outlined Active")
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloOutlineButton(onClick = {}, shape = HaloTheme.shapes.none) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(onClick = {}, mode = ComponentMode.Primary, shape = HaloTheme.shapes.small) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(onClick = {}, mode = ComponentMode.Info, shape = HaloTheme.shapes.medium) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(onClick = {}, mode = ComponentMode.Error, shape = HaloTheme.shapes.large) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(onClick = {}, mode = ComponentMode.Success, shape = HaloTheme.shapes.extraLarge) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(onClick = {}, mode = ComponentMode.Warning, shape = HaloTheme.shapes.full) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 8.dp), text = "Outlined Disabled")
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        shape = HaloTheme.shapes.none,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Primary,
                        shape = HaloTheme.shapes.small,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Info,
                        shape = HaloTheme.shapes.medium,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Error,
                        shape = HaloTheme.shapes.large,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Success,
                        shape = HaloTheme.shapes.extraLarge,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloOutlineButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Warning,
                        shape = HaloTheme.shapes.full,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Text Button Active")
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloTextButton(onClick = {}, shape = HaloTheme.shapes.none) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(onClick = {}, mode = ComponentMode.Primary, shape = HaloTheme.shapes.small) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(onClick = {}, mode = ComponentMode.Info, shape = HaloTheme.shapes.medium) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(onClick = {}, mode = ComponentMode.Error, shape = HaloTheme.shapes.large) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(onClick = {}, mode = ComponentMode.Success, shape = HaloTheme.shapes.extraLarge) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(onClick = {}, mode = ComponentMode.Warning, shape = HaloTheme.shapes.full) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 8.dp), text = "Text Button Disabled")
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        shape = HaloTheme.shapes.none,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Primary,
                        shape = HaloTheme.shapes.small,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Info,
                        shape = HaloTheme.shapes.medium,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Error,
                        shape = HaloTheme.shapes.large,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Success,
                        shape = HaloTheme.shapes.extraLarge,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                    HaloTextButton(
                        onClick = {},
                        enabled = false,
                        mode = ComponentMode.Warning,
                        shape = HaloTheme.shapes.full,
                    ) {
                        HaloText(modifier = Modifier.padding(0.dp), text = "Button")
                    }
                }
            }

            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
