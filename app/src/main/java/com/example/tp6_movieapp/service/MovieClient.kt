package com.example.tp6_movieapp.service

import com.example.tp6_movieapp.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieClient {

    @GET("/3/movie/popular")
    fun getPopularMovies(): Call<MovieList>

}