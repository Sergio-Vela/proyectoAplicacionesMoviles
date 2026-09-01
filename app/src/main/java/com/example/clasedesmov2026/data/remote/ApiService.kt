package com.example.clasedesmov2026.data.remote

import com.example.clasedesmov2026.model.request.login.LoginRequest
import com.example.clasedesmov2026.model.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}