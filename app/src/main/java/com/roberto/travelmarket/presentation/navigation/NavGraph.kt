package com.roberto.travelmarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.roberto.travelmarket.presentation.screens.acercade.AcercaDeScreen
import com.roberto.travelmarket.presentation.screens.categorias.EventosScreen
import com.roberto.travelmarket.presentation.screens.categorias.GastronomiaScreen
import com.roberto.travelmarket.presentation.screens.categorias.LugaresScreen
import com.roberto.travelmarket.presentation.screens.categorias.TransporteScreen
import com.roberto.travelmarket.presentation.screens.detalle.DetalleEventoScreen
import com.roberto.travelmarket.presentation.screens.detalle.DetalleGastronomiaScreen
import com.roberto.travelmarket.presentation.screens.detalle.DetalleLugarScreen
import com.roberto.travelmarket.presentation.screens.detalle.DetalleTransporteScreen
import com.roberto.travelmarket.presentation.screens.inicio.InicioScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {
        // ==================== PANTALLA PRINCIPAL ====================

        composable(Screen.Inicio.route) {
            InicioScreen(navController = navController)
        }

        composable(Screen.AcercaDe.route) {
            AcercaDeScreen(navController)
        }

        // ==================== PENDIENTES (ARNOLD) ====================

        composable(Screen.Explorar.route) {
            // TODO: ExplorarScreen(navController = navController)
        }

        composable(Screen.Favoritos.route) {
            // TODO: FavoritosScreen(navController = navController)
        }

        composable(Screen.Perfil.route) {
            // TODO: PerfilScreen(navController = navController)
        }

        // ==================== CATEGORÍAS (ROBERTO - TUS PANTALLAS) ====================

        composable(Screen.Lugares.route) {
            LugaresScreen(navController = navController)
        }

        composable(Screen.Eventos.route) {
            EventosScreen(navController = navController)
        }

        composable(Screen.Gastronomia.route) {
            GastronomiaScreen(navController = navController)
        }

        composable(Screen.Transporte.route) {
            TransporteScreen(navController = navController)
        }

        // ==================== DETALLES (ROBERTO - TUS PANTALLAS) ====================

        // Detalle Lugar
        composable(
            route = Screen.DetalleLugar.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val lugarId = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleLugarScreen(
                lugarId = lugarId,
                navController = navController
            )
        }

        // Detalle Evento
        composable(
            route = Screen.DetalleEvento.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val eventoId = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleEventoScreen(
                eventoId = eventoId,
                navController = navController
            )
        }

        // Detalle Gastronomía
        composable(
            route = Screen.DetalleGastronomia.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val gastronomiaId = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleGastronomiaScreen(
                gastronomiaId = gastronomiaId,
                navController = navController
            )
        }

        // Detalle Transporte
        composable(
            route = Screen.DetalleTransporte.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val transporteId = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleTransporteScreen(
                transporteId = transporteId,
                navController = navController
            )
        }
    }
}
