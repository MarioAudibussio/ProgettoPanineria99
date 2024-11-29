package com.example.schedacibo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ProdottoAdapter(private val prodottoList: List<Prodotto>) : RecyclerView.Adapter<ProdottoAdapter.ProdottoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdottoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto, parent, false)
        return ProdottoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdottoViewHolder, position: Int) {
        val prodotto = prodottoList[position]

        holder.nomeTextView.text = prodotto.nome
        holder.descrizioneTextView.text = prodotto.descrizione
        holder.prezzoTextView.text = prodotto.prezzo

        // Carica l'immagine con Picasso
        Picasso.get()
            .load(prodotto.immagine)
            .into(holder.immagineImageView)
    }

    override fun getItemCount(): Int {
        return prodottoList.size
    }

    class ProdottoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nameProduct)
        val descrizioneTextView: TextView = itemView.findViewById(R.id.descriptionProduct)
        val prezzoTextView: TextView = itemView.findViewById(R.id.priceProduct)
        val immagineImageView: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}