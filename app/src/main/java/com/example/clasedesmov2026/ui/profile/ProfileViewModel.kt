package com.example.clasedesmov2026.ui.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasedesmov2026.data.repository.ProfileRepository
import com.example.clasedesmov2026.model.request.profile.ProfileRequest
import com.example.clasedesmov2026.model.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

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
                    telefono = profile.body.telefono ?: "",
                    correo = profile.body.correo ?: "",
                    fechaNac = profile.body.fechaNac?.substringBefore("T") ?: "",
                    genero = profile.body.genero ?: "",
                    editando = false,
                    mensaje = ""
                )

                val resultadoFoto = repository.getProfilePhoto(id)

                if (resultadoFoto.isSuccess) {
                    val bytes = resultadoFoto.getOrNull()

                    if (bytes != null) {
                        loadPhotoFromBytes(bytes)
                    }
                } else {
                    _state.value = _state.value.copy(
                        mensaje = "No se pudo cargar la foto"
                    )
                }

            } else {
                _state.value = _state.value.copy(
                    mensaje = resultado.exceptionOrNull()?.message
                        ?: "No se pudo cargar el perfil"
                )
            }
        }
    }

    fun onTakenPhoto(bitmap: Bitmap) {
        _state.value = _state.value.copy(
            fotoPerfil = bitmap,
            fotoModificada = true
        )
    }

    fun onTelefonoChange(telefono: String) {
        _state.value = _state.value.copy(
            telefono = telefono
        )
    }

    fun onCorreoChange(correo: String) {
        _state.value = _state.value.copy(
            correo = correo
        )
    }

    fun onFechaNacChange(fechaNac: String) {
        _state.value = _state.value.copy(
            fechaNac = fechaNac
        )
    }

    fun onGeneroChange(genero: String) {
        _state.value = _state.value.copy(
            genero = genero
        )
    }

    fun onEditandoCambio(editando: Boolean) {
        _state.value = _state.value.copy(
            editando = editando
        )
    }

    fun getPhotoInByte(): ByteArray? {
        val bitmap = _state.value.fotoPerfil ?: return null

        // Limitamos el lado más largo a 1024 píxeles.
        val ladoMaximo = 1024
        val ladoMayor = maxOf(bitmap.width, bitmap.height)

        val escala = minOf(
            1f,
            ladoMaximo.toFloat() / ladoMayor
        )

        val ancho = (bitmap.width * escala).toInt().coerceAtLeast(1)
        val alto = (bitmap.height * escala).toInt().coerceAtLeast(1)

        val bitmapReducido = if (escala < 1f) {
            Bitmap.createScaledBitmap(bitmap, ancho, alto, true)
        } else {
            bitmap
        }

        val stream = ByteArrayOutputStream()
        var calidad = 80

        bitmapReducido.compress(
            Bitmap.CompressFormat.JPEG,
            calidad,
            stream
        )

        // Intentamos mantener el archivo por debajo de unos 700 KB.
        while (stream.size() > 700 * 1024 && calidad > 40) {
            calidad -= 10
            stream.reset()

            bitmapReducido.compress(
                Bitmap.CompressFormat.JPEG,
                calidad,
                stream
            )
        }

        return stream.toByteArray()
    }

    fun loadPhotoFromBytes(bytes: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(
            bytes,
            0,
            bytes.size
        )

        if (bitmap != null) {
            _state.value = _state.value.copy(
                fotoPerfil = bitmap
            )
        }
    }

    fun guardarPerfil(id: Int, token: String) {
        viewModelScope.launch {

            val request = ProfileRequest(
                telefono = _state.value.telefono,
                correo = _state.value.correo,
                fechaNac = _state.value.fechaNac,
                genero = _state.value.genero
            )

            // Solo adjuntar una foto si el usuario tomó una nueva.
            val fotoBytes = if (_state.value.fotoModificada) {
                getPhotoInByte()
            } else {
                null
            }

            val resultado = repository.updateProfile(
                id = id,
                request = request,
                fotoBytes = fotoBytes,
                token = token
            )

            if (resultado.isSuccess) {
                val profile = resultado.getOrNull()!!

                _state.value = _state.value.copy(
                    fotoModificada = false,
                    editando = false,
                    mensaje = profile.standardResponse.message
                )
            } else {
                _state.value = _state.value.copy(
                    mensaje = resultado.exceptionOrNull()?.message
                        ?: "No se pudo actualizar el perfil"
                )
            }
        }
    }
}