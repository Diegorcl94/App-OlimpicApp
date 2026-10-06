package cl.olimpictraining.app.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.olimpictraining.app.R
import cl.olimpictraining.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {}
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(150)
        visible = true
    }

    // Animación infinita muy suave del fondo
    val infiniteTransition = rememberInfiniteTransition(
        label = "background"
    )

    val circleOffset by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000,
                easing = EaseInOutSine
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "circleMovement"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OlimpicBackground)
    ) {

        // CABECERA AZUL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            OlimpicBlueDeep,
                            OlimpicBlue,
                            OlimpicCyan
                        )
                    )
                )
        )

        // CÍRCULO DECORATIVO 1
        Box(
            modifier = Modifier
                .offset(
                    x = 240.dp,
                    y = (30 + circleOffset).dp
                )
                .size(180.dp)
                .alpha(0.12f)
                .clip(CircleShape)
                .background(Color.White)
        )

        // CÍRCULO DECORATIVO 2
        Box(
            modifier = Modifier
                .offset(
                    x = (-55).dp,
                    y = (160 - circleOffset).dp
                )
                .size(150.dp)
                .alpha(0.10f)
                .clip(CircleShape)
                .background(Color.White)
        )

        AnimatedVisibility(
            visible = visible,
            enter =
                fadeIn(
                    animationSpec = tween(700)
                ) +
                slideInVertically(
                    animationSpec = tween(700),
                    initialOffsetY = { 80 }
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 22.dp
                    ),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Spacer(
                    modifier = Modifier.height(48.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_olimpic),
                    contentDescription = "Logo Olimpic Training",
                    modifier = Modifier
                        .size(105.dp)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Olimpic",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "TRAINING",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 4.sp,
                    color = Color.White.copy(alpha = 0.90f)
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Text(
                    text = "Tu progreso\nempieza hoy.",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    lineHeight = 38.sp,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "Entrena, reserva y lleva tu progreso siempre contigo.",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = OlimpicWhite
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .padding(22.dp)
                    ) {

                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.titleLarge,
                            color = OlimpicTextPrimary,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = "Ingresa con tu cuenta corporativa.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OlimpicTextSecondary
                        )

                        Spacer(
                            modifier = Modifier.height(22.dp)
                        )

                        Text(
                            text = "Correo corporativo",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Spacer(
                            modifier = Modifier.height(7.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it.trim()
                                errorMessage = null
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = {
                                Text(
                                    "nombre@cajalosandes.cl"
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    tint = OlimpicBlue
                                )
                            },
                            keyboardOptions =
                                KeyboardOptions(
                                    keyboardType =
                                        KeyboardType.Email
                                ),
                            singleLine = true,
                            shape = RoundedCornerShape(15.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Text(
                            text = "Contraseña",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Spacer(
                            modifier = Modifier.height(7.dp)
                        )

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                errorMessage = null
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = {
                                Text("••••••••")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = OlimpicBlue
                                )
                            },
                            visualTransformation =
                                PasswordVisualTransformation(),
                            singleLine = true,
                            shape = RoundedCornerShape(15.dp)
                        )

                        AnimatedVisibility(
                            visible =
                                errorMessage != null
                        ) {

                            Text(
                                text =
                                    errorMessage ?: "",
                                color = OlimpicError,
                                modifier =
                                    Modifier.padding(
                                        top = 10.dp
                                    ),
                                style =
                                    MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        Button(
                            onClick = {

                                val cleanEmail = email
                                    .trim()
                                    .lowercase()

                                when {

                                    cleanEmail == "alumno@example.cl" -> {
                                        onLoginSuccess()
                                    }

                                    cleanEmail.endsWith("@cajalosandes.cl") -> {
                                        onLoginSuccess()
                                    }

                                    else -> {
                                        errorMessage =
                                            "Debes utilizar un correo autorizado de Caja Los Andes."
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape =
                                RoundedCornerShape(15.dp),
                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        OlimpicBlue
                                )
                        ) {

                            if (loading) {

                                CircularProgressIndicator(
                                    modifier =
                                        Modifier.size(
                                            22.dp
                                        ),
                                    color =
                                        Color.White,
                                    strokeWidth =
                                        2.dp
                                )

                            } else {

                                Text(
                                    text =
                                        "Iniciar sesión",
                                    fontWeight =
                                        FontWeight.Bold
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            HorizontalDivider(
                                modifier =
                                    Modifier.weight(1f),
                                color =
                                    OlimpicBorder
                            )

                            Text(
                                text = "  o  ",
                                color =
                                    OlimpicTextSecondary
                            )

                            HorizontalDivider(
                                modifier =
                                    Modifier.weight(1f),
                                color =
                                    OlimpicBorder
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        OutlinedButton(
                            onClick = onGoogleClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape =
                                RoundedCornerShape(15.dp),
                            border =
                                ButtonDefaults.outlinedButtonBorder
                        ) {

                            Text(
                                text =
                                    "Continuar con Google",
                                color =
                                    OlimpicTextPrimary,
                                fontWeight =
                                    FontWeight.SemiBold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        TextButton(
                            onClick =
                                onRegisterClick,
                            modifier =
                                Modifier.align(
                                    Alignment.CenterHorizontally
                                )
                        ) {

                            Text(
                                text =
                                    "Crear una cuenta",
                                color =
                                    OlimpicBlue,
                                fontWeight =
                                    FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}
