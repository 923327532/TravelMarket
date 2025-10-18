package com.roberto.travelmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roberto.travelmarket.data.db.AppDatabase
import com.roberto.travelmarket.data.model.Transporte
import com.roberto.travelmarket.data.repository.TransporteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransporteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TransporteRepository
    val allTransporte: StateFlow<List<Transporte>>

    init {
        val transporteDao = AppDatabase.getDatabase(application).transporteDao()
        repository = TransporteRepository(transporteDao)
        allTransporte = repository.allTransporte.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insertTransporte(transporte: Transporte) = viewModelScope.launch {
        repository.insertTransporte(transporte)
    }

    fun deleteTransporte(transporte: Transporte) = viewModelScope.launch {
        repository.deleteTransporte(transporte)
    }

    suspend fun getTransporteById(id: Int): Transporte? {
        return repository.getTransporteById(id)
    }
}
