package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class BibiteAdapter(
    private val bibiteList: List<Bibite>,
    private val onItemClick: (Bibite) -> Unit
) : RecyclerView.Adapter<BibiteAdapter.BibiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibiteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto1, parent, false)
        return BibiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: BibiteViewHolder, position: Int) {
        val bibite = bibiteList[position]

        holder.nomeTextView.text = bibite.nome
        holder.ingredintiTextView.text = bibite.ingredienti
        holder.prezzoTextView.text = bibite.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(bibite.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(bibite) // Passa l'oggetto prodotto al callback
        }
    }

    override fun getItemCount(): Int {
        return bibiteList.size
    }

    class BibiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredintiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}

