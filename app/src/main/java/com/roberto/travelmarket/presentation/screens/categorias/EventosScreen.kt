package com.roberto.travelmarket.presentation.screens.categorias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.components.ItemCard
import com.roberto.travelmarket.presentation.navigation.Screen
import com.roberto.travelmarket.viewmodel.EventoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventosScreen(
    navController: NavController,
    viewModel: EventoViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    var showFilterMenu by remember { mutableStateOf(false) }
    var selectedUbicacion by remember { mutableStateOf("Todas las ubicaciones") }

    val eventos by viewModel.allEventos.collectAsState()

    val eventosFiltrados = remember(searchQuery, eventos, selectedUbicacion) {
        var resultado = eventos

        if (searchQuery.isNotEmpty()) {
            resultado = resultado.filter { evento ->
                evento.nombre.contains(searchQuery, ignoreCase = true) ||
                        evento.descripcion.contains(searchQuery, ignoreCase = true) ||
                        evento.ubicacion.contains(searchQuery, ignoreCase = true)
            }
        }

        if (selectedUbicacion != "Todas las ubicaciones") {
            resultado = resultado.filter { evento ->
                evento.ubicacion.contains(selectedUbicacion, ignoreCase = true)
            }
        }

        resultado
    }

    val ubicaciones = remember(eventos) {
        listOf("Todas las ubicaciones") + eventos.map { it.ubicacion }.distinct().sorted()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Eventos",
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
                .background(Color(0xFFF5F5F5))
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                placeholder = {
                    Text(
                        "Buscar por nombre...",
                        fontSize = 14.sp,
                        color = Color(0xFF9E9E9E)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color(0xFF757575),
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Limpiar",
                                tint = Color(0xFF757575),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2196F3),
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
            ) {
                OutlinedButton(
                    onClick = { showFilterMenu = !showFilterMenu },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF757575)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) {
                    Icon(
                        Icons.Default.FilterList,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        selectedUbicacion,
                        fontSize = 13.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Start
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }

                DropdownMenu(
                    expanded = showFilterMenu,
                    onDismissRequest = { showFilterMenu = false },
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    ubicaciones.forEach { ubicacion ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    ubicacion,
                                    fontSize = 14.sp,
                                    color = if (ubicacion == selectedUbicacion)
                                        Color(0xFF2196F3)
                                    else
                                        Color(0xFF424242)
                                )
                            },
                            onClick = {
                                selectedUbicacion = ubicacion
                                showFilterMenu = false
                            },
                            leadingIcon = {
                                if (ubicacion == selectedUbicacion) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color(0xFF2196F3),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        )
                    }
                }
            }

            Text(
                text = "${eventosFiltrados.size} resultados encontrados",
                fontSize = 13.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(eventosFiltrados.size) { index ->
                    val evento = eventosFiltrados[index]
                    ItemCard(
                        id = evento.id,
                        titulo = evento.nombre,
                        subtitulo = evento.descripcion,
                        ubicacion = evento.ubicacion,
                        imagenResId = evento.imagenResId,
                        onClick = {
                            navController.navigate(Screen.DetalleEvento.createRoute(evento.id))
                        }
                    )
                }

                if (eventosFiltrados.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Default.SearchOff,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    tint = Color(0xFF9E9E9E)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    "No se encontraron eventos",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF757575)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
