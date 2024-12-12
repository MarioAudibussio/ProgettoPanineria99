package com.example.schedacibo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedacibo.databinding.FragmentShopBinding
import com.example.schedacibo.viewModel.CartViewModel
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.DetailFragment.ProductDetailFragment
import com.example.schedacibo.R

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()
    private lateinit var paniniAdapter: PaniniAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView with Panini items
        paniniAdapter = PaniniAdapter({ panini ->
            // Optional: Add click listener for panini items
            cartViewModel.addToCart(panini)
        }) { panino ->
            // Nascondi il tab_container se esiste
            val tabContainer = requireActivity().findViewById<View>(R.id.tab_container)
            tabContainer?.visibility = View.GONE

            // Questo codice verrà eseguito quando un prodotto viene cliccato
            val productDetailFragment = ProductDetailFragment.newInstance(Panini)
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    productDetailFragment
                ) // Sostituisci con il contenitore del Fragment
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = paniniAdapter
        }

        // Observe and update panini list
        cartViewModel.cartItems.observe(viewLifecycleOwner) { paniniList ->
            paniniAdapter.submitList(paniniList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}