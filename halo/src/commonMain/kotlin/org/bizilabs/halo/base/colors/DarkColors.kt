package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

//Default Light Primary Color
val BaseDarkPrimaryDarkerColor = Color(0xFF0028BBF)
val BaseDarkPrimaryBaseColor = Color(0xFF2454FF)
val BaseDarkPrimarySubtleColor = Color(0xFF91A9FF)
val BaseDarkPrimaryLightestColor = Color(0xFFFFFFF)

// Default Dark Background Color
private val BaseDarkBackgroundContainerColor = Color(0xFF0A0A0A)
private val BaseDarkBackgroundContentColor = Color(0xFFFFFFFF)

// Default Dark Surface Color
private val BaseDarkSurfaceContainerColor = Color(0xFF141414)
private val BaseDarkSurfaceContentColor = Color(0xFFEBEBEB)

// Default Light Success Color
private val BaseDarkSuccessDarkerColor = Color(0xFF245B27)
private val BaseDarkSuccessBaseColor = Color(0xFF388E3C)
private val BaseDarkSuccessSubtleColor = Color(0xFF90D393)
private val BaseDarkSuccessLightestColor = Color(0xFFFFFFFF)

// Default Dark Error Color
private val BaseDarkErrorDarkerColor = Color(0xFF8C0500)
private val BaseDarkErrorBaseColor = Color(0xFFFF4E47)
private val BaseDarkErrorSubtleColor = Color(0xFFFFA6A3)
private val BaseDarkErrorLightestColor = Color(0xFFFFFFFF)

// Default Dark Warning Color
private val BaseDarkWarningDarkerColor = Color(0xFFA46800)
private val BaseDarkWarningBaseColor = Color(0xFFFFA000)
private val BaseDarkWarningSubtleColor = Color(0xFFFFCA6D)
private val BaseDarkWarningLightestColor = Color(0xFF000000)

// Default Dark Info Color
private val BaseDarkInfoDarkerColor = Color(0xFF104B87)
private val BaseDarkInfoBaseColor = Color(0xFF1976D2)
private val BaseDarkInfoLightestColor = Color(0xFFFFFFFF)
private val BaseDarkInfoSubtleColor = Color(0xFF84BAF1)

internal val HaloDarkColorScheme =
    HaloColorScheme(
        primary = HaloColorValue(
            dark = BaseDarkPrimaryDarkerColor,
            base = BaseDarkPrimaryBaseColor,
            subtle = BaseDarkPrimarySubtleColor,
            onBase = BaseDarkPrimaryLightestColor,
        ),
        background =
            HaloBackgroundValue(
                base = BaseDarkBackgroundContainerColor,
                onBase = BaseDarkBackgroundContentColor,
                surface = BaseDarkSurfaceContainerColor,
                onSurface = BaseDarkSurfaceContentColor
            ),
        success =
            HaloColorValue(
                dark = BaseDarkSuccessDarkerColor,
                base = BaseDarkSuccessBaseColor,
                onBase = BaseDarkSuccessLightestColor,
                subtle = BaseDarkSuccessSubtleColor
            ),
        error =
            HaloColorValue(
                dark = BaseDarkErrorDarkerColor,
                base = BaseDarkErrorBaseColor,
                subtle = BaseDarkErrorSubtleColor,
                onBase = BaseDarkErrorLightestColor,
            ),
        warning =
            HaloColorValue(
                dark = BaseDarkWarningDarkerColor,
                base = BaseDarkWarningBaseColor,
                subtle = BaseDarkWarningSubtleColor,
                onBase = BaseDarkWarningLightestColor,
            ),
        info =
            HaloColorValue(
                dark = BaseDarkInfoDarkerColor,
                base = BaseDarkInfoBaseColor,
                subtle = BaseDarkInfoSubtleColor,
                onBase = BaseDarkInfoLightestColor,
            ),
    )
