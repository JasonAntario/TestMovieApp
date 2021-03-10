package com.example.testmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testmovieapp.repository.MovieDetailsRepository
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.pojo.details.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel (movieDetailsRepository: MovieDetailsRepository, movieId: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieDetailsRepository.fetchMovieDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}