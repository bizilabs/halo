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

// Default Light Success Color
private val BaseDarkSuccessContainerColor = Color(0xFF388E3C)
private val BaseDarkSuccessContentColor = Color(0xFFFFFFFF)

// Default Dark Error Color
private val BaseDarkErrorContainerColor = Color(0xFFFF4E47)
private val BaseDarkErrorContentColor = Color(0xFFFFFFFF)

// Default Dark Warning Color
private val BaseDarkWarningContainerColor = Color(0xFFFFA000)
private val BaseDarkWarningContentColor = Color(0xFF000000)

// Default Dark Info Color
private val BaseDarkInfoContainerColor = Color(0xFF1976D2)
private val BaseDarkInfoContentColor = Color(0xFFFFFFFF)

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
        success =
            HaloColorValue(
                container = BaseDarkSuccessContainerColor,
                content = BaseDarkSuccessContentColor,
            ),
        error =
            HaloColorValue(
                container = BaseDarkErrorContainerColor,
                content = BaseDarkErrorContentColor,
            ),
        warning =
            HaloColorValue(
                container = BaseDarkWarningContainerColor,
                content = BaseDarkWarningContentColor,
            ),
        info =
            HaloColorValue(
                container = BaseDarkInfoContainerColor,
                content = BaseDarkInfoContentColor,
            ),
    )
