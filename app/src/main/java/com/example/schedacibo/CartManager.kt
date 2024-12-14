package com.example.schedacibo
import com.example.schedacibo.DataClass.Product


object CartManager {
    private val cartItems = mutableListOf<Product>()

    fun addItem(item: Product) {
        cartItems.add(item)
    }

    fun getItems(): List<Product> {
        return cartItems
    }
}
