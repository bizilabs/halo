package org.bizilabs.halo.desktop.screens.topactionbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.bizilabs.halo.components.HaloTopActionBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopActionBarSection() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text = "TopActionBar",
                color = HaloTheme.colorScheme.background.onBase,
                fontWeight = FontWeight.SemiBold,
            )
            HaloText(
                text = "The Top App Bar displays information and actions relating to the current screen. " +
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
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Top App Bar with Back Button")
                HaloTopActionBar(
                    title = {
                        Text(
                            text = "Top actionbar"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back icon"
                        )
                    }
                )
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Top App Bar without Back Button")
                HaloTopActionBar(
                    title = {
                        Text(
                            text = "Top actionbar"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}