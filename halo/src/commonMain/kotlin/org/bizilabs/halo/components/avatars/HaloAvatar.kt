package org.bizilabs.halo.components.avatars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.components.loaders.HaloCircularProgressIndicator
import org.bizilabs.halo.state.HaloBorder

/**
 * A flexible and scalable composable for displaying an avatar image with optional shape, border, and state-aware content.
 *
 * [HaloAvatar] provides a unified API for rendering avatars of varying sizes, suitable for different UI contexts—
 * from small icons in toolbars to large profile headers. It builds on [BaseAvatar] and supports:
 *
 * - Theming via [HaloAvatarColors]
 * - Visual border customization through [HaloBorder]
 * - State-aware content slots: loading, error, and placeholder
 * - Manual loading control via [isLoading]
 * - Configurable sizing via [ComponentSize]
 *
 * ### Content behavior:
 * The avatar's content is determined by the current state:
 * - If [isLoading] is `true`, [onLoading] is displayed.
 * - If [model] is non-null, the avatar attempts to load the image via Coil.
 * - If [model] is null and [isLoading] is `false`, [placeholderContent] is displayed.
 *
 * @param model The image source model (e.g., URL, drawable resource, or file).
 * @param modifier Modifier applied to the outer avatar container.
 * @param shape The shape of the avatar. Defaults to a rounded rectangle.
 * @param isLoading If true, forces the avatar into a loading state regardless of image state.
 * @param border Optional visual border defined via [HaloBorder]. If null, no border is applied.
 * @param colors Optional theme-based styling for container, content, and border via [HaloAvatarColors].
 * @param contentScale Defines how the image is scaled inside the avatar. Defaults to [ContentScale.Crop].
 * @param size The avatar size, defined using [ComponentSize]. Defaults to [ComponentSize.Small].
 * @param onClick Optional click handler. If provided, the avatar becomes interactive.
 * @param onLoading Composable content displayed while the avatar is loading.
 * @param onError Composable content displayed when image loading fails.
 * @param placeholderContent Composable content displayed when no [model] is provided and [isLoading] is false.
 *
 * ### Usage:
 * ```
 * HaloAvatar(
 *     model = "https://example.com/avatar.png",
 *     size = ComponentSize.Medium,
 *     border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.content.strong),
 *     onClick = { /* open profile */ }
 * )
 * ```
 */
@Composable
fun HaloAvatar(
    model: Any?,
    modifier: Modifier = Modifier,
    shape: Shape = HaloTheme.shapes.medium,
    isLoading: Boolean = false,
    border: HaloBorder? = null,
    colors: HaloAvatarColors? = null,
    contentScale: ContentScale = ContentScale.Crop,
    size: ComponentSize = ComponentSize.Small,
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
) {
    val sizeDp =
        when (size) {
            ComponentSize.ExtraSmall -> 20.dp
            ComponentSize.Small -> 28.dp
            ComponentSize.Medium -> 36.dp
            ComponentSize.Large -> 64.dp
            ComponentSize.ExtraLarge -> 80.dp
        }

    Box(modifier = modifier) {
        BaseAvatar(
            model = model,
            modifier = Modifier.size(sizeDp),
            onClick = onClick,
            loadingContent = onLoading,
            errorContent = onError,
            shape = shape,
            border = border,
            colors = colors,
            placeholderContent = placeholderContent,
            contentScale = contentScale,
            contentType =
                when {
                    isLoading -> ContentType.Loading
                    model != null -> ContentType.Data
                    else -> ContentType.Placeholder
                },
        )
    }
}
