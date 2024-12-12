package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class PaniniAdapter(
    private val onItemClick: (Any) -> Unit,
    param: (Any) -> Int
) : ListAdapter<Panini, PaniniAdapter.PaniniViewHolder>(PaniniDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaniniViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto1, parent, false)
        return PaniniViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaniniViewHolder, position: Int) {
        val panini = getItem(position)

        holder.nomeTextView.text = panini.nome
        holder.ingredintiTextView.text = panini.ingredienti
        holder.prezzoTextView.text = panini.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(panini.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(panini) // Passa l'oggetto prodotto al callback
        }
    }

    class PaniniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredintiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }

    // DiffUtil callback per confrontare gli elementi della lista
    class PaniniDiffCallback : DiffUtil.ItemCallback<Panini>() {
        override fun areItemsTheSame(oldItem: Panini, newItem: Panini): Boolean {
            // Confronta gli identificatori unici degli elementi
            return oldItem.nome == newItem.nome
        }

        override fun areContentsTheSame(oldItem: Panini, newItem: Panini): Boolean {
            // Confronta il contenuto degli elementi
            return oldItem == newItem
        }
    }
}