package com.example.recyclerview.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.widget.WearableRecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.adapter.ItemAdapter
import com.example.recyclerview.model.Item

/**
 * Actividad principal optimizada para pantallas redondas
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
     * Configura el RecyclerView optimizado para pantallas redondas
     */
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)

        val items = createInteractiveData()

        adapter = ItemAdapter(items) { item ->
            executeItemAction(item)
        }

        recyclerView.apply {
            layoutManager = WearableLinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter

            isEdgeItemsCenteringEnabled = true
            isCircularScrollingGestureEnabled = true

            requestFocus()
        }
    }

    /**
     * Datos optimizados
     */
    private fun createInteractiveData(): List<Item> {
        return listOf(
            Item(
                id = 1,
                title = "Contador",
                description = "Contador interactivo",
                iconResource = android.R.drawable.ic_input_add,
                backgroundColor = "#1B5E20"
            ),
            Item(
                id = 2,
                title = "Sonidos",
                description = "Reproducir sonidos",
                iconResource = android.R.drawable.ic_media_play,
                backgroundColor = "#B71C1C"
            ),
            Item(
                id = 3,
                title = "Alarma",
                description = "Abrir app de reloj",
                iconResource = android.R.drawable.ic_menu_recent_history,
                backgroundColor = "#1A237E"
            ),
            Item(
                id = 4,
                title = "Colores",
                description = "Cambiar colores",
                iconResource = android.R.drawable.ic_menu_edit,
                backgroundColor = "#BF360C"
            ),
            Item(
                id = 5,
                title = "Información",
                description = "Detalles del reloj",
                iconResource = android.R.drawable.ic_menu_info_details,
                backgroundColor = "#2E7D32"
            )
        )
    }

    /**
     * Ejecuta diferentes acciones según el item seleccionado
     */
    private fun executeItemAction(item: Item) {
        when (item.id) {
            1 -> openCounter()
            2 -> openSoundPlayer()
            3 -> openStopwatch()
            4 -> openColorChanger()
            5 -> openDetailScreen(item)
            else -> showToast("Acción no definida")
        }
    }

    /**
     * Abre pantalla con contador interactivo
     */
    private fun openCounter() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM_TITLE, "Contador")
            putExtra(DetailActivity.EXTRA_ITEM_DESCRIPTION, "Toca el botón para contar")
            putExtra(DetailActivity.EXTRA_ITEM_ID, 1)
        }
        startActivity(intent)
    }

    /**
     * Abre reproductor de sonidos
     */
    private fun openSoundPlayer() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM_TITLE, "Reproductor")
            putExtra(DetailActivity.EXTRA_ITEM_DESCRIPTION, "Toca para reproducir sonidos")
            putExtra(DetailActivity.EXTRA_ITEM_ID, 2)
        }
        startActivity(intent)
    }

    /**
     * Abre la app nativa de cronómetro/reloj del sistema
     */
    private fun openStopwatch() {
        try {
            val intent = Intent().apply {
                action = "android.intent.action.SHOW_ALARMS"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        } catch (e: Exception) {
            try {
                val timerIntent = Intent().apply {
                    action = "android.intent.action.SET_TIMER"
                    putExtra("android.intent.extra.alarm.LENGTH", 60)
                    putExtra("android.intent.extra.alarm.MESSAGE", "Timer desde RecyclerView")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(timerIntent)
            } catch (ex: Exception) {
                try {
                    val clockIntent = Intent().apply {
                        action = Intent.ACTION_MAIN
                        addCategory(Intent.CATEGORY_LAUNCHER)
                        setPackage("com.google.android.deskclock")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(clockIntent)
                } catch (finalEx: Exception) {
                    showToast("No se puede abrir cronómetro")
                }
            }
        }
    }

    /**
     * Cambiador de colores de fondo
     */
    private fun openColorChanger() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM_TITLE, "Colores")
            putExtra(DetailActivity.EXTRA_ITEM_DESCRIPTION, "Cambia el color de fondo")
            putExtra(DetailActivity.EXTRA_ITEM_ID, 4)
        }
        startActivity(intent)
    }

    /**
     * Abre la pantalla de detalles normal
     */
    private fun openDetailScreen(item: Item) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ITEM_TITLE, item.title)
            putExtra(DetailActivity.EXTRA_ITEM_DESCRIPTION, item.description)
            putExtra(DetailActivity.EXTRA_ITEM_ID, item.id)
        }
        startActivity(intent)
    }

    /**
     * Muestra un toast con mensaje
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}