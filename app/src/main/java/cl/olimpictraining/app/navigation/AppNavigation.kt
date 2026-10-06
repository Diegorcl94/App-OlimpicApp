package cl.olimpictraining.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.olimpictraining.app.ui.auth.LoginScreen
import cl.olimpictraining.app.ui.evaluacion.EvaluacionScreen
import cl.olimpictraining.app.ui.eventos.EventosScreen
import cl.olimpictraining.app.ui.home.HomeScreen
import cl.olimpictraining.app.ui.perfil.PerfilScreen
import cl.olimpictraining.app.ui.reservas.ReservasScreen
import cl.olimpictraining.app.ui.rutina.RutinaScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

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
                }
            )
        }

        composable("home") {

            HomeScreen(
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
