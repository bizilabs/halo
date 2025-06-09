package org.bizilabs.halo.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.extensions.dashedBorder

@Composable
internal fun CardBase(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        onClick = onClick ?: {},
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = CardDefaults.shape,
    colors: CardColors =
        CardDefaults.cardColors(
            contentColor = HaloTheme.colorScheme.surface.content,
            containerColor = HaloTheme.colorScheme.surface.container,
        ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    CardBase(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick ?: {},
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloSlotCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = CardDefaults.shape,
    colors: CardColors =
        CardDefaults.cardColors(
            contentColor = HaloTheme.colorScheme.surface.content,
            containerColor = HaloTheme.colorScheme.surface.container,
        ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    CardBase(
        modifier =
            modifier.dashedBorder(
                HaloTheme.colorScheme.surface.content
                    .copy(alpha = 0.25f),
                shape = shape,
                strokeWidth = strokeWidth,
                dashLength = dashLength,
                gapLength = gapLength,
                cap = cap,
            ),
        enabled = enabled,
        onClick = onClick ?: {},
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = null,
        interactionSource = interactionSource,
        content = content,
    )
}
