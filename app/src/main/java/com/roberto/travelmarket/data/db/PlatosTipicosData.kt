package com.roberto.travelmarket.data.db

import com.roberto.travelmarket.R

data class PlatoTipico(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val region: String, // Costa, Sierra, Selva
    val ciudad: String,
    val departamento: String,
    val restaurante: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val imagenResId: Int,
    val precio: String
)

object PlatosTipicosData {

    // 🌊 COSTA - Piura, Lima, Ica
    val platosCosta = listOf(
        // PIURA
        PlatoTipico(
            id = 1,
            nombre = "Ceviche Norteño",
            descripcion = "Ceviche de mero fresco con camote, choclo y cancha serrana",
            region = "Costa",
            ciudad = "Piura",
            departamento = "Piura",
            restaurante = "Cevichería El Chalán",
            direccion = "Av. Grau 532, Piura",
            latitud = -5.1945,
            longitud = -80.6328,
            imagenResId = R.drawable.ceviche_norteno,
            precio = "S/ 35"
        ),
        PlatoTipico(
            id = 2,
            nombre = "Seco de Cabrito",
            descripcion = "Cabrito tierno cocido con chicha de jora y frejoles",
            region = "Costa",
            ciudad = "Piura",
            departamento = "Piura",
            restaurante = "Restaurant Tres Estrellas",
            direccion = "Av. Grau 605, Piura",
            latitud = -5.1952,
            longitud = -80.6334,
            imagenResId = R.drawable.seco_cabrito,
            precio = "S/ 42"
        ),
        PlatoTipico(
            id = 3,
            nombre = "Majado de Yuca",
            descripcion = "Yuca frita machacada con chicharrón de cerdo",
            region = "Costa",
            ciudad = "Piura",
            departamento = "Piura",
            restaurante = "El Rincón Piurano",
            direccion = "Jr. Tacna 234, Piura",
            latitud = -5.1986,
            longitud = -80.6312,
            imagenResId = R.drawable.majado_yuca,
            precio = "S/ 28"
        ),

        // LIMA
        PlatoTipico(
            id = 4,
            nombre = "Ceviche Clásico",
            descripcion = "Pescado fresco marinado en limón con cebolla, ají limo y camote",
            region = "Costa",
            ciudad = "Lima",
            departamento = "Lima",
            restaurante = "La Mar Cebichería",
            direccion = "Av. Mariscal La Mar 770, Miraflores",
            latitud = -12.1195,
            longitud = -77.0348,
            imagenResId = R.drawable.ceviche,
            precio = "S/ 48"
        ),
        PlatoTipico(
            id = 5,
            nombre = "Anticuchos",
            descripcion = "Brochetas de corazón de res marinadas con ají panca y comino",
            region = "Costa",
            ciudad = "Lima",
            departamento = "Lima",
            restaurante = "Grimanesa Vargas",
            direccion = "Jirón Ancash 434, Lima Centro",
            latitud = -12.0464,
            longitud = -77.0428,
            imagenResId = R.drawable.anticuchos,
            precio = "S/ 26"
        ),
        PlatoTipico(
            id = 6,
            nombre = "Causa Limeña",
            descripcion = "Capas de papa amarilla prensada rellena de atún, palta y huevo",
            region = "Costa",
            ciudad = "Lima",
            departamento = "Lima",
            restaurante = "Tanta",
            direccion = "Av. 28 de Julio 888, Miraflores",
            latitud = -12.1299,
            longitud = -77.0257,
            imagenResId = R.drawable.causa,
            precio = "S/ 34"
        ),

        // ICA
        PlatoTipico(
            id = 7,
            nombre = "Pallares con Sopa Seca",
            descripcion = "Pallares guisados acompañados de tallarines al pesto verde",
            region = "Costa",
            ciudad = "Ica",
            departamento = "Ica",
            restaurante = "El Catador",
            direccion = "Av. Grau 219, Ica",
            latitud = -14.0678,
            longitud = -75.7286,
            imagenResId = R.drawable.pallares_sopa_seca,
            precio = "S/ 38"
        ),
        PlatoTipico(
            id = 8,
            nombre = "Carapulcra Chinchana",
            descripcion = "Papa seca guisada con cerdo, maní y ají panca",
            region = "Costa",
            ciudad = "Ica",
            departamento = "Ica",
            restaurante = "Plaza 125",
            direccion = "Jr. Bolívar 255, Ica",
            latitud = -14.0691,
            longitud = -75.7295,
            imagenResId = R.drawable.carapulcra,
            precio = "S/ 36"
        ),
        PlatoTipico(
            id = 9,
            nombre = "Morusa Iqueña",
            descripcion = "Frejol canario guisado con carne de cerdo y yuca",
            region = "Costa",
            ciudad = "Ica",
            departamento = "Ica",
            restaurante = "Donde Doña Paulina",
            direccion = "Calle Lima 165, Ica",
            latitud = -14.0715,
            longitud = -75.7268,
            imagenResId = R.drawable.morusa,
            precio = "S/ 32"
        )
    )

