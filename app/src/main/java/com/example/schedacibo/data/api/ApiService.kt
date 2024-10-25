package com.example.schedacibo.data.api

import retrofit2.Response
import retrofit2.http.GET
import com.example.schedacibo.data.model.Post
import retrofit2.Call

interface ApiService {

    @GET("1296768180654301184")
    fun getPosts(): Call<List<Post>>

}