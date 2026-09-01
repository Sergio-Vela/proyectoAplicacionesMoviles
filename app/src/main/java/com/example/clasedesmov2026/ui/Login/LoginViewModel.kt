package com.example.clasedesmov2026.ui.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasedesmov2026.data.AuthRepository
import com.example.clasedesmov2026.model.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEmailChange(nuevoEmail: String) {
        _state.value = _state.value.copy(email = nuevoEmail)
    }

    fun onPasswordChange(nuevoPassword: String) {
        _state.value = _state.value.copy(password = nuevoPassword)
    }

    fun onLoginClic() {
        viewModelScope.launch {

            val resultado = repository.login(_state.value.email, _state.value.password)

            _state.value = _state.value.copy(
                message = if (resultado.isSuccess) "¡Bienvenido desde app movil con patron MVVM!" else "Credenciales erroneas",
                loginExito = resultado.isSuccess
            )
        }
    }
}