package com.example.schedacibo.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.schedacibo.DataClass.Bibite
import android.widget.TextView
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class ShopFragment : Fragment() {

    private var bibite: Bibite? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bibite = it.getParcelable(ARG_BIBITE)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Usa l'oggetto Bibite per popolare la vista
        bibite?.let { prodotto ->
            val imageView = view.findViewById<ImageView>(R.id.imageProduct)
            val nameTextView = view.findViewById<TextView>(R.id.nameProduct)
            val priceTextView = view.findViewById<TextView>(R.id.priceProduct)
            val descriptionTextView = view.findViewById<TextView>(R.id.descriptionProduct)

            // Usa Picasso per caricare l'immagine
            Picasso.get().load(prodotto.immagine).into(imageView)

            nameTextView.text = prodotto.nome
            priceTextView.text = prodotto.prezzo
            descriptionTextView.text = prodotto.tipologia
        }


    }

    companion object {
        private const val ARG_BIBITE = "bibite"

        fun newInstanceBibite1(bibite: Bibite): ShopFragment {
            return ShopFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_BIBITE, bibite)
                }
            }
        }
    }

}
