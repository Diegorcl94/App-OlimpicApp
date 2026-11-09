package cl.olimpictraining.app.ui.registro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(
    userName: String,
    onContinue: () -> Unit
) {

    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        delay(250)

        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        OlimpicBlueDeep,
                        OlimpicBlue,
                        OlimpicCyan
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(
            visible = visible,
            enter =
                fadeIn() +
                scaleIn(
                    initialScale = 0.82f
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier.size(105.dp),
                    shape = CircleShape,
                    color = Color.White
                ) {

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = OlimpicBlue,
                        modifier = Modifier.padding(26.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Text(
                    text = "¡Bienvenido, $userName! 🎉",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text =
                        "Tu registro fue completado correctamente. Ya formas parte de Olimpic Training.",
                    color = Color.White.copy(
                        alpha = 0.90f
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text =
                        "Ahora queremos conocerte un poco mejor para personalizar tu experiencia de entrenamiento.",
                    color = Color.White.copy(
                        alpha = 0.80f
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(34.dp)
                )

                Button(
                    onClick = onContinue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = OlimpicBlue
                    )
                ) {

                    Text(
                        text = "Comenzar",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
