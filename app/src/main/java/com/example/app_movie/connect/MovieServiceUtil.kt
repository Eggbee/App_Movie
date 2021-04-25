package com.example.app_movie.connect

import com.example.app_movie.main.model.BaseResponse
import com.example.app_movie.main.model.MovieModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceUtil {
    @GET("movie/popular")
    fun getPopularMovieInfo(
        @Query("language") language: String = "ko",
        @Query("page") page: Int
    ): Single<BaseResponse<MovieModel>>

    @GET("movie/latest")
    fun getLastedMovie(): Single<BaseResponse<MovieModel>>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("language") language: String = "ko",
        @Query("page") page: Int
    ): Single<BaseResponse<MovieModel>>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("language") language: String = "ko",
        @Query("page") page: Int
    ): Single<BaseResponse<MovieModel>>

    @GET("movie/upcoming")
    fun getUpComingMovie(
        @Query("language") language: String = "ko",
        @Query("page") page: Int
    ): Single<BaseResponse<MovieModel>>
}