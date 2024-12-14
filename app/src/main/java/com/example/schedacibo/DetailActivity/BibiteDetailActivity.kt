package com.example.schedacibo.DetailActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.MainActivity
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.ActivityBibiteDetailBinding
import com.squareup.picasso.Picasso

class BibiteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBibiteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBibiteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recover passed data
        val immagine = intent.getStringExtra("immagine")
        val nome = intent.getStringExtra("nome")
        val prezzo = intent.getStringExtra("prezzo")
        val ingredienti = intent.getStringExtra("ingredienti")
        val tipologia = intent.getStringExtra("tipologia")

        // Set data
        binding.detailName.text = nome
        binding.tipologia.text = tipologia
        binding.ingredienti.text = ingredienti
        binding.detailPrice.text = prezzo
        Picasso.get().load(immagine).into(binding.detailImage)



        // Back button configuration
        binding.backButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
        //---------------------------------------------------
        binding.addToCart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("nome", nome)
                putExtra("tipologia", tipologia)
                putExtra("ingredienti", ingredienti)
                putExtra("prezzo", prezzo)
                putExtra("immagine", immagine)
            }
            startActivity(intent)
            finish()
        }
        //---------------------------------------------------
    }

    companion object {
        fun startActivity(activity: AppCompatActivity, bibite: Bibite) {
            val intent = Intent(activity, BibiteDetailActivity::class.java).apply {
                putExtra("nome", bibite.nome)
                putExtra("tipologia", bibite.tipologia)
                putExtra("ingredienti", bibite.ingredienti)
                putExtra("prezzo", bibite.prezzo)
                putExtra("immagine", bibite.immagine)
            }
            activity.startActivity(intent)
        }
    }
}