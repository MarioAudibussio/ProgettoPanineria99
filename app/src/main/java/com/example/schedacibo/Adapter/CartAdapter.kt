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

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems: List<Bibite> = emptyList()

    fun updateCart(items: List<Bibite>) {
        this.cartItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto1, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = cartItems.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageProduct)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameProduct)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceProduct)

        fun bind(bibite: Bibite) {
            nameTextView.text = bibite.nome
            priceTextView.text = bibite.prezzo
            Picasso.get().load(bibite.immagine).into(imageView)
        }
    }
}
