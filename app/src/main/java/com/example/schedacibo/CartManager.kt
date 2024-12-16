package com.example.schedacibo
import com.example.schedacibo.DataClass.Product

object CartManager {
    // Mappa per associare i prodotti alla loro quantità
    private val cartItems = mutableMapOf<Product, Int>()

    // Aggiunge un elemento al carrello con una quantità specificata
    fun addItem(item: Product, quantity: Int) {
        if (cartItems.containsKey(item)) {
            // Se l'elemento esiste già, aggiorna la quantità
            cartItems[item] = cartItems[item]!! + quantity
        } else {
            // Altrimenti aggiungi il nuovo elemento
            cartItems[item] = quantity
        }
    }

    // Restituisce tutti gli elementi nel carrello come una lista di coppie (Product, Quantity)
    fun getItems(): List<Pair<Product, Int>> {
        return cartItems.map { it.key to it.value }
    }

    // Restituisce il numero totale di elementi nel carrello (conta le quantità)
    fun getTotalItemCount(): Int {
        return cartItems.values.sum()
    }

    // Rimuove un elemento dal carrello
    fun removeItem(item: Product) {
        cartItems.remove(item)
    }

    // Svuota completamente il carrello
    fun clearCart() {
        cartItems.clear()
    }
}
