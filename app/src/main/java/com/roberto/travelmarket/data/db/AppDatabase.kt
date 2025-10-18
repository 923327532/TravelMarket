package com.roberto.travelmarket.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.roberto.travelmarket.R
import com.roberto.travelmarket.data.dao.*
import com.roberto.travelmarket.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Lugar::class, Evento::class, Gastronomia::class, Transporte::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lugarDao(): LugarDao
    abstract fun eventoDao(): EventoDao
    abstract fun gastronomiaDao(): GastronomiaDao
    abstract fun transporteDao(): TransporteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "travel_market_database"
                )
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val context: Context
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database, context)
                    }
                }
            }
        }

        suspend fun populateDatabase(database: AppDatabase, context: Context) {
            val lugarDao = database.lugarDao()
            val eventoDao = database.eventoDao()
            val gastronomiaDao = database.gastronomiaDao()
            val transporteDao = database.transporteDao()

            // Limpiar tablas
            lugarDao.deleteAllLugares()
            eventoDao.deleteAllEventos()
            gastronomiaDao.deleteAllGastronomia()
            transporteDao.deleteAllTransporte()

            // ==================== INSERTAR LUGARES ====================
            val lugares = listOf(
                Lugar(
                    id = 1,
                    nombre = "Parque de la Exposición",
                    descripcion = "Parque y áreas verdes",
                    ubicacion = "Av. 28 de Julio, Lima",
                    latitud = -12.062174252522565,
                    longitud = -77.03665759053403,
                    imagenResId = R.drawable.parque_exposicion
                ),
                Lugar(
                    id = 2,
                    nombre = "Circuito Mágico del Agua",
                    descripcion = "Fuentes y espectáculos",
                    ubicacion = "Centro de Lima",
                    latitud = -12.070207588471881,
                    longitud = -77.0330425040265,
                    imagenResId = R.drawable.circuito_magico_agua
                ),
                Lugar(
                    id = 3,
                    nombre = "Museo Larco",
                    descripcion = "Museo histórico",
                    ubicacion = "Pueblo Libre, Lima",
                    latitud = -12.072390631733752,
                    longitud = -77.07110716354875,
                    imagenResId = R.drawable.museo_larco
                )
            )
            lugarDao.insertAllLugares(lugares)

            // ==================== INSERTAR EVENTOS ====================
            val eventos = listOf(
                Evento(
                    id = 4,
                    nombre = "Ceremonia de Apertura",
                    descripcion = "Inauguración",
                    ubicacion = "Estadio Nacional",
                    fecha = "26 de Julio, 2025",
                    latitud = -12.086670520057341,
                    longitud = -77.00188379264574,
                    imagenResId = R.drawable.ceremonia_apertura
                ),
                Evento(
                    id = 5,
                    nombre = "Competencia de Atletismo",
                    descripcion = "Atletismo",
                    ubicacion = "Villa Deportiva Nacional",
                    fecha = "28 de Julio, 2025",
                    latitud = -12.082123963527396,
                    longitud = -77.00420245395524,
                    imagenResId = R.drawable.competencia_atletismo
                ),
                Evento(
                    id = 6,
                    nombre = "Gala de Clausura",
                    descripcion = "Clausura",
                    ubicacion = "Estadio Nacional",
                    fecha = "11 de Agosto, 2025",
                    latitud = -12.086670520057341,
                    longitud = -77.00188379264574,
                    imagenResId = R.drawable.gala_clausura
                )
            )
            eventoDao.insertAllEventos(eventos)

            // ==================== INSERTAR GASTRONOMÍA ====================
            val gastronomias = listOf(
                Gastronomia(
                    id = 7,
                    nombre = "Central Restaurante",
                    descripcion = "Alta cocina peruana",
                    ubicacion = "Barranco, Lima",
                    latitud = -12.152636674195254,
                    longitud = -77.02246545289982,
                    imagenResId = R.drawable.central_restaurante
                ),
                Gastronomia(
                    id = 8,
                    nombre = "Mercado de Surquillo",
                    descripcion = "Mercado local",
                    ubicacion = "Surquillo, Lima",
                    latitud = -12.109647045396262,
                    longitud = -77.02484983921194,
                    imagenResId = R.drawable.mercado_surquillo
                )
            )
            gastronomiaDao.insertAllGastronomia(gastronomias)

            // ==================== INSERTAR TRANSPORTE ====================
            val transportes = listOf(
                Transporte(
                    id = 9,
                    nombre = "Metro",
                    descripcion = "BRT Lima",
                    ubicacion = "Varias estaciones en Lima",
                    latitud = -12.113192674652272,
                    longitud = -77.02581543443543,
                    imagenResId = R.drawable.metropolitano
                ),
                Transporte(
                    id = 10,
                    nombre = "Linea2",
                    descripcion = "Línea 1",
                    ubicacion = "Villa El Salvador - SJL",
                    latitud = -12.086664976060327,
                    longitud = -77.00394956173317,
                    imagenResId = R.drawable.metro_lima
                )
            )
            transporteDao.insertAllTransporte(transportes)
        }
    }
}
