package com.example.testmovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmovieapp.data.pojo.MovieInfo

class MovieDetailsViewModel : ViewModel() {

    private var popularMoviesLiveData = MutableLiveData<MovieInfo>()
    val popularMovieList: LiveData<MovieInfo> get() = popularMoviesLiveData

    override fun onCleared() {
        super.onCleared()
    }
}