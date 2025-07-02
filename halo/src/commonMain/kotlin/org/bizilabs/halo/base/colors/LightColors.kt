package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

//Default Light Primary Color
val BaseLightPrimaryDarkerColor = Color(0xFF0024A6)
val BaseLightPrimaryBaseColor = Color(0xFF2454FF)
val BaseLightPrimarySubtleColor = Color(0xFF91A9FF)
val BaseLightPrimaryLightestColor = Color(0xFFEFF3FF)

// Default Light Background Color
private val BaseLightBackgroundContainerColor = Color(0xFFFFFFFF)
private val BaseLightBackgroundContentColor = Color(0xFFEFF3FF)

// Default Light Surface Color
private val BaseLightSurfaceContainerColor = Color(0xFFF0F0F0)
private val BaseLightSurfaceContentColor = Color(0xFF0F0F0F)

// Default Light Success Color
private val BaseLightSuccessDarkerColor = Color(0xFF367C39)
private val BaseLightSuccessBaseColor = Color(0xFF4CAF50)
private val BaseLightSuccessSubtleColor = Color(0xFFB2DDB3)
private val BaseLightSuccessLightestColor = Color(0xFFE5F4E6)

// Default Light Error Color
private val BaseLightErrorDarkerColor = Color(0xFFA11308)
private val BaseLightErrorBaseColor = Color(0xFFF44336)
private val BaseLightErrorSubtleColor = Color(0xFFFAADA8)
private val BaseLightErrorLightestColor = Color(0xFFFEF1F0)

// Default Light Warning Color
private val BaseLightWarningDarkerColor = Color(0xFFA77D00)
private val BaseLightWarningBaseColor = Color(0xFFFFC107)
private val BaseLightWarningSubtleColor = Color(0xFFFFE494)
private val BaseLightWarningLightestColor = Color(0xFFFFFBED)

// Default Light Info Color
private val BaseLightInfoDarkerColor = Color(0xFF0960A8)
private val BaseLightInfoBaseColor = Color(0xFF2196F3)
private val BaseLightInfoSubtleColor = Color(0xFF90CAF9)
private val BaseLightInfoLightestColor = Color(0xFFEFF7FE)

internal val HaloLightColorScheme =
    HaloColorScheme(
        primary = HaloColorValue(
            dark = BaseLightPrimaryDarkerColor,
            base = BaseLightPrimaryBaseColor,
            subtle = BaseLightPrimarySubtleColor,
            onBase = BaseLightPrimaryLightestColor),
        background =
            HaloBackgroundValue(
                base = BaseLightBackgroundContainerColor,
                onBase = BaseLightBackgroundContentColor,
                surface = BaseLightSurfaceContainerColor, // Tbd
                onSurface = BaseLightSurfaceContentColor, // tbd
            ),
        success =
            HaloColorValue(
                dark = BaseLightSuccessDarkerColor,
                base = BaseLightSuccessBaseColor,
                subtle = BaseLightSuccessSubtleColor,
                onBase = BaseLightSuccessLightestColor,
            ),
        error =
            HaloColorValue(
                dark = BaseLightErrorDarkerColor,
                base = BaseLightErrorBaseColor,
                subtle = BaseLightErrorSubtleColor,
                onBase = BaseLightErrorLightestColor,
            ),
        warning =
            HaloColorValue(
                dark = BaseLightWarningDarkerColor,
                base = BaseLightWarningBaseColor,
                subtle = BaseLightWarningSubtleColor,
                onBase = BaseLightWarningLightestColor,
            ),
        info =
            HaloColorValue(
                dark = BaseLightInfoDarkerColor,
                base = BaseLightInfoBaseColor,
                subtle = BaseLightInfoSubtleColor,
                onBase = BaseLightInfoLightestColor,
            ),
    )
