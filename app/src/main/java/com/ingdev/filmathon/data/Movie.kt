package com.ingdev.filmathon.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,  // Puede ser null

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("release_date")
    val releaseDate: String,
)