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
import androidx.compose.ui.res.stringResource
import com.example.clasedesmov2026.R

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
            text = stringResource(R.string.registro),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.nombre,
            onValueChange = {
                registerViewModel.onNombreChange(it)
            },
            label = {
                Text(stringResource(R.string.nombre))
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
                Text(stringResource(R.string.apellido))
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
                Text(stringResource(R.string.usuario))
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
                Text(stringResource(R.string.contrasena))
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
                Text(stringResource(R.string.telefono))
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
                Text(stringResource(R.string.correo))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(stringResource(R.string.fecha_nacimiento))

        OutlinedTextField(
            value = state.dia,
            onValueChange = { registerViewModel.onDiaChange(it) },
            label = { Text(stringResource(R.string.dia)) }
        )

        OutlinedTextField(
            value = state.mes,
            onValueChange = { registerViewModel.onMesChange(it) },
            label = { Text(stringResource(R.string.mes)) }
        )

        OutlinedTextField(
            value = state.anyo,
            onValueChange = { registerViewModel.onAnioChange(it) },
            label = { Text(stringResource(R.string.anio)) }
        )


        Spacer(modifier = Modifier.height(15.dp))

        Text(stringResource(R.string.genero))

        Row {
            RadioButton(
                selected = state.genero == "M",
                onClick = {
                    registerViewModel.onGeneroChange("M")
                }
            )
            Text(stringResource(R.string.masculino))

            RadioButton(
                selected = state.genero == "F",
                onClick = {
                    registerViewModel.onGeneroChange("F")
                }
            )
            Text(stringResource(R.string.femenino))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                registerViewModel.onRegisterClic()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.registrar))
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