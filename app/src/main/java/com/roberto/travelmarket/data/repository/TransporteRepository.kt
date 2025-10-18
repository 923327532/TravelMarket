package com.roberto.travelmarket.data.repository

import com.roberto.travelmarket.data.dao.TransporteDao
import com.roberto.travelmarket.data.model.Transporte
import kotlinx.coroutines.flow.Flow

class TransporteRepository(private val transporteDao: TransporteDao) {

    val allTransporte: Flow<List<Transporte>> = transporteDao.getAllTransporte()

    suspend fun getTransporteById(id: Int): Transporte? {
        return transporteDao.getTransporteById(id)
    }

    suspend fun insertTransporte(transporte: Transporte) {
        transporteDao.insertTransporte(transporte)
    }

    suspend fun deleteTransporte(transporte: Transporte) {
        transporteDao.deleteTransporte(transporte)
    }
}
