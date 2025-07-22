package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.HaloColorScheme
import org.bizilabs.halo.base.HaloColorValue

// Default Light Primary Color
val BaseDarkPrimaryDarkerColor = Color(0xFF25033A)
val BaseDarkPrimaryDarkColor = Color(0xFF560887)
val BaseDarkPrimaryBaseColor = Color(0xFFA020F0)
val BaseDarkPrimaryLightColor = Color(0xFFCE8DF7)
val BaseDarkPrimaryLightestColor = Color(0xFFDEB3FA)

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
private val BaseDarkSuccessDarkerColor = Color(0xFF0C1D0D)
private val BaseDarkSuccessDarkColor = Color(0xFF1D491E)
private val BaseDarkSuccessBaseColor = Color(0xFF388E3C)
private val BaseDarkSuccessLightColor = Color(0xFF7CCB80)
private val BaseDarkSuccessLightestColor = Color(0xFFB6E2B8)

// Default Dark Error Color
private val BaseDarkErrorDarkerColor = Color(0xFF290000)
private val BaseDarkErrorDarkColor = Color(0xFF520300)
private val BaseDarkErrorBaseColor = Color(0xFFFF4E47)
private val BaseDarkErrorLightColor = Color(0xFFFF8B85)
private val BaseDarkErrorLightestColor = Color(0xFFFFB1AD)

// Default Dark Warning Color
private val BaseDarkWarningDarkerColor = Color(0xFF291500)
private val BaseDarkWarningDarkColor = Color(0xFF663500)
private val BaseDarkWarningBaseColor = Color(0xFFFF890A)
private val BaseDarkWarningLightColor = Color(0xFFFFB05C)
private val BaseDarkWarningLightestColor = Color(0xFFFFD8AD)

// Default Dark Info Color
private val BaseDarkInfoDarkerColor = Color(0xFF000E3E)
private val BaseDarkInfoDarkColor = Color(0xFF001B7A)
private val BaseDarkInfoBaseColor = Color(0xFF2454FF)
private val BaseDarkInfoLightColor = Color(0xFF859FFF)
private val BaseDarkInfoLightestColor = Color(0xFFC2CFFF)

internal val HaloDarkColorScheme =
    HaloColorScheme(
        primary =
            HaloColorValue(
                stronger = BaseDarkPrimaryLightestColor,
                strong = BaseDarkPrimaryLightColor,
                neutral = BaseDarkPrimaryBaseColor,
                weak = BaseDarkPrimaryDarkColor,
                weaker = BaseDarkPrimaryDarkerColor,
            ),
        background =
            HaloBackgroundValue(
                base = BaseDarkBackgroundContainerColor,
                surface = BaseDarkSurfaceContainerColor,
            ),
        content =
            HaloColorValue(
                stronger = BaseDarkContentStrongerColor,
                strong = BaseDarkContentStrongColor,
                neutral = BaseDarkContentNeutralColor,
                weak = BaseDarkContentWeakColor,
                weaker = BaseDarkContentWeakerColor,
            ),
        success =
            HaloColorValue(
                stronger = BaseDarkSuccessLightestColor,
                strong = BaseDarkSuccessLightColor,
                neutral = BaseDarkSuccessBaseColor,
                weak = BaseDarkSuccessDarkColor,
                weaker = BaseDarkSuccessDarkerColor,
            ),
        error =
            HaloColorValue(
                stronger = BaseDarkErrorLightestColor,
                strong = BaseDarkErrorLightColor,
                neutral = BaseDarkErrorBaseColor,
                weak = BaseDarkErrorDarkColor,
                weaker = BaseDarkErrorDarkerColor,
            ),
        warning =
            HaloColorValue(
                stronger = BaseDarkWarningLightestColor,
                strong = BaseDarkWarningLightColor,
                neutral = BaseDarkWarningBaseColor,
                weak = BaseDarkWarningDarkColor,
                weaker = BaseDarkWarningDarkerColor,
            ),
        info =
            HaloColorValue(
                stronger = BaseDarkInfoLightestColor,
                strong = BaseDarkInfoLightColor,
                neutral = BaseDarkInfoBaseColor,
                weak = BaseDarkInfoDarkColor,
                weaker = BaseDarkInfoDarkerColor,
            ),
        disabled =
            HaloColor(
                container = Gray900,
                content = Gray600,
                border = Gray600,
            ),
    )
