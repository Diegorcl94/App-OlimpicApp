package cl.olimpictraining.app.ui.reservas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasScreen(
    onBack: () -> Unit
) {

    Scaffold(
        containerColor = OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Reservar hora",
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

            Card(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(24.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            OlimpicBlue
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = Color.White,
                        modifier =
                            Modifier.size(42.dp)
                    )

                    Spacer(
                        Modifier.width(16.dp)
                    )

                    Column {

                        Text(
                            "Agenda tu entrenamiento",
                            color =
                                Color.White,
                            fontWeight =
                                FontWeight.Bold,
                            style =
                                MaterialTheme.typography.titleLarge
                        )

                        Text(
                            "Selecciona día y horario disponible.",
                            color =
                                Color.White.copy(
                                    alpha = 0.8f
                                )
                        )
                    }
                }
            }

            Spacer(
                Modifier.height(24.dp)
            )

            Text(
                "Próximamente podrás seleccionar:",
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                Modifier.height(16.dp)
            )

            Text(
                "• Fecha\n• Horario\n• Cupos disponibles\n• Confirmación de reserva",
                color =
                    OlimpicTextSecondary,
                lineHeight =
                    MaterialTheme.typography.bodyLarge.lineHeight
            )
        }
    }
}
