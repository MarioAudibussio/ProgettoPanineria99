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

        if (intent.hasExtra("NAVIGATE_TO_FRAGMENT")) {
            when (intent.getStringExtra("NAVIGATE_TO_FRAGMENT")) {
                "account" -> {
                    // Naviga al fragment Account
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AccountFragment())
                        .commit()

                    // Imposta la bottom navigation sull'item Account
                    binding.buttomNavigationView.selectedItemId = R.id.account
                }
            }
        }

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