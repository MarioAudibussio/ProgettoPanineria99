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
import com.example.schedacibo.Adapter.BibiteAdapter
import com.example.schedacibo.DetailActivity.BibiteDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.schedacibo.DataClass.Product

class BibiteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<Product>
    private lateinit var bibiteAdapter: BibiteAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_main1, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        bibiteAdapter = BibiteAdapter(productList) { bibite ->
            // Usa il metodo statico per aprire ProductDetailActivity
            BibiteDetailActivity.startActivity(requireActivity() as AppCompatActivity, bibite)
        }

        recyclerView.adapter = bibiteAdapter

        database = FirebaseDatabase.getInstance().getReference("bibite")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val product = prodottoSnapshot.getValue(Product::class.java)
                    product?.let {
                        productList.add(it)
                    }
                }

                bibiteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
    private fun loadInitialBibite() {
        val reference = FirebaseDatabase.getInstance().reference.child("Bibite")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = snapshot.children.mapNotNull { it.getValue(Product::class.java) }
                bibiteAdapter = BibiteAdapter(productList) { bibita ->
                    // Handle item click
                }
                recyclerView.adapter = bibiteAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun updateAdapter(newAdapter: BibiteAdapter) {
        recyclerView.visibility = View.VISIBLE  // Ensure RecyclerView is visible
        recyclerView.adapter = newAdapter
        newAdapter.notifyDataSetChanged()
    }
}