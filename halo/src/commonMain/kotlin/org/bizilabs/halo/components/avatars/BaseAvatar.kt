package org.bizilabs.halo.components.avatars

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColor
import org.bizilabs.halo.components.cards.HaloCard
import org.bizilabs.halo.components.cards.HaloOutlineCard
import org.bizilabs.halo.state.HaloBorder

/**
 * A base composable for displaying an avatar image with optional border, shape, and state-specific content.
 *
 * This function serves as the foundation for building reusable avatar components
 * (e.g., user profile pictures, group avatars) with consistent styling and flexible behavior.
 * It supports three avatar content states via [ContentType]:
 *
 * - [ContentType.Data]: Loads an image from a remote source (e.g., URL, file, resource).
 * - [ContentType.Loading]: Displays the [loadingContent] composable, useful for shimmer or progress indicators.
 * - [ContentType.Placeholder]: Displays fallback content via [placeholderContent] when no image is intended or available.
 *
 * - If [withBorder] is true, the avatar is wrapped in an outlined card.
 * - You can customize the visual appearance using [HaloAvatarColors] for container, content, and border colors.
 *
 * @param modifier Modifier applied to the avatar container.
 * @param model The image source to load (e.g., URL, resource ID, or file). Used when [contentType] is [ContentType.Data].
 * @param onClick Optional click handler. If provided, the avatar becomes interactive.
 * @param shape The shape of the avatar. Defaults to a rounded rectangle.
 * @param contentScale Defines how the image is scaled inside the container. Defaults to [ContentScale.Crop].
 * @param withBorder Whether to display a border around the avatar.
 * @param loadingContent Composable content shown during loading (e.g., spinner or shimmer).
 * @param errorContent Composable content shown if the image fails to load.
 * @param placeholderContent Composable content shown when [contentType] is [ContentType.Placeholder].
 * @param colors Optional color configuration for avatar background, content, and border.
 * @param contentType Defines the current avatar content state (loading, image, or placeholder).
 */

data class HaloAvatarColors(
    val default: HaloColor,
    val focused: HaloColor,
    val disabled: HaloColor,
)

enum class ContentType {
    Data,
    Loading,
    Placeholder,
}

@Composable
internal fun BaseAvatar(
    modifier: Modifier = Modifier,
    model: Any?,
    onClick: (() -> Unit)?,
    shape: Shape = RoundedCornerShape(10),
    contentScale: ContentScale = ContentScale.Crop,
    border: HaloBorder? = null,
    colors: HaloAvatarColors? = null,
    contentType: ContentType,
    loadingContent: (@Composable () -> Unit)? = null,
    errorContent: (@Composable () -> Unit)? = null,
    placeholderContent: (@Composable () -> Unit)? = null,
) {
    val cardColors =
        CardDefaults.cardColors(
            contentColor = colors?.default?.content ?: HaloTheme.colorScheme.content.strong,
            containerColor = colors?.default?.container ?: HaloTheme.colorScheme.background.surface,
        )
    val borderColor = border?.color ?: HaloTheme.colorScheme.content.strong

    Box(
        modifier = modifier.clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        val content: @Composable () -> Unit =
            {
                when (contentType) {
                    ContentType.Loading -> {
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .clip(shape)
                                    .wrapContentSize()
                                    .padding(4.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            loadingContent?.invoke()
                        }
                    }
                    ContentType.Data -> {
                        val stateContentBox: @Composable (content: @Composable () -> Unit) -> Unit = { content ->
                            Box(modifier = Modifier.fillMaxSize().padding(4.dp), contentAlignment = Alignment.Center) { content() }
                        }

                        SubcomposeAsyncImage(
                            model = model,
                            contentDescription = "an image of the user's account",
                            modifier = Modifier.fillMaxSize().clip(shape),
                            contentScale = contentScale,
                            loading = { state ->
                                stateContentBox { loadingContent?.invoke() }
                            },
                            error = {
                                stateContentBox { errorContent?.invoke() }
                            },
                        )
                    }
                    ContentType.Placeholder -> {
                        placeholderContent?.invoke()
                    }
                }
            }

        if (border != null) {
            AvatarOutlineCard(
                onClick = onClick,
                colors = cardColors,
                border = BorderStroke(border.width, borderColor),
                shape = shape,
            ) {
                content()
            }
        } else {
            AvatarCard(onClick = onClick, colors = cardColors, shape = shape) {
                content()
            }
        }
    }
}

/**
 * A stylized outline card used to wrap avatar content, with optional click behavior.
 *
 * This is a private utility composable used by avatar-related components to provide
 * a consistent outlined appearance, with support for both static and interactive avatars.
 *
 * @param modifier Modifier to apply to the card container.
 * @param onClick Optional click listener. If null, the card is rendered as non-clickable.
 * @param content Composable content to be displayed inside the outlined card.
 * @param colors Colors to be applied to the card container and content
 */
@Composable
private fun AvatarOutlineCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: CardColors,
    border: BorderStroke,
    shape: Shape,
    content: @Composable ColumnScope.() -> Unit,
) {
    HaloOutlineCard(
        modifier = modifier,
        border = border,
        colors = colors,
        onClick = onClick,
        shape = shape,
        content = content,
    )
}

/**
 * A stylized card used to wrap avatar content, with optional click behavior.
 *
 * This is a private utility composable used by avatar-related components to provide
 * a consistent filled appearance, with support for both static and interactive avatars.
 *
 * @param modifier Modifier to apply to the card container.
 * @param onClick Optional click listener. If null, the card is rendered as non-clickable.
 * @param content Composable content to be displayed inside the outlined card.
 * @param colors Colors to be applied to the card container and content
 */
@Composable
private fun AvatarCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: CardColors,
    shape: Shape,
    content: @Composable ColumnScope.() -> Unit,
) {
    HaloCard(
        modifier = modifier,
        colors = colors,
        onClick = onClick,
        shape = shape,
        content = content,
    )
}
