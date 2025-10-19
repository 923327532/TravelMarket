package com.roberto.travelmarket.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roberto.travelmarket.data.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertUsuario(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getUsuarioByEmail(email: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun getUsuarioById(id: Int): Usuario?
}
