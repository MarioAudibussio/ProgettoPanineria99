package com.example.schedacibo.DetailActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.DataClass.Vaschette
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
            // Crea un Intent per tornare alla SecondActivity e selezionare il tab "bibite"
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("SELECTED_TAB", "hamburgerSpeciali")
            }
            startActivity(intent)
            // Chiudi l'Activity corrente
            finish()
        }
    }

    companion object {
        fun startActivity(activity: AppCompatActivity, panini: Vaschette) {
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