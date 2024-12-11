package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Vaschette
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailBinding
import com.squareup.picasso.Picasso

class VaschetteDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val immagine = arguments?.getString("immagine")
        val nome = arguments?.getString("nome")
        val prezzo = arguments?.getString("prezzo")
        val ingredienti = arguments?.getString("ingredienti")
        val tipologia = arguments?.getString("tipologia")

        binding.detailName.text = nome
        binding.tipologia.text = tipologia
        binding.ingredienti.text = ingredienti
        binding.detailPrice.text = prezzo
        Picasso.get().load(immagine).into(binding.detailImage)

        binding.backButton.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("SELECTED_TAB", "hamburgerSpeciali")  // Specifiy the bibite tab
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstanceVaschette (vaschette: Vaschette): VaschetteDetailFragment {
            val fragment = VaschetteDetailFragment()
            val args = Bundle().apply {
                putString("nome", vaschette.nome)
                putString("tipologia", vaschette.tipologia)
                putString("ingredienti", vaschette.ingredienti)
                putString("prezzo", vaschette.prezzo)
                putString("immagine", vaschette.immagine)
            }
            fragment.arguments = args
            return fragment
        }
    }
}