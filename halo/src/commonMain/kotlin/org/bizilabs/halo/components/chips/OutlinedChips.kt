package org.bizilabs.halo.components.chips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.cards.HaloOutlineCard

@Composable
fun HaloOutlinedSelectionChip(
    enabled: Boolean = true,
    isClickable: Boolean = true,
    containerColor: Color =
        HaloTheme.colorScheme.background.base
            .copy(0.5f),
    contentColor: Color = HaloTheme.colorScheme.background.onBase,
    disabledContentColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    disabledContainerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.05f),
    borderColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.15f),
    iconColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    shape: Shape = RoundedCornerShape(100),
    onClick: () -> Unit = {},
    onClickTrailingIcon: () -> Unit = {},
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    content: @Composable (Color) -> Unit,
) {
    val color =
        when {
            enabled -> contentColor
            else -> CardDefaults.cardColors().disabledContentColor
        }
    val iconColor = if (enabled) iconColor else disabledContentColor

    HaloOutlineCard(
        enabled = enabled,
        onClick = if (isClickable) onClick else null,
        shape = shape,
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
            ),
        border =
            BorderStroke(
                width = 1.dp,
                color = borderColor,
            ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(12.dp))
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
            content(color)
            trailingIcon?.let {
                Icon(
                    modifier =
                        Modifier
                            .size(16.dp)
                            .then(
                                when {
                                    enabled && !isClickable -> Modifier.clickable(onClick = onClickTrailingIcon)
                                    else -> Modifier
                                },
                            ),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
