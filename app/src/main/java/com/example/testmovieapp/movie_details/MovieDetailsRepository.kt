package com.example.testmovieapp.movie_details

import androidx.lifecycle.LiveData
import com.example.testmovieapp.data.network.ITmdbApi
import com.example.testmovieapp.data.pojo.MovieDetails
import com.example.testmovieapp.data.repository.NetworkDataSource
import com.example.testmovieapp.data.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: ITmdbApi) {

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