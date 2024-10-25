package com.example.schedacibo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.schedacibo.data.model.Post
import com.example.schedacibo.data.api.RetrofitClient


class MainViewModel : ViewModel() {

    val posts = MutableLiveData<List<Post>>()
    val error = MutableLiveData<String>()

    fun getPosts() {
        RetrofitClient.retrofit.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, id: Response<List<Post>>) {
                posts.value = id.body()
            }

            override fun onFailure(call: Call<List<Post>>, id: Throwable) {
                error.value = "errore nella chiamata"
            }
        })
    }
}
