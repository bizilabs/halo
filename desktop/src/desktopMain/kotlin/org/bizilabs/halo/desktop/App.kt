package org.bizilabs.halo.desktop

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.desktop.screens.GalleryScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    HaloTheme {
        Navigator(GalleryScreen)
    }
}
