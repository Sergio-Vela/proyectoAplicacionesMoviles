package com.example.clasedesmov2026.data.remote

import com.example.clasedesmov2026.model.request.login.LoginRequest
import com.example.clasedesmov2026.model.request.register.RegisterRequest
import com.example.clasedesmov2026.model.response.login.LoginResponse
import com.example.clasedesmov2026.model.response.profile.ProfileResponse
import com.example.clasedesmov2026.model.response.register.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    // Consultar los datos personales del perfil.
    @GET("api/users/{id}/profile")
    suspend fun getProfile(
        @Path("id") id: Int
    ): Response<ProfileResponse>

    // Descargar la foto como bytes.
    @GET("api/users/{id}/profile/foto")
    suspend fun getProfilePhoto(
        @Path("id") id: Int
    ): Response<ResponseBody>

    // Crear el perfil.
    @Multipart
    @POST("api/users/{id}/profile")
    suspend fun createProfile(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String,
        @Part foto: MultipartBody.Part?,
        @Part("telefono") telefono: RequestBody?,
        @Part("correo") correo: RequestBody?,
        @Part("fechaNac") fechaNac: RequestBody?,
        @Part("genero") genero: RequestBody?
    ): Response<ProfileResponse>

    // Actualizar el perfil.
    @Multipart
    @PUT("api/users/{id}/profile")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String,
        @Part foto: MultipartBody.Part?,
        @Part("telefono") telefono: RequestBody?,
        @Part("correo") correo: RequestBody?,
        @Part("fechaNac") fechaNac: RequestBody?,
        @Part("genero") genero: RequestBody?
    ): Response<ProfileResponse>

    @POST("api/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>
}