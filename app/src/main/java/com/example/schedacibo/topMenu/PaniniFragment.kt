package com.example.schedacibo.topMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.Adapter.Hamburgher_SpecialiAdapter
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.DetailActivity.ProductDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.schedacibo.DataClass.Product

class PaniniFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var paniniList: MutableList<Product>
    private lateinit var paniniAdapter: PaniniAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main1, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paniniList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        paniniAdapter = PaniniAdapter(paniniList) { panini ->
            // Usa il metodo statico per aprire ProductDetailActivity
            ProductDetailActivity.startActivity(requireActivity() as AppCompatActivity, panini)
        }

        recyclerView.adapter = paniniAdapter

        database = FirebaseDatabase.getInstance().getReference("panini")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                paniniList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val product = prodottoSnapshot.getValue(Product::class.java)
                    product?.let {
                        paniniList.add(it)
                    }
                }

                paniniAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
    private fun loadInitialPanini() {
        val reference = FirebaseDatabase.getInstance().reference.child("Panini")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = snapshot.children.mapNotNull { it.getValue(Product::class.java) }
                paniniAdapter = PaniniAdapter(productList) { product ->
                    // Handle item click
                }
                recyclerView.adapter = paniniAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun updateAdapter(newAdapter: PaniniAdapter) {
        recyclerView.visibility = View.VISIBLE  // Ensure RecyclerView is visible
        recyclerView.adapter = newAdapter
        newAdapter.notifyDataSetChanged()
    }
}