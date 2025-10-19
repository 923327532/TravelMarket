package com.roberto.travelmarket.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(90.dp)  // ← CAMBIO: de 85dp a 90dp
            .padding(horizontal = 2.dp),  // ← CAMBIO: de 4dp a 2dp
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Círculo con icono
        Surface(
            modifier = Modifier.size(70.dp),
            shape = CircleShape,
            color = backgroundColor,
            onClick = onClick
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Título
        Text(
            text = title,
            fontSize = 12.sp,  // ← CAMBIO: de 13sp a 12sp (más compacto)
            fontWeight = FontWeight.Medium,
            color = Color(0xFF212121),
            textAlign = TextAlign.Center,
            maxLines = 2,  // ← CAMBIO: de 1 a 2 líneas (por si se necesita)
            lineHeight = 14.sp  // ← NUEVO: espaciado entre líneas
        )
    }
}
