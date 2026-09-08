package com.example.clasedesmov2026.model.request.register

data class RegisterRequest(
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val password: String,
    val fotoBase64: String?,
    val telefono: String,
    val correo: String,
    val fechaNac: String,
    val genero: String
)