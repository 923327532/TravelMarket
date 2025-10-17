package com.roberto.travelmarket.presentation.screens.inicio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.R
import com.roberto.travelmarket.presentation.components.CategoryCard
import com.roberto.travelmarket.presentation.components.ItemCard
import com.roberto.travelmarket.presentation.components.SearchBar
import com.roberto.travelmarket.presentation.navigation.Screen

// Data class para los items CON IMAGEN
data class ItemRecomendado(
    val id: Int,
    val titulo: String,
    val subtitulo: String,
    val categoria: String,
    val ubicacion: String,
    val imagenResId: Int  // ← AGREGADO
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navController: NavController
) {
    // Estado para la búsqueda
    var searchQuery by remember { mutableStateOf("") }

    // Lista completa de items CON IMÁGENES
    val todosLosItems = remember {
        listOf(
            ItemRecomendado(
                id = 0,
                titulo = "Parque de la Exposición",
                subtitulo = "Parque y áreas verdes",
                categoria = "Lugares",
                ubicacion = "Av. 28 de Julio, Lima",
                imagenResId = R.drawable.parque_exposicion  // ← AGREGADO
            ),
            ItemRecomendado(
                id = 1,
                titulo = "Circuito Mágico del Agua",
                subtitulo = "Fuentes y espectáculos",
                categoria = "Lugares",
                ubicacion = "Parque de la Reserva, Lima",
                imagenResId = R.drawable.circuito_magico_agua  // ← AGREGADO
            ),
            ItemRecomendado(
                id = 2,
                titulo = "Museo Larco",
                subtitulo = "Museo histórico",
                categoria = "Lugares",
                ubicacion = "Av. Bolívar 1515, Pueblo Libre",
                imagenResId = R.drawable.museo_larco  // ← AGREGADO
            ),
            ItemRecomendado(
                id = 3,
                titulo = "Ceremonia de Apertura",
                subtitulo = "Inauguración",
                categoria = "Eventos",
                ubicacion = "Estadio Nacional, Lima",
                imagenResId = R.drawable.ceremonia_apertura  // ← AGREGADO
            ),
            ItemRecomendado(
                id = 4,
                titulo = "Competencia de Atletismo",
                subtitulo = "Atletismo",
                categoria = "Eventos",
                ubicacion = "Villa Deportiva Nacional",
                imagenResId = R.drawable.competencia_atletismo  // ← AGREGADO
            )
        )
    }

    // Filtrar items según búsqueda
    val itemsFiltrados = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            todosLosItems
        } else {
            todosLosItems.filter { item ->
                item.titulo.contains(searchQuery, ignoreCase = true) ||
                        item.subtitulo.contains(searchQuery, ignoreCase = true) ||
                        item.categoria.contains(searchQuery, ignoreCase = true) ||
                        item.ubicacion.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "TravelMarket",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Abrir drawer */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.White
                        )
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
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { navController.navigate(Screen.Inicio.route) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2196F3),
                        selectedTextColor = Color(0xFF2196F3)
                    )
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
                    selected = false,
                    onClick = { navController.navigate(Screen.Perfil.route) }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Barra de búsqueda
            item {
                SearchBar(
                    placeholder = "Buscar por nombre o categoría...",
                    onSearch = { query ->
                        searchQuery = query
                    }
                )
            }

            // Categorías en FILA HORIZONTAL
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CategoryCard(
                        title = "Lugares",
                        icon = Icons.Default.LocationOn,
                        backgroundColor = Color(0xFF2196F3),
                        count = "3 disponibles",
                        onClick = {
                            navController.navigate(Screen.Lugares.route)
                        }
                    )
                    CategoryCard(
                        title = "Eventos",
                        icon = Icons.Default.Event,
                        backgroundColor = Color(0xFF9C27B0),
                        count = "3 disponibles",
                        onClick = {
                            navController.navigate(Screen.Eventos.route)
                        }
                    )
                    CategoryCard(
                        title = "Gastronomía",
                        icon = Icons.Default.Restaurant,
                        backgroundColor = Color(0xFFFF5722),
                        count = "2 disponibles",
                        onClick = {
                            navController.navigate(Screen.Gastronomia.route)
                        }
                    )
                    CategoryCard(
                        title = "Transporte",
                        icon = Icons.Default.DirectionsBus,
                        backgroundColor = Color(0xFF4CAF50),
                        count = "2 disponibles",
                        onClick = {
                            navController.navigate(Screen.Transporte.route)
                        }
                    )
                }
            }

            // Mostrar contador de resultados si hay búsqueda activa
            if (searchQuery.isNotEmpty()) {
                item {
                    Text(
                        text = if (itemsFiltrados.isEmpty()) {
                            "No se encontraron resultados para \"$searchQuery\""
                        } else {
                            "Se encontraron ${itemsFiltrados.size} resultados"
                        },
                        fontSize = 14.sp,
                        color = Color(0xFF757575),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // Lista de items CON IMÁGENES
            items(itemsFiltrados.size) { index ->
                val item = itemsFiltrados[index]
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ItemCard(
                        id = item.id,
                        titulo = item.titulo,
                        subtitulo = item.subtitulo,
                        ubicacion = item.ubicacion,
                        imagenResId = item.imagenResId,  // ← CAMBIÓ DE imagenUrl a imagenResId
                        onClick = {
                            navController.navigate(Screen.DetalleLugar.createRoute(item.id))
                        }
                    )
                }
            }

            // Mensaje si no hay resultados
            if (itemsFiltrados.isEmpty() && searchQuery.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F5F5)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.SearchOff,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = Color(0xFF9E9E9E)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "No se encontraron resultados",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF757575)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Intenta con otros términos de búsqueda",
                                fontSize = 14.sp,
                                color = Color(0xFF9E9E9E)
                            )
                        }
                    }
                }
            }
        }
    }
}
