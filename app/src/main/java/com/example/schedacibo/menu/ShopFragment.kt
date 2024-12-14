package com.example.schedacibo.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.schedacibo.CartManager
import com.example.schedacibo.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.Adapter.CarrelloFrittiAdapter
import com.example.schedacibo.Adapter.CarrelloBibiteAdapter
import com.example.schedacibo.Adapter.CarrelloPaniniAdapter
import com.example.schedacibo.Adapter.CarrelloSpecialiAdapter
import com.example.schedacibo.Adapter.CarrelloVaschetteAdapter
import com.example.schedacibo.DataClass.Product
class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarrelloFrittiAdapter
    private lateinit var adapterbibite: CarrelloBibiteAdapter
    private lateinit var adapterpanini: CarrelloPaniniAdapter
    private lateinit var adapterspeciali: CarrelloSpecialiAdapter
    private lateinit var adaptervaschette: CarrelloVaschetteAdapter
    private lateinit var costTextView: TextView
    private lateinit var totalCostTextView: TextView



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
        // Ottieni gli elementi dal carrello
        val items = CartManager.getItems()

        // Configura l'adapter
        adapter = CarrelloFrittiAdapter(items)
        adapterbibite = CarrelloBibiteAdapter(items)
        adapterpanini = CarrelloPaniniAdapter(items)
        adapterspeciali = CarrelloSpecialiAdapter(items)
        adaptervaschette = CarrelloVaschetteAdapter(items)


        recyclerView.adapter = adapter

        updateTotalCost(items)

        return view
    }

    private fun updateTotalCost(items: List<Product>) {
        // Calcola il totale sommando i prezzi convertiti in Double
        val cost = items.sumOf { item ->
            // Rimuove caratteri non numerici (eccetto la virgola e il punto)
            item.prezzo
                ?.replace("€", "")  // Rimuove il simbolo €
                ?.replace(",", ".") // Sostituisce la virgola con un punto
                ?.trim()            // Elimina eventuali spazi
                ?.toDoubleOrNull()  // Converte in Double
                ?: 0.0              // Valore di default se fallisce
        }
        val totalCost = cost + 4.99
        // Aggiorna la TextView con il totale
        costTextView.text = "${String.format("%.2f", cost)}\€"
        totalCostTextView.text = "${String.format("%.2f", totalCost)}\€"
    }
}
