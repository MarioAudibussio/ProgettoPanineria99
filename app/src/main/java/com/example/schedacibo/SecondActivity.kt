package com.example.schedacibo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.schedacibo.databinding.ActivitySecondBinding
import com.example.schedacibo.topMenu.BibiteFragment
import com.example.schedacibo.topMenu.FrittoFragment
import com.example.schedacibo.topMenu.HamburgerSpecialeFragment
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


        val tabPosition = when (selectedTab) {
            "panini" -> 0
            "fritti" -> 1
            "bibite" -> 2
            "hamburgerSpeciali" -> 3
            "vaschette" -> 4
            else -> 0
        }
        binding.tablayout.getTabAt(tabPosition)?.select()

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                Log.d("SecondActivity", "Tab selezionato: ${tab.position}")

                val fragment: Fragment? = when (tab.position) {
                    0 -> PaniniFragment()
                    1 -> FrittoFragment()
                    2 -> BibiteFragment()
                    3 -> HamburgerSpecialeFragment()
                    4 -> VaschetteFragment()
                    else -> null
                }

                fragment?.let {
                    Log.d("SecondActivity", "Fragment sostituito: ${fragment::class.java.simpleName}")
                    replaceFragment(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, fragment)
        transaction.commit()
        // Log per confermare la transazione
        Log.d("SecondActivity", "Fragment transaction committed")
    }
}