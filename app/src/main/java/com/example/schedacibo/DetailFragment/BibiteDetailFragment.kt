package com.example.schedacibo.DetailFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.R
import com.squareup.picasso.Picasso

class BibiteDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail_bibite, container, false)

        val nomeTextView: TextView = view.findViewById(R.id.detailName)
        val tipologiaTextView: TextView = view.findViewById(R.id.tipologia)
        val ingredientiTextView: TextView = view.findViewById(R.id.ingredienti)
        val prezzoTextView: TextView = view.findViewById(R.id.detailPrice)
        val immagineImageView: ImageView = view.findViewById(R.id.detailImage)

        // Recupera i dati passati come argomenti
        val immagine = arguments?.getString("immagine")
        val nome = arguments?.getString("nome")
        val prezzo = arguments?.getString("prezzo")
        val ingredienti = arguments?.getString("ingredienti")
        val tipologia = arguments?.getString("tipologia")

        // Imposta i dati
        nomeTextView.text = nome
        tipologiaTextView.text = tipologia
        ingredientiTextView.text = ingredienti
        prezzoTextView.text = prezzo
        Picasso.get().load(immagine).into(immagineImageView)

        return view
    }

    companion object {
        fun newInstanceBibite(bibite: Bibite): BibiteDetailFragment {
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
