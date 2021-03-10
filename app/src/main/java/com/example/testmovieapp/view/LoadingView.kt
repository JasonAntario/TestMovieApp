package com.example.testmovieapp.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.testmovieapp.NetworkState
import com.example.testmovieapp.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var loadingMessage: TextView
    private var loadingIndicator: CircularProgressIndicator

    init {
        inflate(context, R.layout.view_loading, this)
        loadingMessage = findViewById(R.id.loadingMessage)
        loadingIndicator = findViewById(R.id.loadingIndicator)
    }

    fun setLoadingViewState(state: NetworkState) {
        when (state) {
            NetworkState.LOADING -> {
                this.loadingIndicator.isIndeterminate = true
                this.loadingMessage.text = NetworkState.LOADING.message
                this.isVisible = true
            }
            NetworkState.LOADED -> {
                this.isVisible = false
            }
            NetworkState.ERROR -> {
                this.isVisible = true
                this.loadingMessage.text = NetworkState.ERROR.message
                this.loadingIndicator.isIndeterminate = false
            }
        }
    }
}