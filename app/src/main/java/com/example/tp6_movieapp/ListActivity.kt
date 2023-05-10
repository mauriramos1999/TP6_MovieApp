package com.example.tp6_movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tp6_movieapp.adapter.MoviesAdapter
import com.example.tp6_movieapp.database.MovieDatabaseImpl
import com.example.tp6_movieapp.database.MovieRoomDatabase
import com.example.tp6_movieapp.databinding.ActivityListBinding
import com.example.tp6_movieapp.mvvm.contract.MainContract
import com.example.tp6_movieapp.mvvm.model.MainModel
import com.example.tp6_movieapp.mvvm.viewModel.MainViewModel
import com.example.tp6_movieapp.mvvm.viewModel.ViewModelFactory
import com.example.tp6_movieapp.service.MovieClient
import com.example.tp6_movieapp.service.MovieRequestGenerator
import com.example.tp6_movieapp.service.MovieService

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: MainContract.ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
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
        //binding.textview.text = x.movies.toString()
        when(x.mainStatus){
            MainViewModel.MainStatus.SHOW_INFO ->{
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = MoviesAdapter(x.movies)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}