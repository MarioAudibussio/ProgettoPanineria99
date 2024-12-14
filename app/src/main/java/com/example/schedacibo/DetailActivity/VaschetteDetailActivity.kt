package com.example.schedacibo.DetailActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.CartManager
import com.example.schedacibo.DataClass.Product
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.ActivityVaschetteDetailBinding
import com.squareup.picasso.Picasso

class VaschetteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaschetteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inizializza il binding
        binding = ActivityVaschetteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera i dati passati tramite Intent
        val immagine = intent.getStringExtra("immagine")
        val nome = intent.getStringExtra("nome")
        val prezzo = intent.getStringExtra("prezzo")
        val ingredienti = intent.getStringExtra("ingredienti")
        val tipologia = intent.getStringExtra("tipologia")

        binding.detailName.text = nome
        binding.tipologia.text = tipologia
        binding.ingredienti.text = ingredienti
        binding.detailPrice.text = prezzo
        Picasso.get().load(immagine).into(binding.detailImage)

        // Configura il pulsante indietro per tornare a SecondActivity
        binding.backButton.setOnClickListener {
            // Crea un Intent per tornare all'Activity precedente
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

            // Chiudi questa Activity
            finish()
        }
        binding.addToCart.setOnClickListener {
            // Crea un oggetto Fritti con i dati attuali
            val prodotto = Product(
                nome = nome,
                tipologia = tipologia,
                ingredienti = ingredienti,
                prezzo = prezzo,
                immagine = immagine
            )
            // Aggiungi l'oggetto al carrello
            CartManager.addItem(prodotto)

            // Mostra un messaggio di conferma
            Toast.makeText(this, "$nome aggiunto al carrello", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        fun startActivity(activity: AppCompatActivity, panini: Product) {
            val intent = Intent(activity,VaschetteDetailActivity::class.java).apply {
                putExtra("nome", panini.nome)
                putExtra("tipologia", panini.tipologia)
                putExtra("ingredienti", panini.ingredienti)
                putExtra("prezzo", panini.prezzo)
                putExtra("immagine", panini.immagine)
            }
            activity.startActivity(intent)
        }
    }
}