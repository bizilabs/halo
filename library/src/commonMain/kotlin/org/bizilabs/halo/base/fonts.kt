@file:Suppress("ktlint:standard:filename", "ktlint:standard:property-naming")

package org.bizilabs.halo.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import halo.library.generated.resources.Res
import halo.library.generated.resources.black
import halo.library.generated.resources.bold
import halo.library.generated.resources.bold_extra
import halo.library.generated.resources.bold_semi
import halo.library.generated.resources.light
import halo.library.generated.resources.light_extra
import halo.library.generated.resources.medium
import halo.library.generated.resources.mono_black
import halo.library.generated.resources.mono_bold
import halo.library.generated.resources.mono_bold_extra
import halo.library.generated.resources.mono_bold_semi
import halo.library.generated.resources.mono_light
import halo.library.generated.resources.mono_light_extra
import halo.library.generated.resources.mono_medium
import halo.library.generated.resources.mono_regular
import halo.library.generated.resources.mono_thin
import halo.library.generated.resources.regular
import halo.library.generated.resources.thin
import org.jetbrains.compose.resources.Font

private data object RegularFont {
    val Black = Res.font.black
    val ExtraBold = Res.font.bold_extra
    val Bold = Res.font.bold
    val SemiBold = Res.font.bold_semi
    val Medium = Res.font.medium
    val Regular = Res.font.regular
    val Light = Res.font.light
    val ExtraLight = Res.font.light_extra
    val Thin = Res.font.thin

    val FontFamily: FontFamily
        @Composable
        get() =
            FontFamily(
                Font(Black, FontWeight.Bold),
                Font(ExtraBold, FontWeight.ExtraBold),
                Font(SemiBold, FontWeight.SemiBold),
                Font(Bold, FontWeight.Bold),
                Font(Medium, FontWeight.Medium),
                Font(Regular, FontWeight.Normal),
                Font(Light, FontWeight.Light),
                Font(ExtraLight, FontWeight.ExtraLight),
                Font(Thin, FontWeight.Thin),
            )
}

private data object MonoFont {
    val Black = Res.font.mono_black
    val ExtraBold = Res.font.mono_bold_extra
    val Bold = Res.font.mono_bold
    val SemiBold = Res.font.mono_bold_semi
    val Medium = Res.font.mono_medium
    val Regular = Res.font.mono_regular
    val Light = Res.font.mono_light
    val ExtraLight = Res.font.mono_light_extra
    val Thin = Res.font.mono_thin

    val FontFamily: FontFamily
        @Composable
        get() =
            FontFamily(
                Font(Black, FontWeight.Bold),
                Font(ExtraBold, FontWeight.ExtraBold),
                Font(SemiBold, FontWeight.SemiBold),
                Font(Bold, FontWeight.Bold),
                Font(Medium, FontWeight.Medium),
                Font(Regular, FontWeight.Normal),
                Font(Light, FontWeight.Light),
                Font(ExtraLight, FontWeight.ExtraLight),
                Font(Thin, FontWeight.Thin),
            )
}

class HaloFonts internal constructor() {
    val Regular
        @Composable
        get() = RegularFont.FontFamily

    val Mono
        @Composable
        get() =
            MonoFont.FontFamily
}
