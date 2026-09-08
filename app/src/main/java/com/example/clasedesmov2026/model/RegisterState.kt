package com.example.clasedesmov2026.model

data class RegisterState(
    val nombre: String = "",
    val apellido: String = "",
    val usuario: String = "",
    val password: String = "",
    val fotoBase64: String? = null,
    val telefono: String = "",
    val correo: String = "",
    //val fechaNac: String = "",
    val dia: String = "",
    val mes: String = "",
    val anyo: String = "",
    val genero: String = "",
    val message: String = "",
    val registroExitoso: Boolean = false
)