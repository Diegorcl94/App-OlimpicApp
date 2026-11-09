package cl.olimpictraining.app.ui.evaluacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluacionScreen(
    onBack: () -> Unit,
    onBioimpedanciaClick: () -> Unit,
    onFuerzaClick: () -> Unit,
    onMedidasClick: () -> Unit
) {

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Evaluación física",
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

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            val isTablet = maxWidth >= 600.dp

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 900.dp),

                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(
                        text = "Conoce tu progreso",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Selecciona la evaluación que quieres realizar.",
                        color = OlimpicTextSecondary
                    )
                }

                if (isTablet) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                EvaluationMenuCard(
                                    title = "Bioimpedancia",
                                    description = "Conoce distintos indicadores relacionados con tu composición corporal.",
                                    icon = Icons.Default.Assessment,
                                    onClick = onBioimpedanciaClick
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                EvaluationMenuCard(
                                    title = "Fuerza de agarre",
                                    description = "Mide la fuerza de prensión de tu mano mediante dinamometría.",
                                    icon = Icons.Default.FitnessCenter,
                                    onClick = onFuerzaClick
                                )
                            }
                        }
                    }

                    item {
                        EvaluationMenuCard(
                            title = "Medidas corporales",
                            description = "Registra tu peso y altura y revisa la evolución de tus mediciones.",
                            icon = Icons.Default.MonitorWeight,
                            onClick = onMedidasClick
                        )
                    }
                } else {
                    item {
                        EvaluationMenuCard(
                            title = "Bioimpedancia",
                            description = "Conoce distintos indicadores relacionados con tu composición corporal.",
                            icon = Icons.Default.Assessment,
                            onClick = onBioimpedanciaClick
                        )
                    }

                    item {
                        EvaluationMenuCard(
                            title = "Fuerza de agarre",
                            description = "Mide la fuerza de prensión de tu mano mediante dinamometría.",
                            icon = Icons.Default.FitnessCenter,
                            onClick = onFuerzaClick
                        )
                    }

                    item {
                        EvaluationMenuCard(
                            title = "Medidas corporales",
                            description = "Registra tu peso y altura y revisa la evolución de tus mediciones.",
                            icon = Icons.Default.MonitorWeight,
                            onClick = onMedidasClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EvaluationMenuCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(54.dp),
                shape = CircleShape,
                color = OlimpicBlueLight
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = OlimpicBlue,
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = description,
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = OlimpicTextSecondary
            )
        }
    }
}
