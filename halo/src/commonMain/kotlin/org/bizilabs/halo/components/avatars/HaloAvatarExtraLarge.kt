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
 * A composable that displays an extra-large avatar (80.dp) with optional border, shape, and state-based content.
 *
 * This avatar size is ideal for highly visible UI areas such as user profile headers, onboarding screens,
 * or detail views where visual emphasis is important. It builds on [BaseAvatar] with a fixed size of 80.dp and supports:
 *
 * - A customizable loading indicator
 * - Error and placeholder content
 * - Theming via [HaloAvatarColors]
 * - Manual control over loading state
 * - Optional border and shape customization
 *
 * The avatar dynamically chooses its content based on the [isLoading] flag and [model] availability:
 * - When [isLoading] is true, [onLoading] content is shown regardless of image loading progress.
 * - When [model] is provided, the avatar attempts to load it via Coil.
 * - When [model] is null, [placeholderContent] is displayed.
 *
 * @param model The image source model (e.g., URL, drawable resource, or file).
 * @param modifier Modifier applied to the outer container of the avatar.
 * @param onClick Optional click handler. If provided, the avatar becomes interactive.
 * @param onLoading Composable content displayed while the avatar is loading (either manually or via Coil).
 * @param onError Composable content displayed when image loading fails.
 * @param shape The shape of the avatar. Defaults to a rounded rectangle.
 * @param isLoading If true, manually overrides the loading state and forces the display of [onLoading] content.
 * @param withBorder If true, renders a border around the avatar for visual distinction.
 * @param placeholderContent Composable content displayed when no [model] is provided and [isLoading] is false.
 * @param colors Optional color styling for background, content, and border based on [HaloAvatarColors].
 * @param contentScale Defines how the image is scaled inside the container. Defaults to [ContentScale.Crop].
 *
 * Usage:
 * ```
 * HaloAvatarExtraLarge(
 *     model = "https://example.com/avatar.jpg",
 *     onClick = { /* open profile */ },
 *     withBorder = true
 * )
 * ```
 */
@Composable
fun HaloAvatarExtraLarge(
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
    placeholderContent: (@Composable () -> Unit)? = null,
    shape: Shape = RoundedCornerShape(10),
    isLoading: Boolean = false,
    withBorder: Boolean = false,
    colors: HaloAvatarColors? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(modifier = modifier) {
        BaseAvatar(
            model = model,
            modifier = Modifier.size(80.dp),
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
