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
 * A composable that displays an extra-small avatar (20.dp) with optional border, shape, and interaction.
 *
 * This avatar is ideal for use in compact UI elements such as badges, lists, toolbars, or chat items.
 * It provides hooks for customizing the loading and error UI, supports manual control of the loading state,
 * and allows toggling a decorative border.
 *
 * Internally, this delegates to [BaseAvatar] with a fixed size of 20.dp.
 *
 * @param model The image source model (e.g., URL, drawable resource, or file).
 * @param modifier Modifier applied to the outer container.
 * @param onClick Optional click listener. If provided, the avatar becomes interactive.
 * @param isLoading If true, forces the avatar to remain in a loading state regardless of Coil’s actual status.
 * @param onError Composable displayed when image loading fails.
 * @param shape The shape of the avatar (defaults to rounded corners).
 * @param isLoading If true, forces the avatar into a loading state regardless of Coil’s internal state.
 * @param withBorder If true, displays an outline around the avatar.
 * @param colors Optional color configuration for customizing border/background/avatar styling.
 * @param contentScale Defines how the image is scaled inside the container. Defaults to [ContentScale.Crop].
 */
@Composable
fun HaloAvatarExtraSmall(
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
            modifier = Modifier.size(20.dp),
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
