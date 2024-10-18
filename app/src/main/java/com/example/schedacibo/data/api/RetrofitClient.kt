package com.itsmad.retrofitexample.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Classe che si occupa della conversione
                    .build()
                    .create(ApiService::class.java)
}