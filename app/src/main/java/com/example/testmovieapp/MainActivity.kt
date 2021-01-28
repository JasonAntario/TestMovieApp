package com.example.testmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmovieapp.data.network.ITmdbApi
import com.example.testmovieapp.data.network.RequestHelper
import com.example.testmovieapp.data.repository.NetworkState
import com.example.testmovieapp.movie_details.MovieDetailsFragment
import com.example.testmovieapp.movie_details.MovieDetailsRepository
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainActivity : AppCompatActivity() {

    lateinit var detailsViewModel: MovieDetailsViewModel
    lateinit var detailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieId = 464052
        val apiService: ITmdbApi = RequestHelper.getClient()

        detailsRepository = MovieDetailsRepository(apiService)

        detailsViewModel = getViewModel(movieId)
        detailsViewModel.movieDetails.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MovieDetailsFragment(it)).commit()

        })

        detailsViewModel.networkState.observe(this, Observer {
            findViewById<LinearProgressIndicator>(R.id.loading).visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun getViewModel(movieId: Int): MovieDetailsViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieDetailsViewModel(detailsRepository, movieId) as T
            }
        })[MovieDetailsViewModel::class.java]

    }
}