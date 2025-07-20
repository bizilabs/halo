@file:OptIn(ExperimentalMaterial3Api::class)

package org.bizilabs.halo.components.bottomsheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme

/**
 * A custom wrapper around [ModalBottomSheet] that adds visibility control and default theming from HaloTheme.
 *
 * This composable handles showing and hiding the bottom sheet based on the [isVisible] flag,
 * with automatic animation triggers via [LaunchedEffect]. It also ensures cleanup by preventing
 * unnecessary recompositions when the sheet is neither visible nor active.
 *
 * @param isVisible Whether the bottom sheet should currently be visible.
 * @param onDismissRequest Called when the user attempts to dismiss the sheet (e.g., via gesture or tap outside).
 * @param modifier Optional [Modifier] for styling the bottom sheet container.
 * @param sheetState The [SheetState] instance that manages the current state of the sheet.
 *                   Defaults to [rememberModalBottomSheetState].
 * @param sheetMaxWidth Maximum width of the bottom sheet. Defaults to [BottomSheetDefaults.SheetMaxWidth].
 * @param shape The shape of the sheet surface. Defaults to [BottomSheetDefaults.ExpandedShape].
 * @param containerColor The background color of the sheet. Defaults to [HaloTheme.colorScheme.background.surface].
 * @param contentColor The content color applied to children. Defaults to [HaloTheme.colorScheme.content.strong].
 * @param tonalElevation The elevation for the sheet surface. Defaults to 0.dp.
 * @param scrimColor Color of the background scrim behind the sheet. Defaults to [BottomSheetDefaults.ScrimColor].
 * @param dragHandle Optional composable to show a drag handle at the top of the sheet.
 *                   Defaults to [BottomSheetDefaults.DragHandle].
 * @param contentWindowInsets Insets to apply around the content inside the sheet.
 *                            Defaults to [BottomSheetDefaults.windowInsets].
 * @param properties Platform-specific properties for the sheet behavior.
 *                   Defaults to [ModalBottomSheetDefaults.properties].
 * @param content The composable content displayed inside the sheet.
 */
@Composable
fun HaloBottomSheet(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = HaloTheme.colorScheme.background.surface,
    contentColor: Color = HaloTheme.colorScheme.content.strong,
    tonalElevation: Dp = 0.dp,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    content: @Composable ColumnScope.() -> Unit,
) {
    LaunchedEffect(isVisible) {
        if (isVisible && !sheetState.isVisible) {
            sheetState.show()
        } else if (!isVisible && sheetState.isVisible) {
            sheetState.hide()
        }
    }
    // Make sure we dispose of the bottom sheet when it is no longer needed
    if (!sheetState.isVisible && !isVisible) return

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        sheetMaxWidth = sheetMaxWidth,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        contentWindowInsets = contentWindowInsets,
        properties = properties,
        content = content,
    )
}
