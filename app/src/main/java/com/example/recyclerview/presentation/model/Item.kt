package com.example.recyclerview.model

/**
 * Modelo de datos simple para los elementos del RecyclerView
 */
data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val iconResource: Int? = null
)