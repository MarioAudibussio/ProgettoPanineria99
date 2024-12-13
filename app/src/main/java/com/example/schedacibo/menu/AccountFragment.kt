package com.example.schedacibo.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.schedacibo.Adapter.AccountAdapter
import com.example.schedacibo.DataClass.Account
import com.example.schedacibo.R

class AccountFragment : Fragment() {
    // Default implementation with onCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    // Add onViewCreated method to set up the list
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountList = view.findViewById<ListView>(R.id.accountList)

        // Create a list of Account items
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

            // Add more items as needed
        )

        // Create and set the adapter
        val adapter = AccountAdapter(requireContext(), accounts)
        accountList.adapter = adapter
    }

    companion object {
        // Optional factory method for creating fragment instances with arguments
        fun newInstance() = AccountFragment()
    }
}