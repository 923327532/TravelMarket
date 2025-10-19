package com.roberto.travelmarket.presentation.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen
import com.roberto.travelmarket.viewmodel.AuthViewModel
import com.roberto.travelmarket.viewmodel.FavoritosViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel,
    authViewModel: AuthViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val favoritos by favoritosViewModel.favoritos.collectAsState()
    val usuario by authViewModel.usuarioActual.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            com.roberto.travelmarket.presentation.components.AppNavigationDrawer(
                navController = navController,
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Mi Perfil",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF2196F3)
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = false,
                        onClick = { navController.navigate(Screen.Inicio.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Explorar") },
                        label = { Text("Explorar") },
                        selected = false,
                        onClick = { navController.navigate(Screen.Explorar.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
                        label = { Text("Favoritos") },
                        selected = false,
                        onClick = { navController.navigate(Screen.Favoritos.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = true,
                        onClick = { },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2196F3),
                            selectedTextColor = Color(0xFF2196F3),
                            indicatorColor = Color.Transparent

                        )
                    )
                }
            },
            containerColor = Color(0xFFF5F5F5)
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1976D2))
                            .padding(top = 24.dp, bottom = 90.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // ✅ INICIALES DINÁMICAS DEL USUARIO
                            val iniciales = usuario?.nombreCompleto
                                ?.split(" ")
                                ?.mapNotNull { it.firstOrNull() }
                                ?.take(2)
                                ?.joinToString("")
                                ?.uppercase()
                                ?: "JD"

                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFF9800)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    iniciales,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // ✅ NOMBRE DINÁMICO DEL USUARIO
                            Text(
                                usuario?.nombreCompleto ?: "Usuario",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                "Tarjeta - Perú País de Maravillas",
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.95f)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    "Lima, Perú",
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.95f)
                                )
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-70).dp)
                            .padding(horizontal = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatsItem(icon = Icons.Default.Favorite, value = "${favoritos.size}", label = "Favoritos")
                            StatsItem(icon = Icons.Default.RemoveRedEye, value = "12", label = "Visitados")
                            StatsItem(icon = Icons.Default.Star, value = "5", label = "Reseñas")
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(4.dp))
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // ✅ EMAIL DINÁMICO
                            PerfilMenuItem(
                                icon = Icons.Outlined.Email,
                                title = "Email",
                                subtitle = usuario?.email ?: "No disponible",
                                onClick = { }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = Color(0xFFEEEEEE)
                            )

                            // ✅ FECHA DE REGISTRO DINÁMICA
                            PerfilMenuItem(
                                icon = Icons.Outlined.CalendarToday,
                                title = "Miembro desde",
                                subtitle = usuario?.fechaRegistro ?: "Octubre 2025",
                                onClick = { }
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            PerfilMenuButton(
                                icon = Icons.Outlined.Edit,
                                title = "Editar Perfil",
                                onClick = { }
                            )

                            HorizontalDivider(color = Color(0xFFEEEEEE))

                            PerfilMenuButton(
                                icon = Icons.Outlined.FavoriteBorder,
                                title = "Mis Favoritos",
                                onClick = { navController.navigate(Screen.Favoritos.route) }
                            )

                            HorizontalDivider(color = Color(0xFFEEEEEE))

                            PerfilMenuButton(
                                icon = Icons.Outlined.History,
                                title = "Historial",
                                onClick = { }
                            )

                            HorizontalDivider(color = Color(0xFFEEEEEE))

                            PerfilMenuButton(
                                icon = Icons.Outlined.Settings,
                                title = "Preferencias",
                                onClick = { }
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    // ✅ BOTÓN CERRAR SESIÓN FUNCIONAL
                    OutlinedButton(
                        onClick = {
                            authViewModel.cerrarSesion()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFE57373)
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE57373))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.ExitToApp, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cerrar Sesión")
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun StatsItem(icon: ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, tint = Color(0xFFFF9800), modifier = Modifier.size(22.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF212121))
        Text(label, fontSize = 11.sp, color = Color(0xFF757575))
    }
}

@Composable
fun PerfilMenuItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = Color(0xFF9E9E9E), modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(title, fontSize = 11.sp, color = Color(0xFF9E9E9E))
            Spacer(modifier = Modifier.height(2.dp))
            Text(subtitle, fontSize = 13.sp, color = Color(0xFF212121))
        }
    }
}

@Composable
fun PerfilMenuButton(icon: ImageVector, title: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    icon,
                    contentDescription = title,
                    tint = Color(0xFF9E9E9E),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    title,
                    fontSize = 13.sp,
                    color = Color(0xFF212121)
                )
            }
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
