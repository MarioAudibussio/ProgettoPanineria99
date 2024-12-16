package com.example.schedacibo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.Adapter.*
import com.example.schedacibo.CartManager
import com.example.schedacibo.DataClass.Product
import com.example.schedacibo.R


class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarrelloFrittiAdapter
    private lateinit var adapterBibite: CarrelloBibiteAdapter
    private lateinit var adapterPanini: CarrelloPaniniAdapter
    private lateinit var adapterSpeciali: CarrelloSpecialiAdapter
    private lateinit var adapterVaschette: CarrelloVaschetteAdapter
    private lateinit var costTextView: TextView
    private lateinit var totalCostTextView: TextView
    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inizializza TextView per il costo totale
        costTextView = view.findViewById(R.id.prezzo_costo)
        totalCostTextView = view.findViewById(R.id.prezzo_costo_totale)

        // Aggiungi il bottone per cancellare il carrello
        cancelButton = view.findViewById(R.id.cancella)

        // Gestisci il click sul bottone "Cancella"
        cancelButton.setOnClickListener {
            // Svuota il carrello
            CartManager.clearCart()

            // Aggiorna l'interfaccia utente
            updateUI()
        }

        // Ottieni gli elementi dal carrello
        val cartItems = CartManager.getItems()

        // Espandi gli elementi in una lista piatta di prodotti (considerando la quantità)
        val expandedItems = cartItems.flatMap { (product, quantity) ->
            List(quantity) { product }
        }

        // Configura gli adapter
        adapter = CarrelloFrittiAdapter(expandedItems)
        adapterBibite = CarrelloBibiteAdapter(expandedItems)
        adapterPanini = CarrelloPaniniAdapter(expandedItems)
        adapterSpeciali = CarrelloSpecialiAdapter(expandedItems)
        adapterVaschette = CarrelloVaschetteAdapter(expandedItems)

        recyclerView.adapter = adapter

        // Aggiorna il costo totale
        updateTotalCost(cartItems)

        return view
    }

    private fun updateTotalCost(cartItems: List<Pair<Product, Int>>) {
        // Calcola il totale sommando i prezzi moltiplicati per le quantità
        val cost = cartItems.sumOf { (item, quantity) ->
            val itemPrice = item.prezzo
                ?.replace("€", "")  // Rimuove il simbolo €
                ?.replace(",", ".") // Sostituisce la virgola con un punto
                ?.trim()            // Elimina eventuali spazi
                ?.toDoubleOrNull()  // Converte in Double
                ?: 0.0              // Valore di default se fallisce
            itemPrice * quantity  // Moltiplica il prezzo per la quantità
        }

        // Aggiungi il costo fisso di spedizione
        val totalCost = cost + 4.99

        // Aggiorna le TextView con i valori calcolati
        costTextView.text = "${String.format("%.2f", cost)}€"
        totalCostTextView.text = "${String.format("%.2f", totalCost)}€"
    }
    private fun updateUI() {
        // Ottieni gli elementi aggiornati dal carrello (vuoto)
        val cartItems = CartManager.getItems()

        // Espandi gli elementi in una lista piatta di prodotti (considerando la quantità)
        val expandedItems = cartItems.flatMap { (product, quantity) ->
            List(quantity) { product }
        }

        // Ricarica l'adapter
        adapter = CarrelloFrittiAdapter(expandedItems)
        recyclerView.adapter = adapter

        // Aggiorna il totale
        updateTotalCost(cartItems)
    }
}
