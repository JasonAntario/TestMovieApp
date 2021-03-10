package com.example.testmovieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testmovieapp.Constants
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.datasource.MovieDataSource
import com.example.testmovieapp.datasource.MovieDataSourceFactory
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.preview.MoviePreview
import io.reactivex.disposables.CompositeDisposable

class MovieListRepository(private val apiService: TMDBapi) {

    lateinit var moviePreviewPagedList: LiveData<PagedList<MoviePreview>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<MoviePreview>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.MOVIES_ON_PAGE)
            .build()

        moviePreviewPagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()
        return moviePreviewPagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            movieDataSourceFactory.movieLiveDateSource,
            MovieDataSource::networkState
        )
    }
}