package navarez.katia.proyectofinal.model

data class Libro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val generoOTema: String,
    val numPaginas: Int,
    val sinopsis: String,
    val estado: EstadoLibro,
    val paginaActual: Int = 0,
    val rating: Float = 0f,
    val resena: String = "",
    val fechaInicio: String? = null,
    val fechaFin: String? = null
)