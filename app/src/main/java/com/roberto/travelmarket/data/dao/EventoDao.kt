package com.roberto.travelmarket.data.dao

import androidx.room.*
import com.roberto.travelmarket.data.model.Evento
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDao {
    @Query("SELECT * FROM eventos")
    fun getAllEventos(): Flow<List<Evento>>

    @Query("SELECT * FROM eventos WHERE id = :id")
    suspend fun getEventoById(id: Int): Evento?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvento(evento: Evento)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEventos(eventos: List<Evento>)

    @Delete
    suspend fun deleteEvento(evento: Evento)

    @Query("DELETE FROM eventos")
    suspend fun deleteAllEventos()
}
