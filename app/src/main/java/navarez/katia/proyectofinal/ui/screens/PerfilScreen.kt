package navarez.katia.proyectofinal.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import navarez.katia.proyectofinal.navigation.Screen
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("Juan Francisco López") }
    val correo = "juan.francisco@email.com"
    var fechaNacimiento by remember { mutableStateOf("08/12/1995") }
    var genero by remember { mutableStateOf("Masculino") }
    var profesion by remember { mutableStateOf("Editor literario") }

    var mostrarDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
            }
        }

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
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // correo electrónico
        OutlinedTextField(
            value = correo,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // fecha de nacimiento
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = {},
            readOnly = true,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { mostrarDatePicker = true }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Género
        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                .clickable {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.ListaLibros.route) { inclusive = true }
                    }
                }
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

}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    MaterialTheme {
        PerfilScreen(navController = rememberNavController())
    }
}
