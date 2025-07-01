package com.example.recyclerview.model

/**
 * Modelo de datos para elementos de la NFL con íconos y colores
 */
data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val iconResource: Int, // Ahora es obligatorio
    val backgroundColor: String = "#1a1a1a", // Color de fondo personalizado
    val textColor: String = "#ffffff" // Color de texto personalizado
)