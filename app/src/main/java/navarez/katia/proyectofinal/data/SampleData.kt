package navarez.katia.proyectofinal.data

import navarez.katia.proyectofinal.model.EstadoLibro
import navarez.katia.proyectofinal.model.Libro

object SampleData {
    val libros = listOf(
        Libro(
            1, "El Alquimista", "Paulo Coelho", "Ficción", "Novela",
            192, "Un joven pastor andaluz emprende un viaje en busca de un tesoro.",
            EstadoLibro.EN_CURSO, paginaActual = 125
        ),
        Libro(2, "Cien años de soledad", "Gabriel García Márquez", "Ficción", "Realismo mágico",
            471, "La historia de la familia Buendía a lo largo de varias generaciones.",
            EstadoLibro.TERMINADO, rating = 5f, resena = "Una obra maestra.",
            fechaInicio = "2023-10-01", fechaFin = "2023-10-15"),
        Libro(3, "Sapiens", "Yuval Noah Harari", "No ficción", "Historia",
            443, "Una breve historia de la humanidad.", EstadoLibro.POR_LEER),
        Libro(4, "Clean Code", "Robert C. Martin", "Académico", "Ingeniería de software",
            352, "Buenas prácticas para escribir código mantenible.", EstadoLibro.POR_LEER)
    )
}