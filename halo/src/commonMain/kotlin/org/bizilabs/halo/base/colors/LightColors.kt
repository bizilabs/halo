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

// Default Light Success Color
private val BaseLightSuccessContainerColor = Color(0xFF4CAF50)
private val BaseLightSuccessContentColor = Color(0xFFFFFFFF)

// Default Light Error Color
private val BaseLightErrorContainerColor = Color(0xFFF44336)
private val BaseLightErrorContentColor = Color(0xFFFFFFFF)

// Default Light Warning Color
private val BaseLightWarningContainerColor = Color(0xFFFFC107)
private val BaseLightWarningContentColor = Color(0xFFFFFFFF)

// Default Light Info Color
private val BaseLightInfoContainerColor = Color(0xFF2196F3)
private val BaseLightInfoContentColor = Color(0xFFFFFFFF)

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
        success =
            HaloColorValue(
                content = BaseLightSuccessContentColor,
                container = BaseLightSuccessContainerColor,
            ),
        error =
            HaloColorValue(
                content = BaseLightErrorContentColor,
                container = BaseLightErrorContainerColor,
            ),
        warning =
            HaloColorValue(
                content = BaseLightWarningContentColor,
                container = BaseLightWarningContainerColor,
            ),
        info =
            HaloColorValue(
                content = BaseLightInfoContentColor,
                container = BaseLightInfoContainerColor,
            ),
    )
