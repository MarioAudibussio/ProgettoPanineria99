package com.example.schedacibo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.schedacibo.databinding.ActivityMainBinding
import com.example.schedacibo.menu.AccountFragment
import com.example.schedacibo.menu.HomeFragment
import com.example.schedacibo.menu.InfoFragment
import com.example.schedacibo.menu.ShopFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
//---------------------------------------------------------------------------------------------
        val nome = intent.getStringExtra("nome")
        val tipologia = intent.getStringExtra("tipologia")
        val ingredienti = intent.getStringExtra("ingredienti")
        val prezzo = intent.getStringExtra("prezzo")
        val immagine = intent.getStringExtra("immagine")

        if (nome != null && tipologia != null && ingredienti != null && prezzo != null && immagine != null) {
            // Crea un Bundle con i dati
            val bundle = Bundle().apply {
                putString("nome", nome)
                putString("tipologia", tipologia)
                putString("ingredienti", ingredienti)
                putString("prezzo", prezzo)
                putString("immagine", immagine)
            }

            // Inoltra i dati al Fragment desiderato (es. ShopFragment)
            val shopFragment = ShopFragment()
            shopFragment.arguments = bundle

            replaceFragment(shopFragment)
        }
//---------------------------------------------------------------------------------------------
            window.statusBarColor = ContextCompat.getColor(this, R.color.background)

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.buttomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.shop -> {
                    replaceFragment(ShopFragment())
                    true
                }
                R.id.info -> {
                    replaceFragment(InfoFragment())
                    true
                }
                R.id.account -> {
                    replaceFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }

    }



    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}