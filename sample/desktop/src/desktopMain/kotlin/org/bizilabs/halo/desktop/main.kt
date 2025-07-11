@file:Suppress("ktlint:standard:filename")

package org.bizilabs.halo.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Halo",
        ) {
            window.minimumSize = Dimension(720, 500)
            App()
        }
    }
