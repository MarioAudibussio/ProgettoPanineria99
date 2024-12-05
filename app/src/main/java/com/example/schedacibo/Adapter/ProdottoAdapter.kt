package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.R
import com.squareup.picasso.Picasso


class PaniniAdapter(
    private val paniniList: List<Panini>,
    private val onItemClick: (Panini) -> Unit
) : RecyclerView.Adapter<PaniniAdapter.PaniniViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaniniViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto1, parent, false)
        return PaniniViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaniniViewHolder, position: Int) {
        val panini = paniniList[position]

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

    override fun getItemCount(): Int {
        return paniniList.size
    }

    class PaniniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredintiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}
