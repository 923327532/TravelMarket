package com.roberto.travelmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roberto.travelmarket.data.db.AppDatabase
import com.roberto.travelmarket.data.model.Usuario
import com.roberto.travelmarket.data.repository.UsuarioRepository
import com.roberto.travelmarket.data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository
    private val sessionManager: SessionManager = SessionManager(application)

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual.asStateFlow()

    init {
        val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)

        // Cargar usuario si hay sesión activa
        if (sessionManager.estaLogueado()) {
            cargarUsuarioActual()
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                // Validaciones
                if (email.isBlank() || password.isBlank()) {
                    _authState.value = AuthState.Error("Por favor completa todos los campos")
                    return@launch
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    _authState.value = AuthState.Error("Email inválido")
                    return@launch
                }

                // Intentar login
                val usuario = repository.login(email, password)

                if (usuario != null) {
                    sessionManager.guardarSesion(usuario.id)
                    _usuarioActual.value = usuario
                    _authState.value = AuthState.Success("Bienvenido ${usuario.nombreCompleto}")
                } else {
                    _authState.value = AuthState.Error("Email o contraseña incorrectos")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Error al iniciar sesión: ${e.message}")
            }
        }
    }

    fun registro(nombreCompleto: String, email: String, telefono: String, password: String, confirmarPassword: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                // Validaciones
                if (nombreCompleto.isBlank() || email.isBlank() || password.isBlank()) {
                    _authState.value = AuthState.Error("Por favor completa todos los campos obligatorios")
                    return@launch
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    _authState.value = AuthState.Error("Email inválido")
                    return@launch
                }

                if (password.length < 6) {
                    _authState.value = AuthState.Error("La contraseña debe tener mínimo 6 caracteres")
                    return@launch
                }

                if (password != confirmarPassword) {
                    _authState.value = AuthState.Error("Las contraseñas no coinciden")
                    return@launch
                }

                // Verificar si el email ya existe
                val usuarioExistente = repository.getUsuarioByEmail(email)
                if (usuarioExistente != null) {
                    _authState.value = AuthState.Error("Este email ya está registrado")
                    return@launch
                }

                // Crear usuario
                val fechaActual = SimpleDateFormat("dd MMMM yyyy", Locale("es", "ES")).format(Date())
                val nuevoUsuario = Usuario(
                    nombreCompleto = nombreCompleto,
                    email = email,
                    telefono = telefono,
                    password = password,
                    fechaRegistro = fechaActual
                )

                val usuarioId = repository.registrarUsuario(nuevoUsuario)

                if (usuarioId > 0) {
                    sessionManager.guardarSesion(usuarioId.toInt())
                    _usuarioActual.value = nuevoUsuario.copy(id = usuarioId.toInt())
                    _authState.value = AuthState.Success("Cuenta creada exitosamente")
                } else {
                    _authState.value = AuthState.Error("Error al crear la cuenta")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Error al registrar: ${e.message}")
            }
        }
    }

    fun cerrarSesion() {
        sessionManager.cerrarSesion()
        _usuarioActual.value = null
        _authState.value = AuthState.Idle
    }

    fun estaLogueado(): Boolean {
        return sessionManager.estaLogueado()
    }

    private fun cargarUsuarioActual() {
        viewModelScope.launch {
            try {
                val usuarioId = sessionManager.obtenerUsuarioId()
                if (usuarioId != -1) {
                    val usuario = repository.getUsuarioById(usuarioId)
                    _usuarioActual.value = usuario
                }
            } catch (e: Exception) {
                // Manejar error silenciosamente
            }
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
}
