package com.example.schedacibo.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schedacibo.R
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.paniniView.setOnClickListener {

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "panini")
            startActivity(intent)

        }
        binding.frittiView.setOnClickListener {

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "fritti")
            startActivity(intent)

        }
        binding.bibiteView.setOnClickListener {

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "bibite")
            startActivity(intent)

        }
        binding.hamburgerSpecialiView.setOnClickListener {

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "hamburgerSpeciali")
            startActivity(intent)

        }
        binding.vaschetteView.setOnClickListener {

            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "vaschette")
            startActivity(intent)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}