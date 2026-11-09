package cl.olimpictraining.app.ui.home

import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.olimpictraining.app.R
import cl.olimpictraining.app.ui.components.UserAvatar
import cl.olimpictraining.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    userName: String,
    selectedAvatarId: String,
    onReservasClick: () -> Unit,
    onRutinaClick: () -> Unit,
    onEvaluacionClick: () -> Unit,
    onEventosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(120)
        visible = true
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth >= 600.dp

        Row(modifier = Modifier.fillMaxSize()) {
            if (isTablet) {
                NavigationRail(
                    containerColor = Color.White,
                    header = {
                        Spacer(modifier = Modifier.height(16.dp))
                        Icon(
                            Icons.Default.FitnessCenter,
                            contentDescription = "Olimpic Training",
                            tint = OlimpicBlue
                        )
                    }
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    NavigationRailItem(
                        selected = true,
                        onClick = {},
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") }
                    )
                    NavigationRailItem(
                        selected = false,
                        onClick = onReservasClick,
                        icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Reservas") },
                        label = { Text("Reservas") }
                    )
                    NavigationRailItem(
                        selected = false,
                        onClick = onRutinaClick,
                        icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Rutina") },
                        label = { Text("Rutina") }
                    )
                    NavigationRailItem(
                        selected = false,
                        onClick = onPerfilClick,
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Scaffold(
                modifier = Modifier.weight(1f),
                containerColor = OlimpicBackground,
                bottomBar = {
                    if (!isTablet) {
                        PremiumBottomBar(
                            onReservasClick = onReservasClick,
                            onRutinaClick = onRutinaClick,
                            onPerfilClick = onPerfilClick
                        )
                    }
                }
            ) { padding ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    // Fondo superior premium
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 32.dp,
                                    bottomEnd = 32.dp
                                )
                            )
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        OlimpicBlueDeep,
                                        OlimpicBlue,
                                        OlimpicCyan
                                    )
                                )
                            )
                    ) {
                        Box(
                            modifier = Modifier.matchParentSize(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            HeaderBackgroundLogo()
                        }
                    }

                    // Círculos decorativos
                    Box(
                        modifier = Modifier
                            .offset(x = 250.dp, y = 22.dp)
                            .size(130.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.12f))
                    )

                    Box(
                        modifier = Modifier
                            .offset(x = (-45).dp, y = 70.dp)
                            .size(115.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.10f))
                    )

                    androidx.compose.animation.AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + slideInVertically(initialOffsetY = { 60 })
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .widthIn(max = 900.dp),
                                contentPadding = PaddingValues(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 24.dp,
                                    bottom = 28.dp
                                ),
                                verticalArrangement = Arrangement.spacedBy(18.dp)
                            ) {

                                item {
                                    HeaderSection(
                                        userName = userName,
                                        selectedAvatarId = selectedAvatarId,
                                        onPerfilClick = onPerfilClick
                                    )
                                }

                                item {
                                    HeroCard()
                                }

                                item {
                                    Surface(
                                        shape = RoundedCornerShape(14.dp),
                                        color = OlimpicBlue.copy(alpha = 0.92f),
                                        shadowElevation = 4.dp
                                    ) {
                                        Text(
                                            text = "Accesos rápidos",
                                            modifier = Modifier.padding(
                                                horizontal = 14.dp,
                                                vertical = 8.dp
                                            ),
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                    }
                                }

                                item {
                                    if (isTablet) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                                        ) {
                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Reservar hora",
                                                subtitle = "Agenda tu entrenamiento",
                                                icon = Icons.Default.CalendarMonth,
                                                iconContainerColor = Color(0xFFE9F1FF),
                                                onClick = onReservasClick
                                            )

                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Mi rutina",
                                                subtitle = "Consulta tu plan",
                                                icon = Icons.Default.FitnessCenter,
                                                iconContainerColor = Color(0xFFEFF2FF),
                                                onClick = onRutinaClick
                                            )

                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Evaluación",
                                                subtitle = "Solicita una evaluación",
                                                icon = Icons.Default.Assessment,
                                                iconContainerColor = Color(0xFFEAF7FF),
                                                onClick = onEvaluacionClick
                                            )

                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Eventos",
                                                subtitle = "Revisa actividades",
                                                icon = Icons.Default.Event,
                                                iconContainerColor = Color(0xFFF3EEFF),
                                                onClick = onEventosClick
                                            )
                                        }
                                    } else {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                                        ) {
                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Reservar hora",
                                                subtitle = "Agenda tu entrenamiento",
                                                icon = Icons.Default.CalendarMonth,
                                                iconContainerColor = Color(0xFFE9F1FF),
                                                onClick = onReservasClick
                                            )

                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Mi rutina",
                                                subtitle = "Consulta tu plan",
                                                icon = Icons.Default.FitnessCenter,
                                                iconContainerColor = Color(0xFFEFF2FF),
                                                onClick = onRutinaClick
                                            )
                                        }
                                    }
                                }

                                if (!isTablet) {
                                    item {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                                        ) {
                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Evaluación",
                                                subtitle = "Solicita una evaluación",
                                                icon = Icons.Default.Assessment,
                                                iconContainerColor = Color(0xFFEAF7FF),
                                                onClick = onEvaluacionClick
                                            )

                                            QuickActionCard(
                                                modifier = Modifier.weight(1f),
                                                title = "Eventos",
                                                subtitle = "Revisa actividades",
                                                icon = Icons.Default.Event,
                                                iconContainerColor = Color(0xFFF3EEFF),
                                                onClick = onEventosClick
                                            )
                                        }
                                    }
                                }

                                item {
                                    SectionTitle("Próximos eventos")
                                }

                                item {
                                    UpcomingEventCard(
                                        onClick = onEventosClick
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderBackgroundLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo_olimpic),
        contentDescription = null,
        modifier = Modifier
            .size(180.dp)
            .blur(1.dp),
        contentScale = ContentScale.Fit,
        alpha = 0.18f,
        colorFilter = ColorFilter.tint(
            color = Color.White,
            blendMode = BlendMode.SrcIn
        )
    )
}

