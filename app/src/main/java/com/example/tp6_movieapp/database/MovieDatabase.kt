package com.example.tp6_movieapp.database

import com.example.tp6_movieapp.database.dao.MovieDAO
import com.example.tp6_movieapp.database.mapper.mapToDataBaseMovie
import com.example.tp6_movieapp.database.mapper.mapToLocalMovie
import com.example.tp6_movieapp.service.model.Movie


interface MovieDatabase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDatabaseImpl(private val movieDAO: MovieDAO) : MovieDatabase {

    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDAO.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDAO.getMovies().mapToLocalMovie()
    }
}