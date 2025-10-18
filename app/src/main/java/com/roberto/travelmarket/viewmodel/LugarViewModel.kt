package com.roberto.travelmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roberto.travelmarket.data.db.AppDatabase
import com.roberto.travelmarket.data.model.Lugar
import com.roberto.travelmarket.data.repository.LugarRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LugarRepository
    val allLugares: StateFlow<List<Lugar>>

    init {
        val lugarDao = AppDatabase.getDatabase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        allLugares = repository.allLugares.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insertLugar(lugar: Lugar) = viewModelScope.launch {
        repository.insertLugar(lugar)
    }

    fun deleteLugar(lugar: Lugar) = viewModelScope.launch {
        repository.deleteLugar(lugar)
    }

    suspend fun getLugarById(id: Int): Lugar? {
        return repository.getLugarById(id)
    }
}
