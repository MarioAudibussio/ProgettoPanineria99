package com.example.schedacibo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedacibo.DataClass.Panini

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Panini>>(mutableListOf())
    val cartItems: LiveData<MutableList<Panini>> = _cartItems

    fun addToCart(item: Any) {
        val currentCart = _cartItems.value ?: mutableListOf()

        // Check if item already exists in cart
        val existingItemIndex = currentCart.indexOfFirst { it.nome == item.nome }

        if (existingItemIndex != -1) {
            // Update quantity if item already exists
            currentCart[existingItemIndex].quantita += item.quantita
        } else {
            // Add new item to cart
            currentCart.add(item)
        }

        _cartItems.value = currentCart
    }
}