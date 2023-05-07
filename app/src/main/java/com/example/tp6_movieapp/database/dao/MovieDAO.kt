package com.example.tp6_movieapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp6_movieapp.database.entity.MovieEntity

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getMovies():List<MovieEntity>
}