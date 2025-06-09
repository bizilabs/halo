package org.bizilabs.halo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @param header items that will appear at the top
 * @param content items that will toggle visibility
 */
@Composable
internal fun AccordionBase(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        header()
        AnimatedVisibility(
            visible = collapsed,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            content()
        }
    }
}

@Composable
fun HaloAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    AccordionBase(
        collapsed = collapsed,
        header = header,
        content = content,
        modifier = modifier,
    )
}

@Composable
fun HaloFilledAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    AccordionBase(
        collapsed = collapsed,
        header = header,
        content = content,
        modifier = modifier,
    )
}

@Composable
fun HaloOutlinedAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    AccordionBase(
        collapsed = collapsed,
        header = header,
        content = content,
        modifier = modifier,
    )
}
