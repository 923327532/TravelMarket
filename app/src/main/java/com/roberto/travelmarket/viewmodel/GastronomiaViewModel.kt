package com.roberto.travelmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roberto.travelmarket.data.db.AppDatabase
import com.roberto.travelmarket.data.model.Gastronomia
import com.roberto.travelmarket.data.repository.GastronomiaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GastronomiaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GastronomiaRepository
    val allGastronomia: StateFlow<List<Gastronomia>>

    init {
        val gastronomiaDao = AppDatabase.getDatabase(application).gastronomiaDao()
        repository = GastronomiaRepository(gastronomiaDao)
        allGastronomia = repository.allGastronomia.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insertGastronomia(gastronomia: Gastronomia) = viewModelScope.launch {
        repository.insertGastronomia(gastronomia)
    }

    fun deleteGastronomia(gastronomia: Gastronomia) = viewModelScope.launch {
        repository.deleteGastronomia(gastronomia)
    }

    suspend fun getGastronomiaById(id: Int): Gastronomia? {
        return repository.getGastronomiaById(id)
    }
}
