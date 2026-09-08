package com.example.clasedesmov2026.data

import com.example.clasedesmov2026.data.remote.RetrofitClient
import com.example.clasedesmov2026.model.request.register.RegisterRequest
import com.example.clasedesmov2026.model.response.register.RegisterResponse

class RegisterRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun register(
        request: RegisterRequest
    ): Result<RegisterResponse> {

        return try {

            val response = apiService.register(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(
                    Exception("No se pudo registrar el usuario")
                )
            }

        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }
}