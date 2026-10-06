package cl.olimpictraining.app.ui.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {

    Scaffold(
        containerColor =
            OlimpicBackground,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Mi perfil",
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
                .padding(20.dp),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Surface(
                modifier =
                    Modifier.size(96.dp),
                shape =
                    CircleShape,
                color =
                    OlimpicBlueLight
            ) {

                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint =
                        OlimpicBlue,
                    modifier =
                        Modifier.padding(22.dp)
                )
            }

            Spacer(
                Modifier.height(16.dp)
            )

            Text(
                "Alumno de prueba",
                style =
                    MaterialTheme.typography.titleLarge,
                fontWeight =
                    FontWeight.Bold
            )

            Text(
                "alumno@example.cl",
                color =
                    OlimpicTextSecondary
            )

            Spacer(
                Modifier.height(26.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(22.dp)
            ) {

                Column(
                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    ProfileItem(
                        "Empresa",
                        "Caja Los Andes"
                    )

                    ProfileItem(
                        "Plan",
                        "Colaborador"
                    )

                    ProfileItem(
                        "Estado",
                        "Activo"
                    )
                }
            }

            Spacer(
                Modifier.height(24.dp)
            )

            OutlinedButton(
                onClick = onLogout,
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(14.dp)
            ) {

                Text(
                    "Cerrar sesión"
                )
            }
        }
    }
}

@Composable
private fun ProfileItem(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp
            ),
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            title,
            color =
                OlimpicTextSecondary
        )

        Text(
            value,
            fontWeight =
                FontWeight.Bold
        )
    }
}
