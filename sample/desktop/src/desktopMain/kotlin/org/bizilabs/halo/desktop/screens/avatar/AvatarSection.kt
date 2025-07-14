package org.bizilabs.halo.desktop.screens.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import halo.sample.desktop.generated.resources.Res
import halo.sample.desktop.generated.resources.avatar_image
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.ComponentSize
import org.bizilabs.halo.components.HaloText
import org.bizilabs.halo.components.avatars.HaloAvatar
import org.bizilabs.halo.components.cards.HaloSlotCard
import org.bizilabs.halo.state.HaloBorder
import org.jetbrains.compose.resources.painterResource

@Composable
fun AvatarSection() {
    val painter = painterResource(Res.drawable.avatar_image)
    val placeholderImage: @Composable () -> Unit = {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            HaloText(
                text =
                    "Avatars are compact UI elements that visually represent a user, entity, or object.\n" +
                        "They are commonly used to personalize experiences, indicate identity, " +
                        "or enhance recognition in components such as profiles, lists, and chat messages.",
                color = HaloTheme.colorScheme.background.onBase,
                fontWeight = FontWeight.Light,
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                HaloText(text = "Design")
                Spacer(modifier = Modifier.height(8.dp))
                HaloSlotCard(modifier = Modifier) {
                    Box(
                        modifier = Modifier.padding(8.dp).wrapContentSize(),
                    ) {
                        HaloSlotCard {
                            HaloText(
                                modifier = Modifier.padding(16.dp),
                                text = "Icon",
                            )
                        }
                    }
                }
            }
            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Extra Small")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.ExtraSmall,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.ExtraSmall,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.ExtraSmall,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.ExtraSmall,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Small")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Small,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Small,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Small,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Small,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Medium")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Medium,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Medium,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Medium,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Medium,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Large")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Large,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.Large,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Large,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.Large,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                }
            }

            item {
                HaloText(modifier = Modifier.padding(bottom = 16.dp), text = "Extra Large")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.ExtraLarge,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = false,
                            size = ComponentSize.ExtraLarge,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.ExtraLarge,
                            placeholderContent = placeholderImage,
                        )
                    }
                    item {
                        HaloAvatar(
                            model = null,
                            modifier = Modifier,
                            onClick = null,
                            isLoading = true,
                            size = ComponentSize.ExtraLarge,
                            placeholderContent = placeholderImage,
                            border = HaloBorder(width = 1.dp, color = HaloTheme.colorScheme.background.onSurface),
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.padding(24.dp)) }
        }
    }
}
