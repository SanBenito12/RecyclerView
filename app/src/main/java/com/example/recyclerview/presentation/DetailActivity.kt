package com.example.recyclerview.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.recyclerview.R

/**
 * Actividad que muestra los detalles de un elemento seleccionado
 */
class DetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ITEM_TITLE = "extra_item_title"
        const val EXTRA_ITEM_DESCRIPTION = "extra_item_description"
        const val EXTRA_ITEM_ID = "extra_item_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        setContentView(R.layout.activity_detail)

        // Obtener los datos del Intent
        val itemTitle = intent.getStringExtra(EXTRA_ITEM_TITLE) ?: "Sin título"
        val itemDescription = intent.getStringExtra(EXTRA_ITEM_DESCRIPTION) ?: "Sin descripción"
        val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)

        // Configurar las vistas
        setupViews(itemTitle, itemDescription, itemId)
    }

    private fun setupViews(title: String, description: String, id: Int) {
        val titleTextView = findViewById<TextView>(R.id.tv_detail_title)
        val descriptionTextView = findViewById<TextView>(R.id.tv_detail_description)
        val idTextView = findViewById<TextView>(R.id.tv_detail_id)

        titleTextView.text = title
        descriptionTextView.text = description
        idTextView.text = "ID: $id"
    }
}