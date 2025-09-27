package org.bizilabs.halo.components.textarea

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.TextAreaHeightMode
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.components.HaloSurface
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.textfields.HaloTextFieldColors
import org.bizilabs.halo.components.textfields.TextFieldMode

@Composable
internal fun HaloBaseTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    mode: TextFieldMode = TextFieldMode.FILLED,
    interactionSource: MutableInteractionSource? = null,
    heightMode: TextAreaHeightMode = TextAreaHeightMode.Fixed(lines = 5),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    shape: Shape = HaloTheme.shapes.medium,
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
                                ?: HaloTheme.colorScheme.background.lowest
                    }

                !enabled -> colors?.disabled?.container ?: HaloTheme.colorScheme.disabled.container
                isError ->
                    when (mode) {
                        TextFieldMode.FILLED ->
                            colors?.error?.container
                                ?: HaloTheme.colorScheme.error.weaker

                        TextFieldMode.OUTLINED ->
                            colors?.error?.container
                                ?: HaloTheme.colorScheme.background.lowest
                    }

                else -> {
                    when (mode) {
                        TextFieldMode.FILLED ->
                            if (focused) {
                                colors?.focused?.container
                                    ?: HaloTheme.colorScheme.background.low
                            } else {
                                colors?.default?.container
                                    ?: HaloTheme.colorScheme.background.low
                            }

                        TextFieldMode.OUTLINED ->
                            if (focused) {
                                colors?.focused?.container
                                    ?: HaloTheme.colorScheme.background.low
                            } else {
                                colors?.default?.container ?: HaloTheme.colorScheme.background.lowest
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
                        colors?.focused?.content ?: HaloTheme.colorScheme.content.stronger
                    } else {
                        colors?.default?.content ?: HaloTheme.colorScheme.content.strong
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
        label = "cursorColorAnimation",
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

    val effectiveLineHeight = if (textStyle.lineHeight.isUnspecified) 20.sp else textStyle.lineHeight
    val lineHeightDp = with(LocalDensity.current) { effectiveLineHeight.toDp() }

    val surfaceOuterPadding = 4.dp + 2.dp
    val innerBoxVerticalPadding = 12.dp + 12.dp
    val totalVerticalPadding = surfaceOuterPadding + innerBoxVerticalPadding

    val (minLines, maxLines, modifierHeight) = when (heightMode) {
        is TextAreaHeightMode.Fixed -> {
            val targetHeight = lineHeightDp * heightMode.lines + totalVerticalPadding
            val animatedHeight by animateDpAsState(
                targetValue = targetHeight,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                label = "fixedHeight"
            )
            Triple(heightMode.lines, heightMode.lines, Modifier.height(animatedHeight))
        }
        is TextAreaHeightMode.Expandable -> {
            val targetMinHeight = lineHeightDp * heightMode.minLines + totalVerticalPadding
            val animatedMinHeight by animateDpAsState(
                targetValue = targetMinHeight,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                label = "expandableMinHeight"
            )
            Triple(
                heightMode.minLines,
                heightMode.maxLines,
                Modifier.defaultMinSize(minHeight = animatedMinHeight)
            )
        }
    }

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
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(cursorColor),
    ) { innerTextField ->
        Column(modifier = Modifier.fillMaxWidth()) {
            if (label != null || count != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    label?.invoke()
                    count?.invoke()
                }
            }
            HaloSurface(
                modifier =
                    Modifier
                        .then(modifierHeight)
                        .padding(top = 4.dp, bottom = 2.dp),
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
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth()
                ) {
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

            if (helper != null) {
                ProvideContentColor(
                    color = helperColor,
                ) {
                    helper.invoke()
                }
            }
        }
    }
}

@Composable
fun HaloFilledTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    heightMode: TextAreaHeightMode = TextAreaHeightMode.Fixed(5),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(20),
    colors: HaloTextFieldColors? = null,
) {
    HaloBaseTextArea(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        isError = isError,
        textStyle = textStyle,
        helper = helper,
        count = count,
        shape = shape,
        singleLine = singleLine,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors,
        heightMode = heightMode,
    )
}

@Composable
fun HaloOutlinedTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    count: @Composable (() -> Unit)? = null,
    helper: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    heightMode: TextAreaHeightMode = TextAreaHeightMode.Fixed(5),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(20),
    colors: HaloTextFieldColors? = null,
) {
    HaloBaseTextArea(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        isError = isError,
        textStyle = textStyle,
        helper = helper,
        count = count,
        shape = shape,
        singleLine = singleLine,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors,
        heightMode = heightMode,
        mode = TextFieldMode.OUTLINED,
    )
}
