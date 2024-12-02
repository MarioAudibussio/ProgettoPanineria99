package com.example.schedacibo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ThirdFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var prodottoList: MutableList<Prodotto>
    private lateinit var prodottoAdapter: ProdottoAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_main1, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        prodottoList = mutableListOf()
        prodottoAdapter = ProdottoAdapter(prodottoList)

        recyclerView.adapter = prodottoAdapter

        database = FirebaseDatabase.getInstance().getReference("prodotti")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                prodottoList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val prodotto = prodottoSnapshot.getValue(Prodotto::class.java)
                    prodotto?.let {
                        prodottoList.add(it)
                    }
                }

                prodottoAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}
