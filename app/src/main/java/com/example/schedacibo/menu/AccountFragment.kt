package com.example.schedacibo.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schedacibo.Adapter.AccountAdapter
import com.example.schedacibo.DataClass.Account
import com.example.schedacibo.FormActivity
import com.example.schedacibo.MainActivity
import com.example.schedacibo.R
import com.example.schedacibo.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!! // Accesso sicuro al binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inizializza il binding
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bottone Modifica
        binding.modificaProfilo.setOnClickListener {
            val intent = Intent(requireContext(), FormActivity::class.java)
            startActivity(intent)
        }



        // Configura la lista degli account
        val accounts = listOf(
            Account(
                icon = R.drawable.account_lingua,
                name = "Lingua",
                arrow = R.drawable.account_arrow,
                type = "impostazioni"
            ),
            Account(
                icon = R.drawable.account_indirizzo,
                name = "Indirizzo",
                arrow = R.drawable.account_arrow,
                type = "indirizzo"
            ),
            Account(
                icon = R.drawable.account_version,
                name = "Version",
                arrow = R.drawable.account_arrow,
                type = "version"
            )
        )

        // Imposta l'adapter
        val adapter = AccountAdapter(requireContext(), accounts)
        binding.accountList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Libera il binding per evitare memory leak
        _binding = null
    }


    companion object {
        fun newInstance() = AccountFragment()
    }

}