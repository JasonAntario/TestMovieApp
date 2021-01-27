package com.example.testmovieapp.movies

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.R
import com.example.testmovieapp.data.pojo.MovieInfo

class MoviesListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    fun onBind(movieInfo: MovieInfo) {
        itemView.findViewById<TextView>(R.id.movieTitle).text = movieInfo.title
    }
}