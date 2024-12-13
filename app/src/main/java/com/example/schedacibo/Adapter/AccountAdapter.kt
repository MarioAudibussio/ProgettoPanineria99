package com.example.schedacibo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.schedacibo.DataClass.Account
import com.example.schedacibo.R

class AccountAdapter(
    context: Context,
    private val accounts: List<Account>
) : ArrayAdapter<Account>(context, 0, accounts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate the view if it doesn't exist
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_impostazione1, parent, false)

        // Get the current account item
        val currentAccount = accounts[position]

        // Find views in the layout
        val leftIconImageView: ImageView = itemView.findViewById(R.id.icona)
        val nameTextView: TextView = itemView.findViewById(R.id.mtrl_list_item_text)
        val rightIconImageView: ImageView = itemView.findViewById(R.id.freccia)

        // Set the values
        leftIconImageView.setImageResource(currentAccount.icon)
        nameTextView.text = currentAccount.name
        rightIconImageView.setImageResource(currentAccount.arrow)

        return itemView
    }
}