package com.example.recyclerview.model

/**
 * Modelo de datos optimizado para Wear OS
 * - description es opcional para pantallas pequeñas
 */
data class Item(
    val id: Int,
    val title: String,
    val iconResource: Int,
    val backgroundColor: String = "#2A2A2A",
    val description: String = "" // Ahora es opcional con valor por defecto
)