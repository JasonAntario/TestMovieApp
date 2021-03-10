package com.example.testmovieapp

enum class State {
    SUCCESS,
    RUNNING,
    FAILED
}

class NetworkState(val state: State, val message: String) {

    companion object{
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState(State.SUCCESS, "Success")
            LOADING = NetworkState(State.RUNNING, "Running")
            ERROR = NetworkState(State.FAILED, "Error loading!")
            ENDOFLIST = NetworkState(State.FAILED, "End of list")
        }
    }
}