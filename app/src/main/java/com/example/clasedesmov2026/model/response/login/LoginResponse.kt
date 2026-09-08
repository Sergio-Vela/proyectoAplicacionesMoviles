package com.example.clasedesmov2026.model.response.login

import com.example.clasedesmov2026.model.response.standardResponse.StandardResponse

data class LoginResponse(
    val standarResponse: StandardResponse,
    val body: LoginBody
)
