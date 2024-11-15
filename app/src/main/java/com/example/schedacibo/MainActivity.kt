package com.example.schedacibo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.schedacibo.data.Product

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var productsAdapter: ProductsAdapter
    private val productsList = mutableListOf<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.products_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(productsList)
        recyclerView.adapter = productsAdapter

        // Ottieni dati da Firebase
        database = FirebaseDatabase.getInstance().getReference("products")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productsList.clear()
                for (child in snapshot.children) {
                    val product = child.getValue(Product::class.java)
                    product?.let { productsList.add(it) }
                } }

            override fun onCancelled(error: DatabaseError) {
                // Gestisci l'errore
            }
        })
    }
}
