package com.example.schedacibo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedacibo.Adapter.BibiteAdapter
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.DetailActivity.BibiteDetailActivity
import com.example.schedacibo.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var bibiteList: ArrayList<Bibite> = arrayListOf()
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        // Ricevi i dati passati dal MainActivity
        arguments?.getParcelableArrayList<Bibite>("bibiteList")?.let {
            bibiteList = it
        }

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Crea l'adapter e gestisci il clic su un elemento
        val adapter = BibiteAdapter(bibiteList) { selectedBibite ->
            BibiteDetailActivity.startActivity(requireActivity() as AppCompatActivity, selectedBibite)
        }

        binding.recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(bibiteList: ArrayList<Bibite>): ShopFragment {
            val fragment = ShopFragment()
            val args = Bundle()
            args.putParcelableArrayList("bibiteList", bibiteList)
            fragment.arguments = args
            return fragment
        }
    }
}
