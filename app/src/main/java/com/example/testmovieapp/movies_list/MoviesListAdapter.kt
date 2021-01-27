package com.example.testmovieapp.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.R
import com.example.testmovieapp.data.pojo.MovieInfo

class MoviesListAdapter(private var popularMovieInfoList: List<MovieInfo>) : RecyclerView.Adapter<MoviesListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_preview, parent, false)

        return MoviesListViewHolder(view)
    }

    override fun onBindViewHolder(holderList: MoviesListViewHolder, position: Int) {
        holderList.onBind(popularMovieInfoList[position])
    }

    override fun getItemCount(): Int {
        return popularMovieInfoList.size
    }
}