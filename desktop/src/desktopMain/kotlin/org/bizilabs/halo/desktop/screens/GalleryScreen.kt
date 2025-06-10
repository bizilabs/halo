package org.bizilabs.halo.desktop.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloCard
import org.bizilabs.halo.components.cards.HaloOutlineCard
import org.bizilabs.halo.desktop.screens.accordion.AccordionSection
import org.bizilabs.halo.desktop.screens.card.CardSection

data object GalleryScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { GalleryScreenModel() }
        val state by screenModel.state.collectAsState()
        LandingScreenContent(
            state = state,
            onAction = screenModel::onAction,
        )
    }
}

@Composable
fun LandingScreenContent(
    state: GalleryScreenState,
    onAction: (GalleryScreenAction) -> Unit,
) {
    HaloTheme(
        colorScheme =
            when (state.isDarkModeEnabled) {
                true -> state.colorTheme.dark
                false -> state.colorTheme.light
            },
    ) {
        Scaffold(
            topBar = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        onAction(GalleryScreenAction.ToggleTheme)
                    }) {
                        val vector =
                            when (state.isDarkModeEnabled) {
                                false -> Icons.Rounded.DarkMode
                                true -> Icons.Rounded.LightMode
                            }
                        Icon(
                            imageVector = vector,
                            contentDescription = "theme toggle",
                        )
                    }
                }
            },
            backgroundColor = HaloTheme.colorScheme.background.container,
            contentColor = HaloTheme.colorScheme.background.content,
        ) { pad ->
            AnimatedContent(state.section) { section ->
                when (section) {
                    GalleryScreenSection.Accordion -> {
                        AccordionSection()
                    }

                    GalleryScreenSection.Card -> {
                        CardSection()
                    }

                    null -> GalleryList(state = state, onAction = onAction)
                }
            }
        }
    }
}

@Composable
fun GalleryList(
    state: GalleryScreenState,
    onAction: (GalleryScreenAction) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(state.sections) {
            GalleryItem(title = it.label) { onAction(GalleryScreenAction.UpdateSection(it)) }
        }
    }
}

@Composable
fun GalleryItem(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    HaloCard(
        modifier = modifier.height(150.dp),
        onClick = onClick,
    ) {
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
