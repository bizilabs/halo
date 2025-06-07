@file:Suppress("ktlint:standard:filename")

package org.bizilabs.shady.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val ShapeNone = RoundedCornerShape(0.dp)
private val ShapeXS = RoundedCornerShape(4.dp)
private val ShapeSM = RoundedCornerShape(8.dp)
private val ShapeMD = RoundedCornerShape(12.dp)
private val ShapeLG = RoundedCornerShape(24.dp)
private val ShapeXL = RoundedCornerShape(48.dp)

data object ShadyShapes {
    val None: Shapes =
        Shapes(
            extraSmall = ShapeNone,
            small = ShapeNone,
            medium = ShapeNone,
            large = ShapeNone,
            extraLarge = ShapeNone,
        )
    val ExtraSmall: Shapes =
        Shapes(
            extraSmall = ShapeXS,
            small = ShapeXS,
            medium = ShapeXS,
            large = ShapeXS,
            extraLarge = ShapeXS,
        )
    val Small: Shapes =
        Shapes(
            extraSmall = ShapeSM,
            small = ShapeSM,
            medium = ShapeSM,
            large = ShapeSM,
            extraLarge = ShapeSM,
        )

    val Medium: Shapes =
        Shapes(
            extraSmall = ShapeMD,
            small = ShapeMD,
            medium = ShapeMD,
            large = ShapeMD,
            extraLarge = ShapeMD,
        )

    val Large: Shapes =
        Shapes(
            extraSmall = ShapeLG,
            small = ShapeLG,
            medium = ShapeLG,
            large = ShapeLG,
            extraLarge = ShapeLG,
        )
    val ExtraLarge: Shapes =
        Shapes(
            extraSmall = ShapeXL,
            small = ShapeXL,
            medium = ShapeXL,
            large = ShapeXL,
            extraLarge = ShapeXL,
        )
}
