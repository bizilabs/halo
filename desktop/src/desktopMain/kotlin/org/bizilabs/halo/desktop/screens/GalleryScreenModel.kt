package org.bizilabs.halo.desktop.screens

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

sealed interface GalleryScreenSection {
    val label: String

    data object Accordion : GalleryScreenSection {
        override val label: String
            get() = "Accordion"
    }

    companion object {
        val values: List<GalleryScreenSection>
            get() = listOf(Accordion)
    }
}

sealed interface GalleryScreenAction {
    data class UpdateSection(
        val section: GalleryScreenSection,
    ) : GalleryScreenAction
}

data class GalleryScreenState(
    val section: GalleryScreenSection? = null,
    val sections: List<GalleryScreenSection> = GalleryScreenSection.values,
)

class GalleryScreenModel : StateScreenModel<GalleryScreenState>(GalleryScreenState()) {
    fun onAction(action: GalleryScreenAction) {
        when (action) {
            is GalleryScreenAction.UpdateSection -> updateSection(action.section)
        }
    }

    private fun updateSection(section: GalleryScreenSection) {
        mutableState.update { it.copy(section = section) }
    }
}
