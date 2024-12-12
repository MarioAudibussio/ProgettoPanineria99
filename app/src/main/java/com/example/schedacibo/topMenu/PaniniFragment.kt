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
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.DetailActivity.ProductDetailActivity
import com.example.schedacibo.DetailFragment.ProductDetailFragment
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
    private lateinit var paniniList: MutableList<Panini>
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
                    val panini = prodottoSnapshot.getValue(Panini::class.java)
                    panini?.let {
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
}