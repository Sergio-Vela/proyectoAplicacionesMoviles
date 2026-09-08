package com.example.clasedesmov2026.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    id: Int,
    nombre: String,
    apellido: String,
    usuario: String,
    profileViewModel: ProfileViewModel
) {

    val state by profileViewModel.state.collectAsState()

    LaunchedEffect(id) {
        profileViewModel.cargarPerfil(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Perfil"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {},
            label = {
                Text("Nombre")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = {},
            label = {
                Text("Apellido")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = usuario,
            onValueChange = {},
            label = {
                Text("Usuario")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = state.telefono,
            onValueChange = {},
            label = {
                Text("Teléfono")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = state.correo,
            onValueChange = {},
            label = {
                Text("Correo")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = state.fechaNac,
            onValueChange = {},
            label = {
                Text("Fecha de nacimiento")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = state.genero,
            onValueChange = {},
            label = {
                Text("Género")
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (state.mensaje.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = state.mensaje
            )
        }
    }
}