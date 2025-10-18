package com.roberto.travelmarket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen

@Composable
fun AppNavigationDrawer(
    navController: NavController,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(280.dp)
        ) {
            // ========== HEADER AZUL CON BOTÓN CERRAR ==========
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF2196F3))
            ) {
                // Botón X para cerrar
                IconButton(
                    onClick = { onCloseDrawer() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Cerrar",
                        tint = Color.White
                    )
                }

                // Texto del header
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        "TravelMarket",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Perú - País de Maravillas",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ========== MENÚ PRINCIPAL ==========
            DrawerMenuItem(
                icon = Icons.Outlined.Home,
                title = "Inicio",
                onClick = {
                    navController.navigate(Screen.Inicio.route) {
                        popUpTo(Screen.Inicio.route) { inclusive = true }
                    }
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.Search,
                title = "Explorar",
                onClick = {
                    navController.navigate(Screen.Explorar.route)
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.FavoriteBorder,
                title = "Favoritos",
                onClick = {
                    navController.navigate(Screen.Favoritos.route)
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.Person,
                title = "Perfil",
                onClick = {
                    navController.navigate(Screen.Perfil.route)
                    onCloseDrawer()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ========== CATEGORÍAS CON HEADER ==========
            Text(
                "Categorías",
                fontSize = 12.sp,
                color = Color(0xFF9E9E9E),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            DrawerMenuItem(
                icon = Icons.Outlined.LocationOn,
                title = "Lugares",
                iconTint = Color(0xFF2196F3),
                onClick = {
                    navController.navigate(Screen.Lugares.route)
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.Event,
                title = "Eventos",
                iconTint = Color(0xFF9C27B0),
                onClick = {
                    navController.navigate(Screen.Eventos.route)
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.Restaurant,
                title = "Gastronomía",
                iconTint = Color(0xFFFF5722),
                onClick = {
                    navController.navigate(Screen.Gastronomia.route)
                    onCloseDrawer()
                }
            )

            DrawerMenuItem(
                icon = Icons.Outlined.DirectionsBus,
                title = "Transporte",
                iconTint = Color(0xFF4CAF50),
                onClick = {
                    navController.navigate(Screen.Transporte.route)
                    onCloseDrawer()
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // ========== FOOTER ==========
            Divider(color = Color(0xFFE0E0E0))

            // ✅ ÚNICO BOTÓN "ACERCA DE" (EN FOOTER)
            DrawerMenuItem(
                icon = Icons.Outlined.Info,
                title = "Acerca de",
                onClick = {
                    navController.navigate(Screen.AcercaDe.route)  // ✅ AHORA SÍ NAVEGA
                    onCloseDrawer()
                }
            )
        }
    }
}

@Composable
private fun DrawerMenuItem(
    icon: ImageVector,
    title: String,
    iconTint: Color = Color(0xFF212121),
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title,
            fontSize = 14.sp,
            color = Color(0xFF212121)
        )
    }
}
