package com.example.clasedesmov2026.model

data class LoginState(
    val email: String = "",
    val password: String = "",
    val message: String = "",
    val loginExito: Boolean = false
)
