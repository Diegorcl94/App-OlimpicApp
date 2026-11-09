package cl.olimpictraining.app.ui.reservas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val CAPACIDAD_ENTRENAMIENTO = 20

data class TrainingBlock(
    val time: String,
    val subtitle: String,
    val period: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasScreen(
    onBack: () -> Unit
) {

    var selectedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    var selectedBlock by remember {
        mutableStateOf<TrainingBlock?>(null)
    }

    val availableDates = remember {
        val today = LocalDate.now()

        (0..20)
            .map { today.plusDays(it.toLong()) }
            .filter {
                it.dayOfWeek != DayOfWeek.SATURDAY &&
                        it.dayOfWeek != DayOfWeek.SUNDAY
            }
    }

    val fullFormatter = DateTimeFormatter.ofPattern(
        "EEEE d 'de' MMMM",
        Locale("es", "CL")
    )

    val trainingBlocks = remember(selectedDate) {
        when (selectedDate?.dayOfWeek) {
            DayOfWeek.FRIDAY -> listOf(
                TrainingBlock("07:00 - 10:00", "Bloque de entrenamiento", "Mañana"),
                TrainingBlock("10:00 - 12:00", "Bloque de entrenamiento", "Mañana"),
                TrainingBlock("12:00 - 15:00", "Bloque de entrenamiento", "Tarde"),
                TrainingBlock("15:00 - 17:00", "Bloque de entrenamiento", "Tarde"),
                TrainingBlock("17:00 - 18:00", "Bloque de entrenamiento", "Tarde")
            )

            else -> listOf(
                TrainingBlock("07:00 - 10:00", "Bloque de entrenamiento", "Mañana"),
                TrainingBlock("10:00 - 12:00", "Bloque de entrenamiento", "Mañana"),
                TrainingBlock("12:00 - 15:00", "Bloque de entrenamiento", "Tarde"),
                TrainingBlock("15:00 - 17:00", "Bloque de entrenamiento", "Tarde"),
                TrainingBlock("17:00 - 19:00", "Bloque de entrenamiento", "Tarde"),
                TrainingBlock("19:00 - 21:00", "Bloque de entrenamiento", "Noche")
            )
        }
    }

    val groupedBlocks = trainingBlocks.groupBy { it.period }

    Scaffold(
        containerColor = OlimpicBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Reservar entrenamiento",
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                ReservationHeroCard()
            }

            item {
                Column {
                    Text(
                        text = "Selecciona una fecha",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(availableDates) { date ->
                            TrainingDateCard(
                                date = date,
                                selected = selectedDate == date,
                                onClick = {
                                    selectedDate = date
                                    selectedBlock = null
                                }
                            )
                        }
                    }
                }
            }

            if (selectedDate != null) {

                item {
                    SelectedDateInfoCard(
                        dateText = selectedDate!!
                            .format(fullFormatter)
                            .replaceFirstChar {
                                if (it.isLowerCase()) {
                                    it.titlecase(Locale("es", "CL"))
                                } else {
                                    it.toString()
                                }
                            }
                    )
                }

                groupedBlocks.forEach { (period, blocks) ->

                    item {
                        Text(
                            text = period,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = OlimpicTextPrimary
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            blocks.chunked(2).forEach { rowBlocks ->

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {

                                    rowBlocks.forEach { block ->
                                        Box(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            TrainingBlockCard(
                                                block = block,
                                                selected = selectedBlock == block,
                                                onClick = {
                                                    selectedBlock = block
                                                }
                                            )
                                        }
                                    }

                                    if (rowBlocks.size == 1) {
                                        Spacer(
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = selectedBlock != null
                    ) {
                        SelectionSummaryCard(
                            date = selectedDate!!,
                            block = selectedBlock!!,
                            onConfirm = {
                                // Más adelante:
                                // guardar reserva real en Firebase
                            }
                        )
                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
            }
        }
    }
}

@Composable
private fun ReservationHeroCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            OlimpicBlueDeep,
                            OlimpicBlue
                        )
                    )
                )
                .padding(20.dp)
        ) {

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(42.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.16f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text = "Reserva tu entrenamiento",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Selecciona la fecha y el bloque horario en que deseas entrenar.",
                    color = Color.White.copy(alpha = 0.95f),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White.copy(alpha = 0.14f)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Groups,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "Capacidad máxima por bloque: 20 alumnos",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrainingDateCard(
    date: LocalDate,
    selected: Boolean,
    onClick: () -> Unit
) {

    val dayFormatter = DateTimeFormatter.ofPattern(
        "EEE",
        Locale("es", "CL")
    )

    val monthFormatter = DateTimeFormatter.ofPattern(
        "MMM",
        Locale("es", "CL")
    )

    Card(
        modifier = Modifier
            .width(82.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) OlimpicBlue else Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) OlimpicBlue else Color(0xFFE3E7EF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (selected) 5.dp else 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = date.format(dayFormatter)
                    .replace(".", "")
                    .uppercase(),
                color = if (selected) Color.White else OlimpicTextSecondary,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = date.dayOfMonth.toString(),
                color = if (selected) Color.White else OlimpicTextPrimary,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = date.format(monthFormatter)
                    .replace(".", "")
                    .uppercase(),
                color = if (selected) Color.White.copy(alpha = 0.85f) else OlimpicTextSecondary,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun SelectedDateInfoCard(
    dateText: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                color = OlimpicBlueLight
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = OlimpicBlue,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column {
                Text(
                    text = "Fecha seleccionada",
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = dateText,
                    color = OlimpicTextPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
private fun TrainingBlockCard(
    block: TrainingBlock,
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
            containerColor = if (selected) OlimpicBlueLight else Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) OlimpicBlue else Color(0xFFE3E7EF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (selected) 4.dp else 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = if (selected) OlimpicBlue else OlimpicBlueLight
            ) {
                Icon(
                    imageVector = if (selected) Icons.Default.CheckCircle else Icons.Default.Schedule,
                    contentDescription = null,
                    tint = if (selected) Color.White else OlimpicBlue,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = block.time,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = OlimpicTextPrimary
                )

                Text(
                    text = block.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = OlimpicTextSecondary
                )
            }
        }
    }
}

@Composable
private fun SelectionSummaryCard(
    date: LocalDate,
    block: TrainingBlock,
    onConfirm: () -> Unit
) {

    val formatter = DateTimeFormatter.ofPattern(
        "EEEE d 'de' MMMM",
        Locale("es", "CL")
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Text(
                text = "Tu selección",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = OlimpicTextPrimary
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Fecha",
                color = OlimpicTextSecondary,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = date.format(formatter)
                    .replaceFirstChar {
                        if (it.isLowerCase()) {
                            it.titlecase(Locale("es", "CL"))
                        } else {
                            it.toString()
                        }
                    },
                color = OlimpicTextPrimary,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Bloque",
                color = OlimpicTextSecondary,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = block.time,
                color = OlimpicTextPrimary,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Confirmar reserva",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
