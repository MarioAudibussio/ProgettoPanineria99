package com.example.schedacibo

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)
        val frameLayout = findViewById<FrameLayout>(R.id.framelayout)
        val tabLayout = findViewById<TabLayout>(R.id.tablayout)

        // Imposta il fragment iniziale
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, PaniniFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()

        // Listener per il TabLayout
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var fragment: Fragment? = null
                when (tab.position) {
                    0 -> fragment = PaniniFragment()
                    1 -> fragment = FrittiFragment()
                    2 -> fragment = BibiteFragment()
                    3 -> fragment = HamburgerSpecialiFragment()
                    4 -> fragment = VaschetteFragment()
                }

                fragment?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, it)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Azione opzionale per deselezionare il tab
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Azione opzionale per riselezionare il tab
            }
        })
    }
}