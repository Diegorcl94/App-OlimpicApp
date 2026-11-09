package cl.olimpictraining.app.ui.registro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EventRepeat
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanSelectionScreen(
    onBack: () -> Unit,
    onPlanSelected: () -> Unit
) {

    var selected by remember {
        mutableStateOf(false)
    }

    val renewalDate =
        lastBusinessDayOfMonth(
            LocalDate.now()
        )

    val formatter =
        DateTimeFormatter.ofPattern(
            "d 'de' MMMM",
            Locale("es", "CL")
        )

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Selecciona tu plan",
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .widthIn(max = 650.dp)
        ) {

            Text(
                text = "Plan Caja Los Andes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = OlimpicTextPrimary
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Como colaborador de Caja Los Andes tienes acceso sin costo a Olimpic Training.",
                color = OlimpicTextSecondary
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selected = true
                    },

                shape = RoundedCornerShape(28.dp),

                border =
                    if (selected) {
                        BorderStroke(
                            2.dp,
                            OlimpicBlue
                        )
                    } else {
                        null
                    },

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Surface(
                            shape = RoundedCornerShape(100.dp),
                            color = OlimpicBlueLight
                        ) {

                            Text(
                                text = "COLABORADOR",
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                                color = OlimpicBlue,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                        if (selected) {

                            Surface(
                                modifier = Modifier.size(34.dp),
                                shape = CircleShape,
                                color = OlimpicBlue
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.padding(7.dp)
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Text(
                        text = "Caja Los Andes",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Text(
                            text = "$0",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = OlimpicBlue
                        )

                        Spacer(
                            modifier = Modifier.width(6.dp)
                        )

                        Text(
                            text = "/ mes",
                            color = OlimpicTextSecondary,
                            modifier = Modifier.padding(
                                bottom = 5.dp
                            )
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    HorizontalDivider()

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    PlanFeature(
                        "Reserva de bloques de entrenamiento"
                    )

                    PlanFeature(
                        "Acceso a tus rutinas"
                    )

                    PlanFeature(
                        "Solicitud de evaluaciones físicas"
                    )

                    PlanFeature(
                        "Eventos y notificaciones"
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = OlimpicBlueLight
                    ) {

                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.EventRepeat,
                                contentDescription = null,
                                tint = OlimpicBlue
                            )

                            Spacer(
                                modifier = Modifier.width(10.dp)
                            )

                            Column {

                                Text(
                                    text = "Renovación mensual",
                                    fontWeight = FontWeight.Bold,
                                    color = OlimpicTextPrimary
                                )

                                Text(
                                    text =
                                        "El plan se renueva el último día hábil de cada mes. Próxima renovación: ${
                                            renewalDate.format(formatter)
                                        }.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = OlimpicTextSecondary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onPlanSelected,
                enabled = selected,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(17.dp)
            ) {

                Text(
                    text = "Seleccionar plan",
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
private fun PlanFeature(
    text: String
) {

    Row(
        modifier = Modifier.padding(
            vertical = 5.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = OlimpicBlue,
            modifier = Modifier.size(18.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = text,
            color = OlimpicTextPrimary
        )
    }
}

private fun lastBusinessDayOfMonth(
    date: LocalDate
): LocalDate {

    var result =
        date.withDayOfMonth(
            date.lengthOfMonth()
        )

    while (
        result.dayOfWeek == DayOfWeek.SATURDAY ||
        result.dayOfWeek == DayOfWeek.SUNDAY
    ) {

        result =
            result.minusDays(1)
    }

    return result
}
