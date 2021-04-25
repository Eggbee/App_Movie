package com.example.app_movie.main.model

data class BaseResponse<T>(
    val results: ArrayList<T>? = null
)