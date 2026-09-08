package com.example.clasedesmov2026.ui.register

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit = {}
) {

    val state by registerViewModel.state.collectAsState()

    LaunchedEffect(state.registroExitoso) {
        if (state.registroExitoso) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {

        Text(
            text = "Registro",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.nombre,
            onValueChange = {
                registerViewModel.onNombreChange(it)
            },
            label = {
                Text("Nombre")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.apellido,
            onValueChange = {
                registerViewModel.onApellidoChange(it)
            },
            label = {
                Text("Apellido")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.usuario,
            onValueChange = {
                registerViewModel.onUsuarioChange(it)
            },
            label = {
                Text("Usuario")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                registerViewModel.onPasswordChange(it)
            },
            label = {
                Text("Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.telefono,
            onValueChange = {
                registerViewModel.onTelefonoChange(it)
            },
            label = {
                Text("Teléfono")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.correo,
            onValueChange = {
                registerViewModel.onCorreoChange(it)
            },
            label = {
                Text("Correo")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text("Fecha de nacimiento")

        OutlinedTextField(
            value = state.dia,
            onValueChange = { registerViewModel.onDiaChange(it) },
            label = { Text("Día") }
        )

        OutlinedTextField(
            value = state.mes,
            onValueChange = { registerViewModel.onMesChange(it) },
            label = { Text("Mes") }
        )

        OutlinedTextField(
            value = state.anyo,
            onValueChange = { registerViewModel.onAnioChange(it) },
            label = { Text("Año") }
        )


        Spacer(modifier = Modifier.height(15.dp))

        Text("Género")

        Row {
            RadioButton(
                selected = state.genero == "M",
                onClick = {
                    registerViewModel.onGeneroChange("M")
                }
            )
            Text("Masculino")

            RadioButton(
                selected = state.genero == "F",
                onClick = {
                    registerViewModel.onGeneroChange("F")
                }
            )
            Text("Femenino")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                registerViewModel.onRegisterClic()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.message,
            color = if (state.registroExitoso) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
    }
}