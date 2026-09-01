package com.example.clasedesmov2026.model.response.login

data class LoginBody(
    val token: String,
    val user: UserResponse
)