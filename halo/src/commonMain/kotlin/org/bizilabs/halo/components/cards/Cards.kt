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
import androidx.compose.ui.graphics.Color
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
    shape: Shape = HaloTheme.shapes.medium,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    when (onClick != null) {
        true ->
            Card(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = elevation,
                border = border,
                interactionSource = interactionSource,
                content = content,
            )

        false ->
            Card(
                modifier = modifier,
                shape = shape,
                colors = colors,
                elevation = elevation,
                border = border,
                content = content,
            )
    }
}

@Composable
fun HaloFilledCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = HaloTheme.shapes.medium,
    colors: CardColors =
        CardDefaults.cardColors(
            contentColor = HaloTheme.colorScheme.content.strong,
            containerColor = HaloTheme.colorScheme.background.low,
            disabledContentColor = HaloTheme.colorScheme.disabled.content,
            disabledContainerColor = HaloTheme.colorScheme.disabled.container,
        ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    CardBase(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = null,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloSlotCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = HaloTheme.shapes.medium,
    colors: CardColors =
        CardDefaults.cardColors(
            contentColor = HaloTheme.colorScheme.content.strong,
            containerColor = HaloTheme.colorScheme.background.low,
            disabledContentColor = HaloTheme.colorScheme.disabled.content,
            disabledContainerColor = HaloTheme.colorScheme.disabled.container,
        ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    strokeWidth: Dp = 2.dp,
    dashColor: Color = HaloTheme.colorScheme.background.high,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    CardBase(
        modifier =
            modifier.dashedBorder(
                color = dashColor,
                shape = shape,
                strokeWidth = strokeWidth,
                dashLength = dashLength,
                gapLength = gapLength,
                cap = cap,
            ),
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = null,
        interactionSource = interactionSource,
        content = content,
    )
}
