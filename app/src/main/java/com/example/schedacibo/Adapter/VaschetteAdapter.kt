package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Vaschette
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class VaschetteAdapter(
    private val vaschetteList: List<Vaschette>, // Nome più significativo
    private val onItemClick: (Vaschette) -> Unit
) : RecyclerView.Adapter<VaschetteAdapter.VaschetteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaschetteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto1, parent, false)
        return VaschetteViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaschetteViewHolder, position: Int) {
        val vaschetta = vaschetteList[position] // Ottieni l'elemento corrente dalla lista

        holder.nomeTextView.text = vaschetta.nome
        holder.ingredientiTextView.text = vaschetta.ingredienti
        holder.prezzoTextView.text = vaschetta.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(vaschetta.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(vaschetta) // Passa l'oggetto corrente al callback
        }
    }

    override fun getItemCount(): Int {
        return vaschetteList.size
    }

    class VaschetteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredientiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}
