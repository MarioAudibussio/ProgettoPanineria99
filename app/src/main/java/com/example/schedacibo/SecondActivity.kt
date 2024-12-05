package com.example.schedacibo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.schedacibo.databinding.ActivitySecondBinding
import com.example.schedacibo.topMenu.BibiteFragment
import com.example.schedacibo.topMenu.FrittoFragment
import com.example.schedacibo.topMenu.HamburgerSpecialiFragment
import com.example.schedacibo.topMenu.PaniniFragment
import com.example.schedacibo.topMenu.VaschetteFragment
import com.google.android.material.tabs.TabLayout

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("SecondActivity", "Binding creato con successo")
        val selectedTab = intent.getStringExtra("SELECTED_TAB")

        // Seleziona il tab corretto in base all'intent
        val tabPosition = when (selectedTab) {
            "panini" -> 0
            "fritti" -> 1
            "bibite" -> 2
            "hamburgerSpeciali" -> 3
            "vaschette" -> 4
            else -> 0
        }
        binding.tablayout.getTabAt(tabPosition)?.select()

        // Carica il contenuto iniziale per il tab selezionato
        handleTabSelection(tabPosition)

        // Configura il pulsante indietro
        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Configura i listener del TabLayout
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("SecondActivity", "Tab selezionato: ${tab.position}")
                handleTabSelection(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun handleTabSelection(tabPosition: Int) {
        when (tabPosition) {
            0 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(PaniniFragment())
            }
            1 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(FrittoFragment())
            }
            2 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(BibiteFragment())
            }
            3 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(HamburgerSpecialiFragment())
            }
            else -> {
                binding.appendableContentContainer.visibility = View.GONE
                val fragment: Fragment? = when (tabPosition) {
                    4 -> VaschetteFragment()
                    else -> null
                }
                fragment?.let { replaceFragment(it) }
            }
        }
    }

    private fun replaceAppendableFragment(fragment: Fragment) {
        // Rimuovi frammento precedente dal contenitore appendibile
        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id)
        currentFragment?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }

        // Aggiungi nuovo frammento al contenitore appendibile
        supportFragmentManager.beginTransaction()
            .replace(binding.appendableContentContainer.id, fragment)
            .commit()
        Log.d("SecondActivity", "Appendable fragment sostituito: ${fragment::class.java.simpleName}")
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commit()
        Log.d("SecondActivity", "Fragment transaction committed")
    }


    /**
     * Aggiunge il contenuto del tab "Panini" sotto il menu principale.
     */
    private fun appendContentForPanini() {
        // Mostra il contenitore per il contenuto appendibile
        binding.appendableContentContainer.visibility = View.VISIBLE

        // Verifica se il contenuto è già stato aggiunto
        if (supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(
                binding.appendableContentContainer.id,
                PaniniFragment() // Fragment da appendere
            )
            transaction.commit()
            Log.d("SecondActivity", "Contenuto di Panini aggiunto sotto il menu")
        } else {
            Log.d("SecondActivity", "Contenuto di Panini già presente, nessuna azione necessaria")
        }
    }
    private fun appendContentForBibite() {
        // Mostra il contenitore per il contenuto appendibile
        binding.appendableContentContainer.visibility = View.VISIBLE

        // Verifica se il contenuto è già stato aggiunto
        if (supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(
                binding.appendableContentContainer.id,
                BibiteFragment() // Fragment da appendere
            )
            transaction.commit()
            Log.d("SecondActivity", "Contenuto di Bibite aggiunto sotto il menu")
        } else {
            Log.d("SecondActivity", "Contenuto di Bibite già presente, nessuna azione necessaria")
        }
    }
    private fun appendContentForFritto() {
        // Mostra il contenitore per il contenuto appendibile
        binding.appendableContentContainer.visibility = View.VISIBLE

        // Verifica se il contenuto è già stato aggiunto
        if (supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(
                binding.appendableContentContainer.id,
                BibiteFragment() // Fragment da appendere
            )
            transaction.commit()
            Log.d("SecondActivity", "Contenuto di Fritto aggiunto sotto il menu")
        } else {
            Log.d("SecondActivity", "Contenuto di Fritto già presente, nessuna azione necessaria")
        }
    }
    private fun appendContentForSpeciali() {
        // Mostra il contenitore per il contenuto appendibile
        binding.appendableContentContainer.visibility = View.VISIBLE

        // Verifica se il contenuto è già stato aggiunto
        if (supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(
                binding.appendableContentContainer.id,
                BibiteFragment() // Fragment da appendere
            )
            transaction.commit()
            Log.d("SecondActivity", "Contenuto di Fritto aggiunto sotto il menu")
        } else {
            Log.d("SecondActivity", "Contenuto di Fritto già presente, nessuna azione necessaria")
        }
    }
}
