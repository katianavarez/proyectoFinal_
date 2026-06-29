package navarez.katia.proyectofinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import navarez.katia.proyectofinal.data.SampleData
import navarez.katia.proyectofinal.model.EstadoLibro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLibroScreen(navController: NavController, libroId: Int) {

    val libro = SampleData.libros.find { it.id == libroId }
        ?: SampleData.libros.first()

    var paginaActual by remember { mutableStateOf(libro.paginaActual.toString()) }
    var calificacion by remember { mutableStateOf(libro.rating.toInt()) }
    var resena by remember { mutableStateOf(libro.resena) }
    var fechaInicio by remember { mutableStateOf(libro.fechaInicio ?: "") }
    var fechaFin by remember { mutableStateOf(libro.fechaFin ?: "") }
    var mostrarPickerInicio by remember { mutableStateOf(false) }
    var mostrarPickerFin by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diario de Lectura") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("lista_libros") },
                    icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null) },
                    label = { Text("Mis libros") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("estadisticas") },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                    label = { Text("Estadísticas") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("perfil") },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = libro.portada),
                    contentDescription = "Portada de ${libro.titulo}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(Modifier.height(12.dp))

            val (textoBadge, colorBadge) = when (libro.estado) {
                EstadoLibro.EN_CURSO -> "En curso" to Color(0xFFB3E5FC)
                EstadoLibro.TERMINADO -> "Terminado" to Color(0xFFC8E6C9)
                EstadoLibro.POR_LEER -> "Por leer" to Color(0xFFE0E0E0)
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(colorBadge)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(textoBadge, style = MaterialTheme.typography.labelMedium)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = libro.titulo,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = libro.autor,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        libro.categoria,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "${libro.numPaginas} páginas",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            if (libro.estado == EstadoLibro.EN_CURSO) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        val porcentaje = paginaActual.toIntOrNull()?.toFloat()?.div(libro.numPaginas) ?: 0f
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Progreso de lectura",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "${(porcentaje * 100).toInt()}%",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = porcentaje,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(12.dp))
                        Text("Página actual", style = MaterialTheme.typography.labelMedium)
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = paginaActual,
                                onValueChange = { paginaActual = it },
                                singleLine = true,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("de ${libro.numPaginas}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Marcar como completado")
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            if (libro.estado == EstadoLibro.POR_LEER) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Estado: Por leer",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        OutlinedButton(onClick = { }) { Text("Iniciar libro") }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

