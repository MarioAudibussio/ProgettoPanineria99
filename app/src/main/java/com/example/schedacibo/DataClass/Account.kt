package com.example.schedacibo.DataClass

data class Account(
    val icon: Int,        // Resource ID for the left icon
    val name: String,     // Name of the account item
    val arrow: Int,       // Resource ID for the right arrow icon
    val type: String      // Type or category of the account item
)