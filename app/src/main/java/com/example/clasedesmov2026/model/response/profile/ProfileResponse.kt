package com.example.clasedesmov2026.model.response.profile

import com.example.clasedesmov2026.model.response.standardResponse.StandardResponse

data class ProfileResponse(
    val standardResponse: StandardResponse,
    val body: Profilebody
)
