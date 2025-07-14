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
 * A composable that displays a small-sized avatar (28.dp) with optional border, shape, and state-based UI hooks.
 *
 * This avatar is suitable for use in compact but content-focused UI elements such as message threads, contact lists,
 * or settings screens. It builds on [BaseAvatar] with a fixed size of 28.dp and offers support for loading and error overlays,
 * border toggling, and theming via [HaloAvatarColors].
 *
 * @param model The image source model (e.g., URL, drawable resource, or file).
 * @param modifier Modifier applied to the outer container of the avatar.
 * @param onClick Optional click handler. If provided, the avatar becomes interactive.
 * @param onLoading Composable content displayed while the avatar is loading (either internally via Coil or via [isLoading]).
 * @param onError Composable content displayed when image loading fails.
 * @param shape The shape of the avatar. Defaults to a rounded rectangle.
 * @param isLoading If true, manually overrides loading state and forces the display of [onLoading] content.
 * @param withBorder If true, renders a border around the avatar for visual distinction.
 * @param colors Optional color styling for background, content, and border based on [HaloAvatarColors].
 * @param contentScale Defines how the image is scaled inside the container. Defaults to [ContentScale.Crop].
 *
 * Usage:
 * ```
 * HaloAvatarSmall(
 *     model = "https://example.com/avatar.jpg",
 *     onClick = { /* open profile */ },
 *     withBorder = true
 * )
 * ```
 */
@Composable
fun HaloAvatarSmall(
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
            modifier = Modifier.size(28.dp),
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
