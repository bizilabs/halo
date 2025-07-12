package org.bizilabs.halo.components.textfields

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.LocalTextStyle
import org.bizilabs.halo.components.HaloSurface
import org.bizilabs.halo.components.HaloText

internal enum class CodeFieldMode {
    BASE,
    FILLED,
    OUTLINED,
}

data class HaloCodeFieldColors(
    val default: HaloColor,
    val focused: HaloColor,
    val disabled: HaloColor,
)

@Composable
internal fun HaloCodeCharacterField(
    index: Int,
    count: Int,
    text: String,
    modifier: Modifier = Modifier,
    mode: CodeFieldMode = CodeFieldMode.BASE,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    secret: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = RoundedCornerShape(10),
    colors: HaloCodeFieldColors? = null,
) {
    val codeColor =
        colors ?: HaloCodeFieldColors(
            default =
                HaloColor(
                    container =
                        HaloTheme.colorScheme.background.onSurface
                            .copy(0.1f),
                    content = HaloTheme.colorScheme.background.onBase,
                    border = HaloTheme.colorScheme.background.onBase,
                ),
            focused =
                HaloColor(
                    container =
                        HaloTheme.colorScheme.background.onSurface
                            .copy(0.25f),
                    content = HaloTheme.colorScheme.background.onSurface,
                    border = HaloTheme.colorScheme.background.onSurface,
                ),
            disabled =
                HaloColor(
                    container = HaloTheme.colorScheme.disabled.container,
                    content = HaloTheme.colorScheme.disabled.content,
                    border = HaloTheme.colorScheme.disabled.border,
                ),
        )

    val isCurrentPosition = (text.length == count && index == (count - 1)) || index == text.length
    val completeFocus = isCurrentPosition

    val char =
        when {
            index == text.length -> "  "
            index > text.length -> "  "
            else -> if (secret) "*" else text[index].toString()
        }

    val containerColor by animateColorAsState(
        targetValue =
            when {
                readOnly ->
                    when (mode) {
                        CodeFieldMode.BASE ->
                            codeColor.default.container

                        CodeFieldMode.FILLED ->
                            codeColor.default.container

                        CodeFieldMode.OUTLINED ->
                            codeColor.default.container
                    }

                !enabled -> codeColor.disabled.container
                else -> {
                    if (completeFocus) {
                        codeColor.focused.container
                    } else {
                        codeColor.default.container
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
                readOnly -> codeColor.default.content
                !enabled -> colors?.disabled?.content ?: HaloTheme.colorScheme.disabled.content
                else -> {
                    if (completeFocus) {
                        colors?.focused?.container ?: HaloTheme.colorScheme.background.onBase
                    } else {
                        colors?.default?.container ?: HaloTheme.colorScheme.background.onSurface
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
                else -> {
                    if (completeFocus) {
                        colors?.focused?.border ?: HaloTheme.colorScheme.background.onBase
                    } else {
                        colors?.default?.border ?: HaloTheme.colorScheme.background.onSurface
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

    HaloSurface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border =
            if (mode != CodeFieldMode.OUTLINED) {
                null
            } else {
                BorderStroke(
                    if (completeFocus) 2.dp else 1.dp,
                    color = borderColor,
                )
            },
    ) {
        HaloText(
            modifier = Modifier.padding(16.dp),
            text = char,
            style = textStyle,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
internal fun HaloCodeFieldContent(
    value: String,
    onValueChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    secret: Boolean = false,
    count: Int = 4,
    mode: CodeFieldMode = CodeFieldMode.BASE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
    content: @Composable (Int, String) -> Unit = { index, string ->
        HaloCodeCharacterField(
            index = index,
            count = count,
            text = string,
            mode = mode,
            enabled = enabled,
            secret = secret,
        )
    },
) {
    BasicTextField(
        modifier =
            modifier
                .padding(8.dp),
        value = TextFieldValue(value, selection = TextRange(value.length)),
        onValueChange = {
            if (it.text.length <= count) {
                onValueChange.invoke(it.text, it.text.length == count)
            }
        },
        visualTransformation = if (secret) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(count) { index ->
                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {
                        content(
                            index,
                            value,
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun HaloCodeField(
    value: String,
    onValueChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    secret: Boolean = false,
    count: Int = 4,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
    content: @Composable (Int, String) -> Unit = { index, string ->
        HaloCodeCharacterField(
            index = index,
            count = count,
            text = string,
            mode = CodeFieldMode.BASE,
            enabled = enabled,
            secret = secret,
        )
    },
) {
    HaloCodeFieldContent(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        secret = secret,
        count = count,
        mode = CodeFieldMode.BASE,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        content = content,
    )
}

@Composable
fun HaloCodeFilledField(
    value: String,
    onValueChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    secret: Boolean = false,
    count: Int = 4,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
    content: @Composable (Int, String) -> Unit = { index, string ->
        HaloCodeCharacterField(
            index = index,
            count = count,
            text = string,
            mode = CodeFieldMode.FILLED,
            enabled = enabled,
            secret = secret,
        )
    },
) {
    HaloCodeFieldContent(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        secret = secret,
        count = count,
        mode = CodeFieldMode.FILLED,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        content = content,
    )
}

@Composable
fun HaloCodeOutlinedField(
    value: String,
    onValueChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    secret: Boolean = false,
    count: Int = 4,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
    content: @Composable (Int, String) -> Unit = { index, string ->
        HaloCodeCharacterField(
            index = index,
            count = count,
            text = string,
            mode = CodeFieldMode.OUTLINED,
            enabled = enabled,
            secret = secret,
        )
    },
) {
    HaloCodeFieldContent(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        secret = secret,
        count = count,
        mode = CodeFieldMode.OUTLINED,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        content = content,
    )
}