    // ⛰️ SIERRA - Cusco, Arequipa, Huancayo
    val platosSierra = listOf(
        // CUSCO
        PlatoTipico(
            id = 10,
            nombre = "Cuy al Horno",
            descripcion = "Cuy crujiente al horno con papas doradas y ensalada fresca",
            region = "Sierra",
            ciudad = "Cusco",
            departamento = "Cusco",
            restaurante = "Pachapapa",
            direccion = "Plazoleta San Blas 120, Cusco",
            latitud = -13.5170,
            longitud = -71.9785,
            imagenResId = R.drawable.cuy,
            precio = "S/ 68"
        ),
        PlatoTipico(
            id = 11,
            nombre = "Chiri Uchu",
            descripcion = "Plato frío ceremonial con cuy, gallina, charqui y mote",
            region = "Sierra",
            ciudad = "Cusco",
            departamento = "Cusco",
            restaurante = "Cicciolina",
            direccion = "Calle Triunfo 393, Cusco",
            latitud = -13.5164,
            longitud = -71.9813,
            imagenResId = R.drawable.chiri_uchu,
            precio = "S/ 55"
        ),
        PlatoTipico(
            id = 12,
            nombre = "Kapchi de Setas",
            descripcion = "Setas frescas en salsa cremosa de queso y huevo",
            region = "Sierra",
            ciudad = "Cusco",
            departamento = "Cusco",
            restaurante = "Museo del Pisco",
            direccion = "Calle Santa Catalina Ancha 398, Cusco",
            latitud = -13.5177,
            longitud = -71.9799,
            imagenResId = R.drawable.kapchi,
            precio = "S/ 42"
        ),

        // AREQUIPA
        PlatoTipico(
            id = 13,
            nombre = "Rocoto Relleno",
            descripcion = "Rocoto relleno de carne molida gratinado con queso fresco",
            region = "Sierra",
            ciudad = "Arequipa",
            departamento = "Arequipa",
            restaurante = "La Nueva Palomino",
            direccion = "Calle Leoncio Prado 122, Arequipa",
            latitud = -16.3988,
            longitud = -71.5369,
            imagenResId = R.drawable.rocoto,
            precio = "S/ 38"
        ),
        PlatoTipico(
            id = 14,
            nombre = "Chupe de Camarones",
            descripcion = "Sopa cremosa de camarones de río con papas, huevo y leche",
            region = "Sierra",
            ciudad = "Arequipa",
            departamento = "Arequipa",
            restaurante = "Sol de Mayo",
            direccion = "Jr. Jerusalén 207, Yanahuara",
            latitud = -16.3936,
            longitud = -71.5446,
            imagenResId = R.drawable.chupe_camarones,
            precio = "S/ 52"
        ),
        PlatoTipico(
            id = 15,
            nombre = "Adobo Arequipeño",
            descripcion = "Carne de cerdo marinada en chicha y especias, cocida lentamente",
            region = "Sierra",
            ciudad = "Arequipa",
            departamento = "Arequipa",
            restaurante = "La Cau Cau",
            direccion = "Pasaje Catedral 119, Arequipa",
            latitud = -16.3985,
            longitud = -71.5368,
            imagenResId = R.drawable.adobo,
            precio = "S/ 35"
        ),

        // HUANCAYO
        PlatoTipico(
            id = 16,
            nombre = "Pachamanca",
            descripcion = "Carnes variadas y tubérculos cocidos bajo tierra con piedras calientes",
            region = "Sierra",
            ciudad = "Huancayo",
            departamento = "Junín",
            restaurante = "Casa de la Pachamanca",
            direccion = "Jr. Libertad 234, Huancayo",
            latitud = -12.0658,
            longitud = -75.2049,
            imagenResId = R.drawable.pachamanca,
            precio = "S/ 58"
        ),
        PlatoTipico(
            id = 17,
            nombre = "Papa a la Huancaína",
            descripcion = "Papas sancochadas con salsa cremosa de ají amarillo",
            region = "Sierra",
            ciudad = "Huancayo",
            departamento = "Junín",
            restaurante = "El Olimpo",
            direccion = "Av. Giraldez 199, Huancayo",
            latitud = -12.0685,
            longitud = -75.2088,
            imagenResId = R.drawable.papa_huancaina,
            precio = "S/ 22"
        ),
        PlatoTipico(
            id = 18,
            nombre = "Trucha Frita",
            descripcion = "Trucha fresca del río frita con ensalada y papas",
            region = "Sierra",
            ciudad = "Huancayo",
            departamento = "Junín",
            restaurante = "La Cabaña",
            direccion = "Jr. Ancash 271, Huancayo",
            latitud = -12.0672,
            longitud = -75.2103,
            imagenResId = R.drawable.trucha,
            precio = "S/ 45"
        )
    )

