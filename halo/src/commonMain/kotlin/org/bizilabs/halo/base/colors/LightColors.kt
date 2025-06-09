package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

// Default Light Background Color
private val BaseLightBackgroundContainerColor = Color(0xFFFFFFFF)
private val BaseLightBackgroundContentColor = Color(0xFF000000)

// Default Light Surface Color
private val BaseLightSurfaceContainerColor = Color(0xFFF0F0F0)
private val BaseLightSurfaceContentColor = Color(0xFF0F0F0F)

internal val HaloLightColorScheme =
    HaloColorScheme(
        background =
            HaloColorValue(
                content = BaseLightBackgroundContentColor,
                container = BaseLightBackgroundContainerColor,
            ),
        surface =
            HaloColorValue(
                content = BaseLightSurfaceContentColor,
                container = BaseLightSurfaceContainerColor,
            ),
    )
