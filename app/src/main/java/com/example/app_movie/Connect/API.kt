package com.example.app_movie.Connect

import com.example.app_movie.Main.Model.ExampleModel
import com.example.app_movie.Main.Model.ItemModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {
    @GET("base")
    fun getMovie(@Header("X-Naver-Client-Id") id:String, @Header("X-Naver-Client-Secret") secret:String, @Query("query") query: String, @Query("display") display: Int):Call<ExampleModel>
}