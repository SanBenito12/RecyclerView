package com.example.recyclerview.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Item

/**
 * Adapter para el RecyclerView que muestra la lista de elementos con íconos y colores
 */
class ItemAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * ViewHolder que contiene las vistas de cada elemento
     */
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iv_icon)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)
    }

    /**
     * Crea nuevas vistas (invocado por el layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    /**
     * Reemplaza el contenido de una vista (invocado por el layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        // Configurar el ícono
        holder.iconImageView.setImageResource(item.iconResource)

        // Configurar textos
        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description

        // Aplicar colores personalizados
        try {
            holder.itemView.setBackgroundColor(Color.parseColor(item.backgroundColor))
            holder.titleTextView.setTextColor(Color.parseColor(item.textColor))
        } catch (e: IllegalArgumentException) {
            // Usar colores por defecto si hay error en el parsing
        }

        // Configurar el click listener
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    /**
     * Retorna el tamaño del dataset (invocado por el layout manager)
     */
    override fun getItemCount() = items.size
}