package com.example.clasedesmov2026.model.response.profile

data class Profilebody(
    val id: Int,
    val userId: Int,
    val fotoBase64: String?,
    val telefono: String?,
    val correo: String?,
    val fechaNac: String?,
    val genero: String?
)