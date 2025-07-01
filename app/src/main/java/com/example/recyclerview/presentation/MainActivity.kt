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
     * Crea datos de ejemplo para mostrar en la lista
     */
    private fun createSampleData(): List<Item> {
        return listOf(
            Item(1, "Configuración", "Ajustes del dispositivo"),
            Item(2, "Ejercicio", "Rutinas y entrenamientos"),
            Item(3, "Música", "Control de reproducción"),
            Item(4, "Clima", "Pronóstico del tiempo"),
            Item(5, "Mensajes", "SMS y notificaciones"),
            Item(6, "Contactos", "Lista de contactos"),
            Item(7, "Calendario", "Eventos y recordatorios"),
            Item(8, "Cronómetro", "Medidor de tiempo"),
            Item(9, "Alarmas", "Gestión de alarmas"),
            Item(10, "Batería", "Estado de la batería"),
            Item(11, "WiFi", "Configuración de red"),
            Item(12, "Bluetooth", "Dispositivos conectados"),
            Item(13, "Aplicaciones", "Apps instaladas"),
            Item(14, "Notificaciones", "Centro de notificaciones"),
            Item(15, "Ayuda", "Soporte y ayuda")
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