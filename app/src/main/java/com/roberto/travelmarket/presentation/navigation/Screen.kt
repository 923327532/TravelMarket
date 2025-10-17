package com.roberto.travelmarket.presentation.navigation

sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")
    object Detalle : Screen("detalle")
    object Perfil : Screen("perfil")
}