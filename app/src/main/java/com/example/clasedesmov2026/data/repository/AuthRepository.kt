package com.example.clasedesmov2026.data.repository

import com.example.clasedesmov2026.data.local.dao.UsuarioDao
import com.example.clasedesmov2026.data.local.entity.UsuarioEntity
import com.example.clasedesmov2026.data.remote.RetrofitClient
import com.example.clasedesmov2026.model.request.login.LoginRequest
import com.example.clasedesmov2026.model.response.login.LoginResponse

class AuthRepository(
    private val usuarioDao: UsuarioDao
) {

    private val apiService = RetrofitClient.apiService

    suspend fun login(
        usuario: String,
        password: String
    ): Result<LoginResponse> {

        return try {

            val response = apiService.login(
                LoginRequest(usuario, password)
            )

            if (response.isSuccessful && response.body() != null) {

                val loginResponse = response.body()!!

                val usuarioEntity = UsuarioEntity(
                    id = loginResponse.body.user.id,
                    nombre = loginResponse.body.user.nombre,
                    apellido = loginResponse.body.user.apellido,
                    usuario = loginResponse.body.user.usuario,
                    token = loginResponse.body.token
                )

                usuarioDao.guardarUsuario(usuarioEntity)

                Result.success(loginResponse)

            } else {
                Result.failure(
                    Exception("Credenciales erróneas")
                )
            }

        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión ${e.message}")
            )
        }
    }
}