package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloColorScheme

private val PolarLightBackgroundHighest = Color(0xFFD4D4D4)
private val PolarLightBackgroundHigh = Color(0xFFE5E5E5)
private val PolarLightBackgroundLow = Color(0xFFF5F5F5)
private val PolarLightBackgroundLowest = Color(0xFFFAFAFA)

private val PolarDarkBackgroundHighest = Color(0xFF0A0A0A)
private val PolarDarkBackgroundHigh = Color(0xFF171717)
private val PolarDarkBackgroundLow = Color(0xFF1F1F1F)
private val PolarDarkBackgroundLowest = Color(0xFF333333)
internal val PolarLightColorScheme =
    HaloColorScheme(
        primary = HaloLightColorScheme.primary,
        background =
            HaloElevationColor(
                highest = PolarLightBackgroundHighest,
                high = PolarLightBackgroundHigh,
                low = PolarLightBackgroundLow,
                lowest = PolarLightBackgroundLowest,
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
            HaloElevationColor(
                highest = PolarDarkBackgroundHighest,
                high = PolarDarkBackgroundHigh,
                low = PolarDarkBackgroundLow,
                lowest = PolarDarkBackgroundLowest,
            ),
        content = HaloDarkColorScheme.content,
        success = HaloDarkColorScheme.success,
        error = HaloDarkColorScheme.error,
        warning = HaloDarkColorScheme.warning,
        info = HaloDarkColorScheme.info,
        disabled = HaloDarkColorScheme.disabled,
    )
