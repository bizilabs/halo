package org.bizilabs.halo.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentMode
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.ProvideContentColorTextStyle
import org.bizilabs.halo.components.HaloSurface
import org.bizilabs.halo.components.buttons.buttonDefaults.HaloButtonDefaults

data class HaloButtonColors(
    val enabled: HaloColor,
    val disabled: HaloColor,
)

@Composable
fun HaloFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ComponentSize = ComponentSize.Medium,
    mode: ComponentMode = ComponentMode.Default,
    shape: Shape = HaloTheme.shapes.medium,
    border: BorderStroke? = null,
    colors: HaloButtonColors? = null,
    contentPadding: PaddingValues = HaloButtonDefaults.contentPadding(size),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val enabledColors =
        when (mode) {
            ComponentMode.Default -> HaloTheme.colorScheme.primary.filled
            ComponentMode.Info -> HaloTheme.colorScheme.info.filled
            ComponentMode.Success -> HaloTheme.colorScheme.success.filled
            ComponentMode.Error -> HaloTheme.colorScheme.error.filled
            ComponentMode.Warning -> HaloTheme.colorScheme.warning.filled
        }

    HaloBaseButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        size = size,
        colors =
            colors ?: HaloButtonColors(
                enabled = enabledColors,
                disabled = HaloTheme.colorScheme.disabled,
            ),
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = HaloTheme.shapes.medium,
    size: ComponentSize = ComponentSize.Medium,
    mode: ComponentMode = ComponentMode.Default,
    colors: HaloButtonColors? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = HaloButtonDefaults.contentPadding(size),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val enabledColors =
        when (mode) {
            ComponentMode.Default ->
                HaloTheme.colorScheme.primary.outlined.copy(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.primary.neutral,
                )

            ComponentMode.Info ->
                HaloTheme.colorScheme.info.outlined.copy(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.info.neutral,
                )

            ComponentMode.Success ->
                HaloTheme.colorScheme.success.outlined.copy(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.success.neutral,
                )

            ComponentMode.Error ->
                HaloTheme.colorScheme.error.outlined.copy(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.error.neutral,
                )

            ComponentMode.Warning ->
                HaloTheme.colorScheme.warning.outlined.copy(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.warning.neutral,
                )
        }

    val enabledBorderColor =
        when (mode) {
            ComponentMode.Default -> HaloTheme.colorScheme.primary.outlined.border
            ComponentMode.Info -> HaloTheme.colorScheme.info.outlined.border
            ComponentMode.Success -> HaloTheme.colorScheme.success.outlined.border
            ComponentMode.Error -> HaloTheme.colorScheme.error.outlined.border
            ComponentMode.Warning -> HaloTheme.colorScheme.warning.outlined.border
        }

    HaloBaseButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        size = size,
        colors =
            colors ?: HaloButtonColors(
                enabled = enabledColors,
                disabled = HaloTheme.colorScheme.disabled.copy(container = Color.Transparent),
            ),
        border =
            border ?: BorderStroke(
                width = HaloTheme.thickness.medium,
                color = if (enabled) enabledBorderColor else HaloTheme.colorScheme.disabled.border,
            ),
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ComponentSize = ComponentSize.Medium,
    shape: Shape = HaloTheme.shapes.medium,
    mode: ComponentMode = ComponentMode.Default,
    border: BorderStroke? = null,
    colors: HaloButtonColors? = null,
    contentPadding: PaddingValues = HaloButtonDefaults.contentPadding(size),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val enabledColors =
        when (mode) {
            ComponentMode.Default ->
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.primary.neutral,
                    border = Color.Transparent,
                )

            ComponentMode.Info ->
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.info.neutral,
                    border = Color.Transparent,
                )

            ComponentMode.Success ->
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.success.neutral,
                    border = Color.Transparent,
                )

            ComponentMode.Error ->
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.error.neutral,
                    border = Color.Transparent,
                )

            ComponentMode.Warning ->
                HaloColor(
                    container = Color.Transparent,
                    content = HaloTheme.colorScheme.warning.neutral,
                    border = Color.Transparent,
                )
        }

    HaloBaseButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        size = size,
        shape = shape,
        colors =
            colors ?: HaloButtonColors(
                enabled = enabledColors,
                disabled =
                    HaloTheme.colorScheme.disabled.copy(
                        container = Color.Transparent,
                        border = Color.Transparent,
                    ),
            ),
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun HaloBaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: ComponentSize = ComponentSize.Medium,
    enabled: Boolean = true,
    shape: Shape = HaloTheme.shapes.none,
    colors: HaloButtonColors? = null,
    border: BorderStroke? =
        BorderStroke(
            width = HaloTheme.thickness.small,
            color =
                if (enabled) {
                    HaloTheme.colorScheme.content.strong
                } else {
                    HaloTheme.colorScheme.content.weak
                },
        ),
    contentPadding: PaddingValues = HaloButtonDefaults.contentPadding(size),
    interactionSource: MutableInteractionSource? = null,
    ripple: Indication? = null,
    textStyle: TextStyle = HaloButtonDefaults.textStyle(size),
    content: @Composable RowScope.() -> Unit,
) {
    @Suppress("NAME_SHADOatudeWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val enabledContainerColor = colors?.enabled?.container ?: HaloTheme.colorScheme.content.stronger
    val enabledContentColor = colors?.enabled?.content ?: HaloTheme.colorScheme.content.weaker

    val disabledContainerColor =
        colors?.disabled?.container ?: HaloTheme.colorScheme.disabled.container
    val disabledContentColor = colors?.disabled?.content ?: HaloTheme.colorScheme.disabled.content

    val containerColor = if (enabled) enabledContainerColor else disabledContainerColor
    val contentColor = if (enabled) enabledContentColor else disabledContentColor

    HaloSurface(
        modifier =
            modifier.semantics { role = Role.Button }
                .clip(shape)
                .clickable(
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = ripple ?: LocalIndication.current,
                    onClick = onClick,
                ),
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = border,
    ) {
        ProvideContentColorTextStyle(
            contentColor = contentColor,
            textStyle = textStyle,
        ) {
            Row(
                Modifier.defaultMinSize(
                    minWidth = HaloButtonDefaults.minWidth(size),
                    minHeight = HaloButtonDefaults.minHeight(size),
                )
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}
