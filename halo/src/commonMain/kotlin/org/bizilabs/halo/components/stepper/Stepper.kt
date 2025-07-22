package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentState
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.base.colors.ProvideContentColor
import org.bizilabs.halo.components.HaloText

enum class StepMode {
    Dot,
    Number,
    Icon,
    NumberAndIcon,
}

@Composable
fun HaloStepIndicator(
    index: Int,
    selected: Boolean,
    state: ComponentState = ComponentState.Default,
    mode: StepMode = StepMode.NumberAndIcon,
    modifier: Modifier = Modifier,
    borderWidth: Dp = if (selected) 2.dp else 0.dp,
    shape: Shape = RoundedCornerShape(20),
    colors: HaloColor? = null,
) {
    val color =
        colors ?: when (state) {
            ComponentState.Default -> {
                if (selected) {
                    HaloTheme.colorScheme.success.outlined.copy(
                        border = HaloTheme.colorScheme.success.neutral,
                    )
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Loading -> {
                if (selected) {
                    HaloTheme.colorScheme.success.outlined.copy(
                        border = HaloTheme.colorScheme.success.neutral,
                    )
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Error -> HaloTheme.colorScheme.error.filled
            ComponentState.Success -> HaloTheme.colorScheme.success.filled
        }

    HaloStepIndicator(
        modifier = modifier,
        colors = color,
        borderStroke =
            BorderStroke(
                width = borderWidth,
                color = if (selected) color.border else Color.Transparent,
            ),
        shape = shape,
    ) {
        when (state == ComponentState.Loading) {
            true -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(28.dp).padding(4.dp),
                    strokeWidth = 4.dp,
                    color = color.content,
                )
            }

            false -> {
                when (mode) {
                    StepMode.Number -> {
                        HaloText(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            text = "${index + 1}",
                            style = HaloTheme.typography.bodyLarge,
                            color = color.content,
                        )
                    }

                    StepMode.Icon -> {
                        Icon(
                            modifier = Modifier.padding(2.dp),
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "success",
                            tint = color.content,
                        )
                    }

                    StepMode.NumberAndIcon -> {
                        when (state) {
                            ComponentState.Success -> {
                                Icon(
                                    modifier = Modifier.padding(2.dp),
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = "success",
                                    tint = color.content,
                                )
                            }

                            else -> {
                                HaloText(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                    text = "${index + 1}",
                                    style = HaloTheme.typography.bodyLarge,
                                    color = color.content,
                                )
                            }
                        }
                    }

                    StepMode.Dot -> {
                        Box(modifier = Modifier.size(28.dp))
                    }
                }
            }
        }
    }
}

data class StepperProperties(
    val width: Dp = 300.dp,
    val height: Dp = 50.dp,
    val padding: PaddingValues = PaddingValues(0.dp),
    val shape: Shape = RoundedCornerShape(20),
    val thickness: Dp = 2.dp,
) {
    companion object {
        val Default = StepperProperties()
    }
}

@Composable
fun HaloStepIndicator(
    index: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    state: ComponentState = ComponentState.Default,
) {
    val color =
        when (state) {
            ComponentState.Default -> {
                if (selected) {
                    HaloTheme.colorScheme.primary.filled
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Loading -> {
                if (selected) {
                    HaloTheme.colorScheme.primary.filled
                } else {
                    HaloTheme.colorScheme.disabled
                }
            }

            ComponentState.Error -> HaloTheme.colorScheme.error.filled
            ComponentState.Success -> HaloTheme.colorScheme.success.filled
        }

    HaloStepIndicator(modifier = modifier, colors = color) {
        when (state) {
            ComponentState.Default -> {
                HaloText(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp),
                    text = "${index + 1}",
                    style = HaloTheme.typography.bodyLarge,
                    color = color.content,
                )
            }

            ComponentState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp).size(24.dp),
                    strokeWidth = 3.dp,
                    color = color.content,
                )
            }

            ComponentState.Error -> {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Rounded.Error,
                    contentDescription = "error",
                    tint = color.content,
                )
            }

            ComponentState.Success -> {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "success",
                    tint = color.content,
                )
            }
        }
    }
}

@Composable
fun HaloStepIndicator(
    modifier: Modifier = Modifier,
    colors: HaloColor = HaloTheme.colorScheme.disabled,
    shape: Shape = RoundedCornerShape(20),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    content: @Composable () -> Unit = {
        Box(modifier = Modifier.size(24.dp))
    },
) {
    Box(
        modifier =
            modifier
                .background(color = colors.container, shape = shape)
                .border(border = borderStroke, shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        ProvideContentColor(color = colors.content) {
            content()
        }
    }
}
