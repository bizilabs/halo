package org.bizilabs.halo.desktop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloCard
import org.bizilabs.halo.components.cards.HaloOutlineCard

data object GalleryScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        LandingScreenContent { screen -> navigator?.push(screen) }
    }
}

@Composable
fun LandingScreenContent(navigate: (Screen) -> Unit) {
    Scaffold { pad ->
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            item { GalleryItem(modifier = Modifier.fillMaxWidth(), title = "Accordion") }
            item { GalleryOutlineItem(modifier = Modifier.fillMaxWidth(), title = "Card") }
        }
    }
}

@Composable
fun GalleryItem(
    title: String,
    modifier: Modifier = Modifier,
) {
    HaloCard(modifier = modifier.height(150.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            HaloText(text = title)
        }
    }
}

@Composable
fun GalleryOutlineItem(
    title: String,
    modifier: Modifier = Modifier,
) {
    HaloOutlineCard(modifier = modifier.height(150.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            HaloText(text = title)
        }
    }
}
