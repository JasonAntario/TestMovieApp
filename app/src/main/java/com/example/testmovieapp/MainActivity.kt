package com.example.testmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.testmovieapp.view.MoviesListFragment

class MainActivity : AppCompatActivity() {

    private var isBackClicked = false
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, MoviesListFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!isBackClicked) {
            isBackClicked = true
            Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show()
            handler.postDelayed({
                isBackClicked = false
            }, 1000)
        } else {
            super.onBackPressed()
        }

    }
}