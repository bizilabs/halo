package org.bizilabs.halo.desktop.screens

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.base.HaloColorTheme

sealed interface GalleryScreenSection {
    val label: String

    data object Accordion : GalleryScreenSection {
        override val label: String
            get() = "Accordion"
    }

    data object Badge : GalleryScreenSection {
        override val label: String
            get() = "Badge"
    }

    sealed class Button : GalleryScreenSection {
        companion object : Button()

        override val label: String
            get() = "Button"

        data object Regular : Button() {
            override val label: String
                get() = "Regular"
        }

        data object Icon : Button() {
            override val label: String
                get() = "Icon"
        }
    }

    data object Card : GalleryScreenSection {
        override val label: String
            get() = "Card"
    }

    data object TopBar : GalleryScreenSection {
        override val label: String
            get() = "TopBar"
    }

    sealed class TextField : GalleryScreenSection {
        companion object : TextField()

        override val label: String
            get() = "TextField"

        data object Code : TextField() {
            override val label: String
                get() = "Code"
        }

        data object Field : TextField() {
            override val label: String
                get() = "Field"
        }
    }

    data object Chip : GalleryScreenSection {
        override val label: String
            get() = "Chip"
    }

    data object Avatar : GalleryScreenSection {
        override val label: String
            get() = "Avatar"
    }

    data object BottomSheet : GalleryScreenSection {
        override val label: String
            get() = "BottomSheet"
    }

    sealed class Stepper : GalleryScreenSection {
        companion object : Stepper()

        override val label: String
            get() = "Stepper"

        data object Horizontal : Stepper() {
            override val label: String
                get() = "Horizontal"
        }

        data object Vertical : Stepper() {
            override val label: String
                get() = "Vertical"
        }
    }

    sealed class Charts : GalleryScreenSection {
        companion object : Charts()

        override val label: String
            get() = "Charts"

        data object Bar : Charts() {
            override val label: String
                get() = "Bar"
        }

        data object Line : Charts() {
            override val label: String
                get() = "Line"
        }
    }

    data object BottomBar : GalleryScreenSection {
        override val label: String
            get() = "BottomBar"
    }

    data object Switch : GalleryScreenSection {
        override val label: String
            get() = "Toogle"
    }

    companion object {
        val values: List<GalleryScreenSection>
            get() =
                listOf(
                    Accordion,
                    Avatar,
                    Badge,
                    BottomBar,
                    BottomSheet,
                    Button,
                    Card,
                    Charts,
                    Chip,
                    Stepper,
                    TextField,
                    TopBar,
                    Switch,
                )
    }
}

sealed interface GalleryScreenAction {
    data class UpdateSection(
        val section: GalleryScreenSection,
    ) : GalleryScreenAction

    data object ToggleTheme : GalleryScreenAction

    data object ClickBack : GalleryScreenAction
}

data class GalleryScreenState(
    val isDarkModeEnabled: Boolean = false,
    val colorTheme: HaloColorTheme = HaloDefaults.ColorThemes.Polar,
    val section: GalleryScreenSection? = null,
    val sections: List<GalleryScreenSection> = GalleryScreenSection.values,
)

class GalleryScreenModel : StateScreenModel<GalleryScreenState>(GalleryScreenState()) {
    fun onAction(action: GalleryScreenAction) {
        when (action) {
            is GalleryScreenAction.UpdateSection -> updateSection(action.section)
            GalleryScreenAction.ToggleTheme -> toggleTheme()
            GalleryScreenAction.ClickBack -> navigateBack()
        }
    }

    private fun updateSection(section: GalleryScreenSection) {
        mutableState.update { it.copy(section = section) }
    }

    private fun toggleTheme() {
        mutableState.update { it.copy(isDarkModeEnabled = !it.isDarkModeEnabled) }
    }

    private fun navigateBack() {
        val update =
            when (state.value.section) {
                GalleryScreenSection.TextField.Code -> GalleryScreenSection.TextField
                GalleryScreenSection.TextField.Field -> GalleryScreenSection.TextField
                GalleryScreenSection.Button.Regular -> GalleryScreenSection.Button
                GalleryScreenSection.Button.Icon -> GalleryScreenSection.Button
                GalleryScreenSection.Stepper.Horizontal -> GalleryScreenSection.Stepper
                GalleryScreenSection.Stepper.Vertical -> GalleryScreenSection.Stepper
                GalleryScreenSection.Charts.Line -> GalleryScreenSection.Charts
                GalleryScreenSection.Charts.Bar -> GalleryScreenSection.Charts
                else -> null
            }
        mutableState.update { it.copy(section = update) }
    }
}
