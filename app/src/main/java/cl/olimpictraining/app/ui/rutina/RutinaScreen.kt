package cl.olimpictraining.app.ui.rutina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutinaScreen(
    onBack: () -> Unit
) {

    Scaffold(
        containerColor =
            OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Mi rutina",
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
                text =
                    "Tu entrenamiento",
                style =
                    MaterialTheme.typography.headlineMedium,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                Modifier.height(6.dp)
            )

            Text(
                text =
                    "Rutina asignada por tu profesor.",
                color =
                    OlimpicTextSecondary
            )

            Spacer(
                Modifier.height(24.dp)
            )

            ExerciseCard(
                "Press de banca",
                "4 series · 10 repeticiones"
            )

            ExerciseCard(
                "Sentadilla",
                "4 series · 12 repeticiones"
            )

            ExerciseCard(
                "Remo",
                "3 series · 12 repeticiones"
            )

            ExerciseCard(
                "Plancha",
                "3 series · 45 segundos"
            )
        }
    }
}

@Composable
private fun ExerciseCard(
    title: String,
    subtitle: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 12.dp
            ),
        shape =
            RoundedCornerShape(20.dp),
        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Icon(
                Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = OlimpicBlue
            )

            Spacer(
                Modifier.width(14.dp)
            )

            Column {

                Text(
                    title,
                    fontWeight =
                        FontWeight.Bold
                )

                Text(
                    subtitle,
                    color =
                        OlimpicTextSecondary
                )
            }
        }
    }
}
