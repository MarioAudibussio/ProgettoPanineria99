package com.example.schedacibo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var paniniButton = findViewById<Button>(R.id.paninibutton)
        val frittiButton = findViewById<Button>(R.id.frittibutton)
        val bibiteButton = findViewById<Button>(R.id.bibitebutton)
        val paniniSpecialiButton = findViewById<Button>(R.id.paninispecialibutton)
        val vaschetteButton = findViewById<Button>(R.id.vaschettebutton)

        // Imposta OnClickListener per ogni pulsante
        paniniButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("tab",0)
            startActivity(intent)
        }

        frittiButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("tab",1)
            startActivity(intent)
        }

        bibiteButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("tab",2)
            startActivity(intent)
        }

        paniniSpecialiButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("tab",3)
            startActivity(intent)
        }

        vaschetteButton.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            intent.putExtra("tab",4)
            startActivity(intent)
        }
        /*  val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Imposta il fragment iniziale
        replaceFragment(HomeFragment())

        // Gestisci le selezioni del menu della navbar
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigation_favorites -> {
                    replaceFragment(FavoritesFragment())
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()*/
    }
}