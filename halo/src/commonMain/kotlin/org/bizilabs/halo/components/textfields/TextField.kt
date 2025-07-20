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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.HaloShapes
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.components.HaloSurface
import org.bizilabs.halo.components.HaloText

internal enum class TextFieldMode {
    FILLED,
    OUTLINED,
}

data class HaloTextFieldColors(
    val default: HaloColor,
    val focused: HaloColor,
    val disabled: HaloColor,
    val error: HaloColor,
    val cursor: Color,
    val errorCursor: Color,
    val placeholder: Color,
)

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
    isError: Boolean = false,
    shape: Shape = HaloShapes.Medium.medium,
    colors: HaloTextFieldColors? = null,
) {
    var focused by remember { mutableStateOf(false) }

    val containerColor by animateColorAsState(
        targetValue =
            when {
                readOnly ->
                    when (mode) {
                        TextFieldMode.FILLED ->
                            colors?.default?.container
                                ?: HaloTheme.colorScheme.disabled.container

                        TextFieldMode.OUTLINED ->
                            colors?.default?.container
                                ?: HaloTheme.colorScheme.background.base
                    }

                !enabled -> colors?.disabled?.container ?: HaloTheme.colorScheme.disabled.container
                else -> {
                    when (mode) {
                        TextFieldMode.FILLED ->
                            if (focused) {
                                colors?.focused?.container ?: HaloTheme.colorScheme.background.surface
                            } else {
                                colors?.default?.container ?: HaloTheme.colorScheme.background.surface
                            }

                        TextFieldMode.OUTLINED ->
                            if (focused) {
                                colors?.focused?.container ?: HaloTheme.colorScheme.background.surface
                            } else {
                                colors?.default?.container ?: HaloTheme.colorScheme.background.base
                            }
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
                readOnly -> colors?.default?.content ?: HaloTheme.colorScheme.content.stronger
                !enabled -> colors?.disabled?.content ?: HaloTheme.colorScheme.disabled.content
                else -> {
                    if (focused) {
                        colors?.focused?.container ?: HaloTheme.colorScheme.content.stronger
                    } else {
                        colors?.default?.container ?: HaloTheme.colorScheme.content.strong
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
                readOnly -> colors?.default?.border ?: HaloTheme.colorScheme.disabled.border
                !enabled -> colors?.disabled?.border ?: HaloTheme.colorScheme.disabled.border
                isError -> colors?.error?.border ?: HaloTheme.colorScheme.error.neutral
                else -> {
                    if (focused) {
                        colors?.focused?.border ?: HaloTheme.colorScheme.content.stronger
                    } else {
                        colors?.default?.border ?: HaloTheme.colorScheme.content.neutral
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

    val cursorColor by animateColorAsState(
        targetValue =
            when {
                isError -> colors?.errorCursor ?: HaloTheme.colorScheme.error.neutral
                else -> colors?.cursor ?: HaloTheme.colorScheme.content.stronger
            },
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "borderColorAnimation",
    )

    val placeholderColor by animateColorAsState(
        targetValue = colors?.placeholder ?: HaloTheme.colorScheme.content.weak,
        animationSpec =
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
            ),
        label = "placeholderColor",
    )

    BasicTextField(
        modifier =
            modifier
                .focusable(enabled && readOnly.not())
                .onFocusChanged { focused = it.isFocused },
        value = value,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.copy(color = contentColor),
        onValueChange = onValueChange,
        singleLine = singleLine,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(cursorColor),
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
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .heightIn(
                                min = 32.dp,
                            ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    leading?.invoke()
                    Row(modifier = Modifier.weight(1f)) {
                        ProvideContentColor(color = contentColor) {
                            if (value.isBlank() && !focused && placeholder.isNotBlank()) {
                                HaloText(
                                    text = placeholder,
                                    style = textStyle.copy(color = placeholderColor),
                                )
                            } else {
                                innerTextField()
                            }
                        }
                    }
                    trailing?.invoke()
                }
            }

            val helperColor by animateColorAsState(
                targetValue =
                    when {
                        readOnly -> colors?.default?.border ?: HaloTheme.colorScheme.disabled.border
                        !enabled ->
                            colors?.disabled?.border
                                ?: HaloTheme.colorScheme.disabled.border

                        isError -> colors?.error?.content ?: HaloTheme.colorScheme.error.neutral
                        else -> {
                            if (focused) {
                                colors?.focused?.border ?: HaloTheme.colorScheme.content.stronger
                            } else {
                                colors?.default?.border ?: HaloTheme.colorScheme.content.strong
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

            ProvideContentColor(
                color = helperColor,
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
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    lines: Int = 1,
    shape: Shape = RoundedCornerShape(20),
    colors: HaloTextFieldColors? = null,
) {
    HaloBaseTextField(
        mode = TextFieldMode.FILLED,
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        leading = leading,
        trailing = trailing,
        isError = isError,
        textStyle = textStyle,
        helper = helper,
        count = count,
        shape = shape,
        singleLine = lines == 1,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors,
    )
}

@Composable
fun HaloOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    lines: Int = 1,
    shape: Shape = RoundedCornerShape(20),
    colors: HaloTextFieldColors? = null,
) {
    HaloBaseTextField(
        mode = TextFieldMode.OUTLINED,
        modifier = modifier,
        placeholder = placeholder,
        enabled = enabled,
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        label = label,
        leading = leading,
        trailing = trailing,
        helper = helper,
        isError = isError,
        count = count,
        shape = shape,
        singleLine = lines == 1,
        interactionSource = interactionSource,
        textStyle = textStyle,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        colors = colors,
    )
}
