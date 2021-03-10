package com.example.testmovieapp.repository

import androidx.lifecycle.LiveData
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.datasource.NetworkDataSource
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.details.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: TMDBapi) {

    lateinit var networkDataSource: NetworkDataSource

    fun fetchMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<MovieDetails>{
        networkDataSource = NetworkDataSource(apiService, compositeDisposable)
        networkDataSource.fetchMovieDetails(movieId)
        return networkDataSource.movieDetailsResponse
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return networkDataSource.networkState
    }

}