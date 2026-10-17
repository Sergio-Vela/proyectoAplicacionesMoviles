package com.example.clasedesmov2026.ui.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.stringResource
import com.example.clasedesmov2026.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    onLoginSuccess: (id: Int, nombre: String, apellido: String, usuario: String, token: String) -> Unit = { _, _, _, _, _ -> },
    onRegisterClick: () -> Unit = {}
) {
    val state by loginViewModel.state.collectAsState()

    LaunchedEffect(state.loginExito) {
        if (state.loginExito){
            onLoginSuccess(state.id, state.nombre,state.apellido, state.user, state.token)
        }
    }

    Column (
        modifier = modifier.
        fillMaxSize().
        padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_titulo),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.user,
            onValueChange = { loginViewModel.onUserChange(it) },
            label = { Text(stringResource(R.string.usuario)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = { Text(stringResource(R.string.contrasena)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { loginViewModel.onLoginClic() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.ingresar))
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.registrar))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.message,
            color = if (state.loginExito){
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
    }
}