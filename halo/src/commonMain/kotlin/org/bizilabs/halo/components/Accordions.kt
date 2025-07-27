package org.bizilabs.halo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.cards.HaloFilledCard
import org.bizilabs.halo.components.cards.HaloOutlinedCard

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
    onClick: () -> Unit = { },
) {
    Column(modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable { onClick() },
            ) {
                header()
            }
            AnimatedVisibility(
                visible = collapsed,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                content()
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp)
        }
    }
}

@Composable
fun HaloAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    AccordionBase(
        collapsed = collapsed,
        header = header,
        content = content,
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
fun HaloFilledAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    HaloFilledCard(modifier = modifier) {
        AccordionBase(
            collapsed = collapsed,
            header = { HaloAccordionHeader(collapsed = collapsed, modifier = Modifier, header = header) },
            content = { HaloAccordionContent { content() } },
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
        )
    }
}

@Composable
fun HaloOutlinedAccordion(
    collapsed: Boolean,
    header: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    HaloOutlinedCard(modifier = modifier) {
        AccordionBase(
            collapsed = collapsed,
            header = {
                HaloAccordionHeader(
                    collapsed = collapsed,
                    modifier = Modifier.fillMaxWidth(),
                    header = header,
                )
            },
            content = {
                HaloAccordionContent {
                    content()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
        )
    }
}

@Composable
private fun HaloAccordionHeader(
    collapsed: Boolean,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.weight(1f)) {
                header()
            }
            Box(modifier = Modifier) {
                val icon =
                    when (collapsed) {
                        false -> Icons.Filled.KeyboardArrowDown
                        true -> Icons.Filled.KeyboardArrowUp
                    }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun HaloAccordionContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            content()
        }
    }
}
