package com.intact.moviesbox.data.repository

interface RemoteDataSource {
    fun getPopularMovies()
    fun getTopRatedMovies()

}