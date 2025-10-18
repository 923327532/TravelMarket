package com.roberto.travelmarket.presentation.screens.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class ItemRecomendado(
    val id: Int,
    val titulo: String,
    val subtitulo: String,
    val categoria: String,
    val imagenResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    val todosLosItems = remember {
        listOf(
            ItemRecomendado(0, "Parque de la Exposición", "Parque y áreas verdes", "Lugares", R.drawable.parque_exposicion),
            ItemRecomendado(1, "Circuito Mágico del Agua", "Fuentes y espectáculos", "Lugares", R.drawable.circuito_magico_agua),
            ItemRecomendado(2, "Museo Larco", "Museo histórico", "Lugares", R.drawable.museo_larco),
            ItemRecomendado(3, "Ceremonia de Apertura", "Inauguración", "Eventos", R.drawable.ceremonia_apertura),
            ItemRecomendado(4, "Competencia de Atletismo", "Atletismo", "Eventos", R.drawable.competencia_atletismo)
        )
    }

    val itemsFiltrados = remember(searchQuery) {
        if (searchQuery.isEmpty()) todosLosItems else todosLosItems.filter { item ->
            item.titulo.contains(searchQuery, true) ||
                    item.subtitulo.contains(searchQuery, true) ||
                    item.categoria.contains(searchQuery, true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "TravelMarket",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                modifier = Modifier.height(64.dp)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 10.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio", fontSize = 12.sp) },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1E88E5),
                        selectedTextColor = Color(0xFF1E88E5)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Explorar") },
                    label = { Text("Explorar", fontSize = 12.sp) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Explorar.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
                    label = { Text("Favoritos", fontSize = 12.sp) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Favoritos.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil", fontSize = 12.sp) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Perfil.route) }
                )
            }
        },
        containerColor = Color(0xFFF9FAFB)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        SearchBar(
                            placeholder = "Buscar por nombre o categoría...",
                            onSearch = { query -> searchQuery = query }
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CategoryCard(
                                title = "Lugares",
                                icon = Icons.Default.LocationOn,
                                backgroundColor = Color(0xFF1E88E5),
                                count = "",
                                onClick = { navController.navigate(Screen.Lugares.route) }
                            )
                            CategoryCard(
                                title = "Eventos",
                                icon = Icons.Default.Event,
                                backgroundColor = Color(0xFF9C27B0),
                                count = "",
                                onClick = { navController.navigate(Screen.Eventos.route) }
                            )
                            CategoryCard(
                                title = "Gastronomía",
                                icon = Icons.Default.Restaurant,
                                backgroundColor = Color(0xFFFF5722),
                                count = "",
                                onClick = { navController.navigate(Screen.Gastronomia.route) }
                            )
                            CategoryCard(
                                title = "Transporte",
                                icon = Icons.Default.DirectionsBus,
                                backgroundColor = Color(0xFF4CAF50),
                                count = "",
                                onClick = { navController.navigate(Screen.Transporte.route) }
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

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
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            items(itemsFiltrados.size) { index ->
                val item = itemsFiltrados[index]
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                    ItemCard(
                        id = item.id,
                        titulo = item.titulo,
                        subtitulo = item.subtitulo,
                        imagenResId = item.imagenResId,
                        onClick = {
                            navController.navigate(Screen.DetalleLugar.createRoute(item.id))
                        }
                    )
                }
            }

            if (itemsFiltrados.isEmpty() && searchQuery.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
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
