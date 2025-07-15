package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloBackgroundValue
import org.bizilabs.halo.base.HaloColorScheme

private val PolarLightBackgroundColor = Color(0xFFFFFFFF)
private val PolarLightOnBackgroundColor = Color(0xFF000000)
private val PolarLightSurfaceColor = Color(0xFFEBEBEB)
private val PolarLightOnSurfaceColor = Color(0xFF141414)

private val PolarDarkBackgroundColor = Color(0xFF000000)
private val PolarDarkOnBackgroundColor = Color(0xFFFFFFFF)
private val PolarDarkSurfaceColor = Color(0xFF141414)
private val PolarDarkOnSurfaceColor = Color(0xFFEBEBEB)

internal val PolarLightColorScheme =
    HaloColorScheme(
        primary = HaloLightColorScheme.primary,
        background =
            HaloBackgroundValue(
                base = PolarLightBackgroundColor,
                surface = PolarLightSurfaceColor,
            ),
        content = HaloLightColorScheme.content,
        success = HaloLightColorScheme.success,
        error = HaloLightColorScheme.error,
        warning = HaloLightColorScheme.warning,
        info = HaloLightColorScheme.info,
        disabled = HaloLightColorScheme.disabled,
    )

internal val PolarDarkColorScheme =
    HaloColorScheme(
        primary = HaloDarkColorScheme.primary,
        background =
            HaloBackgroundValue(
                base = PolarDarkBackgroundColor,
                surface = PolarDarkSurfaceColor,
            ),
        content = HaloDarkColorScheme.content,
        success = HaloDarkColorScheme.success,
        error = HaloDarkColorScheme.error,
        warning = HaloDarkColorScheme.warning,
        info = HaloDarkColorScheme.info,
        disabled = HaloDarkColorScheme.disabled,
    )
