package com.example.jci.network

import com.example.jci.data.Root
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query


interface BackEndApi{
    @POST("nearbysearch/json?")
    fun getPlaces(
        @Query("location") lat_long: String, @Query("radius") radius: Int,
        @Query("types") placetype: String,
        @Query("key") api_key: String): Call<Root>
}