package com.roberto.travelmarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.roberto.travelmarket.presentation.screens.detalle.DetalleLugarScreen
import com.roberto.travelmarket.presentation.screens.inicio.InicioScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {
        // Pantalla de Inicio (HOME) - RF11, RF12, RF14
        composable(Screen.Inicio.route) {
            InicioScreen(navController = navController)
        }

        // Pantalla Explorar (Pendiente - Día 4)
        composable(Screen.Explorar.route) {
            // TODO: ExplorarScreen(navController = navController)
        }

        // Pantalla Favoritos (Pendiente - Día 5)
        composable(Screen.Favoritos.route) {
            // TODO: FavoritosScreen(navController = navController)
        }

        // Pantalla Perfil (Pendiente - Día 5)
        composable(Screen.Perfil.route) {
            // TODO: PerfilScreen(navController = navController)
        }

        // ==================== CATEGORÍAS ====================

        // Lugares (Pendiente - Día 4)
        composable(Screen.Lugares.route) {
            // TODO: LugaresScreen(navController = navController)
        }

        // Eventos (Pendiente - Día 4)
        composable(Screen.Eventos.route) {
            // TODO: EventosScreen(navController = navController)
        }

        // Gastronomía (Pendiente - Día 4)
        composable(Screen.Gastronomia.route) {
            // TODO: GastronomiaScreen(navController = navController)
        }

        // Transporte (Pendiente - Día 4)
        composable(Screen.Transporte.route) {
            // TODO: TransporteScreen(navController = navController)
        }

        // ==================== DETALLES CON PARÁMETROS ====================

        // Detalle Lugar - RF14 (HOY)
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

        // Detalle Evento (Pendiente - Día 4)
        composable(
            route = Screen.DetalleEvento.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val eventoId = backStackEntry.arguments?.getInt("id") ?: 0
            // TODO: DetalleEventoScreen(eventoId, navController)
        }

        // Detalle Gastronomía (Pendiente - Día 4)
        composable(
            route = Screen.DetalleGastronomia.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val gastronomiaId = backStackEntry.arguments?.getInt("id") ?: 0
            // TODO: DetalleGastronomiaScreen(gastronomiaId, navController)
        }

        // Detalle Transporte (Pendiente - Día 4)
        composable(
            route = Screen.DetalleTransporte.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val transporteId = backStackEntry.arguments?.getInt("id") ?: 0
            // TODO: DetalleTransporteScreen(transporteId, navController)
        }
    }
}
