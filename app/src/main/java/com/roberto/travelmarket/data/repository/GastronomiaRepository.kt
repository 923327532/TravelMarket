package com.roberto.travelmarket.data.repository

import com.roberto.travelmarket.data.dao.GastronomiaDao
import com.roberto.travelmarket.data.model.Gastronomia
import kotlinx.coroutines.flow.Flow

class GastronomiaRepository(private val gastronomiaDao: GastronomiaDao) {

    val allGastronomia: Flow<List<Gastronomia>> = gastronomiaDao.getAllGastronomia()

    suspend fun getGastronomiaById(id: Int): Gastronomia? {
        return gastronomiaDao.getGastronomiaById(id)
    }

    suspend fun insertGastronomia(gastronomia: Gastronomia) {
        gastronomiaDao.insertGastronomia(gastronomia)
    }

    suspend fun deleteGastronomia(gastronomia: Gastronomia) {
        gastronomiaDao.deleteGastronomia(gastronomia)
    }
}
