package com.example.recyclerview.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Item

/**
 * Adapter optimizado para pantallas redondas de Wear OS
 * - Elementos más grandes y legibles
 * - Menos información por item
 * - Diseño más limpio
 */
class ItemAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: LinearLayout = itemView.findViewById(R.id.item_container)
        val iconImageView: ImageView = itemView.findViewById(R.id.iv_icon)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        // Configurar ícono
        holder.iconImageView.setImageResource(item.iconResource)

        // Configurar título
        holder.titleTextView.text = item.title

        // Aplicar color de fondo al contenedor
        try {
            holder.container.setBackgroundColor(Color.parseColor(item.backgroundColor))
        } catch (e: IllegalArgumentException) {
            // Usar color por defecto si hay error en el parsing
            holder.container.setBackgroundColor(Color.parseColor("#2A2A2A"))
        }

        // Configurar click listener
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        // Animación sutil al hacer scroll
        holder.itemView.alpha = 0.9f
        holder.itemView.animate()
            .alpha(1.0f)
            .setDuration(150)
            .start()
    }

    override fun getItemCount() = items.size
}