package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

// Default Light Primary Color
val BaseLightPrimaryDarkerColor = Color(0xFF3A065A)
val BaseLightPrimaryDarkColor = Color(0xFF690BA2)
val BaseLightPrimaryBaseColor = Color(0xFFA020F0)
val BaseLightPrimarySubtleColor = Color(0xFFC87FF6)
val BaseLightPrimaryLightestColor = Color(0xFFF1DFFD)

// Default Light Background Color
private val BaseLightBackgroundContainerColor = Color(0xFFFFFFFF)
private val BaseLightBackgroundContentColor = Color(0xFF000000)

// Default Light Surface Color
private val BaseLightSurfaceContainerColor = Color(0xFFF0F0F0)
private val BaseLightSurfaceContentColor = Color(0xFF0F0F0F)

// Default Light Success Color
private val BaseLightSuccessDarkerColor = Color(0xFF163217)
private val BaseLightSuccessDarkColor = Color(0xFF367C39)
private val BaseLightSuccessBaseColor = Color(0xFF4CAF50)
private val BaseLightSuccessSubtleColor = Color(0xFFB2DDB3)
private val BaseLightSuccessLightestColor = Color(0xFFE5F4E6)

// Default Light Error Color
private val BaseLightErrorDarkerColor = Color(0xFF500904)
private val BaseLightErrorDarkColor = Color(0xFFA11308)
private val BaseLightErrorBaseColor = Color(0xFFF44336)
private val BaseLightErrorSubtleColor = Color(0xFFFAADA8)
private val BaseLightErrorLightestColor = Color(0xFFFEF1F0)

// Default Light Warning Color
private val BaseLightWarningDarkerColor = Color(0xFF4A3800)
private val BaseLightWarningDarkColor = Color(0xFFA77D00)
private val BaseLightWarningBaseColor = Color(0xFFFFC107)
private val BaseLightWarningSubtleColor = Color(0xFFFFE494)
private val BaseLightWarningLightestColor = Color(0xFFFFFBED)

// Default Light Info Color
private val BaseLightInfoDarkerColor = Color(0xFF001253)
private val BaseLightInfoDarkColor = Color(0xFF0024A6)
private val BaseLightInfoBaseColor = Color(0xFF2454FF)
private val BaseLightInfoSubtleColor = Color(0xFF91A9FF)
private val BaseLightInfoLightestColor = Color(0xFFEFF3FF)

internal val HaloLightColorScheme =
    HaloColorScheme(
        primary =
            HaloColorValue(
                stronger = BaseLightPrimaryDarkerColor,
                strong = BaseLightPrimaryDarkColor,
                neutral = BaseLightPrimaryBaseColor,
                weak = BaseLightPrimarySubtleColor,
                weaker = BaseLightPrimaryLightestColor,
            ),
        background =
            HaloBackgroundValue(
                base = BaseLightBackgroundContainerColor,
                onBase = BaseLightBackgroundContentColor,
                surface = BaseLightSurfaceContainerColor,
                onSurface = BaseLightSurfaceContentColor,
            ),
        success =
            HaloColorValue(
                stronger = BaseLightSuccessDarkerColor,
                strong = BaseLightSuccessDarkColor,
                neutral = BaseLightSuccessBaseColor,
                weak = BaseLightSuccessSubtleColor,
                weaker = BaseLightSuccessLightestColor,
            ),
        error =
            HaloColorValue(
                stronger = BaseLightErrorDarkerColor,
                strong = BaseLightErrorDarkColor,
                neutral = BaseLightErrorBaseColor,
                weak = BaseLightErrorSubtleColor,
                weaker = BaseLightErrorLightestColor,
            ),
        warning =
            HaloColorValue(
                stronger = BaseLightWarningDarkerColor,
                strong = BaseLightWarningDarkColor,
                neutral = BaseLightWarningBaseColor,
                weak = BaseLightWarningSubtleColor,
                weaker = BaseLightWarningLightestColor,
            ),
        info =
            HaloColorValue(
                stronger = BaseLightInfoDarkerColor,
                strong = BaseLightInfoDarkColor,
                neutral = BaseLightInfoBaseColor,
                weak = BaseLightInfoSubtleColor,
                weaker = BaseLightInfoLightestColor,
            ),
        disabled =
            HaloColor(
                container = Gray200,
                content = Gray500,
                border = Gray400,
            ),
    )
