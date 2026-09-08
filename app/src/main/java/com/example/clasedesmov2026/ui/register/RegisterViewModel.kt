package com.example.clasedesmov2026.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasedesmov2026.data.RegisterRepository
import com.example.clasedesmov2026.model.RegisterState
import com.example.clasedesmov2026.model.request.register.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository = RegisterRepository()

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onNombreChange(nombre: String) {
        _state.value = _state.value.copy(nombre = nombre)
    }

    fun onApellidoChange(apellido: String) {
        _state.value = _state.value.copy(apellido = apellido)
    }

    fun onUsuarioChange(usuario: String) {
        _state.value = _state.value.copy(usuario = usuario)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onTelefonoChange(telefono: String) {
        _state.value = _state.value.copy(telefono = telefono)
    }

    fun onCorreoChange(correo: String) {
        _state.value = _state.value.copy(correo = correo)
    }

    fun onFechaNacChange(fechaNac: String) {
        _state.value = _state.value.copy(fechaNac = fechaNac)
    }

    fun onGeneroChange(genero: String) {
        _state.value = _state.value.copy(genero = genero)
    }

    fun onFotoChange(fotoBase64: String?) {
        _state.value = _state.value.copy(fotoBase64 = fotoBase64)
    }

    fun onRegisterClic() {

        viewModelScope.launch {

            val request = RegisterRequest(
                nombre = _state.value.nombre,
                apellido = _state.value.apellido,
                usuario = _state.value.usuario,
                password = _state.value.password,
                fotoBase64 = _state.value.fotoBase64,
                telefono = _state.value.telefono,
                correo = _state.value.correo,
                fechaNac = _state.value.fechaNac,
                genero = _state.value.genero
            )

            val resultado = repository.register(request)

            resultado
                .onSuccess { response ->

                    _state.value = _state.value.copy(
                        registroExitoso = true,
                        message = response.standardResponse.message
                    )
                }
                .onFailure { error ->

                    _state.value = _state.value.copy(
                        registroExitoso = false,
                        message = error.message.toString()
                    )
                }
        }
    }
}