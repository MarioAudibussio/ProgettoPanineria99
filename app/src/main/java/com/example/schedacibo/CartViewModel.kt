package com.example.schedacibo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedacibo.DataClass.Bibite

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<Bibite>>(emptyList())
    val cartItems: LiveData<List<Bibite>> get() = _cartItems

    // Metodo per aggiungere un elemento al carrello
    fun addToCart(bibita: Bibite) {
        val currentList = _cartItems.value.orEmpty()
        _cartItems.value = currentList + bibita
    }
}
