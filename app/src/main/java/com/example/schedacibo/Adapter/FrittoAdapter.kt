package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class FrittoAdapter(
    private val frittiList: List<Fritti>,
    private val onItemClick: (Fritti) -> Unit
) : RecyclerView.Adapter<FrittoAdapter.FrittiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrittiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto, parent, false)
        return FrittiViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrittiViewHolder, position: Int) {
        val fritti = frittiList[position]

        holder.nomeTextView.text = fritti.nome
        holder.ingredintiTextView.text = fritti.ingredienti
        holder.prezzoTextView.text = fritti.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(fritti.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(fritti) // Passa l'oggetto prodotto al callback
        }
    }

    override fun getItemCount(): Int {
        return frittiList.size
    }

    class FrittiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredintiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}

