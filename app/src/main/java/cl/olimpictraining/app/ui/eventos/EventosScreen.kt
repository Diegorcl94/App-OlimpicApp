package cl.olimpictraining.app.ui.eventos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventosScreen(
    onBack: () -> Unit
) {

    Scaffold(
        containerColor =
            OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Eventos",
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
                "Actividades Olimpic",
                style =
                    MaterialTheme.typography.headlineMedium,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                Modifier.height(20.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(24.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            OlimpicBlueLight
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(22.dp)
                ) {

                    Icon(
                        Icons.Default.Event,
                        contentDescription = null,
                        tint =
                            OlimpicBlue,
                        modifier =
                            Modifier.size(42.dp)
                    )

                    Spacer(
                        Modifier.height(14.dp)
                    )

                    Text(
                        "Desafío Olimpic",
                        fontWeight =
                            FontWeight.Bold,
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Text(
                        "30 de octubre",
                        color =
                            OlimpicBlue
                    )

                    Spacer(
                        Modifier.height(10.dp)
                    )

                    Text(
                        "Participa junto a tus compañeros y completa el desafío deportivo.",
                        color =
                            OlimpicTextSecondary
                    )
                }
            }
        }
    }
}
