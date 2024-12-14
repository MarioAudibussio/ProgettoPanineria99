package com.example.schedacibo

import com.example.schedacibo.DataClass.Fritti

object CartManager {
    private val cartItems = mutableListOf<Fritti>()

    fun addItem(item: Fritti) {
        cartItems.add(item)
    }

    fun getItems(): List<Fritti> {
        return cartItems
    }
}
