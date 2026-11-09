package cl.olimpictraining.app.data.model

import androidx.compose.ui.graphics.Color

data class AvatarOption(
    val id: String,
    val name: String,
    val color: Color
)

val defaultAvatarOptions = listOf(
    AvatarOption(
        id = "blue_icon",
        name = "Icono Azul",
        color = Color(0xFF1769FF)
    ),
    AvatarOption(
        id = "pink_icon",
        name = "Icono Rosado",
        color = Color(0xFFE91E63)
    )
)
