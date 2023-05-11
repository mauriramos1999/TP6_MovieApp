package com.example.tp6_movieapp.mvvm.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp6_movieapp.database.MovieDatabase
import com.example.tp6_movieapp.mvvm.model.MainModel
import com.example.tp6_movieapp.service.MovieService
import com.example.tp6_movieapp.util.CoroutineResult
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class MainViewModelTest{

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainViewModel
    private val serviceMock = mockkClass(MovieService::class)
    private val databaseMock = mockkClass(MovieDatabase::class)
    val model: MainModel = MainModel(serviceMock, databaseMock)

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(model)
    }

    @Test
    fun initialStateTest(){
        assert(viewModel.getValue().value == null)
    }

    @Test
    fun `when service called with success`() = runBlocking{
        //viewModel.callService()
        val res = serviceMock.getMovies()
        if(res == CoroutineResult.Success){
            assert(databaseMock)
        }


    }

}