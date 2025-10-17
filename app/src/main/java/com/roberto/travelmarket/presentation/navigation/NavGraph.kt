package com.roberto.travelmarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roberto.travelmarket.presentation.screens.detalle.DetalleScreen
import com.roberto.travelmarket.presentation.screens.inicio.InicioScreen
import com.roberto.travelmarket.presentation.screens.perfil.PerfilScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {
        composable(Screen.Inicio.route) {
            InicioScreen(
                onNavigateToDetalle = {
                    navController.navigate(Screen.Detalle.route)
                }
            )
        }

        composable(Screen.Detalle.route) {
            DetalleScreen(
                onNavigateToPerfil = {
                    navController.navigate(Screen.Perfil.route)
                }
            )
        }

        composable(Screen.Perfil.route) {
            PerfilScreen(
                onNavigateToInicio = {
                    navController.navigate(Screen.Inicio.route) {
                        popUpTo(Screen.Inicio.route) { inclusive = true }
                    }
                }
            )
        }
    }
}