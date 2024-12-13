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
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.Adapter.FrittoAdapter
import com.example.schedacibo.DetailActivity.FrittiDetailActivity
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FrittoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var frittiList: MutableList<Fritti>
    private lateinit var frittiAdapter: FrittoAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_main1, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        frittiList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        frittiAdapter = FrittoAdapter(frittiList) { fritti ->
            // Usa il metodo statico per aprire ProductDetailActivity
           FrittiDetailActivity.startActivity(requireActivity() as AppCompatActivity, fritti)

            recyclerView.adapter = frittiAdapter

        }

        recyclerView.adapter = frittiAdapter

        database = FirebaseDatabase.getInstance().getReference("fritti")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                frittiList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val fritti = prodottoSnapshot.getValue(Fritti::class.java)
                    fritti?.let {
                        frittiList.add(it)
                    }
                }

                frittiAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }
}