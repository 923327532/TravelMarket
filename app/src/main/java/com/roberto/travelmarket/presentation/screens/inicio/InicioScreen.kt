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
import kotlinx.coroutines.launch
import com.roberto.travelmarket.R
import com.roberto.travelmarket.presentation.components.AppNavigationDrawer
import com.roberto.travelmarket.presentation.components.CategoryCard
import com.roberto.travelmarket.presentation.components.ItemCard
import com.roberto.travelmarket.presentation.components.SearchBar
import com.roberto.travelmarket.presentation.navigation.Screen

data class ItemRecomendado(
    val id: Int,
    val titulo: String,
    val subtitulo: String,
    val categoria: String,
    val ubicacion: String,
    val imagenResId: Int,
    val tipoCategoria: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val todosLosItems = remember {
        listOf(
            ItemRecomendado(
                id = 1,
                titulo = "Parque de la Exposición",
                subtitulo = "Parque y áreas verdes",
                categoria = "Lugares",
                ubicacion = "Av. 28 de Julio, Lima",
                imagenResId = R.drawable.parque_exposicion,
                tipoCategoria = "lugar"
            ),
            ItemRecomendado(
                id = 2,
                titulo = "Circuito Mágico del Agua",
                subtitulo = "Fuentes y espectáculos",
                categoria = "Lugares",
                ubicacion = "Parque de la Reserva, Lima",
                imagenResId = R.drawable.circuito_magico_agua,
                tipoCategoria = "lugar"
            ),
            ItemRecomendado(
                id = 3,
                titulo = "Museo Larco",
                subtitulo = "Museo histórico",
                categoria = "Lugares",
                ubicacion = "Av. Bolívar 1515, Pueblo Libre",
                imagenResId = R.drawable.museo_larco,
                tipoCategoria = "lugar"
            ),
            ItemRecomendado(
                id = 4,
                titulo = "Ceremonia de Apertura",
                subtitulo = "Inauguración",
                categoria = "Eventos",
                ubicacion = "Estadio Nacional, Lima",
                imagenResId = R.drawable.ceremonia_apertura,
                tipoCategoria = "evento"
            ),
            ItemRecomendado(
                id = 5,
                titulo = "Competencia de Atletismo",
                subtitulo = "Atletismo",
                categoria = "Eventos",
                ubicacion = "Villa Deportiva Nacional",
                imagenResId = R.drawable.competencia_atletismo,
                tipoCategoria = "evento"
            ),
            ItemRecomendado(
                id = 6,
                titulo = "Gala de Clausura",
                subtitulo = "Clausura",
                categoria = "Eventos",
                ubicacion = "Estadio Nacional",
                imagenResId = R.drawable.gala_clausura,
                tipoCategoria = "evento"
            ),
            ItemRecomendado(
                id = 7,
                titulo = "Central Restaurante",
                subtitulo = "Alta cocina peruana",
                categoria = "Gastronomía",
                ubicacion = "Barranco, Lima",
                imagenResId = R.drawable.central_restaurante,
                tipoCategoria = "gastronomia"
            ),
            ItemRecomendado(
                id = 8,
                titulo = "Mercado de Surquillo",
                subtitulo = "Mercado local",
                categoria = "Gastronomía",
                ubicacion = "Surquillo, Lima",
                imagenResId = R.drawable.mercado_surquillo,
                tipoCategoria = "gastronomia"
            ),
            ItemRecomendado(
                id = 9,
                titulo = "Metropolitano",
                subtitulo = "BRT Lima",
                categoria = "Transporte",
                ubicacion = "Varias estaciones en Lima",
                imagenResId = R.drawable.metropolitano,
                tipoCategoria = "transporte"
            ),
            ItemRecomendado(
                id = 10,
                titulo = "Metro de Lima",
                subtitulo = "Línea 1",
                categoria = "Transporte",
                ubicacion = "Villa El Salvador - SJL",
                imagenResId = R.drawable.metro_lima,
                tipoCategoria = "transporte"
            )
        )
    }

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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppNavigationDrawer(
                navController = navController,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
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
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
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
                    tonalElevation = 0.dp
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = true,
                        onClick = { navController.navigate(Screen.Inicio.route) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF2196F3),
                            selectedTextColor = Color(0xFF2196F3),
                            indicatorColor = Color(0xFFE3F2FD)
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
            },
            containerColor = Color(0xFFF5F5F5)
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(vertical = 0.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp),
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
                                onSearch = { query ->
                                    searchQuery = query
                                }
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                CategoryCard(
                                    title = "Lugares",
                                    icon = Icons.Default.LocationOn,
                                    backgroundColor = Color(0xFF2196F3),
                                    onClick = {
                                        navController.navigate(Screen.Lugares.route)
                                    }
                                )
                                CategoryCard(
                                    title = "Eventos",
                                    icon = Icons.Default.Event,
                                    backgroundColor = Color(0xFF9C27B0),
                                    onClick = {
                                        navController.navigate(Screen.Eventos.route)
                                    }
                                )
                                CategoryCard(
                                    title = "Gastronomía",
                                    icon = Icons.Default.Restaurant,
                                    backgroundColor = Color(0xFFFF5722),
                                    onClick = {
                                        navController.navigate(Screen.Gastronomia.route)
                                    }
                                )
                                CategoryCard(
                                    title = "Transporte",
                                    icon = Icons.Default.DirectionsBus,
                                    backgroundColor = Color(0xFF4CAF50),
                                    onClick = {
                                        navController.navigate(Screen.Transporte.route)
                                    }
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
                    Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                        ItemCard(
                            id = item.id,
                            titulo = item.titulo,
                            subtitulo = item.subtitulo,
                            imagenResId = item.imagenResId,
                            onClick = {
                                when (item.tipoCategoria) {
                                    "lugar" -> navController.navigate(Screen.DetalleLugar.createRoute(item.id))
                                    "evento" -> navController.navigate(Screen.DetalleEvento.createRoute(item.id))
                                    "gastronomia" -> navController.navigate(Screen.DetalleGastronomia.createRoute(item.id))
                                    "transporte" -> navController.navigate(Screen.DetalleTransporte.createRoute(item.id))
                                }
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
}
