package com.example.testmovieapp.data.network

import com.example.testmovieapp.data.pojo.MovieDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNumber: Int): Single<MovieDetails>

    @GET("movie/now_playing")
    fun getPlayingMovies(@Query("page") pageNumber: Int): Single<MovieDetails>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") pageNumber: Int): Single<MovieDetails>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieDetails>
}