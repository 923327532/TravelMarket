package com.roberto.travelmarket.presentation.screens.explorar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorarScreen(
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                            "Explorar",
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
                        selected = true,
                        onClick = { },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2196F3),
                            selectedTextColor = Color(0xFF2196F3),
                            indicatorColor = Color.Transparent


                        )
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
                        selected = false,
                        onClick = { navController.navigate(Screen.Perfil.route) }
                    )
                }
            },
            containerColor = Color(0xFFF5F5F5)
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        "Descubre Lima",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                    Text(
                        "Explora lugares turísticos, eventos deportivos, gastronomía local y opciones de transporte",
                        fontSize = 14.sp,
                        color = Color(0xFF757575),
                        lineHeight = 20.sp
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.TrendingUp,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        "Contenido Disponible",
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                                Text(
                                    "Total de opciones para explorar",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "10",
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Total de ítems",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "4",
                                    color = Color.White,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Categorías",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        "Categorías",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CategoryCardExplorar(
                            title = "Lugares",
                            subtitle = "3 disponibles",
                            icon = Icons.Default.LocationOn,
                            backgroundColor = Color(0xFF2196F3),
                            onClick = { navController.navigate(Screen.Lugares.route) },
                            modifier = Modifier.weight(1f)
                        )
                        CategoryCardExplorar(
                            title = "Eventos",
                            subtitle = "3 disponibles",
                            icon = Icons.Default.Event,
                            backgroundColor = Color(0xFF9C27B0),
                            onClick = { navController.navigate(Screen.Eventos.route) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CategoryCardExplorar(
                            title = "Gastronomía",
                            subtitle = "2 disponibles",
                            icon = Icons.Default.Restaurant,
                            backgroundColor = Color(0xFFFF5722),
                            onClick = { navController.navigate(Screen.Gastronomia.route) },
                            modifier = Modifier.weight(1f)
                        )
                        CategoryCardExplorar(
                            title = "Transporte",
                            subtitle = "2 disponibles",
                            icon = Icons.Default.DirectionsBus,
                            backgroundColor = Color(0xFF4CAF50),
                            onClick = { navController.navigate(Screen.Transporte.route) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Text(
                        "Recomendados",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { navController.navigate(Screen.TourGastronomico.route) },
                        border = BorderStroke(2.dp, Color(0xFFFF9800))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Restaurant,
                                contentDescription = null,
                                tint = Color(0xFFFF9800),
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    "Tour Gastronómico",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF212121)
                                )
                                Text(
                                    "Descubre la rica gastronomía peruana con nuestros restaurantes recomendados",
                                    fontSize = 12.sp,
                                    color = Color(0xFF757575),
                                    lineHeight = 16.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    "Explorar →",
                                    fontSize = 12.sp,
                                    color = Color(0xFFFF9800),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCardExplorar(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                subtitle,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Ver todos →",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}