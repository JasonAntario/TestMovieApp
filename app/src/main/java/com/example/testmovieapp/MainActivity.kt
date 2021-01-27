package com.example.testmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.testmovieapp.movies.MoviesListFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, MoviesListFragment()).commit()
    }
}