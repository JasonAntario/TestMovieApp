package com.example.testmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.testmovieapp.repository.MovieListRepository
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.pojo.preview.MoviePreview
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(private val movieRepository: MovieListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePreviewPagedList: LiveData<PagedList<MoviePreview>> by lazy {
        movieRepository.fetchMoviePagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}