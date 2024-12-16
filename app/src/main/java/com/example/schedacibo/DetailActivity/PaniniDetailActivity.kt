package com.example.schedacibo.DetailActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.CartManager
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.ActivityPaniniDetailBinding
import com.squareup.picasso.Picasso
import com.example.schedacibo.DataClass.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaniniDetailBinding
    private var quantity = 1 // Variabile per la quantità iniziale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaniniDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera i dati passati
        val immagine = intent.getStringExtra("immagine")
        val nome = intent.getStringExtra("nome")
        val prezzo = intent.getStringExtra("prezzo")
        val ingredienti = intent.getStringExtra("ingredienti")
        val tipologia = intent.getStringExtra("tipologia")

        // Imposta i dati
        binding.detailName.text = nome
        binding.tipologia.text = tipologia
        binding.ingredienti.text = ingredienti
        binding.detailPrice.text = prezzo
        Picasso.get().load(immagine).into(binding.detailImage)

        // Configura il pulsante back
        binding.backButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Configura il pulsante "Aggiungi al carrello"
        binding.addToCart.setOnClickListener {
            // Crea un oggetto Product con i dati attuali e aggiungi la quantità
            val prodotto = Product(
                nome = nome,
                tipologia = tipologia,
                ingredienti = ingredienti,
                prezzo = prezzo,
                immagine = immagine
            )
            // Aggiungi l'oggetto al carrello insieme alla quantità
            CartManager.addItem(prodotto, quantity)

            // Mostra un messaggio di conferma con la quantità
            Toast.makeText(this, "$nome (x$quantity) aggiunto al carrello", Toast.LENGTH_SHORT).show()
        }


        // Configura il pulsante per incrementare la quantità
        binding.increaseQuantity.setOnClickListener {
            quantity++ // Incrementa la quantità
            updateQuantityDisplay() // Aggiorna il TextView
        }

        // Configura il pulsante per decrementare la quantità
        binding.decreaseQuantity.setOnClickListener {
            if (quantity > 1) { // Assicurati che la quantità sia almeno 1
                quantity--
                updateQuantityDisplay()
            } else {
                Toast.makeText(this, "La quantità non può essere inferiore a 1", Toast.LENGTH_SHORT).show()
            }
        }

        // Aggiorna inizialmente la quantità nel TextView
        updateQuantityDisplay()
    }

    private fun updateQuantityDisplay() {
        // Aggiorna il valore del TextView con la quantità corrente
        binding.itemQuantity.text = quantity.toString()
    }

    companion object {
        fun startActivity(activity: AppCompatActivity, product: Product) {
            val intent = Intent(activity, ProductDetailActivity::class.java).apply {
                putExtra("nome", product.nome)
                putExtra("tipologia", product.tipologia)
                putExtra("ingredienti", product.ingredienti)
                putExtra("prezzo", product.prezzo)
                putExtra("immagine", product.immagine)
            }
            activity.startActivity(intent)
        }
    }
}
