package cl.olimpictraining.app.ui.rutina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutinaScreen(
    onBack: () -> Unit
) {
    Scaffold(
        containerColor = OlimpicBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi rutina",
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
                .padding(padding)
                .background(OlimpicBackground)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                OlimpicBlueDeep,
                                OlimpicBlue,
                                OlimpicCyan
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(
                    modifier = Modifier.height(42.dp)
                )

                Surface(
                    modifier = Modifier.size(88.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.18f)
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(22.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(26.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 7.dp
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(26.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "🏋️",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Spacer(
                            modifier = Modifier.height(14.dp)
                        )

                        Text(
                            text = "Aún no tienes una rutina asignada",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = OlimpicTextPrimary
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text = "Cuando tu profesor asigne tu plan de entrenamiento, podrás verlo desde esta sección.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OlimpicTextSecondary
                        )

                        Spacer(
                            modifier = Modifier.height(22.dp)
                        )

                        Button(
                            onClick = {
                                // Más adelante aquí conectaremos
                                // la solicitud real a Firebase
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OlimpicBlue
                            )
                        ) {
                            Text(
                                text = "Solicitar mi rutina",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
