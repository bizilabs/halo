package org.bizilabs.halo.base

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * ProvideContentColorTextStyle
 *
 * A convenience method to provide values to both LocalContentColor and LocalTextStyle in one call.
 * This is less expensive than nesting calls to CompositionLocalProvider.
 *
 * Text styles will be merged with the current value of LocalTextStyle.
 */
@Composable
internal fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit,
) {
    val mergedStyle = LocalHaloTextStyle.current.merge(textStyle)

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalHaloTextStyle provides mergedStyle,
        content = content,
    )
}
