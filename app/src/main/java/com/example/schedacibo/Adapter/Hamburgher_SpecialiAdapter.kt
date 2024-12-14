package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Speciali
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class Hamburgher_SpecialiAdapter(
    private val hamburgherSpecialiList: List<Speciali>,
    private val onItemClick: (Speciali) -> Unit
) : RecyclerView.Adapter<Hamburgher_SpecialiAdapter.HamburgherSpecialiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamburgherSpecialiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto, parent, false)
        return HamburgherSpecialiViewHolder(view)
    }

    override fun onBindViewHolder(holder: HamburgherSpecialiViewHolder, position: Int) {
        val hamburgherSpeciali = hamburgherSpecialiList[position]

        holder.nomeTextView.text = hamburgherSpeciali.nome
        holder.ingredintiTextView.text = hamburgherSpeciali.ingredienti
        holder.prezzoTextView.text = hamburgherSpeciali.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(hamburgherSpeciali.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(hamburgherSpeciali) // Passa l'oggetto prodotto al callback
        }
    }

    override fun getItemCount(): Int {
        return hamburgherSpecialiList.size
    }

    class HamburgherSpecialiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredintiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}
