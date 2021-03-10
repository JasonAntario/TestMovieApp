package com.example.testmovieapp.network

import com.example.testmovieapp.pojo.preview.MovieResponse
import com.example.testmovieapp.pojo.details.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBapi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNumber: Int, @Query ("api_key") apiKey: String) : Single<MovieResponse>

    @GET("movie/now_playing")
    fun getPlayingMovies(@Query("page") pageNumber: Int): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") pageNumber: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int, @Query ("api_key") apiKey: String): Single<MovieDetails>
}