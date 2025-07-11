package org.bizilabs.halo.components.textfields

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.components.HaloSurface

internal enum class TextFieldMode {
    FILLED,
    OUTLINED,
}

@Composable
internal fun HaloBaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    mode: TextFieldMode = TextFieldMode.FILLED,
    interactionSource: MutableInteractionSource? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(20),
) {
    var focused by remember { mutableStateOf(false) }

    val containerColor by animateColorAsState(
        targetValue =
            when {
                readOnly ->
                    when (mode) {
                        TextFieldMode.FILLED -> HaloTheme.colorScheme.disabled.container
                        TextFieldMode.OUTLINED -> HaloTheme.colorScheme.background.base
                    }
                !enabled -> HaloTheme.colorScheme.disabled.container
                else -> {
                    if (focused) {
                        HaloTheme.colorScheme.background.surface
                    } else {
                        HaloTheme.colorScheme.background.surface
                            .copy(alpha = 0.75f)
                    }
                }
            },
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "containerColor",
    )

    val contentColor by animateColorAsState(
        targetValue =
            when {
                readOnly -> HaloTheme.colorScheme.background.onBase
                !enabled -> HaloTheme.colorScheme.disabled.content
                else -> {
                    if (focused) {
                        HaloTheme.colorScheme.background.onBase
                    } else {
                        HaloTheme.colorScheme.background.onSurface
                    }
                }
            },
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "containerColor",
    )

    val borderColor by animateColorAsState(
        targetValue =
            when {
                readOnly -> HaloTheme.colorScheme.disabled.border
                !enabled -> HaloTheme.colorScheme.disabled.border
                else -> {
                    if (focused) {
                        HaloTheme.colorScheme.background.onBase
                    } else {
                        HaloTheme.colorScheme.background.onSurface
                    }
                }
            },
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "borderColorAnimation",
    )

    val borderWidth by animateDpAsState(
        targetValue = if (focused) 2.dp else 1.5.dp,
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "borderWidthAnimation",
    )

    BasicTextField(
        modifier =
            modifier
                .focusable(enabled && readOnly.not())
                .onFocusChanged { focused = it.isFocused },
        value = value.ifBlank { placeholder },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.copy(color = contentColor),
        onValueChange = onValueChange,
        singleLine = singleLine,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    ) { innerTextField ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                label?.invoke()
                count?.invoke()
            }
            HaloSurface(
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                color = containerColor,
                contentColor = contentColor,
                shape = shape,
                border =
                    when (mode) {
                        TextFieldMode.FILLED -> null
                        TextFieldMode.OUTLINED ->
                            BorderStroke(
                                width = borderWidth,
                                color = borderColor,
                            )
                    },
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    leading?.invoke()
                    Row(modifier = Modifier.weight(1f)) {
                        ProvideContentColor(color = contentColor) {
                            innerTextField()
                        }
                    }
                    trailing?.invoke()
                }
            }
            ProvideContentColor(
                color =
                    HaloTheme.colorScheme.background.onBase
                        .copy(0.5f),
            ) {
                helper?.invoke()
            }
        }
    }
}

@Composable
fun HaloFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    lines: Int = 1,
    shape: Shape = RoundedCornerShape(20),
) {
    HaloBaseTextField(
        mode = TextFieldMode.FILLED,
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        leading = leading,
        trailing = trailing,
        textStyle = textStyle,
        helper = helper,
        count = count,
        shape = shape,
        singleLine = lines == 1,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun HaloOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    lines: Int = 1,
    shape: Shape = RoundedCornerShape(20),
) {
    HaloBaseTextField(
        mode = TextFieldMode.OUTLINED,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        label = label,
        leading = leading,
        trailing = trailing,
        helper = helper,
        count = count,
        shape = shape,
        singleLine = lines == 1,
        interactionSource = interactionSource,
        textStyle = textStyle,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}
