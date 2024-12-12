package com.example.schedacibo.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schedacibo.DataClass.Bibite
import android.widget.TextView
import com.example.schedacibo.R

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
            view.findViewById<TextView>(R.id.nameProduct).text = prodotto.nome
            view.findViewById<TextView>(R.id.priceProduct).text = prodotto.prezzo
            // Puoi anche mostrare altri dettagli del prodotto
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
