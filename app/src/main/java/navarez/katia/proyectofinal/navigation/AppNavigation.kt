package navarez.katia.proyectofinal.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import navarez.katia.proyectofinal.ui.screens.AgregarLibroScreen
import navarez.katia.proyectofinal.ui.screens.DetalleLibroScreen
import navarez.katia.proyectofinal.ui.screens.ListaLibrosScreen
import navarez.katia.proyectofinal.ui.screens.LoginScreen
import navarez.katia.proyectofinal.ui.screens.RegistroScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Login> {
                LoginScreen(
                    onNavigateToRegistro = { navController.navigate(Registro) },
                    onNavigateToHome = {
                        navController.navigate(ListaLibros) {
                            popUpTo(Login) { inclusive = true }
                        }
                    }
                )
            }

            composable<Registro> {
                RegistroScreen(
                    onNavigateToLogin = { navController.popBackStack() }
                )
            }

            composable<ListaLibros> {
                ListaLibrosScreen(
                    onNavigateToDetalle = { libroId ->
                        navController.navigate(Detalle(libroId))
                    },
                    onNavigateToAgregar = { navController.navigate(AgregarLibro) }
                )
            }
        }
    }
}


/**
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route,
        modifier = Modifier.statusBarsPadding()) {

        composable(Screen.Perfil.route) {
            PerfilScreen(navController)
        }
        composable(Screen.AgregarLibro.route) {
            AgregarLibroScreen(navController)
        }

        composable(Screen.Detalle.route) { backStackEntry ->
            val libroId = backStackEntry.arguments?.getInt("libroId") ?: return@composable
            DetalleLibroScreen(navController, libroId)
        }
    }
} **/