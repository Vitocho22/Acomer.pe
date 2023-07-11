package com.sinche.victor.loginyregister.restaurant.Api

import retrofit2.Call
import retrofit2.http.GET

interface ConsumirApi {
    @GET("restaurante")
    fun getTraer(): Call<Restaurante>
}