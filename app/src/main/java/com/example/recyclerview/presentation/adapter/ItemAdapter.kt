package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Item

/**
 * Adapter para el RecyclerView que muestra la lista de elementos
 */
class ItemAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * ViewHolder que contiene las vistas de cada elemento
     */
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description

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