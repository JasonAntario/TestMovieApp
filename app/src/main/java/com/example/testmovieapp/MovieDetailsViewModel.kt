package com.example.testmovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testmovieapp.data.pojo.MovieDetails
import com.example.testmovieapp.data.repository.NetworkState
import com.example.testmovieapp.movie_details.MovieDetailsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

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