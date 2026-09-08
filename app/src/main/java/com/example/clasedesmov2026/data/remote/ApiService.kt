package com.example.clasedesmov2026.data.remote

import com.example.clasedesmov2026.model.request.login.LoginRequest
import com.example.clasedesmov2026.model.request.profile.ProfileRequest
import com.example.clasedesmov2026.model.request.register.RegisterRequest
import com.example.clasedesmov2026.model.response.login.LoginResponse
import com.example.clasedesmov2026.model.response.profile.ProfileResponse
import com.example.clasedesmov2026.model.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("api/users/{id}/profile")
    suspend fun getProfile(
        @Path("id") id: Int
    ): Response<ProfileResponse>

    @PUT("api/users/{id}/profile")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body request: ProfileRequest
    ): Response<ProfileResponse>

    @POST("api/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

}