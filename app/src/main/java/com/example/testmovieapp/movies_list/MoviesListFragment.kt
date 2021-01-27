package com.example.testmovieapp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.MainActivity
import com.example.testmovieapp.MovieViewModel
import com.example.testmovieapp.R

class MoviesListFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(activity as MainActivity)[MovieViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.pageTitle).text = "Popular"
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.moviesGrid)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = MoviesListAdapter(listOf())
        recyclerView.addItemDecoration(MoviesListItemDecorator(24, 2, false))
        return view
    }
}