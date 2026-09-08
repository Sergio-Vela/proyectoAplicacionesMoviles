package com.example.clasedesmov2026.model.response.user

import com.example.clasedesmov2026.model.response.standardResponse.StandardResponse

data class UserResponse(
    val standardResponse: StandardResponse,
    val body: UserBody
)
