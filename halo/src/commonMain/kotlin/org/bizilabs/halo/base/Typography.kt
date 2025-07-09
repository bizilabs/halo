package org.bizilabs.halo.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class HaloTypography internal constructor(
    private val privateFontFamily: FontFamily,

    val titleLarge: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.5).em
    ),
    val titleMedium: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 44.sp
    ),
    val titleSmall: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    val subTitle: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    val bodyLargeBold: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val bodyMediumBold: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    val bodySmallBold: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = privateFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp
    ),
) {
    constructor(
        fontFamily: FontFamily,
    ) : this(
        privateFontFamily = fontFamily,
    )
}

val LocalHaloTypography = staticCompositionLocalOf {
    HaloTypography(fontFamily = FontFamily.Default)
}

internal fun provideTypography(typography: HaloTypography) =
    LocalHaloTypography provides typography

/**
 * CompositionLocal containing the preferred [TextStyle] that will be used by [Text] components by
 * default. To set the value for this CompositionLocal, see [ProvideTextStyle] which will merge any
 * missing [TextStyle] properties with the existing [TextStyle] set in this CompositionLocal.
 */
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { TextStyle.Default }

/**
 * This function is used to set the current value of [LocalTextStyle], merging the given style
 * with the current style values for any missing attributes. Any [Text] components included in
 * this component's [content] will be styled with this style unless styled explicitly.
 */
@Composable
fun ProvideTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = LocalTextStyle.current.merge(value)
    CompositionLocalProvider(LocalTextStyle provides mergedStyle, content = content)
}