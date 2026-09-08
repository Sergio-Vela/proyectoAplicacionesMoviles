package com.example.clasedesmov2026.model.request.user

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String
)
