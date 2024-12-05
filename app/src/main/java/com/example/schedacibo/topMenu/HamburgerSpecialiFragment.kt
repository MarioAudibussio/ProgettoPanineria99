package com.example.schedacibo.topMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.DataClass.Speciali
import com.example.schedacibo.Adapter.Hamburgher_SpecialiAdapter
import com.example.schedacibo.DetailFragment.HamburgherSpecialiDetailFragment
import com.example.schedacibo.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HamburgherSpecialiFragment.newInstanceSpeciali] factory method to
 * create an instance of this fragment.
 */
class HamburgerSpecialiFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var hamburgher_specialiList: MutableList<Speciali>
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
        hamburgher_specialiList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        hamburgher_specialiAdapter = Hamburgher_SpecialiAdapter(hamburgher_specialiList) { hamburgher_speciali ->
            val tabContainer = requireActivity().findViewById<View>(R.id.tab_container)
            tabContainer?.visibility = View.GONE

            // Questo codice verrà eseguito quando un prodotto viene cliccato
            val hamburgherSpecialiDetailFragment = HamburgherSpecialiDetailFragment.newInstanceSpeciali(hamburgher_speciali)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, hamburgherSpecialiDetailFragment) // Sostituisci con il tuo contenitore effettivo
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = hamburgher_specialiAdapter

        database = FirebaseDatabase.getInstance().getReference("speciali")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hamburgher_specialiList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val hamburgher_speciali = prodottoSnapshot.getValue(Speciali::class.java)
                    hamburgher_speciali?.let {
                        hamburgher_specialiList.add(it)
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

}