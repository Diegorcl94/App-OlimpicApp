package cl.olimpictraining.app.ui.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cl.olimpictraining.app.ui.theme.*

private val enfermedadesCronicas = listOf(
    "Ninguna",
    "Hipertensión arterial",
    "Diabetes",
    "Asma",
    "Cardiopatía",
    "Otra"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    onBack: () -> Unit,
    onCancelRegistration: () -> Unit,
    onRegistrationCompleted: (String, String) -> Unit
) {

    var nombre by rememberSaveable {
        mutableStateOf("")
    }

    var rut by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var genero by rememberSaveable {
        mutableStateOf("")
    }

    var enfermedadCronica by rememberSaveable {
        mutableStateOf("Ninguna")
    }

    var otraEnfermedad by rememberSaveable {
        mutableStateOf("")
    }

    var medicamento by rememberSaveable {
        mutableStateOf("")
    }

    var termsAccepted by rememberSaveable {
        mutableStateOf(false)
    }

    var googleVerified by rememberSaveable {
        mutableStateOf(false)
    }

    var showTerms by remember {
        mutableStateOf(false)
    }

    var showTermsRequiredDialog by remember {
        mutableStateOf(false)
    }

    var showGoogleDialog by remember {
        mutableStateOf(false)
    }

    var showSuccessDialog by remember {
        mutableStateOf(false)
    }

    var formError by remember {
        mutableStateOf<String?>(null)
    }

    fun resetRegistration() {
        nombre = ""
        rut = ""
        email = ""
        genero = ""

        enfermedadCronica = "Ninguna"
        otraEnfermedad = ""
        medicamento = ""

        termsAccepted = false
        googleVerified = false

        formError = null
    }

    Scaffold(
        containerColor = OlimpicBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Registro",
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 720.dp)
                    .navigationBarsPadding()
                    .imePadding(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 18.dp,
                    bottom = 32.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    CajaLosAndesHeader()
                }

                item {
                    GoogleRegisterButton(
                        verified = googleVerified,
                        onClick = {
                            showGoogleDialog = true
                        }
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "  o completa tus datos  ",
                            color = OlimpicTextSecondary,
                            style = MaterialTheme.typography.bodySmall
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text(
                        text = "Datos personales",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )
                }

                item {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            formError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Nombre completo") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                }

                item {
                    OutlinedTextField(
                        value = rut,
                        onValueChange = { newValue ->
                            val clean = newValue
                                .uppercase()
                                .filter { it.isDigit() || it == 'K' }
                                .take(9)
                            rut = clean
                            formError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("RUT") },
                        placeholder = { Text("12.345.678-9") },
                        singleLine = true,
                        visualTransformation = RutVisualTransformation(),
                        isError = rut.length >= 8 && !isValidRut(rut),
                        supportingText = {
                            if (rut.length >= 8 && !isValidRut(rut)) {
                                Text(text = "Ingresa un RUT válido.")
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                item {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            if (!googleVerified) {
                                email = it
                            }
                            formError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Correo corporativo") },
                        placeholder = { Text("nombre@cajalosandes.cl") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        readOnly = googleVerified,
                        singleLine = true,
                        isError = email.isNotBlank() && !isCajaLosAndesEmail(email),
                        supportingText = {
                            when {
                                googleVerified -> {
                                    Text(
                                        text = "Correo corporativo validado mediante Google.",
                                        color = OlimpicBlue
                                    )
                                }
                                email.isNotBlank() && !isCajaLosAndesEmail(email) -> {
                                    Text(
                                        text = "Esta sede solo admite correos @cajalosandes.cl."
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                item {
                    Text(
                        text = "Género",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FilterChip(
                            selected = genero == "Hombre",
                            onClick = { genero = "Hombre" },
                            label = { Text("Hombre") },
                            modifier = Modifier.weight(1f)
                        )

                        FilterChip(
                            selected = genero == "Mujer",
                            onClick = { genero = "Mujer" },
                            label = { Text("Mujer") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text(
                        text = "Información de salud",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = OlimpicTextPrimary
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Esta información será utilizada para apoyar una atención segura durante tus actividades.",
                        color = OlimpicTextSecondary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                item {
                    ChronicDiseaseSelector(
                        selected = enfermedadCronica,
                        onSelected = {
                            enfermedadCronica = it
                            if (it != "Otra") {
                                otraEnfermedad = ""
                            }
                        }
                    )
                }

                if (enfermedadCronica == "Otra") {
                    item {
                        OutlinedTextField(
                            value = otraEnfermedad,
                            onValueChange = { otraEnfermedad = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Especifica la enfermedad") },
                            leadingIcon = {
                                Icon(Icons.Default.HealthAndSafety, contentDescription = null)
                            }
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = medicamento,
                        onValueChange = { medicamento = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Medicamentos (opcional)") },
                        placeholder = { Text("Ej: Losartán 50 mg") },
                        leadingIcon = {
                            Icon(Icons.Default.Medication, contentDescription = null)
                        },
                        minLines = 1,
                        maxLines = 3
                    )
                }

                item {
                    TermsCard(
                        accepted = termsAccepted,
                        onAcceptedChange = { termsAccepted = it },
                        onViewTerms = { showTerms = true }
                    )
                }

                if (formError != null) {
                    item {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFFFEEEE)
                        ) {
                            Text(
                                text = formError.orEmpty(),
                                modifier = Modifier.padding(14.dp),
                                color = Color(0xFFB3261E),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                item {
                    Button(
                        onClick = {
                            formError = validateForm(
                                nombre = nombre,
                                rut = rut,
                                email = email,
                                genero = genero,
                                enfermedad = enfermedadCronica,
                                otraEnfermedad = otraEnfermedad
                            )

                            if (formError == null) {
                                if (!termsAccepted) {
                                    showTermsRequiredDialog = true
                                } else {
                                    onRegistrationCompleted(
                                        nombre.trim(),
                                        email.trim().lowercase()
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(17.dp)
                    ) {
                        Text(
                            text = "Crear mi cuenta",
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                item {
                    TextButton(
                        onClick = {
                            resetRegistration()
                            onCancelRegistration()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "No deseo continuar con el registro",
                            color = OlimpicTextSecondary
                        )
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // TÉRMINOS Y CONDICIONES
    // ---------------------------------------------------

    if (showTerms) {
        AlertDialog(
            onDismissRequest = { showTerms = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = OlimpicBlue
                )
            },
            title = {
                Text(
                    text = "Términos y condiciones",
                    fontWeight = FontWeight.ExtraBold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .heightIn(max = 430.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Términos y Condiciones de Olimpic Training",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Esta versión corresponde al flujo de demostración de la aplicación."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "El acceso gratuito de esta versión está destinado a colaboradores de Caja Los Andes que utilicen un correo corporativo @cajalosandes.cl."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "La aplicación permitirás gestionar reservas de entrenamiento, solicitudes de evaluación, rutinas, eventos y otras funciones relacionadas con Olimpic Training."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "La información definitiva de privacidad, tratamiento de datos y condiciones de uso será reemplazada por la documentación legal aprobada antes de la publicación de la aplicación."
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showTerms = false }
                ) {
                    Text(text = "Volver al registro")
                }
            }
        )
    }

    // ---------------------------------------------------
    // TÉRMINOS OBLIGATORIOS
    // ---------------------------------------------------

    if (showTermsRequiredDialog) {
        AlertDialog(
            onDismissRequest = { showTermsRequiredDialog = false },
            title = {
                Text(
                    text = "Aceptación requerida",
                    fontWeight = FontWeight.ExtraBold
                )
            },
            text = {
                Text(
                    text = "Para utilizar nuestra aplicación debes aceptar los Términos y Condiciones."
                )
            },
            confirmButton = {
                Button(
                    onClick = { showTermsRequiredDialog = false }
                ) {
                    Text(text = "Volver al registro")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTermsRequiredDialog = false
                        resetRegistration()
                        onCancelRegistration()
                    }
                ) {
                    Text(text = "No deseo continuar")
                }
            }
        )
    }

    // ---------------------------------------------------
    // REGISTRO CON GOOGLE - DEMO
    // ---------------------------------------------------

    if (showGoogleDialog) {
        GoogleRegistrationDemoDialog(
            currentName = nombre,
            currentEmail = email,
            onDismiss = { showGoogleDialog = false },
            onGoogleAccountAccepted = { googleName, googleEmail ->
                nombre = googleName.trim()
                email = googleEmail.trim().lowercase()
                googleVerified = true
                showGoogleDialog = false
            }
        )
    }

    // ---------------------------------------------------
    // REGISTRO EXITOSO
    // ---------------------------------------------------

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = OlimpicBlue
                )
            },
            title = {
                Text(
                    text = "¡Registro completado!",
                    fontWeight = FontWeight.ExtraBold
                )
            },
            text = {
                Text(
                    text = "Tu cuenta de demostración fue creada correctamente para la sede Caja Los Andes."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        onRegistrationCompleted(
                            nombre.trim(),
                            email.trim().lowercase()
                        )
                    }
                ) {
                    Text(text = "Ir a Olimpic Training")
                }
            }
        )
    }
}

@Composable
private fun CajaLosAndesHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = OlimpicBlue
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.18f)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Caja Los Andes",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Registro gratuito para colaboradores",
                    color = Color.White.copy(alpha = 0.82f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun GoogleRegisterButton(
    verified: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(17.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Surface(
            modifier = Modifier.size(30.dp),
            shape = CircleShape,
            color = if (verified) OlimpicBlueLight else Color(0xFFF4F6FA)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = if (verified) "✓" else "G",
                    color = OlimpicBlue,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = if (verified) {
                "Cuenta Google corporativa vinculada"
            } else {
                "Continuar con Google"
            },
            color = OlimpicTextPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ChronicDiseaseSelector(
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Icon(
                imageVector = Icons.Default.HealthAndSafety,
                contentDescription = null,
                tint = OlimpicBlue
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Enfermedad crónica",
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    text = selected,
                    color = OlimpicTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.widthIn(min = 260.dp)
        ) {
            enfermedadesCronicas.forEach { enfermedad ->
                DropdownMenuItem(
                    text = { Text(enfermedad) },
                    onClick = {
                        onSelected(enfermedad)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun TermsCard(
    accepted: Boolean,
    onAcceptedChange: (Boolean) -> Unit,
    onViewTerms: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = OlimpicBlue
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Términos y condiciones",
                    fontWeight = FontWeight.ExtraBold,
                    color = OlimpicTextPrimary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Antes de crear tu cuenta puedes revisar las condiciones de uso de Olimpic Training.",
                color = OlimpicTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = onViewTerms) {
                Text(text = "Ver Términos y Condiciones")
            }

            HorizontalDivider()

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = accepted,
                    onCheckedChange = onAcceptedChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "He leído y acepto los Términos y Condiciones.",
                    color = OlimpicTextPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun GoogleRegistrationDemoDialog(
    currentName: String,
    currentEmail: String,
    onDismiss: () -> Unit,
    onGoogleAccountAccepted: (String, String) -> Unit
) {
    var googleName by remember {
        mutableStateOf(currentName.ifBlank { "Alumno de prueba" })
    }
    var googleEmail by remember {
        mutableStateOf(currentEmail.ifBlank { "alumno@cajalosandes.cl" })
    }
    var googleError by remember {
        mutableStateOf<String?>(null)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = OlimpicBlue
            )
        },
        title = {
            Text(
                text = "Continuar con Google",
                fontWeight = FontWeight.ExtraBold
            )
        },
        text = {
            Column {
                Text(
                    text = "Modo demostración: simula la cuenta que Google devolverá cuando integremos Firebase Authentication."
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = googleName,
                    onValueChange = { googleName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nombre de Google") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = googleEmail,
                    onValueChange = {
                        googleEmail = it
                        googleError = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo de Google") },
                    singleLine = true,
                    isError = googleError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                if (googleError != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = googleError.orEmpty(),
                        color = Color(0xFFB3261E),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Solo se permitirá una cuenta @cajalosandes.cl.",
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    when {
                        googleName.isBlank() -> {
                            googleError = "Google debe entregar un nombre válido."
                        }
                        !isCajaLosAndesEmail(googleEmail) -> {
                            googleError = "Esta cuenta no pertenece a Caja Los Andes."
                        }
                        else -> {
                            onGoogleAccountAccepted(googleName, googleEmail)
                        }
                    }
                }
            ) {
                Text(text = "Usar esta cuenta")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}

private fun validateForm(
    nombre: String,
    rut: String,
    email: String,
    genero: String,
    enfermedad: String,
    otraEnfermedad: String
): String? {
    return when {
        nombre.trim().length < 3 -> "Ingresa tu nombre completo."
        !isValidRut(rut) -> "Ingresa un RUT válido."
        !isCajaLosAndesEmail(email) -> "Para registrarte en Caja Los Andes debes utilizar un correo @cajalosandes.cl."
        genero.isBlank() -> "Selecciona tu género."
        enfermedad == "Otra" && otraEnfermedad.isBlank() -> "Indica cuál es tu enfermedad crónica."
        else -> null
    }
}

private fun isCajaLosAndesEmail(email: String): Boolean {
    val clean = email.trim().lowercase()
    val parts = clean.split("@")
    return (parts.size == 2 && parts[0].isNotBlank() && parts[1] == "cajalosandes.cl")
}

private class RutVisualTransformation : VisualTransformation {

    override fun filter(
        text: AnnotatedString
    ): TransformedText {

        val raw = text.text
            .uppercase()
            .take(9)

        if (raw.length < 8) {
            return TransformedText(
                text = AnnotatedString(raw),
                offsetMapping = OffsetMapping.Identity
            )
        }

        val formatted = formatRutForDisplay(raw)

        val originalToTransformed = IntArray(raw.length + 1)
        originalToTransformed[0] = 0

        var sourceIndex = 0

        formatted.forEachIndexed { index, char ->
            if (char != '.' && char != '-') {
                sourceIndex++
                if (sourceIndex < originalToTransformed.size) {
                    originalToTransformed[sourceIndex] = index + 1
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return originalToTransformed[offset.coerceIn(0, raw.length)]
            }

            override fun transformedToOriginal(offset: Int): Int {
                val safeOffset = offset.coerceIn(0, formatted.length)
                var originalOffset = 0

                for (index in 0 until safeOffset) {
                    if (formatted[index] != '.' && formatted[index] != '-') {
                        originalOffset++
                    }
                }

                return originalOffset.coerceIn(0, raw.length)
            }
        }

        return TransformedText(
            text = AnnotatedString(formatted),
            offsetMapping = offsetMapping
        )
    }
}

private fun formatRutForDisplay(
    rawRut: String
): String {

    val clean = rawRut
        .replace(".", "")
        .replace("-", "")
        .uppercase()

    if (clean.length < 2) {
        return clean
    }

    val body = clean.dropLast(1)
    val verifier = clean.last()

    val formattedBody = body
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()

    return "$formattedBody-$verifier"
}

private fun isValidRut(rut: String): Boolean {
    val clean = rut.replace(".", "").replace("-", "").uppercase()
    if (clean.length < 2) return false

    val body = clean.dropLast(1)
    val verifier = clean.last()

    if (!body.all { it.isDigit() }) return false

    var sum = 0
    var multiplier = 2

    body.reversed().forEach { char ->
        sum += char.digitToInt() * multiplier
        multiplier = if (multiplier == 7) 2 else multiplier + 1
    }

    val result = 11 - (sum % 11)

    val expected = when (result) {
        11 -> '0'
        10 -> 'K'
        else -> result.toString().first()
    }

    return verifier == expected
}
