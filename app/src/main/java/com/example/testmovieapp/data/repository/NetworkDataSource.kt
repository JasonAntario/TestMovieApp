package com.example.testmovieapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmovieapp.data.network.ITmdbApi
import com.example.testmovieapp.data.pojo.MovieDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class NetworkDataSource(private val apiService: ITmdbApi, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> get() = _networkState

    private val _movieDetailsResponse = MutableLiveData<MovieDetails>()
    val movieDetailsResponse: LiveData<MovieDetails> get() = _movieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _movieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e(javaClass.name,  it.message!!)
                        }
                    )
            )

        } catch (e: Exception) {
            Log.e(javaClass.name,  e.message!!)
        }
    }
}