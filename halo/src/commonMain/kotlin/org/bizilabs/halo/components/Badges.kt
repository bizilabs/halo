package org.bizilabs.halo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentMode
import org.bizilabs.halo.base.HaloShapes

data class BadgeProperties(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val borderStrokeWidth: Dp,
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val shape: Shape = HaloShapes.Default.medium,
) {
    companion object {
        /**
         * Returns the default configuration for badges.
         *
         * @return A BadgeProperties object with default settings.
         */
        fun default() =
            BadgeProperties(
                horizontalPadding = 8.dp,
                verticalPadding = 4.dp,
                borderStrokeWidth = 1.dp,
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
                borderColor = Color.Transparent,
            )
    }
}

@Composable
fun HaloFilledBadge(
    modifier: Modifier = Modifier,
    mode: ComponentMode = ComponentMode.Default,
    properties: BadgeProperties = BadgeProperties.default(),
    content: @Composable () -> Unit,
) {
    val colors =
        when (mode) {
            ComponentMode.Default -> HaloTheme.colorScheme.content.filled.copy(container = HaloTheme.colorScheme.content.strong)
            ComponentMode.Primary -> HaloTheme.colorScheme.primary.filled
            ComponentMode.Info -> HaloTheme.colorScheme.info.filled
            ComponentMode.Success -> HaloTheme.colorScheme.success.filled
            ComponentMode.Error -> HaloTheme.colorScheme.error.filled
            ComponentMode.Warning -> HaloTheme.colorScheme.warning.filled
        }

    HaloBadge(
        modifier = modifier,
        properties =
            properties.copy(
                backgroundColor = colors.container,
                contentColor = colors.content,
            ),
    ) {
        content()
    }
}

@Composable
fun HaloOutlinedBadge(
    modifier: Modifier = Modifier,
    mode: ComponentMode = ComponentMode.Default,
    properties: BadgeProperties = BadgeProperties.default(),
    content: @Composable () -> Unit,
) {
    val colors =
        when (mode) {
            ComponentMode.Default -> HaloTheme.colorScheme.content.outlined
            ComponentMode.Primary -> HaloTheme.colorScheme.primary.outlined
            ComponentMode.Info -> HaloTheme.colorScheme.info.outlined
            ComponentMode.Success -> HaloTheme.colorScheme.success.outlined
            ComponentMode.Error -> HaloTheme.colorScheme.error.outlined
            ComponentMode.Warning -> HaloTheme.colorScheme.warning.outlined
        }
    HaloBadge(
        modifier = modifier,
        properties =
            properties.copy(
                backgroundColor = colors.container,
                contentColor = colors.content,
                borderColor = colors.border,
            ),
    ) {
        content()
    }
}

@Composable
fun HaloBadge(
    modifier: Modifier = Modifier,
    properties: BadgeProperties,
    content: @Composable (() -> Unit),
) {
    Row(
        modifier =
            modifier
                .clip(properties.shape)
                .background(color = properties.backgroundColor)
                .border(
                    shape = properties.shape,
                    width = properties.borderStrokeWidth,
                    color = properties.borderColor,
                ).padding(
                    horizontal = properties.horizontalPadding,
                    vertical = properties.verticalPadding,
                ),
    ) {
        CompositionLocalProvider(
            LocalContentColor provides properties.contentColor,
        ) {
            content()
        }
    }
}
