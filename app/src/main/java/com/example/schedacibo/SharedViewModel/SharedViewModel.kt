package com.example.schedacibo.SharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedacibo.DataClass.Bibite

class SharedViewModel : ViewModel() {
    private val _bibiteList = MutableLiveData<MutableList<Bibite>>(mutableListOf())
    val bibiteList: LiveData<MutableList<Bibite>> = _bibiteList

    fun addBibita(bibita: Bibite) {
        val currentList = _bibiteList.value ?: mutableListOf()
        currentList.add(bibita)
        _bibiteList.value = currentList
    }
}