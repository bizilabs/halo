package org.bizilabs.halo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
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
) {
    companion object {
        /**
         * Returns the default configuration for badges.
         *
         * @return A BadgeProperties object with default settings.
         */
        fun default() = BadgeProperties(
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
    text: String,
    mode: ComponentMode = ComponentMode.Default,
    shape: Shape = HaloShapes.Medium.extraSmall,
    badgeProperties: BadgeProperties = BadgeProperties.default(), //To be updated to have separate spacing and color properties
) {
    val (backgroundColor: Color, contentColor: Color) = when (mode) {
        ComponentMode.Default -> Pair(
            HaloTheme.colorScheme.primary.neutral,
            HaloTheme.colorScheme.primary.weaker
        )

        ComponentMode.Info -> Pair(
            HaloTheme.colorScheme.info.neutral,
            HaloTheme.colorScheme.info.weaker
        )

        ComponentMode.Success -> Pair(
            HaloTheme.colorScheme.success.neutral,
            HaloTheme.colorScheme.success.weaker
        )

        ComponentMode.Error -> Pair(
            HaloTheme.colorScheme.error.neutral,
            HaloTheme.colorScheme.error.weaker
        )

        ComponentMode.Warning -> Pair(
            HaloTheme.colorScheme.warning.neutral,
            HaloTheme.colorScheme.warning.weaker
        )
    }
    /**
     * Issues:
     * Content color is not visible when passed like this,
     */
    HaloBadge(
        modifier = modifier,
        badgeProperties = BadgeProperties.default().copy(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        HaloText(
            text = text,
            style = HaloTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun HaloOutlinedBadge(
    modifier: Modifier = Modifier,
    text: String,
    mode: ComponentMode = ComponentMode.Default,
    shape: Shape = HaloShapes.Medium.extraSmall,
    badgeProperties: BadgeProperties = BadgeProperties.default(),
) {
    val (backgroundColor: Color, contentColor: Color, borderColor: Color) = when (mode) {
        ComponentMode.Default -> Triple(
            //HaloTheme.colorScheme.primary.weaker, // This doesn't work
            Color(0xFFEFF3FF), // This works
            //HaloTheme.colorScheme.primary.strong, // This content color doesn't
            Color(0xFF0024A6), // This content color doesn't
            HaloTheme.colorScheme.primary.weak
        )

        ComponentMode.Info -> Triple(
            HaloTheme.colorScheme.info.weaker,
            HaloTheme.colorScheme.info.strong,
            HaloTheme.colorScheme.info.weak
        )

        ComponentMode.Success -> Triple(
            HaloTheme.colorScheme.success.weaker,
            HaloTheme.colorScheme.success.strong,
            HaloTheme.colorScheme.success.weak
        )

        ComponentMode.Error -> Triple(
            HaloTheme.colorScheme.error.weaker,
            HaloTheme.colorScheme.error.strong,
            HaloTheme.colorScheme.error.weak
        )

        ComponentMode.Warning -> Triple(
            HaloTheme.colorScheme.warning.weaker,
            HaloTheme.colorScheme.warning.strong,
            HaloTheme.colorScheme.warning.weak
        )
    }
    /**
     * Issues:
     * both the backgroundColor and content colors are not visible when passed like this,however,
     * if you pass the actual color value eg. Color(0xFFEFF3FF) as the background above it works
     *
     * When you pass a color to the content value directly -- in this case the text below, the content color is visible
     * */

    HaloBadge(
        modifier = modifier,
        badgeProperties = BadgeProperties.default().copy(
            backgroundColor = backgroundColor, // This is not visible
            contentColor = contentColor, // This is not visible
            borderColor = borderColor,
        ),
        shape = shape
    ) {
        HaloText(
            text = text,
            style = HaloTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun HaloBadge(
    modifier: Modifier = Modifier,
    shape: Shape = HaloShapes.Medium.extraSmall,
    badgeProperties: BadgeProperties,
    content: @Composable (() -> Unit),
) {
    Row(
        modifier = modifier
            .clip(shape)
            .background(color = badgeProperties.backgroundColor)
            .border(
                shape = shape,
                width = badgeProperties.borderStrokeWidth,
                color = badgeProperties.borderColor
            )
            .padding(
                horizontal = badgeProperties.horizontalPadding,
                vertical = badgeProperties.verticalPadding
            )
    ) {
        CompositionLocalProvider(
            LocalContentColor provides badgeProperties.contentColor
        ) {
            content()
        }
    }
}

@Composable
fun BadgePreview() {
    HaloTheme {
        Column(
            modifier = Modifier
                .padding(48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HaloText("Halo Badges", color = HaloTheme.colorScheme.primary.neutral)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HaloFilledBadge(text = "Filled Badge")
                HaloFilledBadge(
                    text = "Filled Badge",
                    mode = ComponentMode.Error
                )
                HaloFilledBadge(
                    text = "Filled Badge",
                    mode = ComponentMode.Success
                )
                HaloFilledBadge(
                    text = "Filled Badge",
                    mode = ComponentMode.Warning
                )
                HaloFilledBadge(
                    text = "Filled Badge",
                    mode = ComponentMode.Info
                )
            }

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HaloOutlinedBadge(text = "Outlined Badge")
                HaloOutlinedBadge(
                    text = "Outlined Badge",
                    mode = ComponentMode.Error
                )
                HaloOutlinedBadge(
                    text = "Outlined Badge",
                    mode = ComponentMode.Success
                )
                HaloOutlinedBadge(
                    text = "Outlined Badge",
                    mode = ComponentMode.Warning
                )
                HaloOutlinedBadge(
                    text = "Outlined Badge",
                    mode = ComponentMode.Info
                )
            }
        }
    }
}