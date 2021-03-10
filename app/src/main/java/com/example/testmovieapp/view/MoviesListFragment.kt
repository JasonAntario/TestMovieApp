package com.example.testmovieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.*
import com.example.testmovieapp.network.Client
import com.example.testmovieapp.network.TMDBapi
import com.example.testmovieapp.pojo.preview.MoviePreview
import com.example.testmovieapp.repository.MovieListRepository
import com.example.testmovieapp.viewmodel.MovieListViewModel

class MoviesListFragment : Fragment() {

    private lateinit var loadingView: LoadingView
    private val onMovieClick: (MoviePreview) -> Unit = {
        parentFragmentManager.beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragmentContainer, MovieDetailsFragment(it.id))
            .commit()
    }
    private lateinit var viewModel: MovieListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.pageTitle).text = getString(R.string.popular)
        loadingView = view.findViewById(R.id.loadingView)

        val recyclerView = view.findViewById<RecyclerView>(R.id.moviesGrid)
        val adapter = MoviesListAdapter(context!!, onMovieClick)

        viewModel.moviePreviewPagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            loadingView.setLoadingViewState(it)
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(MoviesListItemDecorator(15))
    }

    private fun getViewModel(): MovieListViewModel {
        val apiService: TMDBapi = Client.getClient()

        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieListViewModel(MovieListRepository(apiService)) as T
            }
        })[MovieListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getViewModel()
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        return view
    }
}