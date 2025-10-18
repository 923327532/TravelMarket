package com.roberto.travelmarket.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastronomia")
data class Gastronomia(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val ubicacion: String,
    val latitud: Double,
    val longitud: Double,
    val imagenResId: Int,
    val categoria: String = "GASTRONOMIA"
)
