package com.roberto.travelmarket.presentation.screens.favoritos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen
import com.roberto.travelmarket.viewmodel.FavoritosViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(
    navController: NavController,
    favoritosViewModel: FavoritosViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val favoritos by favoritosViewModel.favoritos.collectAsState()

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
                            "Mis Favoritos",
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
                    tonalElevation = 0.dp  // ← SIN elevación
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
                        selected = true,
                        onClick = { },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2196F3),
                            selectedTextColor = Color(0xFF2196F3),
                            indicatorColor = Color(0xFFE3F2FD)
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = false,
                        onClick = { navController.navigate(Screen.Perfil.route) }
                    )
                }
            },
            containerColor = Color.White  // ← Fondo BLANCO
        ) { paddingValues ->
            if (favoritos.isEmpty()) {
                // ======= PANTALLA VACÍA (COMO FIGMA) =======
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Círculo con corazón (IDÉNTICO AL FIGMA)
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFFEEEEEE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = Color(0xFF9E9E9E)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        "No tienes favoritos aún",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Guarda lugares, eventos y más desde su pantalla de\ndetalle para acceder rápidamente a ellos aquí.",
                        fontSize = 14.sp,
                        color = Color(0xFF757575),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigate(Screen.Inicio.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(horizontal = 48.dp)
                    ) {
                        Text(
                            "Explorar contenido",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            } else {
                // ======= LISTA DE FAVORITOS (COMO FIGMA) =======
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White)
                ) {
                    // Contador de favoritos
                    Text(
                        "${favoritos.size} favorito${if (favoritos.size != 1) "s" else ""} guardado${if (favoritos.size != 1) "s" else ""}",
                        fontSize = 14.sp,
                        color = Color(0xFF757575),
                        modifier = Modifier.padding(16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(favoritos.size) { index ->
                            val item = favoritos[index]
                            FavoritoCard(
                                titulo = item.titulo,
                                subtitulo = item.subtitulo,
                                categoria = item.categoria,
                                imagenResId = item.imagenResId,
                                onClickDetalle = {
                                    when (item.categoria) {
                                        "Lugares" -> navController.navigate(Screen.DetalleLugar.createRoute(item.id))
                                        "Eventos" -> navController.navigate(Screen.DetalleEvento.createRoute(item.id))
                                        "Gastronomía" -> navController.navigate(Screen.DetalleGastronomia.createRoute(item.id))
                                        "Transporte" -> navController.navigate(Screen.DetalleTransporte.createRoute(item.id))
                                    }
                                },
                                onClickEliminar = {
                                    favoritosViewModel.quitarFavorito(item.id, item.categoria)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritoCard(
    titulo: String,
    subtitulo: String,
    categoria: String,
    imagenResId: Int,
    onClickDetalle: () -> Unit,
    onClickEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imagenResId),
                contentDescription = titulo,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
                Text(
                    subtitulo,
                    fontSize = 14.sp,
                    color = Color(0xFF757575)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    color = Color(0xFFFF9800),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        categoria,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onClickDetalle, contentPadding = PaddingValues(0.dp)) {
                    Text("Ver detalle →", color = Color(0xFF2196F3), fontSize = 12.sp)
                }
            }

            IconButton(onClick = onClickEliminar) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color(0xFFE57373)
                )
            }
        }
    }
}
