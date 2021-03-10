package com.example.testmovieapp.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmovieapp.Constants
import com.example.testmovieapp.R
import com.example.testmovieapp.pojo.preview.MoviePreview
import com.google.android.material.textview.MaterialTextView

class MoviesListViewHolder(view: View, private val clickListener: (MoviePreview) -> Unit) : RecyclerView.ViewHolder(view) {

    fun onBind(moviePreview: MoviePreview, context: Context) {
        val movieTitle = itemView.findViewById<MaterialTextView>(R.id.item_title)
        movieTitle.text = moviePreview.title
        val releaseDate = itemView.findViewById<MaterialTextView>(R.id.item_release_date)
        releaseDate.text = moviePreview.releaseDate
        val rating = itemView.findViewById<AppCompatRatingBar>(R.id.item_rating)
        rating.rating = moviePreview.rating.toFloat()
        val voteCount = itemView.findViewById<MaterialTextView>(R.id.item_vote_count)
        voteCount.text = moviePreview.votes.toString()
        val moviePosterUrl = Constants.POSTER_BASE_URL + moviePreview.posterPath
        val posterView = itemView.findViewById<ImageView>(R.id.poster)
        Glide
            .with(itemView.context)
            .load(moviePosterUrl)
            .into(posterView)
        itemView.setOnClickListener {
            clickListener.invoke(moviePreview)
            Toast.makeText(context, moviePreview.title, Toast.LENGTH_SHORT).show()
        }
    }
}