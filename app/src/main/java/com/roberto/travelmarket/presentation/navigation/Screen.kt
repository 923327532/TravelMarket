package com.roberto.travelmarket.presentation.navigation

sealed class Screen(val route: String) {

    // Auth
    object Login : Screen("login")
    object Registro : Screen("registro")
    object Inicio : Screen("inicio")
    object Explorar : Screen("explorar")
    object Favoritos : Screen("favoritos")
    // En tu archivo Screen.kt
    object TourGastronomico : Screen("tour_gastronomico")

    object Perfil : Screen("perfil")


    // Categorías
    object Lugares : Screen("lugares")
    object Eventos : Screen("eventos")
    object Gastronomia : Screen("gastronomia")
    object Transporte : Screen("transporte")

    object AcercaDe : Screen("acerca_de")  // ✅


    // Detalle con parámetro
    object DetalleLugar : Screen("detalle_lugar/{id}") {
        fun createRoute(id: Int) = "detalle_lugar/$id"
    }

    object DetalleEvento : Screen("detalle_evento/{id}") {
        fun createRoute(id: Int) = "detalle_evento/$id"
    }

    object DetalleGastronomia : Screen("detalle_gastronomia/{id}") {
        fun createRoute(id: Int) = "detalle_gastronomia/$id"
    }

    object DetalleTransporte : Screen("detalle_transporte/{id}") {
        fun createRoute(id: Int) = "detalle_transporte/$id"
    }

}
