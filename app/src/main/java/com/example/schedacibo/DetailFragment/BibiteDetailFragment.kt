package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailBibiteBinding
import com.squareup.picasso.Picasso

class BibiteDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBibiteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBibiteBinding.inflate(inflater, container, false)
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
            intent.putExtra("SELECTED_TAB", "bibite")  // Specifiy the bibite tab
            startActivity(intent)
        }
        // Listener per il pulsante "Aggiungi"
        binding.addToCart.setOnClickListener {
            addToCart(nome, prezzo)
        }

    }

    private fun addToCart(nome: String?, prezzo: String?) {
        // Logica di gestione
        val prodottoAggiunto = "Prodotto aggiunto al carrello: $nome ($prezzo)"
        Toast.makeText(requireContext(), prodottoAggiunto, Toast.LENGTH_SHORT).show()

        // Puoi aggiungere ulteriori azioni come inviare dati a un ViewModel o un'Activity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstanceBibite (bibite: Bibite): BibiteDetailFragment {
            val fragment = BibiteDetailFragment()
            val args = Bundle().apply {
                putString("nome", bibite.nome)
                putString("tipologia", bibite.tipologia)
                putString("ingredienti", bibite.ingredienti)
                putString("prezzo", bibite.prezzo)
                putString("immagine", bibite.immagine)
            }
            fragment.arguments = args
            return fragment
        }
    }
}