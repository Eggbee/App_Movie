package com.example.app_movie.connect

import com.example.app_movie.main.model.ExampleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/")
    fun getMovie(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<ExampleModel>

    @GET("/v1/search/movie")
    fun getMovieCategory(
        @Query("query") query: String,
        @Query("genre") category: Int
    ): Call<ExampleModel>
}