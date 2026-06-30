package navarez.katia.proyectofinal.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Registro

@Serializable
object ListaLibros

@Serializable
object AgregarLibro

@Serializable
object Perfil

@Serializable
object Estadisticas

@Serializable
data class Detalle(val libroId: Int)