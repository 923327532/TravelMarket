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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.roberto.travelmarket.presentation.navigation.Screen
import com.roberto.travelmarket.viewmodel.AuthState
import com.roberto.travelmarket.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var nombreCompleto by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmarPasswordVisible by remember { mutableStateOf(false) }
    var aceptaTerminos by remember { mutableStateOf(false) }

    val authState by authViewModel.authState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // ✅ Estado para mostrar el dialog
    var showSuccessDialog by remember { mutableStateOf(false) }

    // ✅ EFECTO 1: Manejar AuthState
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                authViewModel.cerrarSesion()
                authViewModel.resetAuthState()
                showSuccessDialog = true
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

    // ✅ EFECTO 2: Redirección automática cuando se muestra el dialog
    LaunchedEffect(showSuccessDialog) {
        if (showSuccessDialog) {
            kotlinx.coroutines.delay(2500)
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Volver", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3)
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Public,
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Crear Cuenta",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        "Únete a TravelMarket",
                        fontSize = 12.sp,
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
                // Nombre
                Text(
                    "Nombre Completo",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = nombreCompleto,
                    onValueChange = { nombreCompleto = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Juan Pérez", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(20.dp))
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Email
                Text(
                    "Correo Electrónico",
                    fontSize = 13.sp,
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
                        Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(20.dp))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Teléfono
                Text(
                    "Teléfono (Opcional)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("+51 999 999 999", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(20.dp))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Contraseña
                Text(
                    "Contraseña",
                    fontSize = 13.sp,
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
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(20.dp))
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

                Text(
                    "Mínimo 6 caracteres",
                    fontSize = 11.sp,
                    color = Color(0xFF9E9E9E),
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Confirmar Contraseña
                Text(
                    "Confirmar Contraseña",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = confirmarPassword,
                    onValueChange = { confirmarPassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("••••••••", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF9E9E9E), modifier = Modifier.size(20.dp))
                    },
                    trailingIcon = {
                        IconButton(onClick = { confirmarPasswordVisible = !confirmarPasswordVisible }) {
                            Icon(
                                if (confirmarPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color(0xFF9E9E9E),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    visualTransformation = if (confirmarPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2196F3),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Términos
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = aceptaTerminos,
                        onCheckedChange = { aceptaTerminos = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF2196F3)
                        )
                    )
                    Text(
                        "Acepto los términos y condiciones y la política de privacidad",
                        fontSize = 12.sp,
                        color = Color(0xFF757575),
                        lineHeight = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón Crear Cuenta
                Button(
                    onClick = {
                        if (aceptaTerminos) {
                            authViewModel.registro(
                                nombreCompleto.trim(),
                                email.trim(),
                                telefono.trim(),
                                password,
                                confirmarPassword
                            )
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Debes aceptar los términos y condiciones")
                            }
                        }
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
                            "Crear Cuenta",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ya tienes cuenta
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "¿Ya tienes una cuenta?",
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                    TextButton(onClick = { navController.navigateUp() }) {
                        Text(
                            "Iniciar Sesión",
                            fontSize = 14.sp,
                            color = Color(0xFF2196F3),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }

    // ✅ DIALOG DE ÉXITO (fuera del Scaffold)
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = { },
            icon = {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF4CAF50), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            },
            title = {
                Text(
                    "¡Registro Exitoso!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    "Tu cuenta ha sido creada correctamente.\nSerás redirigido al inicio de sesión...",
                    fontSize = 15.sp,
                    color = Color(0xFF757575),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        )
    }
}
