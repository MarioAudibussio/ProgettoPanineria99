package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Product
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class VaschetteAdapter(
    private val productList: List<Product>, // Nome più significativo
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<VaschetteAdapter.VaschetteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaschetteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto, parent, false)
        return VaschetteViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaschetteViewHolder, position: Int) {
        val vaschette = productList[position] // Ottieni l'elemento corrente dalla lista

        holder.nomeTextView.text = vaschette.nome
        holder.ingredientiTextView.text = vaschette.ingredienti
        holder.prezzoTextView.text = vaschette.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(vaschette.immagine)
            .into(holder.immagineImageView)

        // Imposta il listener per il clic
        holder.itemView.setOnClickListener {
            onItemClick(vaschette) // Passa l'oggetto corrente al callback
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class VaschetteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val ingredientiTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}
