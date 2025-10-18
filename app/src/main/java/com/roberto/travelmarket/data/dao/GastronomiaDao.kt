package com.roberto.travelmarket.data.dao

import androidx.room.*
import com.roberto.travelmarket.data.model.Gastronomia
import kotlinx.coroutines.flow.Flow

@Dao
interface GastronomiaDao {
    @Query("SELECT * FROM gastronomia")
    fun getAllGastronomia(): Flow<List<Gastronomia>>

    @Query("SELECT * FROM gastronomia WHERE id = :id")
    suspend fun getGastronomiaById(id: Int): Gastronomia?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGastronomia(gastronomia: Gastronomia)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGastronomia(gastronomias: List<Gastronomia>)

    @Delete
    suspend fun deleteGastronomia(gastronomia: Gastronomia)

    @Query("DELETE FROM gastronomia")
    suspend fun deleteAllGastronomia()
}
