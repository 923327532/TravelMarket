package com.roberto.travelmarket.data.repository

import com.roberto.travelmarket.data.dao.UsuarioDao
import com.roberto.travelmarket.data.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrarUsuario(usuario: Usuario): Long {
        return usuarioDao.insertUsuario(usuario)
    }

    suspend fun login(email: String, password: String): Usuario? {
        return usuarioDao.login(email, password)
    }

    suspend fun getUsuarioByEmail(email: String): Usuario? {
        return usuarioDao.getUsuarioByEmail(email)
    }

    suspend fun getUsuarioById(id: Int): Usuario? {
        return usuarioDao.getUsuarioById(id)
    }
}
