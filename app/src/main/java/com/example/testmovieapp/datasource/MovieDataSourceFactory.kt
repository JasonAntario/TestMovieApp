package com.example.testmovieapp.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.preview.MoviePreview
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService: TMDBapi, private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, MoviePreview>() {

    val movieLiveDateSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MoviePreview> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)
        movieLiveDateSource.postValue(movieDataSource)
        return movieDataSource
    }
}