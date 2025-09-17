package org.bizilabs.halo.desktop.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.cards.HaloFilledCard
import org.bizilabs.halo.components.cards.HaloOutlinedCard
import org.bizilabs.halo.desktop.screens.accordion.AccordionSection
import org.bizilabs.halo.desktop.screens.avatar.AvatarSection
import org.bizilabs.halo.desktop.screens.badge.BadgeSection
import org.bizilabs.halo.desktop.screens.bottombar.BottomBarSection
import org.bizilabs.halo.desktop.screens.bottomsheet.BottomSheetSection
import org.bizilabs.halo.desktop.screens.button.ButtonSection
import org.bizilabs.halo.desktop.screens.button.IconButtonSection
import org.bizilabs.halo.desktop.screens.card.CardSection
import org.bizilabs.halo.desktop.screens.charts.LineChartSection
import org.bizilabs.halo.desktop.screens.chip.ChipSection
import org.bizilabs.halo.desktop.screens.stepper.HorizontalStepperSection
import org.bizilabs.halo.desktop.screens.stepper.VerticalStepperSection
import org.bizilabs.halo.desktop.screens.textfield.CodeFieldSection
import org.bizilabs.halo.desktop.screens.textfield.TextFieldSection
import org.bizilabs.halo.desktop.screens.topbar.TopBarSection

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AnimatedVisibility(visible = state.section != null) {
                        IconButton(
                            onClick = { onAction(GalleryScreenAction.ClickBack) },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "navigate back",
                            )
                        }
                    }
                    HaloText(
                        modifier = Modifier.padding(16.dp),
                        text = if (state.section != null) state.section.label else "Halo",
                        fontSize = 24.sp,
                    )
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
            backgroundColor = HaloTheme.colorScheme.background.lowest,
            contentColor = HaloTheme.colorScheme.content.stronger,
        ) { pad ->
            AnimatedContent(state.section) { section ->
                when (section) {
                    GalleryScreenSection.Accordion -> AccordionSection()

                    GalleryScreenSection.Card -> CardSection()
                    GalleryScreenSection.Chip -> ChipSection()

                    GalleryScreenSection.Badge -> BadgeSection()

                    GalleryScreenSection.TopBar -> TopBarSection()

                    GalleryScreenSection.TextField -> {
                        GalleryList(
                            sections =
                                listOf(
                                    GalleryScreenSection.TextField.Code,
                                    GalleryScreenSection.TextField.Field,
                                ),
                            onAction = onAction,
                        )
                    }

                    GalleryScreenSection.TextField.Code -> CodeFieldSection()
                    GalleryScreenSection.TextField.Field -> TextFieldSection()
                    GalleryScreenSection.Avatar -> AvatarSection()
                    GalleryScreenSection.Button ->
                        GalleryList(
                            sections =
                                listOf(
                                    GalleryScreenSection.Button.Regular,
                                    GalleryScreenSection.Button.Icon,
                                ),
                            onAction = onAction,
                        )

                    GalleryScreenSection.Button.Icon -> IconButtonSection()
                    GalleryScreenSection.Button.Regular -> ButtonSection()
                    GalleryScreenSection.BottomSheet -> BottomSheetSection()
                    GalleryScreenSection.Stepper -> {
                        GalleryList(
                            sections =
                                listOf(
                                    GalleryScreenSection.Stepper.Horizontal,
                                    GalleryScreenSection.Stepper.Vertical,
                                ),
                            onAction = onAction,
                        )
                    }

                    GalleryScreenSection.Stepper.Horizontal -> HorizontalStepperSection()
                    GalleryScreenSection.Stepper.Vertical -> VerticalStepperSection()
                    GalleryScreenSection.BottomBar -> BottomBarSection()
                    GalleryScreenSection.Charts -> {
                        GalleryList(
                            sections =
                                listOf(
                                    GalleryScreenSection.Charts.Bar,
                                    GalleryScreenSection.Charts.Line,
                                ),
                            onAction = onAction,
                        )
                    }

                    GalleryScreenSection.Charts.Bar -> LineChartSection()
                    GalleryScreenSection.Charts.Line -> LineChartSection()
                    null -> GalleryList(sections = state.sections, onAction = onAction)
                }
            }
        }
    }
}

@Composable
fun GalleryList(
    sections: List<GalleryScreenSection>,
    onAction: (GalleryScreenAction) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sections) {
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
    HaloFilledCard(
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
    HaloOutlinedCard(modifier = modifier.height(150.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            HaloText(text = title)
        }
    }
}
