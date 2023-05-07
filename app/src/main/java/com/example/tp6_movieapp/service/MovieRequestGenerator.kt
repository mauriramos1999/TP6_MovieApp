package com.example.tp6_movieapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {

    private const val AUTHORIZATION = "Authorization"
    private const val API_MOVIE_URL = "https://api.themoviedb.org"

    private val httpClient = OkHttpClient.Builder().addInterceptor{ chain ->
        val defaultRequest = chain.request()
        val defaultHttpUrl = defaultRequest.url
        val httpUrl = defaultHttpUrl.newBuilder()
            .build()
        val requestBuilder = defaultRequest.newBuilder()
            .header(AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDAxOGJlNWVhMmY0MWE2Mzk1OWU4ODkxZmIxM2YwMSIsInN1YiI6IjY0NGFmMDE5NTk2YTkxMDUyMTU3NmMxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jR_KWowjGSDayM82stNIWoCv8Ps1Wtj5Gel6dFCeUPY")
            .url(httpUrl)
        chain.proceed(requestBuilder.build())
    }

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}