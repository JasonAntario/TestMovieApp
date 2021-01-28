package com.example.testmovieapp.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testmovieapp.R
import com.example.testmovieapp.data.pojo.Genre
import com.example.testmovieapp.data.pojo.MovieDetails
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MovieDetailsFragment (private val movieDetails: MovieDetails): Fragment() {

    companion object {
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        movieDetails.let {
            view.findViewById<TextView>(R.id.infoTitle).text = it.originalTitle
            view.findViewById<TextView>(R.id.infoDescription).text = it.overview
            view.findViewById<RatingBar>(R.id.infoRating).rating = it.rating.toFloat()
            view.findViewById<TextView>(R.id.infoReleaseDate).text = it.releaseDate
            val moviePosterUrl = POSTER_BASE_URL + it.posterPath
            Glide.with(this)
                .load(moviePosterUrl)
                .into(view.findViewById(R.id.moviePoster))

            addChips(it.genres,view.findViewById(R.id.infoGenre))

        }

        return view
    }

    private fun addChips(genres: List<Genre>, chips: ChipGroup){
        for(i in genres.indices){
            val chip = Chip(context)
            chip.text = genres[i].name
            chips.addView(chip)
        }
    }
}