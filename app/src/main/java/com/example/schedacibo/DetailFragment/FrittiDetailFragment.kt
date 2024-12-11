package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailFrittiBinding
import com.squareup.picasso.Picasso

class FrittiDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailFrittiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailFrittiBinding.inflate(inflater, container, false)
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
            intent.putExtra("SELECTED_TAB", "fritti")  // Specifiy the bibite tab
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstanceFritti (fritti: Fritti): FrittiDetailFragment {
            val fragment = FrittiDetailFragment()
            val args = Bundle().apply {
                putString("nome", fritti.nome)
                putString("tipologia", fritti.tipologia)
                putString("ingredienti", fritti.ingredienti)
                putString("prezzo", fritti.prezzo)
                putString("immagine", fritti.immagine)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
