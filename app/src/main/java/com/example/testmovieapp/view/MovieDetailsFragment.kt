package com.example.testmovieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testmovieapp.Constants
import com.example.testmovieapp.viewmodel.MovieDetailsViewModel
import com.example.testmovieapp.R
import com.example.testmovieapp.repository.MovieDetailsRepository
import com.example.testmovieapp.network.Client
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.details.Genre
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textview.MaterialTextView

class MovieDetailsFragment(private val movieId: Int) : Fragment() {


    lateinit var detailsViewModel: MovieDetailsViewModel
    lateinit var detailsRepository: MovieDetailsRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            view.findViewById<MaterialTextView>(R.id.info_title).text = it.originalTitle
            view.findViewById<MaterialTextView>(R.id.infoDescription).text = it.overview
            view.findViewById<AppCompatRatingBar>(R.id.info_rating).rating = it.rating.toFloat()
            view.findViewById<MaterialTextView>(R.id.info_release_date).text = it.releaseDate
            view.findViewById<MaterialTextView>(R.id.info_runtime).text = it.runtime.toString()
            view.findViewById<MaterialTextView>(R.id.info_tagline).text = it.tagline
            val backdropUrl = Constants.POSTER_BASE_URL + it.backdropPath
            Glide.with(this)
                .load(backdropUrl)
                .into(view.findViewById(R.id.info_backdrop))
            addChips(it.genres, view.findViewById(R.id.info_genre))
        })
        detailsViewModel.networkState.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun getViewModel(movieId: Int): MovieDetailsViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieDetailsViewModel(detailsRepository, movieId) as T
            }
        })[MovieDetailsViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val apiService: TMDBapi = Client.getClient()
        detailsRepository = MovieDetailsRepository(apiService)
        detailsViewModel = getViewModel(movieId)
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    private fun addChips(genres: List<Genre>, chips: ChipGroup) {
        for (i in genres.indices) {
            val chip = Chip(context)
            chip.text = genres[i].name
            chips.addView(chip)
        }
    }
}