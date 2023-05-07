package com.example.tp6_movieapp.util

sealed class CoroutineResult<out T : Any> {
    class Success<out T : Any>(val data: T) : CoroutineResult<T>()
    class Failure(val exception: Exception) : CoroutineResult<Nothing>()
}