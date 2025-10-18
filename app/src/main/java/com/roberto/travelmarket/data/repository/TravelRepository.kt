package com.roberto.travelmarket.data.repository

import com.roberto.travelmarket.R
import com.roberto.travelmarket.data.model.*

class TravelRepository {

    // LUGARES
    private val lugares = listOf(
        Lugar(
            id = 0,
            nombre = "Parque de la Exposición",
            descripcion = "Hermoso parque urbano con áreas verdes, ideal para disfrutar en familia. Cuenta con espacios recreativos y zonas de descanso.",
            ubicacion = "Av. 28 de Julio, Lima",
            latitud = -12.062174252522565,
            longitud = -77.03665759053403,
            imagenResId = R.drawable.parque_exposicion
        ),
        Lugar(
            id = 1,
            nombre = "Circuito Mágico del Agua",
            descripcion = "Parque con fuentes iluminadas que ofrecen un espectáculo de luz y sonido único. Es uno de los principales atractivos turísticos de Lima.",
            ubicacion = "Parque de la Reserva, Lima",
            latitud = -12.070207588471881,
            longitud = -77.0330425040265,
            imagenResId = R.drawable.circuito_magico_agua
        ),
        Lugar(
            id = 2,
            nombre = "Museo Larco",
            descripcion = "Museo arqueológico con colección precolombina. Descubre la historia del antiguo Perú a través de sus piezas.",
            ubicacion = "Av. Bolívar 1515, Pueblo Libre",
            latitud = -12.072390631733752,
            longitud = -77.07110716354875,
            imagenResId = R.drawable.museo_larco
        )
    )

    // EVENTOS
    private val eventos = listOf(
        Evento(
            id = 3,
            nombre = "Ceremonia de Apertura",
            descripcion = "Inauguración oficial de los Juegos Panamericanos. Un evento histórico que reúne a atletas de todo el continente.",
            ubicacion = "Ministerio de Cultura, Lima",
            fecha = "26 de Julio, 2025",
            latitud = -12.086670520057341,
            longitud = -77.00188379264574,
            imagenResId = R.drawable.ceremonia_apertura
        ),
        Evento(
            id = 4,
            nombre = "Competencia de Atletismo",
            descripcion = "Competencia de atletismo con los mejores corredores. Presencia la velocidad y resistencia de atletas de élite.",
            ubicacion = "Villa Deportiva Nacional",
            fecha = "28 de Julio, 2025",
            latitud = -12.082123963527396,
            longitud = -77.00420245395524,
            imagenResId = R.drawable.competencia_atletismo
        )
    )

    // ===== FUNCIONES PÚBLICAS =====

    fun getTodosLosLugares(): List<Lugar> = lugares

    fun getTodosLosEventos(): List<Evento> = eventos

    fun getLugarPorId(id: Int): Lugar? {
        return lugares.find { it.id == id }
    }

    fun getEventoPorId(id: Int): Evento? {
        return eventos.find { it.id == id }
    }

    fun buscarPorNombre(query: String): List<Any> {
        val lugaresCoincidentes = lugares.filter { lugar ->
            lugar.nombre.contains(query, ignoreCase = true) ||
                    lugar.descripcion.contains(query, ignoreCase = true) ||
                    lugar.ubicacion.contains(query, ignoreCase = true)
        }

        val eventosCoincidentes = eventos.filter { evento ->
            evento.nombre.contains(query, ignoreCase = true) ||
                    evento.descripcion.contains(query, ignoreCase = true) ||
                    evento.ubicacion.contains(query, ignoreCase = true)
        }

        return lugaresCoincidentes + eventosCoincidentes
    }

    fun getItemsPorCategoria(categoria: Categoria): List<Any> {
        return when (categoria) {
            Categoria.LUGAR -> lugares
            Categoria.EVENTO -> eventos
            else -> emptyList()
        }
    }
}
