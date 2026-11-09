package cl.olimpictraining.app.ui.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

data class TrainingProfileData(
    val height: String,
    val weight: String,
    val level: String,
    val objective: String
)

private val levels =
    listOf(
        "Básico",
        "Medio",
        "Avanzado"
    )

private val objectives =
    listOf(
        "Bajar de peso",
        "Tonificar",
        "Prepararme para competencia",
        "Otro"
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingProfileScreen(
    onBack: () -> Unit,
    onContinue: (TrainingProfileData) -> Unit
) {

    var height by rememberSaveable {
        mutableStateOf("")
    }

    var weight by rememberSaveable {
        mutableStateOf("")
    }

    var level by rememberSaveable {
        mutableStateOf("")
    }

    var objective by rememberSaveable {
        mutableStateOf("")
    }

    var otherObjective by rememberSaveable {
        mutableStateOf("")
    }

    var error by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {

                    Text(
                        text = "Tu entrenamiento",
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
                .imePadding()
                .navigationBarsPadding()
                .widthIn(max = 720.dp),

            contentPadding =
                PaddingValues(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),

            verticalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = OlimpicBlue,
                    modifier = Modifier.size(44.dp)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Cuéntanos sobre ti",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text =
                        "Estos datos nos ayudarán a adaptar mejor tu experiencia en Olimpic Training.",
                    color = OlimpicTextSecondary
                )
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = height,

                        onValueChange = {
                            height = it.filter {
                                char ->
                                char.isDigit()
                            }
                        },

                        modifier =
                            Modifier.weight(1f),

                        label = {
                            Text("Altura")
                        },

                        suffix = {
                            Text("cm")
                        },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Number
                            ),

                        singleLine = true
                    )

                    OutlinedTextField(
                        value = weight,

                        onValueChange = {
                            weight = it.filter {
                                char ->
                                char.isDigit() ||
                                char == ',' ||
                                char == '.'
                            }
                        },

                        modifier =
                            Modifier.weight(1f),

                        label = {
                            Text("Peso")
                        },

                        suffix = {
                            Text("kg")
                        },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Decimal
                            ),

                        singleLine = true
                    )
                }
            }

            item {

                Text(
                    text = "Nivel de entrenamiento",
                    style =
                        MaterialTheme.typography.titleLarge,
                    fontWeight =
                        FontWeight.ExtraBold
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                levels.forEach { option ->

                    FilterChip(
                        selected =
                            level == option,

                        onClick = {
                            level = option
                        },

                        label = {
                            Text(option)
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 3.dp
                                )
                    )
                }
            }

            item {

                Text(
                    text = "¿Cuál es tu objetivo?",
                    style =
                        MaterialTheme.typography.titleLarge,
                    fontWeight =
                        FontWeight.ExtraBold
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                objectives.forEach {
                        option ->

                    FilterChip(
                        selected =
                            objective == option,

                        onClick = {
                            objective = option

                            if (
                                option != "Otro"
                            ) {
                                otherObjective = ""
                            }
                        },

                        label = {
                            Text(option)
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 3.dp
                                )
                    )
                }
            }

            if (objective == "Otro") {

                item {

                    OutlinedTextField(
                        value =
                            otherObjective,

                        onValueChange = {
                            otherObjective = it
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        label = {
                            Text(
                                "Cuéntanos tu objetivo"
                            )
                        },

                        minLines = 2
                    )
                }
            }

            if (error != null) {

                item {

                    Text(
                        text =
                            error.orEmpty(),
                        color =
                            MaterialTheme.colorScheme.error,
                        fontWeight =
                            FontWeight.Medium
                    )
                }
            }

            item {

                Button(
                    onClick = {

                        error =
                            when {

                                height.isBlank() ->
                                    "Ingresa tu altura."

                                weight.isBlank() ->
                                    "Ingresa tu peso."

                                level.isBlank() ->
                                    "Selecciona tu nivel de entrenamiento."

                                objective.isBlank() ->
                                    "Selecciona tu objetivo."

                                objective == "Otro" &&
                                otherObjective.isBlank() ->
                                    "Describe tu objetivo."

                                else -> null
                            }

                        if (error == null) {

                            onContinue(
                                TrainingProfileData(
                                    height =
                                        height,

                                    weight =
                                        weight,

                                    level =
                                        level,

                                    objective =
                                        if (
                                            objective == "Otro"
                                        ) {
                                            otherObjective
                                        } else {
                                            objective
                                        }
                                )
                            )
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    shape =
                        RoundedCornerShape(17.dp)
                ) {

                    Text(
                        text = "Continuar",
                        fontWeight =
                            FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
