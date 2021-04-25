package com.example.app_movie.connect

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connecter {
    var retrofit: Retrofit
    var api: API

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", "w3Sm4xnRiqhOIpNHgOY0")
                    .addHeader("X-Naver-Client-Secret", "m4NT9Ij63u")
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit
            .Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(API::class.java)
    }

    fun createApi() = retrofit.create(API::class.java)
}