package com.roberto.travelmarket.data.repository
import com.roberto.travelmarket.data.dao.EventoDao
import com.roberto.travelmarket.data.model.Evento
import kotlinx.coroutines.flow.Flow

class EventoRepository(private val eventoDao: EventoDao) {

    val allEventos: Flow<List<Evento>> = eventoDao.getAllEventos()

    suspend fun getEventoById(id: Int): Evento? {
        return eventoDao.getEventoById(id)
    }

    suspend fun insertEvento(evento: Evento) {
        eventoDao.insertEvento(evento)
    }

    suspend fun deleteEvento(evento: Evento) {
        eventoDao.deleteEvento(evento)
    }
}
