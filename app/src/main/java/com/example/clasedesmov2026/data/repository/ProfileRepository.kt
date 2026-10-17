package com.example.clasedesmov2026.data.repository

import com.example.clasedesmov2026.data.remote.RetrofitClient
import com.example.clasedesmov2026.model.request.profile.ProfileRequest
import com.example.clasedesmov2026.model.response.profile.ProfileResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class ProfileRepository {

    private val apiService = RetrofitClient.apiService

    private val tipoTexto = "text/plain".toMediaType()

    private fun crearParteTexto(valor: String?): RequestBody? {
        return valor?.toRequestBody(tipoTexto)
    }

    private fun crearParteFoto(bytes: ByteArray?): MultipartBody.Part? {
        if (bytes == null) {
            return null
        }

        val cuerpoFoto = bytes.toRequestBody(
            "image/jpeg".toMediaType()
        )

        return MultipartBody.Part.createFormData(
            "foto",
            "foto.jpg",
            cuerpoFoto
        )
    }

    suspend fun getProfile(id: Int): Result<ProfileResponse> {
        return try {
            val response = apiService.getProfile(id)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(
                    Exception("No se pudo obtener el perfil. Código: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }

    suspend fun getProfilePhoto(id: Int): Result<ByteArray?> {
        return try {
            val response = apiService.getProfilePhoto(id)

            when {
                response.isSuccessful -> {
                    Result.success(response.body()?.bytes())
                }

                response.code() == 404 -> {
                    // No existe una fotografía para este perfil.
                    Result.success(null)
                }

                else -> {
                    Result.failure(
                        Exception("No se pudo descargar la foto. Código: ${response.code()}")
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }

    suspend fun createProfile(
        id: Int,
        request: ProfileRequest,
        fotoBytes: ByteArray?,
        token: String
    ): Result<ProfileResponse> {
        return try {
            val response = apiService.createProfile(
                id = id,
                authorization = "Bearer $token",
                foto = crearParteFoto(fotoBytes),
                telefono = crearParteTexto(request.telefono),
                correo = crearParteTexto(request.correo),
                fechaNac = crearParteTexto(request.fechaNac),
                genero = crearParteTexto(request.genero)
            )

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(
                    Exception("No se pudo crear el perfil. Código: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }

    suspend fun updateProfile(
        id: Int,
        request: ProfileRequest,
        fotoBytes: ByteArray?,
        token: String
    ): Result<ProfileResponse> {
        return try {
            val response = apiService.updateProfile(
                id = id,
                authorization = "Bearer $token",
                foto = crearParteFoto(fotoBytes),
                telefono = crearParteTexto(request.telefono),
                correo = crearParteTexto(request.correo),
                fechaNac = crearParteTexto(request.fechaNac),
                genero = crearParteTexto(request.genero)
            )

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(
                    Exception("No se pudo actualizar el perfil. Código: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Error de conexión: ${e.message}")
            )
        }
    }
}