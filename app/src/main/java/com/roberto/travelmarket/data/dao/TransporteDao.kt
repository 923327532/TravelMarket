package com.roberto.travelmarket.data.dao

import androidx.room.*
import com.roberto.travelmarket.data.model.Transporte
import kotlinx.coroutines.flow.Flow

@Dao
interface TransporteDao {
    @Query("SELECT * FROM transporte")
    fun getAllTransporte(): Flow<List<Transporte>>

    @Query("SELECT * FROM transporte WHERE id = :id")
    suspend fun getTransporteById(id: Int): Transporte?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransporte(transporte: Transporte)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransporte(transportes: List<Transporte>)

    @Delete
    suspend fun deleteTransporte(transporte: Transporte)

    @Query("DELETE FROM transporte")
    suspend fun deleteAllTransporte()
}