@Composable
private fun HeaderSection(
    userName: String,
    selectedAvatarId: String,
    onPerfilClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Olimpic Training",
                color = Color.White.copy(alpha = 0.92f),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hola, $userName 👋",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Tu espacio de bienestar y entrenamiento.",
                color = Color.White.copy(alpha = 0.82f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.18f)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }

            UserAvatar(
                avatarId = selectedAvatarId,
                size = 52.dp,
                modifier = Modifier.clickable {
                    onPerfilClick()
                }
            )
        }
    }
}

@Composable
private fun HeroCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Surface(
                color = OlimpicBlueLight,
                shape = RoundedCornerShape(100.dp)
            ) {

                Text(
                    text = "Colaborador Caja Los Andes",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    ),
                    color = OlimpicBlue,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = "Tu bienestar, en un solo lugar",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = OlimpicTextPrimary
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Reserva tus entrenamientos, solicita evaluaciones y mantente al día con las actividades de Olimpic Training.",
                style = MaterialTheme.typography.bodyMedium,
                color = OlimpicTextSecondary
            )
        }
    }
}

@Composable
private fun SectionTitle(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.ExtraBold,
        color = OlimpicTextPrimary
    )
}

@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconContainerColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .heightIn(min = 170.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Surface(
                modifier = Modifier.size(46.dp),
                shape = CircleShape,
                color = iconContainerColor
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = OlimpicBlue,
                    modifier = Modifier.padding(11.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                color = OlimpicTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = subtitle,
                color = OlimpicTextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 19.sp
            )
        }
    }
}

@Composable
private fun UpcomingEventCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = OlimpicBlueLight,
                modifier = Modifier.size(52.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = null,
                    tint = OlimpicBlue,
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "No hay eventos programados",
                    fontWeight = FontWeight.ExtraBold,
                    color = OlimpicTextPrimary,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Te avisaremos cuando tengamos nuevas actividades para ti.",
                    color = OlimpicTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
private fun PremiumBottomBar(
    onReservasClick: () -> Unit,
    onRutinaClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 18.dp
    ) {
        Column {
            HorizontalDivider(color = OlimpicBorder.copy(alpha = 0.55f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BottomBarItem(
                    selected = true,
                    label = "Inicio",
                    icon = Icons.Default.Home,
                    onClick = {}
                )

                BottomBarItem(
                    selected = false,
                    label = "Reservas",
                    icon = Icons.Default.CalendarMonth,
                    onClick = onReservasClick
                )

                BottomBarItem(
                    selected = false,
                    label = "Rutina",
                    icon = Icons.Default.FitnessCenter,
                    onClick = onRutinaClick
                )

                BottomBarItem(
                    selected = false,
                    label = "Perfil",
                    icon = Icons.Default.Person,
                    onClick = onPerfilClick
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    selected: Boolean,
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .background(
                if (selected) OlimpicBlueLight else Color.Transparent
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) OlimpicBlue else OlimpicTextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = if (selected) OlimpicBlue else OlimpicTextSecondary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
        )
    }
}
