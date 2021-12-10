package com.example.retrofitapp.api


import com.example.retrofitapp.models.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {
    @GET("playlist/{id}")
    fun getPlayListById(@Path("id") id: String): Call<PlayList>
}