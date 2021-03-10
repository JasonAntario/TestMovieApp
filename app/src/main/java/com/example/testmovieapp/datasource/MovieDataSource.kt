package com.example.testmovieapp.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.testmovieapp.BuildConfig
import com.example.testmovieapp.Constants
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.preview.MoviePreview
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(private val apiService: TMDBapi, private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, MoviePreview>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviePreview>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPopularMovies(Constants.FIRST_PAGE, BuildConfig.TMDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.result, null, Constants.FIRST_PAGE + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e(javaClass.name, "loadInitial: ${it.message}")
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePreview>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePreview>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPopularMovies(params.key, BuildConfig.TMDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.result, params.key + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e(javaClass.name, "loadInitial: ${it.message}")
                    }
                )
        )
    }
}