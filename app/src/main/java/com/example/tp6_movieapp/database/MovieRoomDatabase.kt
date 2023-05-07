package com.example.tp6_movieapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp6_movieapp.database.dao.MovieDAO
import com.example.tp6_movieapp.database.entity.MovieEntity


@Database(entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}