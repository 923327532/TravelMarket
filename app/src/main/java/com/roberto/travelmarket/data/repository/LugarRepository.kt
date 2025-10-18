package com.roberto.travelmarket.data.repository

import com.roberto.travelmarket.data.dao.LugarDao
import com.roberto.travelmarket.data.model.Lugar
import kotlinx.coroutines.flow.Flow

class LugarRepository(private val lugarDao: LugarDao) {

    val allLugares: Flow<List<Lugar>> = lugarDao.getAllLugares()

    suspend fun getLugarById(id: Int): Lugar? {
        return lugarDao.getLugarById(id)
    }

    suspend fun insertLugar(lugar: Lugar) {
        lugarDao.insertLugar(lugar)
    }

    suspend fun deleteLugar(lugar: Lugar) {
        lugarDao.deleteLugar(lugar)
    }
}
