package com.example.tp6_movieapp.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.tp6_movieapp.mvvm.viewModel.MainViewModel
import com.example.tp6_movieapp.service.model.Movie
import com.example.tp6_movieapp.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {

    interface ViewModel{
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }

    interface Model{
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

}