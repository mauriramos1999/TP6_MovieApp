package com.example.tp6_movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.tp6_movieapp.database.MovieDatabase
import com.example.tp6_movieapp.database.MovieDatabaseImpl
import com.example.tp6_movieapp.database.MovieRoomDatabase
import com.example.tp6_movieapp.databinding.ActivityMainBinding
import com.example.tp6_movieapp.mvvm.contract.MainContract
import com.example.tp6_movieapp.mvvm.model.MainModel
import com.example.tp6_movieapp.mvvm.viewModel.MainViewModel
import com.example.tp6_movieapp.mvvm.viewModel.ViewModelFactory
import com.example.tp6_movieapp.service.MovieClient
import com.example.tp6_movieapp.service.MovieRequestGenerator
import com.example.tp6_movieapp.service.MovieService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainContract.ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBase: MovieRoomDatabase by lazy {
            Room.databaseBuilder(this, MovieRoomDatabase::class.java, "movie-DataBase")
                .build()
        }

        viewModel = ViewModelProvider(this,
            ViewModelFactory(
                arrayOf(
                    MainModel(
                        MovieService(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDatabaseImpl(dataBase.movieDao())
                    )
        )
        )).
        get(MainViewModel::class.java)

        viewModel.getValue().observe(this){updateUI(it)}
    }

    private fun updateUI(x: MainViewModel.MainData){
        binding.textview.text = x.movies.toString()
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}