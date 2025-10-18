package com.roberto.travelmarket.presentation.screens.detalle

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.navigation.NavController
import com.roberto.travelmarket.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLugarScreen(
    lugarId: Int,
    navController: NavController
) {
    // Estado para manejar favoritos
    var isFavorito by remember { mutableStateOf(false) }

    // Contexto para abrir Google Maps
    val context = LocalContext.current

    // Datos mock con COORDENADAS REALES
    val lugares = mapOf(
        0 to LugarCompleto(
            titulo = "Parque de la Exposición",
            ubicacion = "Av. 28 de Julio, Lima",
            imagenResId = R.drawable.parque_exposicion,
            latitud = -12.062174252522565,
            longitud = -77.03665759053403
        ),
        1 to LugarCompleto(
            titulo = "Circuito Mágico del Agua",
            ubicacion = "Parque de la Reserva, Lima",
            imagenResId = R.drawable.circuito_magico_agua,
            latitud = -12.070207588471881,
            longitud = -77.0330425040265
        ),
        2 to LugarCompleto(
            titulo = "Museo Larco",
            ubicacion = "Av. Bolívar 1515, Pueblo Libre",
            imagenResId = R.drawable.museo_larco,
            latitud = -12.072390631733752,
            longitud = -77.07110716354875
        ),
        3 to LugarCompleto(
            titulo = "Ceremonia de Apertura",
            ubicacion = "Ministerio de Cultura, Lima",
            imagenResId = R.drawable.ceremonia_apertura,
            latitud = -12.086670520057341,
            longitud = -77.00188379264574
        ),
        4 to LugarCompleto(
            titulo = "Competencia de Atletismo",
            ubicacion = "Villa Deportiva Nacional",
            imagenResId = R.drawable.competencia_atletismo,
            latitud = -12.082123963527396,
            longitud = -77.00420245395524
        )
    )

    val lugarData = lugares[lugarId] ?: lugares[0]!!

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
                        isFavorito = !isFavorito
                    }) {
                        Icon(
                            if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (isFavorito) Color.Red else Color.White
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
            // Imagen principal
            Image(
                painter = painterResource(id = lugarData.imagenResId),
                contentDescription = lugarData.titulo,
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
                // Título
                Text(
                    text = lugarData.titulo,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ubicación
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
                        text = lugarData.ubicacion,
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
                        onClick = {
                            isFavorito = !isFavorito
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFavorito) Color.Red else Color(0xFF2196F3)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isFavorito) "Guardado" else "Agregar a Favoritos")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        // Abrir Google Maps con coordenadas
                        val uri = Uri.parse(
                            "geo:${lugarData.latitud},${lugarData.longitud}?q=${lugarData.latitud},${lugarData.longitud}(${lugarData.titulo})"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")

                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Si no tiene Google Maps, abrir en navegador
                            val webUri = Uri.parse(
                                "https://www.google.com/maps/search/?api=1&query=${lugarData.latitud},${lugarData.longitud}"
                            )
                            context.startActivity(Intent(Intent.ACTION_VIEW, webUri))
                        }
                    },
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

// Data class para almacenar información completa del lugar
data class LugarCompleto(
    val titulo: String,
    val ubicacion: String,
    val imagenResId: Int,
    val latitud: Double,
    val longitud: Double
)
