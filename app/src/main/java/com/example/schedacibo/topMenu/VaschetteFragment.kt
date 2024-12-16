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
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.DataClass.Product
import com.example.schedacibo.Adapter.VaschetteAdapter
import com.example.schedacibo.DetailActivity.VaschetteDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VaschetteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<Product>
    private lateinit var vaschetteAdapter: VaschetteAdapter
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
       vaschetteAdapter = VaschetteAdapter(productList) { vaschette ->
            // Usa il metodo statico per aprire ProductDetailActivity
            VaschetteDetailActivity.startActivity(requireActivity() as AppCompatActivity, vaschette)
        }

        recyclerView.adapter = vaschetteAdapter

        database = FirebaseDatabase.getInstance().getReference("vaschette")

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

                vaschetteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    private fun loadInitialVaschette() {
        val reference = FirebaseDatabase.getInstance().reference.child("Panini")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = snapshot.children.mapNotNull { it.getValue(Product::class.java) }
                vaschetteAdapter = VaschetteAdapter(productList) { product ->
                    // Handle item click
                }
                recyclerView.adapter = vaschetteAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun updateAdapter(newAdapter: VaschetteAdapter) {
        recyclerView.visibility = View.VISIBLE  // Ensure RecyclerView is visible
        recyclerView.adapter = newAdapter
        newAdapter.notifyDataSetChanged()
    }

}