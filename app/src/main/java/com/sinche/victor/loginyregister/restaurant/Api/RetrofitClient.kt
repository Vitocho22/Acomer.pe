package com.sinche.victor.loginyregister.restaurant.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://demo6225783.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val consumirApi = retrofit.create(ConsumirApi::class.java)
}