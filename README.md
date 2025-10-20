# TravelMarket

## Integrantes
- **Roberto Carlos Lopez Calle** – Líder del proyecto  
- **Arnold Kyle Alva Torres** – Desarrollador 

---

Dìa 1

---

## 1. Nombre del proyecto
**TravelMarket**

---

## 2. Descripción general
TravelMarket es una aplicación móvil que permite explorar y descubrir destinos turísticos, eventos locales, experiencias gastronómicas y medios de transporte.  
Su objetivo es centralizar la información de viaje en un solo lugar, ofreciendo una experiencia sencilla, atractiva y útil para el usuario.

---

## 3. Lluvia de ideas

**¿Qué ofrece TravelMarket?**  
- Información de lugares turísticos, eventos culturales, gastronomía típica y medios de transporte.  

**¿A quién está dirigida?**  
- A turistas nacionales e internacionales interesados en conocer Perú y organizar sus viajes de forma práctica.  

**¿Qué la diferencia de otras apps?**  
- Su enfoque integral: reúne distintos tipos de información turística dentro de una sola interfaz visual y ordenada.  

**¿Qué problema soluciona?**  
- La dispersión de información sobre destinos, horarios de eventos y opciones de transporte.  

**¿Qué funcionalidades iniciales tendrá?**  
- Visualización de categorías principales.  
- Listado de elementos dentro de cada categoría.  
- Pantalla de detalle con descripción e imagen.  
- Sección de favoritos para guardar elementos seleccionados.  

---

## 4. Flujo de Navegación

**Estructura general del flujo de pantallas:**

1. 🟢 **Pantalla de Bienvenida / Inicio de Sesión**
   - El usuario abre la aplicación y puede iniciar sesión o registrarse.
   - Una vez autenticado, se muestra la pantalla principal (Home).

   ↓

2. 🏠 **Pantalla Home**
   - Muestra las principales categorías: Lugares, Eventos, Gastronomía y Transporte.
   - Desde aquí, el usuario puede seleccionar una categoría o buscar algo específico.

   ↓

3. 🗺️ **Pantalla de Categoría Seleccionada**
   - Se muestra una lista (LazyColumn o Grid) con los elementos de la categoría elegida.
   - Cada elemento incluye imagen, nombre, descripción breve y botón "Ver más".

   ↓

4. 📄 **Pantalla de Detalle**
   - Presenta la información completa del lugar o evento: imagen, descripción, ubicación, horarios, etc.
   - Permite agregar el elemento a la lista de favoritos.

   ↓

5. 💖 **Guardar en Favoritos**
   - Al presionar el botón “❤️ Agregar a Favoritos”, el elemento se almacena en el perfil del usuario.
   - El usuario puede visualizar luego sus elementos guardados.

   ↓

6. 👤 **Pantalla de Favoritos / Perfil**
   - Muestra todos los elementos guardados por el usuario.
   - Permite ver detalles de cada uno o eliminarlos de favoritos.
   - También incluye acceso al perfil y la opción “Cerrar sesión”.

   ↓

7. 🔙 **Regresar a Home**
   - Desde cualquier pantalla, el usuario puede volver a la pantalla principal mediante el menú inferior (Bottom Navigation Bar).

## 5. link del figma
 https://www.figma.com/make/DkiOcsjvkfK7sXeDnos4Vz/TravelMarket-App-Prototype?node-id=0-1&t=pHkgCxXEx7lFh0jg-1    

---

Día 2

---

## 1. Configuración del Entorno de Desarrollo

### Creación del Proyecto en Android Studio
- **Nombre del proyecto:** TravelMarket
- **Lenguaje:** Kotlin
- **SDK Mínimo:** API 24 (Android 7.0)
- **Framework UI:** Jetpack Compose
- **Build System:** Gradle con Kotlin DSL

**Cumple:** RF06 - Sistema desarrollado en Android Studio usando Kotlin y Jetpack Compose

---

## 2. Configuración de Dependencias (build.gradle.kts)

Se agregaron las dependencias necesarias para el proyecto

---

## 3. Estructura de Carpetas del Proyecto (RF07)

Se creó la estructura de paquetes siguiendo arquitectura MVVM:

<img width="297" height="527" alt="image" src="https://github.com/user-attachments/assets/7ac17ffe-2ac4-4f4d-87ab-7a1579f42007" />

**Cumple:** RF07 - Arquitectura organizada con paquetes ui, data, model y navigation

---

