package com.example.app_movie.main.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val id : Int,
    val title : String,
    val overview : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("vote_count")
    val voteCount : Int,
    @SerializedName("release_date")
    val releaseDate : String,
    val video : Boolean
)