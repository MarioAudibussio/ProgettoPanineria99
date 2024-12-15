package com.example.schedacibo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ShopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarrelloFrittiAdapter
    private lateinit var adapterbibite: CarrelloBibiteAdapter
    private lateinit var adapterpanini: CarrelloPaniniAdapter
    private lateinit var adapterspeciali: CarrelloSpecialiAdapter
    private lateinit var adaptervaschette: CarrelloVaschetteAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Ottieni gli elementi dal carrello
        val items = CartManager.getItems()

        // Configura l'adapter
        adapter = CarrelloFrittiAdapter(items)
        adapterbibite = CarrelloBibiteAdapter(items)
        adapterpanini = CarrelloPaniniAdapter(items)
        adapterspeciali = CarrelloSpecialiAdapter(items)
        adaptervaschette = CarrelloVaschetteAdapter(items)


        recyclerView.adapter = adapter

        return view
    }
}
