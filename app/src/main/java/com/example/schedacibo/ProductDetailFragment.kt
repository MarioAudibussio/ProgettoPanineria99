package com.example.schedacibo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        val nomeTextView: TextView = view.findViewById(R.id.detailName)
        val descrizioneTextView: TextView = view.findViewById(R.id.detailDescription)
        val prezzoTextView: TextView = view.findViewById(R.id.detailPrice)
        val immagineImageView: ImageView = view.findViewById(R.id.detailImage)

        // Recupera i dati passati come argomenti
        val nome = arguments?.getString("nome")
        val descrizione = arguments?.getString("descrizione")
        val prezzo = arguments?.getString("prezzo")
        val immagine = arguments?.getString("immagine")

        // Imposta i dati
        nomeTextView.text = nome
        descrizioneTextView.text = descrizione
        prezzoTextView.text = prezzo
        Picasso.get().load(immagine).into(immagineImageView)

        return view
    }

    companion object {
        fun newInstance(prodotto: Prodotto): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle().apply {
                putString("nome", prodotto.nome)
                putString("descrizione", prodotto.descrizione)
                putString("prezzo", prodotto.prezzo)
                putString("immagine", prodotto.immagine)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
