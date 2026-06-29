package navarez.katia.proyectofinal.navigation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import navarez.katia.proyectofinal.ui.screens.AgregarLibroScreen
import navarez.katia.proyectofinal.ui.screens.ListaLibrosScreen
import navarez.katia.proyectofinal.ui.screens.LoginScreen
import navarez.katia.proyectofinal.ui.screens.PerfilScreen
import navarez.katia.proyectofinal.ui.screens.RegistroScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route,
        modifier = Modifier.statusBarsPadding()) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Registro.route) {
            RegistroScreen(navController)
        }
        composable(Screen.ListaLibros.route) {
            ListaLibrosScreen(navController)
        }

        composable(Screen.Perfil.route) {
            PerfilScreen(navController)
        }
        composable(Screen.AgregarLibro.route) {
            AgregarLibroScreen(navController)
        }
    }
}