package com.roberto.travelmarket.presentation.screens.tour

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.data.db.PlatoTipico
import com.roberto.travelmarket.data.db.PlatosTipicosData
import com.roberto.travelmarket.presentation.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourGastronomicoScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedPlato by remember { mutableStateOf<PlatoTipico?>(null) }

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
                        Text("Tour Gastronómico del Perú", color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
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
                        onClick = { navController.navigate(Screen.Explorar.route) },
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
                    .background(Color.White),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Header
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            "🇵🇪 Sabores del Perú",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF212121)
                        )
                        Text(
                            "Descubre los platos más representativos de cada región",
                            fontSize = 14.sp,
                            color = Color(0xFF757575),
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // COSTA
                item {
                    RegionSection(
                        region = "Costa",
                        emoji = "🌊",
                        color = Color(0xFF2196F3),
                        platos = PlatosTipicosData.platosCosta,
                        onPlatoClick = { selectedPlato = it }
                    )
                }

                // SIERRA
                item {
                    RegionSection(
                        region = "Sierra",
                        emoji = "⛰️",
                        color = Color(0xFF8D6E63),
                        platos = PlatosTipicosData.platosSierra,
                        onPlatoClick = { selectedPlato = it }
                    )
                }

                // SELVA
                item {
                    RegionSection(
                        region = "Selva",
                        emoji = "🌴",
                        color = Color(0xFF4CAF50),
                        platos = PlatosTipicosData.platosSelva,
                        onPlatoClick = { selectedPlato = it }
                    )
                }
            }
        }

        // Detalle plato
        selectedPlato?.let { plato ->
            PlatoDetalleDialog(
                plato = plato,
                onDismiss = { selectedPlato = null }
            )
        }
    }
}

@Composable
fun RegionSection(
    region: String,
    emoji: String,
    color: Color,
    platos: List<PlatoTipico>,
    onPlatoClick: (PlatoTipico) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    region,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
                Text(
                    "${platos.size} platos típicos",
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(platos) { plato ->
                PlatoCard(plato = plato, color = color, onClick = { onPlatoClick(plato) })
            }
        }
    }
}

@Composable
fun PlatoCard(plato: PlatoTipico, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(280.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column {
            Image(
                painter = painterResource(id = plato.imagenResId),
                contentDescription = plato.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Surface(
                    color = color.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        plato.region,
                        fontSize = 10.sp,
                        color = color,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    plato.nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )

                Text(
                    plato.descripcion,
                    fontSize = 13.sp,
                    color = Color(0xFF757575),
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            plato.ciudad,
                            fontSize = 12.sp,
                            color = Color(0xFF757575)
                        )
                    }

                    Text(
                        plato.precio,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Ver detalles →",
                    fontSize = 13.sp,
                    color = color,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun PlatoDetalleDialog(plato: PlatoTipico, onDismiss: () -> Unit) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        },
        title = {
            Text(
                plato.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = plato.imagenResId),
                    contentDescription = plato.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(plato.descripcion, fontSize = 14.sp, color = Color(0xFF757575))

                HorizontalDivider()

                InfoRow(Icons.Default.Restaurant, "Restaurante", plato.restaurante)
                InfoRow(Icons.Default.LocationOn, "Dirección", plato.direccion)
                InfoRow(Icons.Default.LocationCity, "Ciudad", plato.ciudad)
                InfoRow(Icons.Default.AttachMoney, "Precio", plato.precio)

                // Botón Google Maps
                Button(
                    onClick = {
                        val uri = "geo:${plato.latitud},${plato.longitud}?q=${plato.latitud},${plato.longitud}(${plato.restaurante})"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Icon(Icons.Default.Map, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Abrir en Google Maps")
                }
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color(0xFF9E9E9E))
            Text(value, fontSize = 13.sp, color = Color(0xFF212121), fontWeight = FontWeight.Medium)
        }
    }
}
