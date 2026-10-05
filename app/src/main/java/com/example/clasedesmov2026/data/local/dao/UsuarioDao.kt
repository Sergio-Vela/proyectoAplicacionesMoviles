package com.example.clasedesmov2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clasedesmov2026.data.local.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario LIMIT 1")
    suspend fun obtenerUsuario(): UsuarioEntity?

    @Query("DELETE FROM usuario")
    suspend fun eliminarUsuario()
}