package com.roberto.travelmarket.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roberto.travelmarket.data.db.AppDatabase
import com.roberto.travelmarket.data.model.Evento
import com.roberto.travelmarket.data.repository.EventoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EventoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventoRepository
    val allEventos: StateFlow<List<Evento>>

    init {
        val eventoDao = AppDatabase.getDatabase(application).eventoDao()
        repository = EventoRepository(eventoDao)
        allEventos = repository.allEventos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insertEvento(evento: Evento) = viewModelScope.launch {
        repository.insertEvento(evento)
    }

    fun deleteEvento(evento: Evento) = viewModelScope.launch {
        repository.deleteEvento(evento)
    }

    suspend fun getEventoById(id: Int): Evento? {
        return repository.getEventoById(id)
    }
}
