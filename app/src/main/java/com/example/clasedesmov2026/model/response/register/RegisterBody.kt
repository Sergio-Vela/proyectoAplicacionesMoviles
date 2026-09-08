package com.example.clasedesmov2026.model.response.register

import com.example.clasedesmov2026.model.response.profile.ProfileResponse
import com.example.clasedesmov2026.model.response.user.UserResponse

data class RegisterBody(
    val user: UserResponse,
    val profile: ProfileResponse
)
