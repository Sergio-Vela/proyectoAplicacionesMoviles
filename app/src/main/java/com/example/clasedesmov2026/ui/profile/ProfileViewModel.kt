package com.example.clasedesmov2026.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasedesmov2026.data.ProfileRepository
import com.example.clasedesmov2026.model.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    fun cargarPerfil(id: Int) {
        viewModelScope.launch {

            val resultado = repository.getProfile(id)

            if (resultado.isSuccess) {

                val profile = resultado.getOrNull()!!

                _state.value = _state.value.copy(
                    fotoBase64 = profile.body.fotoBase64,
                    telefono = profile.body.telefono ?: "",
                    correo = profile.body.correo ?: "",
                    fechaNac = profile.body.fechaNac?.substringBefore("T") ?: "",
                    genero = profile.body.genero ?: ""
                )

            } else {

                _state.value = _state.value.copy(
                    mensaje = "No se pudo cargar el perfil"
                )
            }
        }
    }
}