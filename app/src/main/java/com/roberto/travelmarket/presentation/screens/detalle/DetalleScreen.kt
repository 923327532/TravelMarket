package com.roberto.travelmarket.presentation.screens.detalle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLugarScreen(
    lugarId: Int,
    navController: NavController
) {
    // Datos mock según el ID
    val lugares = mapOf(
        0 to Triple("Parque de la Exposición", "Av. 28 de Julio, Lima", R.drawable.parque_exposicion),
        1 to Triple("Circuito Mágico del Agua", "Parque de la Reserva, Lima", R.drawable.circuito_magico_agua),
        2 to Triple("Museo Larco", "Av. Bolívar 1515, Pueblo Libre", R.drawable.museo_larco),
        3 to Triple("Ceremonia de Apertura", "Estadio Nacional, Lima", R.drawable.ceremonia_apertura),
        4 to Triple("Competencia de Atletismo", "Villa Deportiva Nacional", R.drawable.competencia_atletismo)
    )

    val (titulo, ubicacion, imagenResId) = lugares[lugarId]
        ?: Triple("Parque de la Exposición", "Av. 28 de Julio, Lima", R.drawable.parque_exposicion)

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
                    IconButton(onClick = { /* Agregar a favoritos */ }) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = Color.White
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
        ) {
            // Imagen principal (ACTUALIZADO)
            Image(
                painter = painterResource(id = imagenResId),
                contentDescription = titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Título (ACTUALIZADO)
                Text(
                    text = titulo,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ubicación (ACTUALIZADO)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Ubicación",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = ubicacion,
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción
                Text(
                    text = "Espacio cultural con exposiciones y eventos deportivos. Un lugar emblemático de Lima que combina historia, cultura y espacios verdes para disfrutar en familia o con amigos.",
                    fontSize = 14.sp,
                    color = Color(0xFF212121),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botones de acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { /* Agregar a favoritos */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Agregar a Favoritos")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { /* Ver en mapa */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF2196F3)
                    )
                ) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ver en mapa")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Información adicional
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Información adicional",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
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
