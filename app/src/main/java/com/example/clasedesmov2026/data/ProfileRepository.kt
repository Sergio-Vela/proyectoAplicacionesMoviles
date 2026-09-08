package com.example.clasedesmov2026.data

import com.example.clasedesmov2026.data.remote.RetrofitClient
import com.example.clasedesmov2026.model.response.profile.ProfileResponse

class ProfileRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getProfile(id: Int): Result<ProfileResponse> {
        return try {
            val response = apiService.getProfile(id)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(
                    Exception("No se pudo obtener el perfil")
                )
            }

        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }
}