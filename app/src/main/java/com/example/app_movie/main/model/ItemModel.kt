package com.example.app_movie.main.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("title")
    var title: String,

    @SerializedName("link")
    var link: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("subtitle")
    var subtitle: String,

    @SerializedName("pubDate")
    var pubDate: String,

    @SerializedName("director")
    @Expose
    var director: String,

    @SerializedName("actor")
    var actor: String,

    @SerializedName("userRating")
    var userRating: String
)
