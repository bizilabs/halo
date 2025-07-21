@file:OptIn(ExperimentalMaterial3Api::class)

package org.bizilabs.halo.components.bottomsheets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.bizilabs.halo.HaloTheme

/**
 * Remembers and returns a [HaloSheetLauncher] that can be used to programmatically
 * launch or dismiss a bottom sheet with smooth vertical slide-in/out animations.
 *
 * This utility wraps [HaloBottomSheet] and provides imperative-style control via a returned launcher.
 * It handles coroutine scope management, visibility tracking, animation, and sheet state transitions.
 *
 * Example usage:
 * ```
 * val launcher = rememberHaloSheetLauncher {
 *     Column {
 *         Text("Hello from the sheet!")
 *     }
 * }
 * Button(onClick = { launcher.launch() }) {
 *     Text("Open Sheet")
 * }
 * ```
 *
 * @param sheetSize Determines whether the sheet should fully expand or allow partial height. See [HaloSheetSize].
 * @param colors Defines the container and content colors used in the sheet. Defaults to [HaloSheetDefaults.sheetColors].
 * @param shape The shape of the sheet surface. Defaults to [BottomSheetDefaults.ExpandedShape].
 * @param sheetMaxWidth Maximum width constraint for the sheet. Defaults to [HaloSheetDefaults.SheetMaxWidth].
 * @param content The composable content displayed inside the bottom sheet.
 *
 * @return A [HaloSheetLauncher] instance that provides `launch()` and `dismiss()` functions to control sheet visibility.
 */
@Composable
fun rememberHaloSheetLauncher(
    sheetSize: HaloSheetSize = HaloSheetSize.Full,
    colors: HaloSheetColors = HaloSheetDefaults.sheetColors(),
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    sheetMaxWidth: Dp = HaloSheetDefaults.SheetMaxWidth,
    content: @Composable ColumnScope.() -> Unit,
): HaloSheetLauncher {
    val skipPartiallyExpanded =
        when (sheetSize) {
            HaloSheetSize.Full -> true
            HaloSheetSize.Partial -> false
        }

    var isVisible by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    val scope = rememberCoroutineScope()
    var job: Job? = null
    val cancelAndLaunchCoroutine: (block: suspend CoroutineScope.() -> Unit) -> Unit = { block ->
        job?.cancel()
        job = scope.launch(block = block)
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { height -> height },
        exit = slideOutVertically { height -> height },
    ) {
        HaloBottomSheet(
            sheetState = sheetState,
            isVisible = isVisible,
            shape = shape,
            containerColor = colors.container,
            contentColor = colors.content,
            sheetMaxWidth = sheetMaxWidth,
            onDismissRequest = { isVisible = isVisible.not() },
            content = content,
        )
    }

    return HaloSheetLauncher(
        onLaunch = {
            cancelAndLaunchCoroutine { sheetState.show() }
            isVisible = true
        },
        onDismiss = {
            cancelAndLaunchCoroutine { sheetState.hide() }
            isVisible = false
        },
    )
}

class HaloSheetLauncher internal constructor(
    private val onLaunch: () -> Unit,
    private val onDismiss: () -> Unit,
) {
    fun launch() {
        onLaunch.invoke()
    }

    fun dismiss() {
        onDismiss.invoke()
    }
}

enum class HaloSheetSize {
    Full,
    Partial,
}

data class HaloSheetColors(
    val container: Color,
    val content: Color,
)

object HaloSheetDefaults {
    val SheetMaxWidth: Dp
        @Composable
        get() = LocalWindowInfo.current.containerSize.width.dp

    @Composable
    fun sheetColors(
        container: Color = HaloTheme.colorScheme.background.surface,
        content: Color = HaloTheme.colorScheme.content.strong,
    ): HaloSheetColors =
        HaloSheetColors(
            container = container,
            content = content,
        )
}
