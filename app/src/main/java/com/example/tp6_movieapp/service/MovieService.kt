package com.example.tp6_movieapp.service

import com.example.tp6_movieapp.service.model.MovieList
import com.example.tp6_movieapp.util.CoroutineResult

class MovieService(private val movieClient: MovieClient) {

    suspend fun getMovies(): CoroutineResult<MovieList>{
        try {
            val response = movieClient.getPopularMovies().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it)
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        }
        catch(e: Exception){
            return CoroutineResult.Failure(e)
        }
    }
}