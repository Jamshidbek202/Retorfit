package com.example.retrofitproject.Network

import com.example.retrofitproject.Models.Album
import com.example.retrofitproject.Models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("albums")
    fun getAlbums(@Query("userId") userID : String): Call<ArrayList<Album>>

    companion object {
        fun GetResponse(): API {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(API::class.java)
        }
    }
}