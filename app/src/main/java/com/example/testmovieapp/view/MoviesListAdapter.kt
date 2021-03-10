package com.example.testmovieapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.R
import com.example.testmovieapp.pojo.preview.MoviePreview

class MoviesListAdapter(private val context: Context, private val clickListener: (MoviePreview) -> Unit) : PagedListAdapter<MoviePreview, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MoviesListViewHolder).onBind(getItem(position)!!, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_movie_item, parent, false)
        return MoviesListViewHolder(view, clickListener)
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MoviePreview>() {
        override fun areItemsTheSame(oldItem: MoviePreview, newItem: MoviePreview): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviePreview, newItem: MoviePreview): Boolean {
            return oldItem.id == newItem.id
        }
    }
}