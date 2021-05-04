package com.example.app_movie.connect

import com.example.app_movie.main.model.BaseResponse
import com.example.app_movie.main.model.MovieDetailModel
import com.example.app_movie.main.model.MovieModel
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("search/multi")
    fun searchMovie(
        @Query("language") language: String = "ko",
        @Query("query") query: String
    ): Single<BaseResponse<MovieModel>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String = "ko"
    ): Single<MovieDetailModel>

    @GET("movie/{movie_id}/videos")
    fun getVideoInfo(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String = "ko"
    ): Single<BaseResponse<JsonObject>>
}