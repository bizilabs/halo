package org.bizilabs.halo.desktop.screens

import androidx.compose.material.Badge
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

    data object Card : GalleryScreenSection {
        override val label: String
            get() = "Card"
    }

    companion object {
        val values: List<GalleryScreenSection>
            get() = listOf(Accordion, Badge, Card)
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
    val colorTheme: HaloColorTheme = HaloDefaults.ColorThemes.Default,
    val section: GalleryScreenSection? = null,
    val sections: List<GalleryScreenSection> = GalleryScreenSection.values,
)

class GalleryScreenModel : StateScreenModel<GalleryScreenState>(GalleryScreenState()) {
    fun onAction(action: GalleryScreenAction) {
        when (action) {
            is GalleryScreenAction.UpdateSection -> updateSection(action.section)
            GalleryScreenAction.ToggleTheme -> toggleTheme()
            GalleryScreenAction.ClickBack -> mutableState.update { it.copy(section = null) }
        }
    }

    private fun updateSection(section: GalleryScreenSection) {
        mutableState.update { it.copy(section = section) }
    }

    private fun toggleTheme() {
        mutableState.update { it.copy(isDarkModeEnabled = !it.isDarkModeEnabled) }
    }
}
