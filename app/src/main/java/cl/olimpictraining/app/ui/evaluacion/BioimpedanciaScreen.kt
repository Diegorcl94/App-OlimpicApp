package cl.olimpictraining.app.ui.evaluacion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.components.WeekdayDateSelector
import cl.olimpictraining.app.ui.theme.*
import java.time.LocalDate

private val horasBioimpedancia = listOf(
    "07:00",
    "08:30"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BioimpedanciaScreen(
    onBack: () -> Unit
) {

    var selectedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    var selectedHour by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Bioimpedancia",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .navigationBarsPadding(),

            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 18.dp
            ),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Text(
                    text = "Evaluación de bioimpedancia",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Esta evaluación permite obtener información relacionada con distintos componentes de tu composición corporal.",
                    color = OlimpicTextSecondary
                )
            }

            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color(0xFFFFF5DD)
                ) {
                    Text(
                        text = "💡 Recuerda asistir en ayunas. No necesitas utilizar ropa deportiva para realizar esta evaluación.",
                        modifier = Modifier.padding(16.dp),
                        color = Color(0xFF795500),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            item {
                Text(
                    text = "Selecciona una fecha",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                WeekdayDateSelector(
                    selectedDate = selectedDate,
                    onDateSelected = {
                        selectedDate = it
                        selectedHour = null
                    }
                )
            }

            if (selectedDate != null) {

                item {
                    Text(
                        text = "Horas disponibles",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                items(horasBioimpedancia.size) { index ->

                    val hour = horasBioimpedancia[index]

                    EvaluationHourCard(
                        hour = hour,
                        selected = selectedHour == hour,
                        onClick = {
                            selectedHour = hour
                        }
                    )
                }

                item {
                    AnimatedVisibility(
                        visible = selectedHour != null
                    ) {
                        Button(
                            onClick = {
                                // Firestore posteriormente
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                "Confirmar hora",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EvaluationHourCard(
    hour: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                if (selected) OlimpicBlue
                else Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null,
                tint =
                    if (selected) Color.White
                    else OlimpicBlue
            )

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Text(
                text = hour,
                color =
                    if (selected) Color.White
                    else OlimpicTextPrimary,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
