package com.example.schedacibo.topMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.DetailFragment.ProductDetailFragment
import com.example.schedacibo.R
import com.example.schedacibo.databinding.FragmentPaniniBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PaniniFragment : Fragment() {
    private var _binding: FragmentPaniniBinding? = null
    private val binding get() = _binding!!

    private lateinit var paniniList: MutableList<Panini>
    private lateinit var paniniAdapter: PaniniAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaniniBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paniniList = mutableListOf()

        // Inizializza l'adapter con il callback per il click
        paniniAdapter = PaniniAdapter(paniniList) { panino ->
            // Nascondi il tab_container se esiste
            val tabContainer = requireActivity().findViewById<View>(R.id.tab_container)
            tabContainer?.visibility = View.GONE

            // Questo codice verrà eseguito quando un prodotto viene cliccato
            val productDetailFragment = ProductDetailFragment.newInstance(panino)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, productDetailFragment) // Sostituisci con il contenitore del Fragment
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.adapter = paniniAdapter

        database = FirebaseDatabase.getInstance().getReference("panini")

        // Aggiungi un listener per i dati di Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                paniniList.clear() // Pulisci la lista prima di aggiungere nuovi dati

                for (prodottoSnapshot in snapshot.children) {
                    val panino = prodottoSnapshot.getValue(Panini::class.java)
                    panino?.let {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
