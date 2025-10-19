package com.roberto.travelmarket.presentation.screens.detalle

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.roberto.travelmarket.viewmodel.FavoritosViewModel
import com.roberto.travelmarket.viewmodel.ItemFavorito
import com.roberto.travelmarket.viewmodel.LugarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLugarScreen(
    lugarId: Int,
    navController: NavController,
    favoritosViewModel: FavoritosViewModel,
    viewModel: LugarViewModel = viewModel()
) {
    val context = LocalContext.current

    val lugares by viewModel.allLugares.collectAsState()
    val lugar = remember(lugares, lugarId) {
        lugares.find { it.id == lugarId } ?: lugares.firstOrNull()
    }

    val favoritos by favoritosViewModel.favoritos.collectAsState()
    val esFavorito = favoritos.any { it.id == lugarId && it.categoria == "Lugares" }

    lugar?.let { lugarData ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Detalle",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if (esFavorito) {
                                favoritosViewModel.quitarFavorito(lugarId, "Lugares")
                            } else {
                                favoritosViewModel.agregarFavorito(
                                    ItemFavorito(
                                        id = lugarId,
                                        titulo = lugarData.nombre,
                                        subtitulo = "Parque y áreas verdes",
                                        categoria = "Lugares",
                                        imagenResId = lugarData.imagenResId
                                    )
                                )
                            }
                        }) {
                            Icon(
                                if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (esFavorito) Color.Red else Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF2196F3)
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFF5F5F5))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Image(
                        painter = painterResource(id = lugarData.imagenResId),
                        contentDescription = lugarData.nombre,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFF9800)
                    ) {
                        Text(
                            text = "Lugares",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = lugarData.nombre,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF212121)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFF2196F3),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = lugarData.ubicacion,
                                fontSize = 14.sp,
                                color = Color(0xFF757575)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = lugarData.descripcion,
                            fontSize = 15.sp,
                            color = Color(0xFF424242),
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (esFavorito) {
                                    favoritosViewModel.quitarFavorito(lugarId, "Lugares")
                                } else {
                                    favoritosViewModel.agregarFavorito(
                                        ItemFavorito(
                                            id = lugarId,
                                            titulo = lugarData.nombre,
                                            subtitulo = "Parque y áreas verdes",
                                            categoria = "Lugares",
                                            imagenResId = lugarData.imagenResId
                                        )
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (esFavorito) Color(0xFFE57373) else Color(0xFF2196F3)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (esFavorito) "Quitar de Favoritos" else "Agregar a Favoritos",
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedButton(
                            onClick = {
                                val gmmIntentUri = Uri.parse("geo:${lugarData.latitud},${lugarData.longitud}?q=${lugarData.latitud},${lugarData.longitud}(${lugarData.nombre})")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                mapIntent.setPackage("com.google.android.apps.maps")
                                context.startActivity(mapIntent)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFF2196F3)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                Icons.Default.Place,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Ver en mapa",
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    "Información adicional",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF2196F3)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    "Esta información está disponible durante los Juegos Panamericanos. Para más detalles, consulta la aplicación oficial del evento.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF757575),
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
