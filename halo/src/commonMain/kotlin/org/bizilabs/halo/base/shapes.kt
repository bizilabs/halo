@file:Suppress("ktlint:standard:filename")

package org.bizilabs.halo.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val ShapeNone = RoundedCornerShape(0)
private val ShapeXS = RoundedCornerShape(1.dp)
private val ShapeSM = RoundedCornerShape(2.dp)
private val ShapeMD = RoundedCornerShape(4.dp)
private val ShapeLG = RoundedCornerShape(8.dp)
private val ShapeXL = RoundedCornerShape(12.dp)
private val ShapeFull = RoundedCornerShape(100)

data class HaloShapes(
    val none: RoundedCornerShape = ShapeNone,
    val extraSmall: RoundedCornerShape = ShapeXS,
    val small: RoundedCornerShape = ShapeSM,
    val medium: RoundedCornerShape = ShapeMD,
    val large: RoundedCornerShape = ShapeLG,
    val extraLarge: RoundedCornerShape = ShapeXL,
    val full: RoundedCornerShape = ShapeFull,
) {
    companion object {
        val Default = HaloShapes()
        val Rounded =
            HaloShapes(
                extraSmall = RoundedCornerShape(2.dp),
                small = RoundedCornerShape(4.dp),
                medium = RoundedCornerShape(8.dp),
                large = RoundedCornerShape(12.dp),
                extraLarge = RoundedCornerShape(20.dp),
            )
    }
}

val LocalHaloShapes = staticCompositionLocalOf { HaloShapes() }

internal fun provideHaloShapes(shapes: HaloShapes) = LocalHaloShapes provides shapes

internal fun HaloShapes.asMaterialShapes() =
    Shapes(
        extraSmall = extraSmall,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge,
    )
