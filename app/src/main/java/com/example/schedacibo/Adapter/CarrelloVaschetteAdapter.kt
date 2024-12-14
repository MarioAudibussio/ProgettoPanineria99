package com.example.schedacibo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.R
import com.example.schedacibo.DataClass.Product
import com.squareup.picasso.Picasso


class CarrelloVaschetteAdapter(private val items: List<Product>) :
    RecyclerView.Adapter<CarrelloVaschetteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.nameProduct)
        val prezzo: TextView = itemView.findViewById(R.id.priceProduct)
        val tipologia: TextView = itemView.findViewById(R.id.descriptionProduct)
        val immagine: ImageView = itemView.findViewById(R.id.imageProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prodotto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nome.text = item.nome
        holder.prezzo.text = item.prezzo
        Picasso.get().load(item.immagine).into(holder.immagine)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
