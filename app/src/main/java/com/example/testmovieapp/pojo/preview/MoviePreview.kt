package com.example.testmovieapp.pojo.preview


import com.google.gson.annotations.SerializedName

data class MoviePreview(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val votes: Int
)