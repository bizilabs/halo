package org.bizilabs.halo.components.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// ----------------- Models -----------------

/**
 * Represents the state of a single step in the stepper.
 */
enum class StepState {
    COMPLETED,
    CURRENT,
    PENDING,
}

/**
 * Data class to hold the information for a single step.
 *
 * @param title The title of the step.
 * @param state The current state of the step.
 * @param icon The icon to display for the step.
 */
data class Step(
    val title: String,
    val state: StepState,
    val icon: ImageVector,
)

// ----------------- Vertical Stepper -----------------

/**
 * A composable that displays a vertical stepper.
 *
 * @param steps The list of steps to display.
 * @param modifier The modifier to be applied to the stepper.
 * @param stepContent The composable lambda to render the content for each step.
 */
@Composable
fun VerticalStepper(
    steps: List<Step>,
    modifier: Modifier = Modifier,
    stepContent: @Composable (Step) -> Unit,
) {
    Column(modifier = modifier) {
        steps.forEachIndexed { index, step ->
            VerticalStepItem(
                step = step,
                isLastStep = index == steps.size - 1,
                content = { stepContent(step) },
            )
        }
    }
}

/**
 * A composable that displays a single item in a vertical stepper.
 *
 * @param step The step to display.
 * @param isLastStep Whether this is the last step in the stepper.
 * @param content The composable lambda to render the content of the step.
 */
@Composable
fun VerticalStepItem(
    step: Step,
    isLastStep: Boolean,
    content: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StepIcon(state = step.state, icon = step.icon)
            if (!isLastStep) {
                Divider(
                    color = if (step.state == StepState.COMPLETED) Color(0xFF4CAF50) else Color.LightGray,
                    modifier =
                        Modifier
                            .height(60.dp)
                            .width(2.dp),
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            content()
        }
    }
}

// ----------------- Horizontal Stepper -----------------

/**
 * A composable that displays a horizontal stepper.
 *
 * @param steps The list of steps to display.
 * @param modifier The modifier to be applied to the stepper.
 * @param stepHeader The composable lambda to render the header for each step.
 */
@Composable
fun HorizontalStepper(
    steps: List<Step>,
    modifier: Modifier = Modifier,
    stepHeader: @Composable (Step) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        steps.forEachIndexed { index, step ->
            HorizontalStepItem(
                step = step,
                isLastStep = index == steps.size - 1,
                header = { stepHeader(step) },
            )
        }
    }
}

/**
 * A composable that displays a single item in a horizontal stepper.
 *
 * @param step The step to display.
 * @param isLastStep Whether this is the last step in the stepper.
 * @param header The composable lambda to render the header of the step.
 */
@Composable
fun RowScope.HorizontalStepItem(
    step: Step,
    isLastStep: Boolean,
    header: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1f),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            StepIcon(state = step.state, icon = step.icon)
            if (!isLastStep) {
                Divider(
                    color = if (step.state == StepState.COMPLETED) Color(0xFF4CAF50) else Color.LightGray,
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        header()
    }
}

// ----------------- Common Components -----------------

/**
 * A composable that displays the icon for a step, styled based on the step's state.
 *
 * @param state The state of the step.
 * @param icon The icon to display.
 */
@Composable
fun StepIcon(
    state: StepState,
    icon: ImageVector,
) {
    val (backgroundColor, iconColor) =
        when (state) {
            StepState.COMPLETED -> Color(0xFF4CAF50) to Color.White
            StepState.CURRENT -> Color(0xFF3F51B5) to Color.White
            StepState.PENDING -> Color.LightGray to Color.White
        }

    Box(
        modifier =
            Modifier
                .size(40.dp)
                .background(color = backgroundColor, shape = MaterialTheme.shapes.extraLarge),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp),
        )
    }
}
