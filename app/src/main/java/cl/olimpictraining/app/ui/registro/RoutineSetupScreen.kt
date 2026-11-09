package cl.olimpictraining.app.ui.registro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@Composable
fun RoutineSetupScreen(
    onHasRoutine: () -> Unit,
    onRequestRoutine: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(20.dp)
            .widthIn(max = 700.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Surface(
            modifier =
                Modifier.size(72.dp),

            shape =
                CircleShape,

            color =
                OlimpicBlueLight
        ) {

            Icon(
                imageVector =
                    Icons.Default.FitnessCenter,
                contentDescription = null,
                tint =
                    OlimpicBlue,
                modifier =
                    Modifier.padding(18.dp)
            )
        }

        Spacer(
            modifier =
                Modifier.height(22.dp)
        )

        Text(
            text = "¿Ya tienes una rutina?",
            style =
                MaterialTheme.typography.headlineMedium,
            fontWeight =
                FontWeight.ExtraBold,
            color =
                OlimpicTextPrimary
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                "Cuéntanos cómo quieres continuar para preparar tu experiencia de entrenamiento.",
            color =
                OlimpicTextSecondary,
            style =
                MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier =
                Modifier.height(28.dp)
        )

        RoutineOptionCard(
            title =
                "Sí, tengo rutina",

            description =
                "Ya cuento con un plan de entrenamiento y quiero continuar utilizándolo.",

            emoji = "💪",

            onClick =
                onHasRoutine
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        RoutineOptionCard(
            title =
                "Solicitar rutina",

            description =
                "Quiero solicitar un plan de entrenamiento a un profesor de Olimpic Training.",

            emoji = "🏋️",

            onClick =
                onRequestRoutine
        )
    }
}

@Composable
private fun RoutineOptionCard(
    title: String,
    description: String,
    emoji: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            ),

        shape =
            RoundedCornerShape(24.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    4.dp
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = emoji,
                style =
                    MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier =
                    Modifier.width(16.dp)
            )

            Column {

                Text(
                    text = title,
                    fontWeight =
                        FontWeight.ExtraBold,
                    style =
                        MaterialTheme.typography.titleMedium,
                    color =
                        OlimpicTextPrimary
                )

                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )

                Text(
                    text =
                        description,
                    color =
                        OlimpicTextSecondary,
                    style =
                        MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
