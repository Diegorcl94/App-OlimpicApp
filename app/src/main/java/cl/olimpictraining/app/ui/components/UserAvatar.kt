package cl.olimpictraining.app.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.OlimpicBlue
import cl.olimpictraining.app.util.ProfileImageUtils

@Composable
fun UserAvatar(
    avatarId: String,
    modifier: Modifier = Modifier,
    size: Dp = 52.dp,
    customAvatarUpdatedKey: Long = 0L
) {
    val context = LocalContext.current

    when (avatarId) {
        "pink_icon" -> {
            Surface(
                modifier = modifier.size(size),
                shape = CircleShape,
                color = Color(0xFFE91E63),
                shadowElevation = 3.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar Rosado",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize(0.55f)
                    )
                }
            }
        }
        "custom" -> {
            val customFile = remember(avatarId, customAvatarUpdatedKey) {
                ProfileImageUtils.getCustomAvatarFile(context)
            }
            val imageBitmap = remember(customFile, customAvatarUpdatedKey) {
                customFile?.let { file ->
                    try {
                        BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
                    } catch (e: Exception) {
                        null
                    }
                }
            }

            if (imageBitmap != null) {
                Surface(
                    modifier = modifier.size(size),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 3.dp
                ) {
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                UserAvatar(
                    avatarId = "blue_icon",
                    modifier = modifier,
                    size = size
                )
            }
        }
        else -> { // "blue_icon" or default
            Surface(
                modifier = modifier.size(size),
                shape = CircleShape,
                color = OlimpicBlue,
                shadowElevation = 3.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar Azul",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize(0.55f)
                    )
                }
            }
        }
    }
}
