package org.bizilabs.halo.base.colors

import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.base.HaloColorScheme

/**
 * Polar : Light Background Elevation colors
 */
private val PolarLightBackgroundHighest = Color(0xFFD0D1D0)
private val PolarLightBackgroundHigh = Color(0xFFD9D9D9)
private val PolarLightBackgroundMiddle = Color(0xFFE2E3E2)
private val PolarLightBackgroundLow = Color(0xFFECEDEC)
private val PolarLightBackgroundLowest = Color(0xFFF7F6F7)

/**
 * Polar : Dark Background Elevation colors
 */
private val PolarDarkBackgroundHighest = Color(0xFF4C4C4D)
private val PolarDarkBackgroundHigh = Color(0xFF414140)
private val PolarDarkBackgroundMiddle = Color(0xFF363736)
private val PolarDarkBackgroundLow = Color(0xFF2A2A2B)
private val PolarDarkBackgroundLowest = Color(0xFF1F1F1E)

internal val PolarLightColorScheme =
    HaloColorScheme(
        primary = HaloLightColorScheme.primary,
        background =
            HaloElevationColor(
                highest = PolarLightBackgroundHighest,
                high = PolarLightBackgroundHigh,
                middle = PolarDarkBackgroundMiddle,
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
                middle = PolarLightBackgroundMiddle,
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
