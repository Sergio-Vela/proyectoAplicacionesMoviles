
package com.example.clasedesmov2026.ui.profile

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.clasedesmov2026.R
import com.example.clasedesmov2026.ui.profile.components.FotoPerfil
import com.example.clasedesmov2026.ui.profile.components.PersonalInfoCard
import com.example.clasedesmov2026.ui.profile.util.cargarBitmapOrientado

@Composable
fun ProfileScreen(
    id: Int,
    nombre: String,
    apellido: String,
    usuario: String,
    token: String,
    profileViewModel: ProfileViewModel,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val state by profileViewModel.state.collectAsState()

    var mostrarOpcionesFoto by remember {
        mutableStateOf(false)
    }

    // Recibe la fotografía tomada con la cámara.
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            profileViewModel.onTakenPhoto(bitmap)
        }
    }

    // Permite seleccionar una imagen de la galería.
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val bitmap = cargarBitmapOrientado(context, uri)

            if (bitmap != null) {
                profileViewModel.onTakenPhoto(bitmap)
            } else {
                Toast.makeText(
                    context,
                    "No se pudo abrir la imagen seleccionada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Solicita permiso cuando se intenta abrir la cámara.
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraLauncher.launch(null)
        } else {
            Toast.makeText(
                context,
                "Se necesita permiso para utilizar la cámara",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openCamera() {
        val alreadyGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (alreadyGranted) {
            cameraLauncher.launch(null)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // Carga la información del perfil al entrar a la pantalla.
    LaunchedEffect(id) {
        profileViewModel.cargarPerfil(id)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 20.dp,
                vertical = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.perfil),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Componente que muestra la foto y los datos del usuario.
        FotoPerfil(
            foto = state.fotoPerfil,
            nombre = nombre,
            apellido = apellido,
            usuario = usuario,
            onCambiarFoto = {
                mostrarOpcionesFoto = true
            },
            onGuardarCambios = {
                profileViewModel.guardarPerfil(id, token)
            }
        )

        // Componente que muestra los datos personales.
        PersonalInfoCard(
            telefono = state.telefono,
            correo = state.correo,
            fechaNac = state.fechaNac,
            genero = state.genero
        )

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.cerrar_sesion))
        }

        if (state.mensaje.isNotEmpty()) {
            Text(
                text = state.mensaje,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

    // Diálogo para escoger de dónde obtener la foto.
    if (mostrarOpcionesFoto) {
        AlertDialog(
            onDismissRequest = {
                mostrarOpcionesFoto = false
            },
            title = {
                Text(
                    text = "Cambiar foto de perfil",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextButton(
                        onClick = {
                            mostrarOpcionesFoto = false
                            openCamera()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Tomar fotografía")
                    }

                    TextButton(
                        onClick = {
                            mostrarOpcionesFoto = false
                            galleryLauncher.launch("image/*")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Elegir de la galería")
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarOpcionesFoto = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
