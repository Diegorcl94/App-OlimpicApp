package cl.olimpictraining.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.olimpictraining.app.navigation.AppNavigation
import cl.olimpictraining.app.ui.theme.OlimpicTrainingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            OlimpicTrainingTheme {

                AppNavigation()

            }
        }
    }
}