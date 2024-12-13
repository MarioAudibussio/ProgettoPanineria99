package com.example.schedacibo.DetailActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.ActivityFrittiDetailBinding
import com.squareup.picasso.Picasso



class FrittiDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrittiDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inizializza il binding
        binding = ActivityFrittiDetailBinding.inflate(layoutInflater)
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
                putExtra("SELECTED_TAB", "fritti")
            }
            startActivity(intent)
            // Chiudi l'Activity corrente
            finish()
        }
    }

    companion object {
        fun startActivity(activity: AppCompatActivity, panini: Fritti) {
            val intent = Intent(activity, FrittiDetailActivity::class.java).apply {
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