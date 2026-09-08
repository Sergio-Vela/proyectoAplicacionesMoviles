package com.example.clasedesmov2026.model.response.register

import com.example.clasedesmov2026.model.response.standardResponse.StandardResponse

data class RegisterResponse(
    val standardResponse: StandardResponse,
    val body: RegisterBody
)
