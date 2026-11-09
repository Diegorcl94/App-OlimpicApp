package cl.olimpictraining.app.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.olimpictraining.app.ui.auth.LoginScreen
import cl.olimpictraining.app.ui.evaluacion.BioimpedanciaScreen
import cl.olimpictraining.app.ui.evaluacion.EvaluacionScreen
import cl.olimpictraining.app.ui.evaluacion.FuerzaAgarreScreen
import cl.olimpictraining.app.ui.evaluacion.MedidasScreen
import cl.olimpictraining.app.ui.eventos.EventosScreen
import cl.olimpictraining.app.ui.home.HomeScreen
import cl.olimpictraining.app.ui.perfil.PerfilScreen
import cl.olimpictraining.app.ui.registro.PlanSelectionScreen
import cl.olimpictraining.app.ui.registro.RegistroScreen
import cl.olimpictraining.app.ui.registro.RoutineSetupScreen
import cl.olimpictraining.app.ui.registro.SedeSelectionScreen
import cl.olimpictraining.app.ui.registro.TrainingProfileData
import cl.olimpictraining.app.ui.registro.TrainingProfileScreen
import cl.olimpictraining.app.ui.registro.WelcomeScreen
import cl.olimpictraining.app.ui.reservas.ReservasScreen
import cl.olimpictraining.app.ui.rutina.RutinaScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var demoUserName by rememberSaveable {
        mutableStateOf("Diego")
    }

    var demoUserEmail by rememberSaveable {
        mutableStateOf("alumno@example.cl")
    }

    var selectedAvatarId by rememberSaveable {
        mutableStateOf("man_fitness")
    }

    var trainingProfile by remember {
        mutableStateOf<TrainingProfileData?>(null)
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            LoginScreen(
                onLoginSuccess = {

                    navController.navigate("home") {

                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onRegisterClick = {
                    navController.navigate("registro_sede")
                }
            )
        }

        composable("registro_sede") {

            SedeSelectionScreen(
                onBack = {
                    navController.popBackStack()
                },

                onCajaLosAndesClick = {
                    navController.navigate("registro_datos")
                }
            )
        }

        composable("registro_datos") {

            RegistroScreen(
                onBack = {
                    navController.popBackStack()
                },

                onCancelRegistration = {

                    navController.navigate("login") {

                        popUpTo("registro_sede") {
                            inclusive = true
                        }
                    }
                },

                onRegistrationCompleted = { nombre, email ->

                    demoUserName = nombre.trim().substringBefore(" ")

                    demoUserEmail = email

                    navController.navigate("registro_plan")
                }
            )
        }

        composable("registro_plan") {

            PlanSelectionScreen(
                onBack = {
                    navController.popBackStack()
                },

                onPlanSelected = {

                    navController.navigate("registro_bienvenida")
                }
            )
        }

        composable("registro_bienvenida") {

            WelcomeScreen(
                userName = demoUserName,

                onContinue = {

                    navController.navigate("registro_entrenamiento")
                }
            )
        }

        composable("registro_entrenamiento") {

            TrainingProfileScreen(
                onBack = {
                    navController.popBackStack()
                },

                onContinue = { profileData ->

                    trainingProfile = profileData

                    navController.navigate("registro_rutina")
                }
            )
        }

        composable("registro_rutina") {

            RoutineSetupScreen(
                onHasRoutine = {

                    navController.navigate("home") {

                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onRequestRoutine = {

                    navController.navigate("home") {

                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("home") {

            HomeScreen(
                userName = demoUserName,
                selectedAvatarId = selectedAvatarId,

                onReservasClick = {
                    navController.navigate("reservas")
                },

                onRutinaClick = {
                    navController.navigate("rutina")
                },

                onEvaluacionClick = {
                    navController.navigate("evaluacion")
                },

                onEventosClick = {
                    navController.navigate("eventos")
                },

                onPerfilClick = {
                    navController.navigate("perfil")
                }
            )
        }

        composable("reservas") {

            ReservasScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("rutina") {

            RutinaScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("evaluacion") {

            EvaluacionScreen(
                onBack = {
                    navController.popBackStack()
                },

                onBioimpedanciaClick = {
                    navController.navigate("bioimpedancia")
                },

                onFuerzaClick = {
                    navController.navigate("fuerza_agarre")
                },

                onMedidasClick = {
                    navController.navigate("medidas")
                }
            )
        }

        composable("bioimpedancia") {

            BioimpedanciaScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("fuerza_agarre") {

            FuerzaAgarreScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("medidas") {

            MedidasScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("eventos") {

            EventosScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("perfil") {

            PerfilScreen(
                userName = demoUserName,
                userEmail = demoUserEmail,
                selectedAvatarId = selectedAvatarId,

                onAvatarSelected = {
                    selectedAvatarId = it
                },

                onBack = {
                    navController.popBackStack()
                },

                onLogout = {

                    navController.navigate("login") {

                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
