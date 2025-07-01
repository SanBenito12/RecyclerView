package com.example.recyclerview.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.adapter.ItemAdapter
import com.example.recyclerview.model.Item

/**
 * Actividad principal que muestra una lista de elementos usando RecyclerView
 */
class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: WearableRecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    /**
     * Configura el RecyclerView con datos de ejemplo
     */
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)

        // Crear datos de ejemplo
        val items = createSampleData()

        // Configurar el adapter con el listener de clicks
        adapter = ItemAdapter(items) { item ->
            openDetailScreen(item)
        }

        // Configurar el RecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter

            // Habilitar el scrolling circular típico de Wear OS
            isEdgeItemsCenteringEnabled = true
            isCircularScrollingGestureEnabled = true
        }
    }

    /**
     * Crea datos de ejemplo para mostrar en la lista - Temática NFL
     */
    private fun createSampleData(): List<Item> {
        return listOf(
            Item(
                id = 1,
                title = "Partidos de Hoy",
                description = "Ver los juegos programados para hoy",
                iconResource = R.drawable.ic_football,
                backgroundColor = "#1B5E20", // Verde oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 2,
                title = "Clasificación",
                description = "Tabla de posiciones y standings",
                iconResource = R.drawable.ic_trophy,
                backgroundColor = "#B71C1C", // Rojo oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 3,
                title = "Estadísticas",
                description = "Stats de jugadores y equipos",
                iconResource = R.drawable.ic_stats,
                backgroundColor = "#1A237E", // Azul oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 4,
                title = "Estadios",
                description = "Información de los estadios",
                iconResource = R.drawable.ic_stadium,
                backgroundColor = "#4A148C", // Púrpura oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 5,
                title = "Noticias",
                description = "Últimas noticias de la NFL",
                iconResource = R.drawable.ic_football,
                backgroundColor = "#E65100", // Naranja oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 6,
                title = "Playoffs",
                description = "Bracket y calendario de playoffs",
                iconResource = R.drawable.ic_trophy,
                backgroundColor = "#BF360C", // Rojo-naranja oscuro
                textColor = "#FFFFFF"
            ),
            Item(
                id = 7,
                title = "Fantasy Football",
                description = "Tu liga de fantasy",
                iconResource = R.drawable.ic_stats,
                backgroundColor = "#2E7D32", // Verde
                textColor = "#FFFFFF"
            ),
            Item(
                id = 8,
                title = "Historial",
                description = "Resultados de temporadas anteriores",
                iconResource = R.drawable.ic_stadium,
                backgroundColor = "#5D4037", // Marrón
                textColor = "#FFFFFF"
            ),
            Item(
                id = 9,
                title = "Draft",
                description = "Información del draft de rookies",
                iconResource = R.drawable.ic_football,
                backgroundColor = "#37474F", // Gris azulado
                textColor = "#FFFFFF"
            ),
            Item(
                id = 10,
                title = "Super Bowl",
                description = "Historia y próximo Super Bowl",
                iconResource = R.drawable.ic_trophy,
                backgroundColor = "#1565C0", // Azul
                textColor = "#FFFFFF"
            )
        )
    }

    /**
     * Abre la pantalla de detalles con la información del elemento seleccionado
     */
    private fun openDetailScreen(item: Item) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM_TITLE, item.title)
            putExtra(DetailActivity.EXTRA_ITEM_DESCRIPTION, item.description)
            putExtra(DetailActivity.EXTRA_ITEM_ID, item.id)
        }
        startActivity(intent)
    }
}