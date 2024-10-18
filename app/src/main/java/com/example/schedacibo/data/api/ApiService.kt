package com.itsmad.retrofitexample.data.api

import com.itsmad.retrofitexample.data.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //GET -> tipo
    //posts -> API come da documentazione
    //Call<List<Post>> -> dati in risposta alla chiamata
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}