package cl.olimpictraining.app.ui.evaluacion

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedidasScreen(
    onBack: () -> Unit
) {

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var historial by remember {
        mutableStateOf<List<Float>>(emptyList())
    }

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Medidas corporales",
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
                .navigationBarsPadding()
                .imePadding(),

            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 18.dp
            ),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Text(
                    text = "Registra tus medidas",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Mantén actualizado tu peso y altura para revisar tu evolución.",
                    color = OlimpicTextSecondary
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        OutlinedTextField(
                            value = peso,
                            onValueChange = {
                                peso = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Peso")
                            },
                            suffix = {
                                Text("kg")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(14.dp)
                        )

                        OutlinedTextField(
                            value = altura,
                            onValueChange = {
                                altura = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Altura")
                            },
                            suffix = {
                                Text("cm")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Button(
                            onClick = {
                                peso
                                    .replace(",", ".")
                                    .toFloatOrNull()
                                    ?.let {
                                        historial = historial + it
                                    }
                            },
                            enabled =
                                peso.isNotBlank() &&
                                altura.isNotBlank(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Text(
                                "Guardar medición",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Evolución de peso",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        if (historial.size < 2) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "📈 Necesitas al menos dos registros para mostrar tu evolución.",
                                    color = OlimpicTextSecondary
                                )
                            }

                        } else {

                            WeightChart(
                                values = historial
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeightChart(
    values: List<Float>
) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {

        val max = values.maxOrNull() ?: return@Canvas
        val min = values.minOrNull() ?: return@Canvas
        val range = (max - min).takeIf { it > 0f } ?: 1f

        val horizontalStep =
            size.width / (values.size - 1)

        val path = Path()

        values.forEachIndexed { index, value ->

            val x = horizontalStep * index

            val normalized =
                (value - min) / range

            val y =
                size.height -
                    normalized * (size.height * 0.75f) -
                    size.height * 0.12f

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }

            drawCircle(
                color = OlimpicBlue,
                radius = 7f,
                center = Offset(x, y)
            )
        }

        drawPath(
            path = path,
            color = OlimpicBlue,
            style = Stroke(
                width = 7f,
                cap = StrokeCap.Round
            )
        )
    }
}
