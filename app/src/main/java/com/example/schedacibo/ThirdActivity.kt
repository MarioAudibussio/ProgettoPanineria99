package com.example.schedacibo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference


class ThirdActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var prodottoList: MutableList<Prodotto>
    private lateinit var prodottoAdapter: ProdottoAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        prodottoList = mutableListOf()
        prodottoAdapter = ProdottoAdapter(prodottoList)

        recyclerView.adapter = prodottoAdapter

        database = FirebaseDatabase.getInstance().getReference("prodotti")

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
                Toast.makeText(this@ThirdActivity, "Errore durante il recupero dei dati", Toast.LENGTH_SHORT).show()
            }
        })
    }
}