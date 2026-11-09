package cl.olimpictraining.app.ui.evaluacion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.components.WeekdayDateSelector
import cl.olimpictraining.app.ui.theme.*
import java.time.LocalDate

private val horasFuerza = listOf(
    "07:00",
    "08:30"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuerzaAgarreScreen(
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
                        "Fuerza de agarre",
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
                    text = "Test de fuerza de agarre",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Esta evaluación mide la fuerza de prensión de tu mano mediante un dinamómetro y permite registrar tu progreso.",
                    color = OlimpicTextSecondary
                )
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

                items(horasFuerza.size) { index ->

                    val hour = horasFuerza[index]

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
