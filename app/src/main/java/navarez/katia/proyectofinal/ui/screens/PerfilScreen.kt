package navarez.katia.proyectofinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(onCerrarSesion: () -> Unit) {

    var nombre by remember { mutableStateOf("Juan Francisco López") }
    val correo = "juan.francisco@email.com"
    var fechaNacimiento by remember { mutableStateOf("08/12/1995") }
    var genero by remember { mutableStateOf("Masculino") }
    var profesion by remember { mutableStateOf("Editor literario") }

    var mostrarDatePicker by remember { mutableStateOf(false) }

    var profileImageUri by remember { mutableStateOf<android.net.Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: android.net.Uri? ->
        profileImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            // Imagen de perfil
            if (profileImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data(profileImageUri)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } else {
                // muestra un círculo gris si no hay imagen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar foto de perfil",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Lector entusiasta desde 2018",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Formularios

            // Nombre Completo
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Nombre completo", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(6.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text("nombre completo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Correo electrónico", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(6.dp))
                // correo electrónico
                OutlinedTextField(
                    value = correo,
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    enabled = false,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text("Fecha de nacimiento", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(6.dp))
                // fecha de nacimiento
                OutlinedTextField(
                    value = fechaNacimiento,
                    onValueChange = {},
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { mostrarDatePicker = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Género", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(6.dp))
                // Género
                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Profesión", style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(6.dp))
                // Profesión
                OutlinedTextField(
                    value = profesion,
                    onValueChange = { profesion = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            // botones
            Button(
                onClick = {
                    //┻━┻ ︵\(°□°)/︵ ┻━┻
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Guardar cambios", modifier = Modifier.padding(vertical = 4.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    // ┻━┻ ︵\(°□°)/︵ ┻━┻
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Cambiar contraseña", modifier = Modifier.padding(vertical = 4.dp))
            }

            Spacer(modifier = Modifier.height(40.dp))

            // xerrar sesion
            Row(
                modifier = Modifier
                    .clickable { onCerrarSesion() }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Cerrar sesión",
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cerrar sesión",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        if (mostrarDatePicker) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { mostrarDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            fechaNacimiento = formato.format(java.util.Date(millis))
                        }
                        mostrarDatePicker = false
                    }) { Text("Aceptar") }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDatePicker = false }) { Text("Cancelar") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    MaterialTheme {
        PerfilScreen(onCerrarSesion = {})
    }
}
