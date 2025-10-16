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

