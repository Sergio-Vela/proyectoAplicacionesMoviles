package com.example.clasedesmov2026.model.state

import android.graphics.Bitmap

data class ProfileState(
    val fotoPerfil: Bitmap? = null,
    val fotoModificada: Boolean = false,
    val telefono: String = "",
    val correo: String = "",
    val fechaNac: String = "",
    val genero: String = "",
    val mensaje: String = ""
)