## 4. Sistema de Navegación Base (RF08)

### Configuración de Navigation Compose

Se configuró la estructura base del sistema de navegación sin implementar las pantallas completas.

**Archivos base creados:**
- `presentation/navigation/Screen.kt` - Definición de rutas
- `presentation/navigation/NavGraph.kt` - NavHost base
- `MainActivity.kt` - Configuración inicial

**Rutas definidas:**
- `inicio` - Pantalla HOME
- `explorar` - Pantalla Explorar
- `perfil` - Pantalla Perfil
- `favoritos` - Pantalla Favoritos

**Cumple:** RF08 - Navegador de pantallas basado en Navigation Compose

---

## 5. Configuración de Control de Versiones (RF09)

### Estructura de Ramas en GitHub

Se configuró el repositorio con 3 ramas principales:

main → Rama principal 
lopez → Rama de Roberto Lopez
arnold → Rama de Arnold Alva

### Configuración realizada:

**Inicialización del repositorio Git**
- Comando: `VCS > Enable Version Control Integration > Git`

**Conexión con GitHub remoto**
- URL: [https://github.com/923327532/TravelMarket.git](https://github.com/923327532/TravelMarket.git)
- Rama principal: `main`

**Creación de ramas individuales**

En Android Studio: Git > Branches > New Branch
Roberto Lopez
lopez

Arnold Alva
arnold


**Flujo de trabajo implementado**
- Cada integrante trabaja en su rama personal
- Commits descriptivos por tarea realizada
- Merge directo a `main` después de pruebas locales

---

## 6. Verificación de Navegación entre Pantallas (RF10)

### Pruebas de Navegación Realizadas

Se verificó que la navegación base funcione correctamente entre las pantallas principales:

**Pantallas validadas:**
- ✅ **HOME (Inicio)** - Pantalla principal configurada
- ✅ **DETALLE** - Estructura de navegación con parámetros configurada
- ✅ **PERFIL** - Ruta de navegación configurada

**Estado:** La estructura de navegación está funcional. Las pantallas completas se implementarán en los días siguientes.

**Cumple:** RF10 - Sistema ejecuta correctamente la navegación entre pantallas base

**Responsable:** Arnold Alva (configuración y pruebas)

---

## 7. Resumen de Actividades del Día 2

| Tarea | Responsable | Estado |
|-------|-------------|--------|
| Configuración de dependencias Gradle | Arnold Alva | ✅ Completado |
| Creación de estructura de carpetas | Roberto Lopez | ✅ Completado |
| Configuración Navigation Compose | Arnold Alva | ✅ Completado |
| Setup repositorio GitHub con ramas | Roberto Lopez | ✅ Completado |
| Verificación de navegación base | Arnold Alva | ✅ Completado |

---

**Fecha:** Día 2 del proyecto

---

Día 3

---

## 1. Estado de Requisitos Funcionales

| Código | Requisito | Estado | Responsable |
|:-------:|------------|:--------:|--------------|
| **RF11** | LazyColumn con lista de elementos en pantalla principal | ✅ Completado | Roberto Lopez |
| **RF12** | Imágenes, textos y botones interactivos | ✅ Completado | Roberto Lopez |
| **RF13** | Diseño respeta Material 3 | ✅ Completado | Arnold Alva |
| **RF14** | Navegación desde lista hacia detalle | ✅ Completado | Roberto Lopez |
| **RF15** | Interfaz visual coherente con Figma | ✅ Completado | Arnold Alva |

---

## 2. Trabajo Realizado

### 1. Theme y Estilos
- ✅ `presentation/ui/theme/Color.kt` – Paleta de colores Material 3  
- ✅ `presentation/ui/theme/Theme.kt` – Configuración de LightColorScheme y tipografía según Figma  

### 2. Componentes Reutilizables
- ✅ `presentation/components/CategoryCard.kt` – Cuatro categorías circulares clickeables (Lugares, Eventos, Gastronomía, Transporte)  
- ✅ `presentation/components/ItemCard.kt` – Cards con imagen, título y ubicación  
- ✅ `presentation/components/SearchBar.kt` – Barra de búsqueda con filtrado en tiempo real  

### 3. Pantallas
- ✅ `presentation/screens/inicio/InicioScreen.kt` – Pantalla principal completa con lista dinámica, búsqueda y categorías  
- ✅ `presentation/screens/detalle/DetalleLugarScreen.kt` – Pantalla de detalle con imágenes dinámicas y botón de acción  

### 4. Navegación
- ✅ `presentation/navigation/Screen.kt` – Rutas configuradas con parámetros  
- ✅ `presentation/navigation/NavGraph.kt` – Flujo de navegación Home → Detalle totalmente funcional  

### 5. Configuración
- ✅ `build.gradle.kts` – Dependencias actualizadas (Material Icons Extended, Room, Coil)  

---

## 3. Funcionalidades Implementadas

| Funcionalidad | Requisito | Estado |
|----------------|------------|:--------:|
| Lista dinámica con **LazyColumn** | RF11 | ✅ |
| Búsqueda en tiempo real con filtrado | RF12 | ✅ |
| Cuatro categorías interactivas (Lugares, Eventos, Gastronomía, Transporte) | RF12 | ✅ |
| Cinco ítems con imágenes desde `/drawable` | RF12 | ✅ |
| Navegación item → detalle con parámetro ID | RF14 | ✅ |
| **TopBar** personalizada con colores Material 3 | RF13 | ✅ |
| **BottomNavigationBar** funcional y con íconos | RF13 | ✅ |
| Mensaje “Sin resultados” al no encontrar coincidencias | RF12 | ✅ |
| Detalle con imágenes dinámicas según ID seleccionado | RF14 | ✅ |
| Aplicación completa del tema y tipografía según diseño Figma | RF15 | ✅ |

---

## 4. Imágenes Agregadas al Proyecto

- ✅ `res/drawable/parque_exposicion.jpg`  
- ✅ `res/drawable/circuito_magico_agua.jpg`  
- ✅ `res/drawable/museo_larco.jpg`  
- ✅ `res/drawable/ceremonia_apertura.jpg`  
- ✅ `res/drawable/competencia_atletismo.jpg`  

---

## 5. Observaciones Generales

- Se completó el desarrollo visual de todas las pantallas principales del proyecto.  
- La interfaz respeta los lineamientos de **Material Design 3** y coincide con el diseño de **Figma**.  
- Se validó la navegación funcional entre pantallas, componentes interactivos y diseño responsivo.  

---

## 6. Resumen de Actividades del Día 3

| Tarea | Responsable | Estado |
|-------|--------------|:--------:|
| Implementar `HomeScreen` con lista dinámica (LazyColumn) | Roberto Lopez | ✅ |
| Crear `DetalleLugarScreen` con navegación desde item | Roberto Lopez | ✅ |
| Implementar componentes reutilizables (Cards, SearchBar, Categorías) | Roberto Lopez | ✅ |
| Aplicar estilos Material 3 y tema de color según Figma | Arnold Alva | ✅ |
| Ajustar coherencia visual de todas las pantallas | Arnold Alva | ✅ |

---

**Fecha:** Día 3 del proyecto  

---

# Día 4

---

## 1. Implementación de Room Database (RF11)

### Configuración de Base de Datos Local

Se implementó Room Database para persistencia de datos con 4 entidades y pre-carga de 10 registros de ejemplo.

**Archivos creados:**
- `data/model/` - 4 entidades (Lugar, Evento, Gastronomia, Transporte)
- `data/dao/` - 4 DAOs con operaciones CRUD
- `data/db/AppDatabase.kt` - Base de datos con Singleton
- `data/repository/` - 5 repositorios MVVM

**Cumple:** RF11 - Room Database funcional con datos de ejemplo

**Responsable:** Roberto Lopez

---

## 2. Pantallas de Categorías (RF12)

### Implementación de 4 Pantallas con Filtros

Se crearon pantallas para cada categoría con búsqueda en tiempo real y filtros dropdown.

**Pantallas creadas:**

| Pantalla | Características | Responsable |
|----------|-----------------|-------------|
| LugaresScreen | Búsqueda, filtros, navegación a detalle | Roberto Lopez |
| EventosScreen | Búsqueda, filtros, navegación a detalle | Roberto Lopez |
| GastronomiaScreen | Búsqueda, filtros, navegación a detalle | Roberto Lopez |
| TransporteScreen | Búsqueda, filtros, navegación a detalle | Roberto Lopez |

**Funcionalidades:**
- ✅ Barra de búsqueda en tiempo real
- ✅ Dropdown de filtros
- ✅ LazyColumn con ItemCard
- ✅ Navegación a pantalla de detalle

**Cumple:** RF12 - Pantallas de categorías funcionales

---

## 3. Pantallas Complementarias (RF13)

### Navegación Principal

Se implementaron las 3 pantallas del BottomNavigationBar.

**Pantallas creadas:**

| Pantalla | Función | Responsable |
|----------|---------|-------------|
| ExplorarScreen | Búsqueda avanzada de contenido | Arnold Alva |
| FavoritosScreen | Lista de elementos favoritos | Arnold Alva |
| PerfilScreen | Información del usuario | Arnold Alva |

**Cumple:** RF13 - Pantallas de navegación principal completadas

---

## 4. Pantalla Acerca de (RF14)

----
### Información de la App

Se creó la pantalla con información sobre TravelMarket.

**Archivo creado:**
- `presentation/screens/acercade/AcercaDeScreen.kt`

**Contenido:**
- Descripción de la aplicación
- Integrantes del equipo
- Versión y contacto

**Cumple:** RF14 - Pantalla Acerca de implementada


---

## 5. Componentes UI Reutilizables (RF15)

### Creación de Componentes

Se implementaron 4 componentes para la interfaz de usuario.

**Componentes creados:**

| Componente | Uso |
|------------|-----|
| ItemCard | Tarjetas para listas de elementos |
| CategoryCard | Círculos de categorías en inicio |
| SearchBar | Barra de búsqueda con filtros |
| AppNavigationDrawer | Menú lateral de navegación |

**Cumple:** RF15 - Componentes UI creados

---

## 6. Recursos Gráficos (RF16)

### Imágenes Agregadas

Se incorporaron 10 imágenes al proyecto en formato JPG/JPEG.

**Imágenes en res/drawable:**
- 3 Lugares (Parque Exposición, Circuito Mágico, Museo Larco)
- 3 Eventos (Ceremonia Apertura, Competencia, Gala Clausura)
- 2 Gastronomía (Central, Mercado Surquillo)
- 2 Transporte (Metropolitano, Metro)

**Cumple:** RF16 - Recursos gráficos completos


---

## 7. Actualización del Sistema de Navegación (RF17)

### NavGraph Completo

Se actualizó el NavGraph con todas las rutas de navegación.

**Rutas implementadas:**
- 4 Pantallas de categorías
- 3 Pantallas de navegación principal
- 1 Pantalla Acerca de

**Total:** 8 pantallas navegables

**Cumple:** RF17 - Sistema de navegación completo

**Responsables:** Roberto Lopez + Arnold Alva

---

## 8. Control de Versiones (RF18)

### Merge a Main

Se realizó el merge de las ramas individuales a main.

**Estadísticas del merge:**

**Fecha:** Día 4 del proyecto

---

# Día 5

---

## 1. Estado de Requisitos Funcionales

| Código | Requisito | Estado |
|:-------:|-----------|:--------:|
| **RF21** | El sistema permite filtrar o buscar elementos por nombre o categoría | ✅ Completado |
| **RF22** | Se incluyen imágenes locales o remotas para enriquecer la presentación | ✅ Completado |
| **RF23** | Manejo de errores de carga o datos vacíos implementado correctamente | ✅ Completado |
| **RF24** | Todos los commits en GitHub registrados con mensajes descriptivos | ✅ Completado |
| **RF25** | Fusión final de ramas sin errores e integración completa del sistema | ✅ Completado |

**Avance General Día 5:** 100% completado (5/5 requisitos)

---

## 2. Resultados Obtenidos

| Funcionalidad | Descripción | Estado |
|---------------|------------|--------|
| Búsqueda y filtrado | Resultados dinámicos por texto o categoría | ✅ |
| Imágenes enriquecidas | Visualización con Coil y drawables | ✅ |
| Manejo de errores | Estados controlados para vacío o fallo de carga | ✅ |
| Registro e inicio de sesión | Implementación completa de autenticación con validaciones | ✅ |
| Historial Git | Commits claros, organizados y aprobados | ✅ |
| Integración continua | Sistema estable tras merge final | ✅ |

---

## 3. Observaciones Generales

- Se agregó el módulo de **registro e inicio de sesión**, permitiendo gestionar cuentas de usuario dentro del sistema.
- La aplicación se encuentra en una versión candidata funcional (v1.0)
- La navegación fluye sin interrupciones entre pantallas
- Todas las funciones están integradas y operativas
- Se confirmó la estabilidad del sistema luego de la fusión final, con autenticación activa

---

**Fecha:** Día 5 del proyecto  
**Versión entregada:** v1.0 – Versión candidata final con interfaz completa, autenticación activa y navegación fluida





