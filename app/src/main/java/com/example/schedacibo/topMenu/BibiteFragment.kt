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
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.Adapter.BibiteAdapter
import com.example.schedacibo.DetailActivity.BibiteDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BibiteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bibiteList: MutableList<Bibite>
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
        bibiteList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        bibiteAdapter = BibiteAdapter(bibiteList) { bibite ->
            // Usa il metodo statico per aprire ProductDetailActivity
            BibiteDetailActivity.startActivity(requireActivity() as AppCompatActivity, bibite)
        }

        recyclerView.adapter = bibiteAdapter

        database = FirebaseDatabase.getInstance().getReference("bibite")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bibiteList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val bibite = prodottoSnapshot.getValue(Bibite::class.java)
                    bibite?.let {
                        bibiteList.add(it)
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
}