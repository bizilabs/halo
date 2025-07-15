package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

// Default Light Primary Color
val BaseDarkPrimaryDarkerColor = Color(0xFF3A065A)
val BaseDarkPrimaryDarkColor = Color(0xFF690BA2)
val BaseDarkPrimaryBaseColor = Color(0xFFA020F0)
val BaseDarkPrimarySubtleColor = Color(0xFFC87FF6)
val BaseDarkPrimaryLightestColor = Color(0xFFF1DFFD)

// Default Dark Background Color
private val BaseDarkBackgroundContainerColor = Color(0xFF0A0A0A)
private val BaseDarkSurfaceContainerColor = Color(0xFF141414)

// Default Dark Surface Color
private val BaseDarkContentStrongerColor = Color(0xFFFFFFFF)
private val BaseDarkContentStrongColor = Color(0xFFEBEBEB)
private val BaseDarkContentNeutralColor = Color(0xFFBDBDBD)
private val BaseDarkContentWeakColor = Color(0xFF474747)
private val BaseDarkContentWeakerColor = Color(0xFF212122)

// Default Light Success Color
private val BaseDarkSuccessDarkerColor = Color(0xFF163217)
private val BaseDarkSuccessDarkColor = Color(0xFF245B27)
private val BaseDarkSuccessBaseColor = Color(0xFF388E3C)
private val BaseDarkSuccessSubtleColor = Color(0xFF90D393)
private val BaseDarkSuccessLightestColor = Color(0xFFFFFFFF)

// Default Dark Error Color
private val BaseDarkErrorDarkerColor = Color(0xFF500904)
private val BaseDarkErrorDarkColor = Color(0xFF8C0500)
private val BaseDarkErrorBaseColor = Color(0xFFFF4E47)
private val BaseDarkErrorSubtleColor = Color(0xFFFFA6A3)
private val BaseDarkErrorLightestColor = Color(0xFFFFFFFF)

// Default Dark Warning Color
private val BaseDarkWarningDarkerColor = Color(0xFF4A3800)
private val BaseDarkWarningDarkColor = Color(0xFFA46800)
private val BaseDarkWarningBaseColor = Color(0xFFFFA000)
private val BaseDarkWarningSubtleColor = Color(0xFFFFCA6D)
private val BaseDarkWarningLightestColor = Color(0xFF000000)

// Default Dark Info Color
private val BaseDarkInfoDarkerColor = Color(0xFF001253)
private val BaseDarkInfoDarkColor = Color(0xFF0024A6)
private val BaseDarkInfoBaseColor = Color(0xFF2454FF)
private val BaseDarkInfoSubtleColor = Color(0xFF91A9FF)
private val BaseDarkInfoLightestColor = Color(0xFFEFF3FF)

internal val HaloDarkColorScheme =
    HaloColorScheme(
        primary =
            HaloColorValue(
                stronger = BaseDarkPrimaryDarkerColor,
                strong = BaseDarkPrimaryDarkColor,
                neutral = BaseDarkPrimaryBaseColor,
                weak = BaseDarkPrimarySubtleColor,
                weaker = BaseDarkPrimaryLightestColor,
            ),
        background =
            HaloBackgroundValue(
                base = BaseDarkBackgroundContainerColor,
                surface = BaseDarkSurfaceContainerColor,
            ),
        content = HaloColorValue(
            stronger = BaseDarkContentStrongerColor,
            strong = BaseDarkContentStrongColor,
            neutral = BaseDarkContentNeutralColor,
            weak = BaseDarkContentWeakColor,
            weaker = BaseDarkContentWeakerColor
        ),
        success =
            HaloColorValue(
                stronger = BaseDarkSuccessDarkerColor,
                strong = BaseDarkSuccessDarkColor,
                neutral = BaseDarkSuccessBaseColor,
                weaker = BaseDarkSuccessLightestColor,
                weak = BaseDarkSuccessSubtleColor,
            ),
        error =
            HaloColorValue(
                stronger = BaseDarkInfoDarkerColor,
                strong = BaseDarkErrorDarkColor,
                neutral = BaseDarkErrorBaseColor,
                weak = BaseDarkErrorSubtleColor,
                weaker = BaseDarkErrorLightestColor,
            ),
        warning =
            HaloColorValue(
                stronger = BaseDarkWarningDarkerColor,
                strong = BaseDarkWarningDarkColor,
                neutral = BaseDarkWarningBaseColor,
                weak = BaseDarkWarningSubtleColor,
                weaker = BaseDarkWarningLightestColor,
            ),
        info =
            HaloColorValue(
                stronger = BaseDarkInfoDarkerColor,
                strong = BaseDarkInfoDarkColor,
                neutral = BaseDarkInfoBaseColor,
                weak = BaseDarkInfoSubtleColor,
                weaker = BaseDarkInfoLightestColor,
            ),
        disabled =
            HaloColor(
                container = Gray900,
                content = Gray600,
                border = Gray600,
            ),
    )
