package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

// Default Dark Background Color
private val BaseDarkBackgroundContainerColor = Color(0xFF0A0A0A)
private val BaseDarkBackgroundContentColor = Color(0xFFFFFFFF)

// Default Dark Surface Color
private val BaseDarkSurfaceContainerColor = Color(0xFF141414)
private val BaseDarkSurfaceContentColor = Color(0xFFEBEBEB)

internal val HaloDarkColorScheme =
    HaloColorScheme(
        background =
            HaloColorValue(
                container = BaseDarkBackgroundContainerColor,
                content = BaseDarkBackgroundContentColor,
            ),
        surface =
            HaloColorValue(
                container = BaseDarkSurfaceContainerColor,
                content = BaseDarkSurfaceContentColor,
            ),
    )
