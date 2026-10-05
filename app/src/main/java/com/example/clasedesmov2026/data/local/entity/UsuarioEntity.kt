package com.example.clasedesmov2026.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val token: String
)