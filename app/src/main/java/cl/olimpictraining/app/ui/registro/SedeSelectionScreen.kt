package cl.olimpictraining.app.ui.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

private data class SedeOption(
    val name: String,
    val subtitle: String,
    val available: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SedeSelectionScreen(
    onBack: () -> Unit,
    onCajaLosAndesClick: () -> Unit
) {

    val sedes = listOf(
        SedeOption(
            name = "Caja Los Andes",
            subtitle = "Gimnasio corporativo",
            available = true
        ),
        SedeOption(
            name = "Gimnasio Olimpic Training",
            subtitle = "Próximamente",
            available = false
        )
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedSede by remember { mutableStateOf<SedeOption?>(null) }

    Scaffold(
        containerColor = OlimpicBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Crear cuenta",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                .navigationBarsPadding()
                .padding(20.dp)
                .widthIn(max = 600.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Selecciona tu empresa o sede",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = OlimpicTextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Elige la empresa o gimnasio al que perteneces para continuar con tu registro.",
                style = MaterialTheme.typography.bodyMedium,
                color = OlimpicTextSecondary
            )

            Spacer(modifier = Modifier.height(28.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedSede?.let {
                        if (it.available) it.name else "${it.name} (Próximamente)"
                    } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Empresa o sede") },
                    placeholder = { Text("Seleccionar una opción...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Business,
                            contentDescription = null,
                            tint = OlimpicBlue
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    sedes.forEach { sede ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(
                                        text = sede.name,
                                        fontWeight = FontWeight.Bold,
                                        color = if (sede.available) OlimpicTextPrimary else OlimpicTextSecondary
                                    )
                                    Text(
                                        text = sede.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = if (sede.available) OlimpicBlue else OlimpicTextSecondary
                                    )
                                }
                            },
                            onClick = {
                                if (sede.available) {
                                    selectedSede = sede
                                    expanded = false
                                }
                            },
                            enabled = sede.available
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (selectedSede?.available == true) {
                        onCajaLosAndesClick()
                    }
                },
                enabled = selectedSede?.available == true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(17.dp)
            ) {
                Text(
                    text = "Continuar",
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}
