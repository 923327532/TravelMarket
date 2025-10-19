package com.roberto.travelmarket.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen
import com.roberto.travelmarket.viewmodel.AuthState
import com.roberto.travelmarket.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val authState by authViewModel.authState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observar cambios de estado
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar((authState as AuthState.Success).message)
                }
                authViewModel.resetAuthState()
                navController.navigate(Screen.Inicio.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
            is AuthState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar((authState as AuthState.Error).message)
                }
                authViewModel.resetAuthState()
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            // Header con gradiente azul
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(vertical = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Public,
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "TravelMarket",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        "Perú - País de Maravillas",
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            // Formulario
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    "Iniciar Sesión",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )

                Text(
                    "Ingresa tus credenciales para continuar",
                    fontSize = 14.sp,
                    color = Color(0xFF757575),
                    modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
                )

                // Email
                Text(
                    "Correo Electrónico",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("ejemplo@correo.com", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = null,
                            tint = Color(0xFF9E9E9E),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contraseña
                Text(
                    "Contraseña",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("••••••••", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF9E9E9E),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color(0xFF9E9E9E),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Login
                Button(
                    onClick = {
                        authViewModel.login(email.trim(), password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = authState !is AuthState.Loading
                ) {
                    if (authState is AuthState.Loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            "Iniciar Sesión",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ✅ REGISTRO PROFESIONAL (ESTILO INSTAGRAM, FACEBOOK, ETC.)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "¿No tienes cuenta?",
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                    TextButton(onClick = { navController.navigate(Screen.Registro.route) }) {
                        Text(
                            "Regístrate",
                            fontSize = 14.sp,
                            color = Color(0xFF2196F3),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
