package cl.olimpictraining.app.ui.evaluacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluacionScreen(
    onBack: () -> Unit
) {

    Scaffold(
        containerColor =
            OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Evaluaciones",
                        fontWeight =
                            FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBack
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription =
                                "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            Text(
                "Evalúa tu progreso",
                style =
                    MaterialTheme.typography.headlineMedium,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                Modifier.height(8.dp)
            )

            Text(
                "Solicita una evaluación con el equipo de Olimpic Training.",
                color =
                    OlimpicTextSecondary
            )

            Spacer(
                Modifier.height(26.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(24.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.surface
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(22.dp)
                ) {

                    Icon(
                        Icons.Default.Assessment,
                        contentDescription = null,
                        tint =
                            OlimpicBlue,
                        modifier =
                            Modifier.size(44.dp)
                    )

                    Spacer(
                        Modifier.height(16.dp)
                    )

                    Text(
                        "Evaluación física",
                        fontWeight =
                            FontWeight.Bold,
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        Modifier.height(6.dp)
                    )

                    Text(
                        "Solicita una hora para conocer tu progreso y actualizar tus objetivos.",
                        color =
                            OlimpicTextSecondary
                    )

                    Spacer(
                        Modifier.height(20.dp)
                    )

                    Button(
                        onClick = {},
                        modifier =
                            Modifier.fillMaxWidth(),
                        shape =
                            RoundedCornerShape(14.dp)
                    ) {

                        Text(
                            "Solicitar evaluación"
                        )
                    }
                }
            }
        }
    }
}
