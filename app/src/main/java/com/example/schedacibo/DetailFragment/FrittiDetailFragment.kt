package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailBinding
import com.squareup.picasso.Picasso

class FrittiDetailFragment : Fragment() {

    // Variabile per il View Binding
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inizializza il binding
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera i dati passati come argomenti
        val immagine = arguments?.getString("immagine")
        val nome = arguments?.getString("nome")
        val prezzo = arguments?.getString("prezzo")
        val ingredienti = arguments?.getString("ingredienti")
        val tipologia = arguments?.getString("tipologia")

        // Imposta i dati
        binding.detailName.text = nome
        binding.tipologia.text = tipologia
        binding.ingredienti.text = ingredienti
        binding.detailPrice.text = prezzo
        Picasso.get().load(immagine).into(binding.detailImage)

        // Configura il pulsante indietro per tornare a SecondActivity
        binding.backButton.setOnClickListener {
            // Crea un Intent per tornare all'Activity
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)

            // Chiudi il Fragment per evitare duplicati
            requireActivity().finish()
        }
    }


    companion object {
        fun newInstanceFritti (fritti: Fritti): ProductDetailFragment {
            val fragment = ProductDetailFragment()
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
