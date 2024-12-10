package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Vaschette
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailVaschetteBinding
import com.squareup.picasso.Picasso

class VaschetteDetailFragment : Fragment() {

    // Variabile per il View Binding
    private var _binding: FragmentProductDetailVaschetteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inizializza il binding
        _binding = FragmentProductDetailVaschetteBinding.inflate(inflater, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita memory leaks
    }

    companion object {
        fun newInstanceVaschette(vaschette: Vaschette): VaschetteDetailFragment {
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
