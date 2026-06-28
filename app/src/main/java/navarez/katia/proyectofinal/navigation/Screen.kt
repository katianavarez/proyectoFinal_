package navarez.katia.proyectofinal.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Registro : Screen("registro")
    object ListaLibros : Screen("lista_libros")
    object AgregarLibro : Screen("agregar_libro")
    object Perfil : Screen("perfil")
    object Estadisticas : Screen("estadisticas")
    object Detalle : Screen("detalle/{libroId}") {
        fun createRoute(libroId: Int) = "detalle/$libroId"
    }
}