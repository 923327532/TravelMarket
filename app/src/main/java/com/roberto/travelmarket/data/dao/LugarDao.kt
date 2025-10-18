package com.roberto.travelmarket.data.dao

import androidx.room.*
import com.roberto.travelmarket.data.model.Lugar
import kotlinx.coroutines.flow.Flow

@Dao
interface LugarDao {
    @Query("SELECT * FROM lugares")
    fun getAllLugares(): Flow<List<Lugar>>

    @Query("SELECT * FROM lugares WHERE id = :id")
    suspend fun getLugarById(id: Int): Lugar?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLugar(lugar: Lugar)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLugares(lugares: List<Lugar>)

    @Delete
    suspend fun deleteLugar(lugar: Lugar)

    @Query("DELETE FROM lugares")
    suspend fun deleteAllLugares()
}
