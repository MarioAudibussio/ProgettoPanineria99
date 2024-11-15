package com.example.schedacibo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}