package com.example.schedacibo.topMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedacibo.Prodotto
import com.example.schedacibo.ProdottoAdapter
import com.example.schedacibo.ProductDetailFragment
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
 * Use the [PaniniFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaniniFragment : Fragment() {
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

        // Inizializza l'adapter con il callback per il click
        prodottoAdapter = ProdottoAdapter(prodottoList) { prodotto ->
            val tabContainer = requireActivity().findViewById<View>(R.id.tab_container)
            tabContainer?.visibility = View.GONE

            // Questo codice verrà eseguito quando un prodotto viene cliccato
            val productDetailFragment = ProductDetailFragment.newInstance(prodotto)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, productDetailFragment) // Sostituisci con il tuo contenitore effettivo
                .addToBackStack(null)
                .commit()
        }

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