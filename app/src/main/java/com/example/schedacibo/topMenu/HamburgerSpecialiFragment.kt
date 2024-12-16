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
import com.example.schedacibo.Adapter.Hamburgher_SpecialiAdapter
import com.example.schedacibo.DetailActivity.HamburgerSpecialiDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.schedacibo.DataClass.Product

class HamburgerSpecialiFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var hamburgher_productList: MutableList<Product>
    private lateinit var hamburgher_specialiAdapter: Hamburgher_SpecialiAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_main1, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        hamburgher_productList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
       hamburgher_specialiAdapter = Hamburgher_SpecialiAdapter(hamburgher_productList) { speciali ->
            // Usa il metodo statico per aprire ProductDetailActivity
            HamburgerSpecialiDetailActivity.startActivity(requireActivity() as AppCompatActivity, speciali)
        }
        recyclerView.adapter = hamburgher_specialiAdapter


        database = FirebaseDatabase.getInstance().getReference("speciali")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hamburgher_productList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val hamburgher_product = prodottoSnapshot.getValue(Product::class.java)
                    hamburgher_product?.let {
                        hamburgher_productList.add(it)
                    }
                }

                hamburgher_specialiAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
    private fun loadInitialSpeciali() {
        val reference = FirebaseDatabase.getInstance().reference.child("Speciali")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = snapshot.children.mapNotNull { it.getValue(Product::class.java) }
                hamburgher_specialiAdapter = Hamburgher_SpecialiAdapter(productList) { product ->
                    // Handle item click
                }
                recyclerView.adapter = hamburgher_specialiAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun updateAdapter(newAdapter: Hamburgher_SpecialiAdapter) {
        recyclerView.visibility = View.VISIBLE  // Ensure RecyclerView is visible
        recyclerView.adapter = newAdapter
        newAdapter.notifyDataSetChanged()
    }

}