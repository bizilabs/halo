package org.bizilabs.halo.components.chips

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
import org.bizilabs.halo.components.cards.HaloCard

@Composable
fun HaloSelectionChip(
    enabled: Boolean = true,
    containerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.15f),
    contentColor: Color = HaloTheme.colorScheme.background.onBase,
    disabledContentColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    disabledContainerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.1f),
    iconColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    shape: Shape = RoundedCornerShape(50),
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

    HaloCard(
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
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
                            .size(16.dp),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@Composable
fun HaloInputChip(
    enabled: Boolean = true,
    containerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.15f),
    contentColor: Color = HaloTheme.colorScheme.background.onBase,
    disabledContentColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    disabledContainerColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.1f),
    iconColor: Color =
        HaloTheme.colorScheme.background.onBase
            .copy(0.5f),
    shape: Shape = RoundedCornerShape(50),
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

    HaloCard(
        enabled = enabled,
        shape = shape,
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
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
                            .clickable(onClick = onClickTrailingIcon),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
