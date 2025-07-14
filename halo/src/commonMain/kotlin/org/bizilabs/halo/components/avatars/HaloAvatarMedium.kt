package org.bizilabs.halo.components.avatars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.components.loaders.HaloCircularProgressIndicator

/**
 * A composable that displays a medium-sized avatar (36.dp) with support for borders, shapes,
 * and custom loading or error content.
 *
 * This avatar size is suitable for common profile or identity use cases in layouts like
 * chat messages, settings screens, or profile summaries. It wraps [BaseAvatar] with a fixed
 * size of 36.dp and allows consumers to customize appearance, behavior, and loading states.
 *
 * @param model The image source model (e.g., URL, drawable resource, or file).
 * @param modifier Modifier applied to the outer container of the avatar.
 * @param onClick Optional click handler. If provided, the avatar becomes interactive.
 * @param onLoading Composable content shown while the avatar is loading (either from Coil or manually via [isLoading]).
 * @param onError Composable content shown when image loading fails.
 * @param shape The shape of the avatar. Defaults to a rounded rectangle.
 * @param isLoading If true, forces the avatar to remain in a loading state regardless of Coil’s actual status.
 * @param withBorder If true, displays a border around the avatar for visual emphasis.
 * @param colors Optional color styling for the avatar’s container, content, and border using [HaloAvatarColors].
 * @param contentScale Defines how the image is scaled inside the container. Defaults to [ContentScale.Crop].
 *
 * Usage:
 * ```
 * HaloAvatarMedium(
 *     model = "https://example.com/avatar.png",
 *     onClick = { /* open user profile */ },
 *     withBorder = true,
 * )
 * ```
 */
@Composable
fun HaloAvatarMedium(
    model: Any?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onLoading: @Composable () -> Unit = @Composable {
        HaloCircularProgressIndicator()
    },
    onError: @Composable () -> Unit = @Composable {
        Box(
            modifier = Modifier.fillMaxSize(),
        )
    },
    shape: Shape = RoundedCornerShape(10),
    isLoading: Boolean = false,
    withBorder: Boolean = false,
    placeholderContent: (@Composable () -> Unit)? = null,
    colors: HaloAvatarColors? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(modifier = modifier) {
        BaseAvatar(
            model = model,
            modifier = Modifier.size(36.dp),
            onClick = onClick,
            loadingContent = onLoading,
            errorContent = onError,
            shape = shape,
            withBorder = withBorder,
            colors = colors,
            placeholderContent = placeholderContent,
            contentScale = contentScale,
            contentType =
                when {
                    isLoading -> ContentType.LOADING
                    model != null -> ContentType.REMOTE_IMAGE
                    else -> ContentType.PLACEHOLDER
                },
        )
    }
}
