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
    val shape: Shape = HaloShapes.Small.small,
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
    val (container, content) =
        when (mode) {
            ComponentMode.Default ->
                Pair(
                    HaloTheme.colorScheme.primary.neutral,
                    HaloTheme.colorScheme.primary.weaker,
                )

            ComponentMode.Info ->
                Pair(
                    HaloTheme.colorScheme.info.neutral,
                    HaloTheme.colorScheme.info.weaker,
                )

            ComponentMode.Success ->
                Pair(
                    HaloTheme.colorScheme.success.neutral,
                    HaloTheme.colorScheme.success.weaker,
                )

            ComponentMode.Error ->
                Pair(
                    HaloTheme.colorScheme.error.neutral,
                    HaloTheme.colorScheme.error.weaker,
                )

            ComponentMode.Warning ->
                Pair(
                    HaloTheme.colorScheme.warning.neutral,
                    HaloTheme.colorScheme.warning.weaker,
                )
        }

    HaloBadge(
        modifier = modifier,
        properties =
            properties.copy(
                backgroundColor = container,
                contentColor = content,
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
    val (container, content, border) =
        when (mode) {
            ComponentMode.Default ->
                Triple(
                    HaloTheme.colorScheme.primary.weaker,
                    HaloTheme.colorScheme.primary.strong,
                    HaloTheme.colorScheme.primary.weak,
                )

            ComponentMode.Info ->
                Triple(
                    HaloTheme.colorScheme.info.weaker,
                    HaloTheme.colorScheme.info.strong,
                    HaloTheme.colorScheme.info.weak,
                )

            ComponentMode.Success ->
                Triple(
                    HaloTheme.colorScheme.success.weaker,
                    HaloTheme.colorScheme.success.strong,
                    HaloTheme.colorScheme.success.weak,
                )

            ComponentMode.Error ->
                Triple(
                    HaloTheme.colorScheme.error.weaker,
                    HaloTheme.colorScheme.error.strong,
                    HaloTheme.colorScheme.error.weak,
                )

            ComponentMode.Warning ->
                Triple(
                    HaloTheme.colorScheme.warning.weaker,
                    HaloTheme.colorScheme.warning.strong,
                    HaloTheme.colorScheme.warning.weak,
                )
        }
    HaloBadge(
        modifier = modifier,
        properties =
            properties.copy(
                backgroundColor = container,
                contentColor = content,
                borderColor = border,
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
