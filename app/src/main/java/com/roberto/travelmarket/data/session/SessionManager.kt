package com.roberto.travelmarket.data.session


import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "TravelMarketSession",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun guardarSesion(usuarioId: Int) {
        prefs.edit().apply {
            putInt(KEY_USER_ID, usuarioId)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun obtenerUsuarioId(): Int {
        return prefs.getInt(KEY_USER_ID, -1)
    }

    fun estaLogueado(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun cerrarSesion() {
        prefs.edit().clear().apply()
    }
}
