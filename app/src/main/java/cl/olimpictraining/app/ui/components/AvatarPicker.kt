package cl.olimpictraining.app.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.data.model.defaultAvatarOptions
import cl.olimpictraining.app.ui.theme.*
import cl.olimpictraining.app.util.ProfileImageUtils

@Composable
fun AvatarPicker(
    selectedAvatarId: String,
    onAvatarSelected: (String) -> Unit
) {
    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val success = ProfileImageUtils.compressAndSaveImage(context, it)
            if (success) {
                onAvatarSelected("custom")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Iconos predeterminados",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = OlimpicTextPrimary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            defaultAvatarOptions.forEach { option ->
                val selected = selectedAvatarId == option.id

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onAvatarSelected(option.id) },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) OlimpicBlueLight else Color.White
                    ),
                    border = if (selected) {
                        androidx.compose.foundation.BorderStroke(2.dp, OlimpicBlue)
                    } else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            color = option.color
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = option.name,
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = option.name,
                            fontWeight = FontWeight.Bold,
                            color = OlimpicTextPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        if (selected) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Seleccionado",
                                color = OlimpicBlue,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Foto personalizada",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = OlimpicTextPrimary
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { photoPickerLauncher.launch("image/*") },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (selectedAvatarId == "custom") OlimpicBlueLight else Color.White
            ),
            border = if (selectedAvatarId == "custom") {
                androidx.compose.foundation.BorderStroke(2.dp, OlimpicBlue)
            } else null,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = OlimpicBlueLight
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.AddAPhoto,
                            contentDescription = "Subir foto",
                            tint = OlimpicBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Subir foto desde galería",
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "La foto se optimizará automáticamente en alta calidad.",
                        color = OlimpicTextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (selectedAvatarId == "custom") {
                    Text(
                        text = "Activa",
                        color = OlimpicBlue,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
