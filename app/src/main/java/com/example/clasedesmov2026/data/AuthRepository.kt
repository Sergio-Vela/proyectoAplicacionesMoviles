package com.example.clasedesmov2026.data

import com.example.clasedesmov2026.data.remote.RetrofitClient
import com.example.clasedesmov2026.model.request.login.LoginRequest
import com.example.clasedesmov2026.model.response.login.LoginResponse

class AuthRepository {
    private val apiService = RetrofitClient.apiService
    suspend fun login(usuario : String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(usuario, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Credenciales erroneas"))
            }
        } catch (e: Exception){
            Result.failure(Exception("Error de conexión ${e.message}"))
        }
    }
}