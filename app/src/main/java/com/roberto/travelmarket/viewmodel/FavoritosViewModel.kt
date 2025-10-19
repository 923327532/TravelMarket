package com.roberto.travelmarket.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ItemFavorito(
    val id: Int,
    val titulo: String,
    val subtitulo: String,
    val categoria: String,
    val imagenResId: Int
)

class FavoritosViewModel : ViewModel() {
    private val _favoritos = MutableStateFlow<List<ItemFavorito>>(emptyList())
    val favoritos: StateFlow<List<ItemFavorito>> = _favoritos.asStateFlow()

    fun agregarFavorito(item: ItemFavorito) {
        val listaActual = _favoritos.value.toMutableList()
        if (!listaActual.any { it.id == item.id && it.categoria == item.categoria }) {
            listaActual.add(item)
            _favoritos.value = listaActual
        }
    }

    fun quitarFavorito(id: Int, categoria: String) {
        _favoritos.value = _favoritos.value.filter {
            !(it.id == id && it.categoria == categoria)
        }
    }

    fun esFavorito(id: Int, categoria: String): Boolean {
        return _favoritos.value.any { it.id == id && it.categoria == categoria }
    }
}