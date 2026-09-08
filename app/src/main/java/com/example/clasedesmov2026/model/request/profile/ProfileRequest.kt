package com.example.clasedesmov2026.model.request.profile

data class ProfileRequest(
    val fotoBase64: String?,
    val telefono: String?,
    val correo: String?,
    val fechaNac: String?,
    val genero: String?
)