    // 🌴 SELVA - Iquitos, Pucallpa, Tarapoto
    val platosSelva = listOf(
        // IQUITOS
        PlatoTipico(
            id = 19,
            nombre = "Juane de Gallina",
            descripcion = "Arroz con gallina y especias envuelto en hoja de bijao",
            region = "Selva",
            ciudad = "Iquitos",
            departamento = "Loreto",
            restaurante = "El Ojito de Agua",
            direccion = "Av. Abelardo Quiñones 1050, Iquitos",
            latitud = -3.7437,
            longitud = -73.2516,
            imagenResId = R.drawable.juane,
            precio = "S/ 22"
        ),
        PlatoTipico(
            id = 20,
            nombre = "Tacacho con Cecina",
            descripcion = "Plátano verde asado y machacado con cecina ahumada",
            region = "Selva",
            ciudad = "Iquitos",
            departamento = "Loreto",
            restaurante = "La Mishquina",
            direccion = "Jr. Napo 158, Iquitos",
            latitud = -3.7491,
            longitud = -73.2511,
            imagenResId = R.drawable.tacacho,
            precio = "S/ 30"
        ),
        PlatoTipico(
            id = 21,
            nombre = "Inchicapi",
            descripcion = "Sopa de gallina con maní molido y culantro amazónico",
            region = "Selva",
            ciudad = "Iquitos",
            departamento = "Loreto",
            restaurante = "Al Frío y al Fuego",
            direccion = "Malecón Tarapacá 115, Iquitos",
            latitud = -3.7456,
            longitud = -73.2472,
            imagenResId = R.drawable.inchicapi,
            precio = "S/ 26"
        ),

        // PUCALLPA
        PlatoTipico(
            id = 22,
            nombre = "Patarashca",
            descripcion = "Pescado envuelto en hojas de bijao asado a la parrilla",
            region = "Selva",
            ciudad = "Pucallpa",
            departamento = "Ucayali",
            restaurante = "La Casa de Barro",
            direccion = "Jr. Tarapacá 670, Pucallpa",
            latitud = -8.3791,
            longitud = -74.5539,
            imagenResId = R.drawable.patarashca,
            precio = "S/ 35"
        ),
        PlatoTipico(
            id = 23,
            nombre = "Timbuche",
            descripcion = "Sopa de pescado fresco con plátano verde y culantro",
            region = "Selva",
            ciudad = "Pucallpa",
            departamento = "Ucayali",
            restaurante = "El Embarcadero",
            direccion = "Malecón Grau 185, Pucallpa",
            latitud = -8.3825,
            longitud = -74.5492,
            imagenResId = R.drawable.timbuche,
            precio = "S/ 24"
        ),
        PlatoTipico(
            id = 24,
            nombre = "Suri Frito",
            descripcion = "Larvas de escarabajo fritas, delicia amazónica rica en proteínas",
            region = "Selva",
            ciudad = "Pucallpa",
            departamento = "Ucayali",
            restaurante = "Sabor Amazónico",
            direccion = "Jr. Ucayali 445, Pucallpa",
            latitud = -8.3809,
            longitud = -74.5518,
            imagenResId = R.drawable.suri,
            precio = "S/ 18"
        ),

        // TARAPOTO
        PlatoTipico(
            id = 25,
            nombre = "Cecina con Tacacho",
            descripcion = "Cecina de chancho con plátano verde asado y ensalada",
            region = "Selva",
            ciudad = "Tarapoto",
            departamento = "San Martín",
            restaurante = "El Rincón Sureño",
            direccion = "Jr. Alegría Arias de Morey 153, Tarapoto",
            latitud = -6.4899,
            longitud = -76.3631,
            imagenResId = R.drawable.cecina_tacacho,
            precio = "S/ 28"
        ),
        PlatoTipico(
            id = 26,
            nombre = "Ninajuane",
            descripcion = "Juane envuelto en dos hojas, relleno de arroz y gallina con huevo",
            region = "Selva",
            ciudad = "Tarapoto",
            departamento = "San Martín",
            restaurante = "La Patarashca",
            direccion = "Jr. Lamas 261, Tarapoto",
            latitud = -6.4874,
            longitud = -76.3658,
            imagenResId = R.drawable.ninajuane,
            precio = "S/ 25"
        ),
        PlatoTipico(
            id = 27,
            nombre = "Avispa Juane",
            descripcion = "Juane de arroz con carne de monte y huevos de avispa",
            region = "Selva",
            ciudad = "Tarapoto",
            departamento = "San Martín",
            restaurante = "Don José Juanería",
            direccion = "Jr. Jiménez Pimentel 127, Tarapoto",
            latitud = -6.4912,
            longitud = -76.3619,
            imagenResId = R.drawable.avispa_juane,
            precio = "S/ 27"
        )
    )

    // ✅ FUNCIÓN HELPER PARA FILTRAR POR CIUDAD
    fun getPlatosPorCiudad(ciudad: String): List<PlatoTipico> {
        return (platosCosta + platosSierra + platosSelva).filter { it.ciudad == ciudad }
    }

    // ✅ FUNCIÓN HELPER PARA OBTENER TODAS LAS CIUDADES
    fun getCiudadesPorRegion(region: String): List<String> {
        val platos = when (region) {
            "Costa" -> platosCosta
            "Sierra" -> platosSierra
            "Selva" -> platosSelva
            else -> emptyList()
        }
        return platos.map { it.ciudad }.distinct()
    }
}
