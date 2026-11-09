package cl.olimpictraining.app.ui.perfil

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.components.AvatarPicker
import cl.olimpictraining.app.ui.components.UserAvatar
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    userName: String,
    userEmail: String,
    selectedAvatarId: String,
    onAvatarSelected: (String) -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {

    val context = LocalContext.current

    var showTerms by remember {
        mutableStateOf(false)
    }

    var showWithdrawConsent by remember {
        mutableStateOf(false)
    }

    var showWithdrawSuccess by remember {
        mutableStateOf(false)
    }

    var showAvatarPicker by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi perfil",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 900.dp)
                    .navigationBarsPadding(),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    UserAvatar(
                        avatarId = selectedAvatarId,
                        size = 112.dp
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    TextButton(
                        onClick = {
                            showAvatarPicker = true
                        }
                    ) {

                        Text(
                            text = "Cambiar avatar",
                            color = OlimpicBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Text(
                        text = userName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )

                    Text(
                        text = userEmail,
                        color = OlimpicTextSecondary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            ProfileItem(
                                title = "Empresa",
                                value = "Caja Los Andes"
                            )

                            ProfileItem(
                                title = "Plan",
                                value = "Colaborador"
                            )

                            ProfileItem(
                                title = "Estado",
                                value = "Activo"
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                }

                item {
                    Text(
                        text = "Cuenta y privacidad",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    ProfileOptionButton(
                        icon = Icons.Default.Description,
                        title = "Términos y condiciones",
                        subtitle = "Consulta las condiciones de uso de Olimpic Training.",
                        onClick = {
                            showTerms = true
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    ProfileOptionButton(
                        icon = Icons.Default.PrivacyTip,
                        title = "Retirar consentimiento",
                        subtitle = "Gestiona tu consentimiento y solicitud de eliminación de datos.",
                        onClick = {
                            showWithdrawConsent = true
                        },
                        danger = true
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    ProfileOptionButton(
                        icon = Icons.Default.Email,
                        title = "Soporte",
                        subtitle = "Contáctanos si necesitas ayuda con la aplicación.",
                        onClick = {

                            val intent = Intent(
                                Intent.ACTION_SENDTO
                            ).apply {

                                data = Uri.parse(
                                    "mailto:soporte.olimpictraining@gmail.com"
                                )

                                putExtra(
                                    Intent.EXTRA_SUBJECT,
                                    "Soporte aplicación Olimpic Training"
                                )
                            }

                            context.startActivity(intent)
                        }
                    )
                }

                item {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedButton(
                        onClick = onLogout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = OlimpicBlue
                        )
                    ) {

                        Text(
                            text = "Cerrar sesión",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                }
            }
        }
    }

    // TÉRMINOS Y CONDICIONES
    if (showTerms) {

        AlertDialog(
            onDismissRequest = {
                showTerms = false
            },

            title = {
                Text(
                    text = "Términos y condiciones",
                    fontWeight = FontWeight.ExtraBold
                )
            },

            text = {

                Text(
                    text =
                        "Aquí se mostrará la versión vigente de los Términos y Condiciones de Olimpic Training.\n\n" +
                        "En la versión final, este contenido será reemplazado por el documento legal aprobado para la aplicación."
                )
            },

            confirmButton = {

                Button(
                    onClick = {
                        showTerms = false
                    }
                ) {

                    Text("Entendido")
                }
            }
        )
    }

    // RETIRO DEL CONSENTIMIENTO
    if (showWithdrawConsent) {

        AlertDialog(
            onDismissRequest = {
                showWithdrawConsent = false
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.PrivacyTip,
                    contentDescription = null,
                    tint = OlimpicError
                )
            },

            title = {
                Text(
                    text = "Retirar consentimiento",
                    fontWeight = FontWeight.ExtraBold
                )
            },

            text = {

                Column {

                    Text(
                        text =
                            "Al retirar tu consentimiento, solicitarás dejar de utilizar tus datos personales para los fines asociados a esta cuenta."
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "También podrás solicitar la eliminación permanente de los datos personales asociados a tu perfil, cuando corresponda."
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "Esta acción puede provocar la pérdida de acceso a funciones de Olimpic Training y no podrá deshacerse una vez completada.",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "Por ahora esta función se encuentra en modo de demostración.",
                        color = OlimpicTextSecondary
                    )
                }
            },

            confirmButton = {

                Button(
                    onClick = {

                        showWithdrawConsent = false
                        showWithdrawSuccess = true

                        // FUTURO:
                        // 1. registrar solicitud
                        // 2. eliminar datos en Firestore
                        // 3. eliminar documentos asociados
                        // 4. eliminar cuenta de Firebase Auth
                        // 5. cerrar sesión
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = OlimpicError
                    )
                ) {

                    Text(
                        text = "Retirar consentimiento"
                    )
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showWithdrawConsent = false
                    }
                ) {

                    Text(
                        text = "Cancelar"
                    )
                }
            }
        )
    }

    // CONFIRMACIÓN TEMPORAL
    if (showWithdrawSuccess) {

        AlertDialog(
            onDismissRequest = {
                showWithdrawSuccess = false
            },

            title = {
                Text(
                    text = "Solicitud recibida"
                )
            },

            text = {
                Text(
                    text =
                        "La función de retiro del consentimiento está actualmente simulada. Cuando conectemos Firebase, esta acción gestionará la solicitud real."
                )
            },

            confirmButton = {

                Button(
                    onClick = {
                        showWithdrawSuccess = false
                    }
                ) {

                    Text("Aceptar")
                }
            }
        )
    }

    // MODAL BOTTOM SHEET AVATAR PICKER
    if (showAvatarPicker) {

        ModalBottomSheet(
            onDismissRequest = {
                showAvatarPicker = false
            },

            containerColor =
                OlimpicBackground
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .navigationBarsPadding()
            ) {

                Text(
                    text = "Elige tu avatar",

                    style =
                        MaterialTheme.typography.headlineMedium,

                    fontWeight =
                        FontWeight.ExtraBold,

                    color =
                        OlimpicTextPrimary
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(
                    text =
                        "Personaliza tu perfil con el avatar que más te represente.",

                    color =
                        OlimpicTextSecondary,

                    style =
                        MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                AvatarPicker(
                    selectedAvatarId =
                        selectedAvatarId,

                    onAvatarSelected = { avatarId ->
                        onAvatarSelected(avatarId)
                        showAvatarPicker = false
                    }
                )

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )
            }
        }
    }
}

@Composable
private fun ProfileItem(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color = OlimpicTextSecondary
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = OlimpicTextPrimary
        )
    }
}

@Composable
private fun ProfileOptionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    danger: Boolean = false
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                if (danger) {
                    Color(0xFFFFF1F0)
                } else {
                    Color.White
                }
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(46.dp),
                shape = CircleShape,

                color =
                    if (danger) {
                        Color(0xFFFFE4E1)
                    } else {
                        OlimpicBlueLight
                    }
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,

                    tint =
                        if (danger) {
                            OlimpicError
                        } else {
                            OlimpicBlue
                        },

                    modifier = Modifier.padding(11.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,

                    color =
                        if (danger) {
                            OlimpicError
                        } else {
                            OlimpicTextPrimary
                        }
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = subtitle,
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
