package com.example.schedacibo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var costTextView: TextView
    private lateinit var totalCostTextView: TextView
    private lateinit var discountEditText: EditText
    private lateinit var confirmButton: View
    private lateinit var cancelButton: Button

    private var isDiscountApplied = false
    private val discountPercentage = 25.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        costTextView = view.findViewById(R.id.prezzo_costo)
        totalCostTextView = view.findViewById(R.id.prezzo_costo_totale)
        discountEditText = view.findViewById(R.id.inserisci_sconto)
        confirmButton = view.findViewById(R.id.frame_102)
        cancelButton = view.findViewById(R.id.cancella)

        cancelButton.setOnClickListener {
            CartManager.clearCart()
            updateUI()
        }

        confirmButton.setOnClickListener {
            val enteredCode = discountEditText.text.toString().trim()
            if (enteredCode.equals("marionx", ignoreCase = true)) {
                if (!isDiscountApplied) {
                    isDiscountApplied = true
                    Toast.makeText(requireContext(), "Sconto del 25% APPLICATO", Toast.LENGTH_SHORT).show()
                    updateUI() // Ricalcola il totale con lo sconto
                } else {
                    Toast.makeText(requireContext(), "Sconto già applicato!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Codice sconto non valido", Toast.LENGTH_SHORT).show()
            }
        }

        updateUI()

        return view
    }

    private fun updateUI() {
        val cartItems = CartManager.getItems()
        val expandedItems = cartItems.flatMap { (product, quantity) ->
            List(quantity) { product }
        }

        adapter = CarrelloFrittiAdapter(expandedItems)
        recyclerView.adapter = adapter

        updateTotalCost(cartItems)
    }

    private fun updateTotalCost(cartItems: List<Pair<Product, Int>>) {
        val cost = cartItems.sumOf { (item, quantity) ->
            val itemPrice = item.prezzo
                ?.replace("€", "")
                ?.replace(",", ".")
                ?.trim()
                ?.toDoubleOrNull() ?: 0.0
            itemPrice * quantity
        }

        var totalCost = cost + 4.99

        if (isDiscountApplied) {
            totalCost *= (1 - discountPercentage / 100)
        }

        costTextView.text = "${String.format("%.2f", cost)}€"
        totalCostTextView.text = "${String.format("%.2f", totalCost)}€"

        if (isDiscountApplied) {
            totalCostTextView.setTextColor(resources.getColor(R.color.vaschette, null))
        } else {
            totalCostTextView.setTextColor(resources.getColor(R.color.black, null))
        }
    }
}
