package com.example.schedacibo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.data.Product
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.product_image)
        val name: TextView = view.findViewById(R.id.product_name)
        val price: TextView = view.findViewById(R.id.product_price)
        val description: TextView = view.findViewById(R.id.product_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.nome
        holder.price.text = "€${product.prezzo}"
        holder.description.text = product.descrizione

        // Carica immagine usando Picasso (o Glide)
        Picasso.get().load(product.immagine).placeholder(R.drawable.ic_placeholder_image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = products.size
}
