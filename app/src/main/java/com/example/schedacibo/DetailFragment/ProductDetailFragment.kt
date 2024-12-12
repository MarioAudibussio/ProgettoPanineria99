package com.example.schedacibo.DetailFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.SecondActivity
import com.example.schedacibo.databinding.FragmentProductDetailBinding
import com.example.schedacibo.viewModel.CartViewModel
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    // Variabile per il View Binding
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()

    private var currentQuantity = 1

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

        // Quantity controls
        binding.increaseQuantity.setOnClickListener {
            if (currentQuantity > 1) {
                currentQuantity--
                updateQuantityDisplay()
            }
        }

        binding.decreaseQuantity.setOnClickListener {
            currentQuantity++
            updateQuantityDisplay()
        }

        // Add to cart functionality
        binding.addToCart.setOnClickListener {
            val cartItem = Panini(
                nome = nome ?: "",
                tipologia = tipologia ?: "",
                ingredienti = ingredienti ?: "",
                prezzo = prezzo ?: "",
                immagine = immagine ?: "",
                quantita = currentQuantity

            )

            cartViewModel.addToCart(cartItem)

            // Navigate back or show a confirmation
            parentFragmentManager.popBackStack()
        }


        // Configura il pulsante indietro per tornare a SecondActivity
        binding.backButton.setOnClickListener {
            // Crea un Intent per tornare all'Activity
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)

            // Chiudi il Fragment per evitare duplicati
            requireActivity().finish()
        }
    }
    private fun updateQuantityDisplay() {
        // Update UI to show current quantity
        // You might want to add a TextView to show the current quantity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita memory leaks
    }

    companion object {
        fun newInstance(panini:Panini): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle().apply {
                putString("nome", panini.nome)
                putString("tipologia", panini.tipologia)
                putString("ingredienti", panini.ingredienti)
                putString("prezzo", panini.prezzo)
                putString("immagine", panini.immagine)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
