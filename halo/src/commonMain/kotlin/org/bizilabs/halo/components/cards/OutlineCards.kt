package org.bizilabs.halo.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme

@Composable
internal fun OutlineCardBase(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = HaloTheme.shapes.medium,
    colors: CardColors = CardDefaults.outlinedCardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    when (onClick != null) {
        true ->
            OutlinedCard(
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
            OutlinedCard(
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
fun HaloOutlinedCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    shape: Shape = HaloTheme.shapes.medium,
    colors: CardColors =
        CardDefaults.outlinedCardColors(
            contentColor = HaloTheme.colorScheme.content.strong,
            containerColor = HaloTheme.colorScheme.background.surface,
            disabledContentColor = HaloTheme.colorScheme.disabled.content,
            disabledContainerColor = HaloTheme.colorScheme.disabled.container,
        ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke =
        BorderStroke(
            HaloTheme.thickness.medium,
            HaloTheme.colorScheme.content.weak,
        ),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    OutlineCardBase(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource,
        content = content,
    )
}
