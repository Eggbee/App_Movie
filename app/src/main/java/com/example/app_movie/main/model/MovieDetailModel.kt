package com.example.app_movie.main.model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("release_date")
    val releaseDate : String
)